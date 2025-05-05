/**
 * 
 */
package API;

/**
 * 
 */
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class GeminiAssistant {
    private static JWindow frame;
    private static float opacity = 0.5f;
    private static Point initialClick;
    private static final int MINIMAL_SIZE = 50;
    private static boolean isMinimized = false;
    private static final Dimension NORMAL_SIZE = new Dimension(500, 600);
    private static final Dimension MINIMIZED_SIZE = new Dimension(20, 20);
    private static final String API_KEY = "AIzaSyAxivjuqDUYv5UYM1v9GQhsC0S2_dPUCcU";
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private static JTextArea markdownArea;
    private static JEditorPane htmlPreview;
    private static JPanel codeBlocksPanel;
    private static String currentResponse = "";
    private static JLabel statusBar;
    private static JTextField promptField;

    public static void main(String[] args) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            System.err.println("Error: GEMINI_API_KEY environment variable is not set.");
            System.exit(1);
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Silently continue with default look and feel
        }
        
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JWindow();
        frame.setSize(NORMAL_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setFocusable(true);
        frame.setFocusableWindowState(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        mainPanel.setBackground(new Color(240, 240, 240, 240));

        makeTranslucent(opacity);
        addDragCapability(mainPanel);

        // Title bar with controls
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(50, 50, 50));
        titleBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        
        JLabel titleLabel = new JLabel("Screenshot Assistant");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleBar.add(titleLabel, BorderLayout.WEST);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        controlPanel.setOpaque(false);

        JButton screenshotButton = createButton("📸", "Take Screenshot");
        screenshotButton.addActionListener(e -> takeAndAnalyzeScreenshot());
        screenshotButton.setForeground(Color.WHITE);

        JButton copyButton = createButton("📋", "Copy Response");
        copyButton.addActionListener(e -> copyToClipboard());
        copyButton.setForeground(Color.WHITE);

        JButton saveButton = createButton("💾", "Save as HTML");
        saveButton.addActionListener(e -> saveAsHtml());
        saveButton.setForeground(Color.WHITE);

        JButton opacityButton = createButton("👁", "Toggle opacity");
        opacityButton.addActionListener(e -> toggleOpacity());
        opacityButton.setForeground(Color.WHITE);

        JButton minimizeButton = createButton("_", "Minimize");
        minimizeButton.addActionListener(e -> toggleMinimize());
        minimizeButton.setForeground(Color.WHITE);

        JButton closeButton = createButton("×", "Close");
        closeButton.addActionListener(e -> System.exit(0));
        closeButton.setForeground(Color.WHITE);

        controlPanel.add(screenshotButton);
        controlPanel.add(copyButton);
        controlPanel.add(saveButton);
        controlPanel.add(opacityButton);
        controlPanel.add(minimizeButton);
        controlPanel.add(closeButton);

        titleBar.add(controlPanel, BorderLayout.EAST);
        mainPanel.add(titleBar, BorderLayout.NORTH);

        // Prompt input field
        JPanel promptPanel = new JPanel(new BorderLayout());
        promptPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel promptLabel = new JLabel("Prompt: ");
        promptField = new JTextField("Analyze the image to detect any questions (e.g., 'What is...?', 'How does...?', or code-related queries). If a question is found, provide only the answer in Markdown format, using headers, lists, and code blocks as appropriate. Do not include the question or additional context. If no question is found, respond with: 'No question detected in the image. If code is detected, identify any issues or bugs in the code, explain how to fix the problems, show the expected output of the code when applicable, and format code solutions in appropriate code blocks.'");
        promptField.setFont(new Font("Arial", Font.PLAIN, 12));
        promptPanel.add(promptLabel, BorderLayout.WEST);
        promptPanel.add(promptField, BorderLayout.CENTER);
        mainPanel.add(promptPanel, BorderLayout.SOUTH);

        // Create tabbed pane for markdown, preview, and code blocks
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);

        // Markdown source tab
        markdownArea = new JTextArea("Click the 📸 button to capture a screenshot.\nThe response will be shown here in Markdown format.\nUse the 📋 button to copy the content.");
        markdownArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        markdownArea.setForeground(Color.BLACK);
        markdownArea.setLineWrap(true);
        markdownArea.setWrapStyleWord(true);
        markdownArea.setEditable(true);
        markdownArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        markdownArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String currentText = markdownArea.getText();
                updateHtmlPreview(currentText);
                updateCodeBlocks(currentText);
            }
        });
        
        JScrollPane markdownScrollPane = new JScrollPane(markdownArea);
        tabbedPane.addTab("Markdown", markdownScrollPane);

        // HTML Preview tab
        htmlPreview = new JEditorPane();
        htmlPreview.setEditable(false);
        htmlPreview.setContentType("text/html");
        htmlPreview.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        HTMLEditorKit kit = new HTMLEditorKit();
        htmlPreview.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("body { font-family: Arial, sans-serif; margin: 8px; }");
        styleSheet.addRule("pre, code { background-color: #f0f0f0; padding: 2px; font-family: Consolas, monospace; }");
        styleSheet.addRule("h1, h2, h3 { color: #333; }");
        styleSheet.addRule("ul, ol { margin-left: 20px; }");
        
        JScrollPane htmlScrollPane = new JScrollPane(htmlPreview);
        tabbedPane.addTab("Preview", htmlScrollPane);

        // Code Blocks tab
        codeBlocksPanel = new JPanel();
        codeBlocksPanel.setLayout(new BoxLayout(codeBlocksPanel, BoxLayout.Y_AXIS));
        JScrollPane codeBlocksScrollPane = new JScrollPane(codeBlocksPanel);
        tabbedPane.addTab("Code Blocks", codeBlocksScrollPane);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Resize handle
        addResizeCapability(mainPanel);
        
        // Add status bar
        statusBar = new JLabel(" Ready");
        statusBar.setFont(new Font("Arial", Font.PLAIN, 10));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        statusBar.setOpaque(true);
        statusBar.setBackground(new Color(225, 225, 225));
        mainPanel.add(statusBar, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
        frame.requestFocus();

        // Global hotkeys
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isAltDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_T) {
                    frame.setAlwaysOnTop(!frame.isAlwaysOnTop());
                    statusBar.setText(" Always on top: " + (frame.isAlwaysOnTop() ? "Enabled" : "Disabled"));
                }
                if (e.isAltDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_H) {
                    toggleMinimize();
                }
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C && !markdownArea.isFocusOwner()) {
                    copyToClipboard();
                }
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
                    saveAsHtml();
                }
            }
        });

        // Update initial preview and code blocks
        updateHtmlPreview(markdownArea.getText());
        updateCodeBlocks(markdownArea.getText());
    }

    private static void takeAndAnalyzeScreenshot() {
        try {
            frame.setVisible(false);
            Thread.sleep(200);
            BufferedImage screenshot = takeScreenshot();
            frame.setVisible(true);
            
            String base64Image = convertToBase64(screenshot);
            statusBar.setText(" Analyzing screenshot...");
            String response = sendImageToGemini(base64Image);
            currentResponse = extractResponseText(response);
            markdownArea.setText(currentResponse);
            updateHtmlPreview(currentResponse);
            updateCodeBlocks(currentResponse);
            statusBar.setText(" Analysis complete.");
        } catch (Exception e) {
            frame.setVisible(true);
            markdownArea.setText("Error: " + e.getMessage());
            updateHtmlPreview("<p style='color:red'>Error: " + e.getMessage() + "</p>");
            updateCodeBlocks("");
            statusBar.setText(" Error occurred.");
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void updateHtmlPreview(String markdown) {
        SwingUtilities.invokeLater(() -> {
            try {
                String html = convertMarkdownToHtml(markdown);
                htmlPreview.setText(html);
                htmlPreview.setCaretPosition(0);
            } catch (Exception e) {
                System.err.println("Error updating HTML preview: " + e.getMessage());
                htmlPreview.setText("<p style='color:red'>Error rendering preview: " + e.getMessage() + "</p>");
            }
        });
    }

    private static void updateCodeBlocks(String markdown) {
        codeBlocksPanel.removeAll();
        Pattern pattern = Pattern.compile("```([a-zA-Z0-9]+)?\\n([\\s\\S]*?)\\n```", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(markdown);
        java.util.List<JPanel> codePanels = new java.util.ArrayList<>();
        while (matcher.find()) {
            String language = matcher.group(1);
            String code = matcher.group(2).trim();
            String labelText = language != null ? language + " Code" : "Code";
            JLabel label = new JLabel(labelText);
            JTextArea codeArea = new JTextArea(code);
            codeArea.setFont(new Font("Consolas", Font.PLAIN, 14));
            codeArea.setEditable(false);
            codeArea.setLineWrap(true);
            codeArea.setWrapStyleWord(true);
            JScrollPane codeScrollPane = new JScrollPane(codeArea);
            codeScrollPane.setPreferredSize(new Dimension(400, 100));
            JButton copyButton = new JButton("Copy");
            copyButton.addActionListener(e -> {
                StringSelection selection = new StringSelection(codeArea.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
            });
            JPanel codePanel = new JPanel(new BorderLayout());
            codePanel.add(label, BorderLayout.NORTH);
            codePanel.add(codeScrollPane, BorderLayout.CENTER);
            codePanel.add(copyButton, BorderLayout.EAST);
            codePanels.add(codePanel);
        }
        if (codePanels.isEmpty()) {
            codeBlocksPanel.add(new JLabel("No code blocks found."));
        } else {
            for (int i = 0; i < codePanels.size(); i++) {
                if (i > 0) {
                    codeBlocksPanel.add(Box.createVerticalStrut(10));
                }
                codeBlocksPanel.add(codePanels.get(i));
            }
        }
        codeBlocksPanel.revalidate();
        codeBlocksPanel.repaint();
    }

    private static String convertMarkdownToHtml(String markdown) {
        if (markdown == null || markdown.isEmpty()) {
            return "<html><body><p>No content</p></body></html>";
        }

        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        
        Pattern codePattern = Pattern.compile("```(?:([a-zA-Z0-9]+)?\\n)?([\\s\\S]*?)```");
        Matcher codeMatcher = codePattern.matcher(markdown);
        StringBuffer codeBuffer = new StringBuffer();
        
        while (codeMatcher.find()) {
            String language = codeMatcher.group(1) != null ? codeMatcher.group(1) : "";
            String code = codeMatcher.group(2);
            code = code.replace("&", "&").replace("<", "<").replace(">", ">");
            codeMatcher.appendReplacement(codeBuffer, "<pre><code class=\"language-" + language + "\">" + code + "</code></pre>");
        }
        codeMatcher.appendTail(codeBuffer);
        markdown = codeBuffer.toString();
        
        markdown = markdown.replaceAll("`([^`]+)`", "<code>$1</code>");
        markdown = markdown.replaceAll("(?m)^# (.+)$", "<h1>$1</h1>");
        markdown = markdown.replaceAll("(?m)^## (.+)$", "<h2>$1</h2>");
        markdown = markdown.replaceAll("(?m)^### (.+)$", "<h3>$1</h3>");
        markdown = markdown.replaceAll("(?m)^#### (.+)$", "<h4>$1</h4>");
        markdown = markdown.replaceAll("\\*\\*([^*]+)\\*\\*", "<strong>$1</strong>");
        markdown = markdown.replaceAll("\\*([^*]+)\\*", "<em>$1</em>");
        markdown = markdown.replaceAll("__([^_]+)__", "<strong>$1</strong>");
        markdown = markdown.replaceAll("_([^_]+)_", "<em>$1</em>");
        markdown = markdown.replaceAll("\\[([^\\]]+)\\]\\(([^)]+)\\)", "<a href=\"$2\">$1</a>");
        
        Pattern ulPattern = Pattern.compile("(?m)^[*+-] (.+)$");
        Matcher ulMatcher = ulPattern.matcher(markdown);
        StringBuffer ulBuffer = new StringBuffer();
        boolean inUl = false;
        
        while (ulMatcher.find()) {
            if (!inUl) {
                ulMatcher.appendReplacement(ulBuffer, "<ul>\n<li>$1</li>");
                inUl = true;
            } else {
                ulMatcher.appendReplacement(ulBuffer, "<li>$1</li>");
            }
        }
        if (inUl) {
            ulBuffer.append("</ul>");
        }
        ulMatcher.appendTail(ulBuffer);
        markdown = ulBuffer.toString();
        
        Pattern olPattern = Pattern.compile("(?m)^\\d+\\. (.+)$");
        Matcher olMatcher = olPattern.matcher(markdown);
        StringBuffer olBuffer = new StringBuffer();
        boolean inOl = false;
        
        while (olMatcher.find()) {
            if (!inOl) {
                olMatcher.appendReplacement(olBuffer, "<ol>\n<li>$1</li>");
                inOl = true;
            } else {
                olMatcher.appendReplacement(olBuffer, "<li>$1</li>");
            }
        }
        if (inOl) {
            olBuffer.append("</ol>");
        }
        olMatcher.appendTail(olBuffer);
        markdown = olBuffer.toString();
        
        StringBuilder paragraphed = new StringBuilder();
        boolean inParagraph = false;
        String[] lines = markdown.split("\n");
        
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                if (inParagraph) {
                    paragraphed.append("</p>\n");
                    inParagraph = false;
                }
                paragraphed.append("\n");
            } else if (trimmed.startsWith("<h") || trimmed.startsWith("<pre") || 
                       trimmed.startsWith("<ul") || trimmed.startsWith("<ol") ||
                       trimmed.startsWith("<li")) {
                if (inParagraph) {
                    paragraphed.append("</p>\n");
                    inParagraph = false;
                }
                paragraphed.append(line).append("\n");
            } else if (!inParagraph && !trimmed.startsWith("<")) {
                paragraphed.append("<p>").append(line);
                inParagraph = true;
            } else {
                paragraphed.append(line);
                if (inParagraph && line.endsWith("</p>")) {
                    inParagraph = false;
                }
            }
        }
        if (inParagraph) {
            paragraphed.append("</p>");
        }
        
        html.append(paragraphed);
        html.append("</body></html>");
        return html.toString();
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
        String customPrompt = promptField.getText().isEmpty() ? 
        "Analyze the image to detect any questions (e.g., 'What is...?', 'How does...?','why...?','who...?','when...?', or code-related queries). If a question is found, provide only the answer in Markdown format, using headers, lists, and code blocks as appropriate. Do not include the question or additional context. If no question is found, respond with: 'No question detected in the image. If code is detected, identify any issues or bugs in the code, explain how to fix the problems, show the expected output of the code when applicable, and format code solutions in appropriate code blocks.'" 
        : promptField.getText();
        String requestBody = String.format(
            "{\"contents\":[{\"parts\":[{\"text\":\"%s\"},{\"inlineData\":{\"mimeType\":\"image/png\",\"data\":\"%s\"}}]}]}",
            customPrompt.replace("\"", "\\\""),
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
            Pattern pattern = Pattern.compile("\"text\"\\s*:\\s*\"((?:\\\\\"|[^\"])*?)\"");
            Matcher matcher = pattern.matcher(jsonResponse);
            if (matcher.find()) {
                String text = matcher.group(1);
                return text.replace("\\n", "\n")
                           .replace("\\\"", "\"")
                           .replace("\\\\", "\\")
                           .replace("\\t", "\t")
                           .replace("\\r", "\r")
                           .replace("\\b", "\b")
                           .replace("\\f", "\f");
            }
        } catch (Exception e) {
        }
        return "Unable to parse response: " + jsonResponse;
    }

    private static void copyToClipboard() {
        try {
            String textToCopy = markdownArea.getText();
            StringSelection selection = new StringSelection(textToCopy);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            markdownArea.requestFocus();
            markdownArea.selectAll();
            statusBar.setText(" Response copied to clipboard.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error copying to clipboard: " + e.getMessage(), "Copy Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void saveAsHtml() {
        try {
            File tempFile = File.createTempFile("screenshotAssistant_", ".html");
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("<!DOCTYPE html>\n");
                writer.write("<html>\n<head>\n");
                writer.write("<meta charset=\"UTF-8\">\n");
                writer.write("<title>Screenshot Assistant Output</title>\n");
                writer.write("<style>\n");
                writer.write("body { font-family: Arial, sans-serif; margin: 20px; line-height: 1.5; }\n");
                writer.write("pre, code { background-color: #f0f0f0; padding: 2px; font-family: Consolas, monospace; }\n");
                writer.write("h1, h2, h3 { color: #333; }\n");
                writer.write("ul, ol { margin-left: 20px; }\n");
                writer.write("</style>\n");
                writer.write("</head>\n<body>\n");
                writer.write(convertMarkdownToHtml(markdownArea.getText()).replace("<html><body>", "").replace("</body></html>", ""));
                writer.write("\n</body>\n</html>");
            }
            Desktop.getDesktop().browse(tempFile.toURI());
            statusBar.setText(" HTML saved and opened.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error saving as HTML: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void addResizeCapability(JPanel panel) {
        JLabel resizeCorner = new JLabel("⌟");
        resizeCorner.setForeground(Color.GRAY);
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