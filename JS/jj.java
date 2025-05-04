import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class jj {
    private static JWindow frame;
    private static float opacity = 0.4f;
    private static Point initialClick;
    private static final int MINIMAL_SIZE = 50;
    private static boolean isMinimized = false;
    private static final Dimension NORMAL_SIZE = new Dimension(400, 500);
    private static final Dimension MINIMIZED_SIZE = new Dimension(20, 20);
    private static final String API_KEY = System.getenv("GEMINI_API_KEY") != null ? System.getenv("GEMINI_API_KEY") : "AIzaSyA5jMq0-7oGxEA6vWLJurDoiT4DcELuTao";
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private static JTextPane responseArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JWindow();
        frame.setSize(NORMAL_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        panel.setBackground(new Color(240, 240, 240, 200));

        makeTranslucent(opacity);
        addDragCapability(panel);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        controlPanel.setOpaque(false);

        JButton screenshotButton = createButton("📸", "Take Screenshot");
        screenshotButton.addActionListener(e -> takeAndAnalyzeScreenshot());

        JButton copyButton = createButton("📋", "Copy response to clipboard");
        copyButton.addActionListener(e -> {
            String text = responseArea.getText();
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new java.awt.datatransfer.StringSelection(text), null);
        });

        JButton closeButton = createButton("×", "Close application");
        closeButton.addActionListener(e -> System.exit(0));

        JButton hideButton = createButton("_", "Minimize/Restore");
        hideButton.addActionListener(e -> toggleMinimize());

        JButton opacityButton = createButton("○", "Toggle opacity");
        opacityButton.addActionListener(e -> toggleOpacity());

        controlPanel.add(screenshotButton);
        controlPanel.add(copyButton);
        controlPanel.add(opacityButton);
        controlPanel.add(hideButton);
        controlPanel.add(closeButton);

        panel.add(controlPanel, BorderLayout.NORTH);

        responseArea = new JTextPane();
        responseArea.setFont(new Font("Arial", Font.PLAIN, 14));
        responseArea.setForeground(Color.BLACK);
        responseArea.setEditable(false); // Prevent editing, allow selection
        responseArea.setOpaque(false);
        parseAndSetStyledText("Click the 📸 button to capture a screenshot. Only the answer to any detected question will be shown in Markdown.\nSelect and copy (Ctrl+C) the response as needed, or use the 📋 button to copy all.");
        JScrollPane scrollPane = new JScrollPane(responseArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);

        addResizeCapability(panel);
        frame.add(panel);

        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isMinimized) {
                    toggleMinimize();
                }
            }
        });

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isAltDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_T) {
                    frame.setAlwaysOnTop(!frame.isAlwaysOnTop());
                }
                if (e.isAltDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_H) {
                    toggleMinimize();
                }
            }
        });

        frame.setVisible(true);
    }

    private static void takeAndAnalyzeScreenshot() {
        try {
            BufferedImage screenshot = takeScreenshot();
            String base64Image = convertToBase64(screenshot);
            String response = sendImageToGemini(base64Image);
            String responseText = extractResponseText(response);
            parseAndSetStyledText(responseText);
            responseArea.setCaretPosition(0); // Scroll to top
        } catch (Exception e) {
            parseAndSetStyledText("Error: " + e.getMessage());
        }
    }

    private static void parseAndSetStyledText(String text) {
        responseArea.setText(""); // Clear existing text
        StyledDocument doc = responseArea.getStyledDocument();

        // Define styles
        Style defaultStyle = doc.addStyle("default", null);
        StyleConstants.setFontFamily(defaultStyle, "Arial");
        StyleConstants.setFontSize(defaultStyle, 14);

        // Header styles for different levels
        for (int i = 1; i <= 6; i++) {
            Style headerStyle = doc.addStyle("header" + i, defaultStyle);
            StyleConstants.setBold(headerStyle, true);
            int size = 24 - (i - 1) * 2; // e.g., 24, 22, 20, 18, 16, 14
            StyleConstants.setFontSize(headerStyle, size);
        }

        Style codeStyle = doc.addStyle("code", defaultStyle);
        StyleConstants.setFontFamily(codeStyle, "Monospaced");
        StyleConstants.setFontSize(codeStyle, 14);

        // Parse and insert text
        String[] lines = text.split("\n");
        boolean inCodeBlock = false;

        for (String line : lines) {
            if (line.trim().equals("```")) {
                inCodeBlock = !inCodeBlock;
                continue;
            }

            try {
                if (inCodeBlock) {
                    doc.insertString(doc.getLength(), line + "\n", codeStyle);
                } else if (line.startsWith("#")) {
                    int level = 0;
                    while (level < line.length() && line.charAt(level) == '#') {
                        level++;
                    }
                    if (level > 0 && level <= 6) {
                        String headerText = line.substring(level).trim();
                        Style headerStyle = doc.getStyle("header" + level);
                        doc.insertString(doc.getLength(), headerText + "\n", headerStyle);
                    } else {
                        doc.insertString(doc.getLength(), line + "\n", defaultStyle);
                    }
                } else {
                    doc.insertString(doc.getLength(), line + "\n", defaultStyle);
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    private static BufferedImage takeScreenshot() throws AWTException {
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(screenRect);
    }

    private static String convertToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private static String sendImageToGemini(String base64Image) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String requestBody = String.format(
            "{\"contents\":[{\"parts\":[{\"text\":\"Analyze the image to detect any questions (e.g., 'What is...?', 'How does...?', or code-related queries). If a question is found, provide only the answer in Markdown format, using headers, lists, and code blocks as appropriate. Do not include the question or additional context. If no question is found, respond with: 'No question detected in the image.'\"},{\"inlineData\":{\"mimeType\":\"image/png\",\"data\":\"%s\"}}]}]}",
            base64Image
        );

        String url = GEMINI_API_URL + "?key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            String errorMessage = "HTTP Error " + response.statusCode() + ": " + response.body();
            if (response.statusCode() == 400 && response.body().contains("API_KEY_INVALID")) {
                errorMessage += "\nInvalid API key. Verify in Google Cloud Console (https://console.cloud.google.com/apis/credentials) or Google AI Studio (https://aistudio.google.com/).";
            } else if (response.statusCode() == 403) {
                errorMessage += "\nPermission denied. Check API key access or project billing/quota.";
            } else if (response.statusCode() == 429) {
                errorMessage += "\nRate limit exceeded. Check quota in Google Cloud Console.";
            }
            throw new IOException(errorMessage);
        }
        return response.body();
    }

    private static String extractResponseText(String jsonResponse) {
        try {
            String marker = "\"text\": \"";
            int start = jsonResponse.indexOf(marker) + marker.length();
            int end = jsonResponse.indexOf("\"", start);
            if (start > marker.length() && end > start) {
                String text = jsonResponse.substring(start, end);
                return text.replace("\\n", "\n").replace("\\\"", "\"").replace("\\\\", "\\");
            }
        } catch (Exception e) {
            // Fallback to raw response
        }
        return "Unable to parse response: " + jsonResponse;
    }

    private static void addResizeCapability(JPanel panel) {
        JLabel resizeCorner = new JLabel("⌟");
        resizeCorner.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        resizeCorner.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(resizeCorner, BorderLayout.SOUTH);

        resizeCorner.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        resizeCorner.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (!isMinimized) {
                    int width = frame.getWidth();
                    int height = frame.getHeight();
                    int newWidth = width + e.getX() - initialClick.x;
                    int newHeight = height + e.getY() - initialClick.y;
                    newWidth = Math.max(MINIMAL_SIZE, newWidth);
                    newHeight = Math.max(MINIMAL_SIZE, newHeight);
                    frame.setSize(newWidth, newHeight);
                }
            }
        });
    }

    private static void addDragCapability(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                frame.setLocation(X, Y);
            }
        });
    }

    private static void makeTranslucent(float opacity) {
        frame.setOpacity(opacity);
    }

    private static void toggleOpacity() {
        opacity = (opacity == 1.0f) ? 0.7f : 1.0f;
        makeTranslucent(opacity);
    }

    private static void toggleMinimize() {
        isMinimized = !isMinimized;
        if (isMinimized) {
            frame.setSize(MINIMIZED_SIZE);
        } else {
            frame.setSize(NORMAL_SIZE);
        }
    }

    private static JButton createButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(20, 20));
        button.setToolTipText(tooltip);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
    }
}