package API;
import java.util.List;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.net.http.*;
import java.nio.file.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import java.util.regex.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.html.*;

// Constants class for centralized configuration
class Constants {
    static final String CONFIG_FILE = "stealth_config.dat";
    static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    static final Dimension NORMAL_SIZE = new Dimension(200, 150);
    static final Dimension MINIMIZED_SIZE = new Dimension(10, 10);
    static final int MAX_WIDTH = 1200;
    static final int MAX_HEIGHT = 800;
    static final int MIN_WIDTH = 200;
    static final int MIN_HEIGHT = 100;
    static final float DEFAULT_OPACITY = 0.1f;
    static final float STEALTH_OPACITY = 0.2f;
    static final float NORMAL_OPACITY = 0.3f;
    static final float GHOST_OPACITY = 0.05f;
    static final Color PRIMARY_BG = new Color(20, 20, 20, 200);
    static final Color TITLE_BAR_BG = new Color(15, 15, 15, 180);
    static final Color PROMPT_BG = new Color(25, 25, 25, 150);
    static final Color TEXT_FIELD_BG = new Color(40, 40, 40);
    static final Color STATUS_BAR_BG = new Color(20, 40, 20);
    static final Color ERROR_BORDER = new Color(200, 50, 50);
    static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 16);
    static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    static final Font TEXT_FONT = new Font("Consolas", Font.PLAIN, 11);
    static final String[] PROCESS_NAMES = {
        "svchost", "dwm", "winlogon", "csrss", "lsass", "explorer", "dllhost",
        "conhost", "taskhostw", "RuntimeBroker", "spoolsv", "wininit", "fontdrvhost",
        "smss", "services", "ctfmon", "sihost", "searchapp", "msedge", "msteams",
        "wmpnetwk", "audiodg", "msiexec", "dashost", "searchindexer"
    };
    static final Set<String> SECURITY_PROCESSES = new HashSet<>(Arrays.asList(
        "procmon", "procexp", "taskmgr", "wireshark", "fiddler", "cheatengine", "ollydbg",
        "x64dbg", "ida", "vmware", "virtualbox", "sandboxie", "avp", "mcshield", "windefend",
        "malwarebytes", "avgui", "avguard", "ekrn", "eset", "fsav", "sophosui", "trendmicro"
    ));
}

public class AdvancedStealthAssistant1 {
    private static final Logger LOGGER = Logger.getLogger(AdvancedStealthAssistant1.class.getName());
    private static JWindow frame;
    private static float opacity = Constants.DEFAULT_OPACITY;
    private static Point initialClick;
    private static boolean isMinimized = false;
    private static boolean isStealthMode = true;
    private static String API_KEY = null;
    private static JTextArea markdownArea;
    private static JEditorPane htmlPreview;
    private static JPanel codeBlocksPanel;
    private static String currentResponse = "";
    private static JLabel statusBar;
    private static JTextField promptField;
    private static ScheduledExecutorService scheduler;
    private static String currentProcessName;
    private static boolean isHidden = false;
    private static Robot robot;

    static {
        try {
            robot = new Robot();
            scheduler = Executors.newScheduledThreadPool(3);
            currentProcessName = getRandomProcessName();
            initializeStealthFeatures();
        } catch (Exception e) {
            LOGGER.severe("Initialization failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        API_KEY = getStoredApiKey();
        if (API_KEY == null || API_KEY.isEmpty()) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                LOGGER.warning("Failed to set look and feel: " + e.getMessage());
            }
            API_KEY = promptForApiKey();
            if (API_KEY == null || API_KEY.isEmpty()) {
                System.exit(1);
            }
            storeApiKey(API_KEY);
        }

        try {
            if (isDebuggingDetected() || isVirtualMachineDetected()) {
                System.exit(0);
            }
            startSecurityMonitoring();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(0);
        }

        SwingUtilities.invokeLater(AdvancedStealthAssistant1::createStealthGUI);
    }

    private static String getStoredApiKey() {
        try {
            Path configPath = Paths.get(System.getProperty("user.home"), ".stealth_assistant", Constants.CONFIG_FILE);
            if (Files.exists(configPath)) {
                List<String> lines = Files.readAllLines(configPath);
                if (!lines.isEmpty()) {
                    return new String(Base64.getDecoder().decode(lines.get(0)));
                }
            }
        } catch (Exception e) {
            LOGGER.warning("Failed to read API key: " + e.getMessage());
        }
        return null;
    }

    private static void storeApiKey(String apiKey) {
        try {
            Path configDir = Paths.get(System.getProperty("user.home"), ".stealth_assistant");
            Files.createDirectories(configDir);
            Path configPath = configDir.resolve(Constants.CONFIG_FILE);
            String encodedKey = Base64.getEncoder().encodeToString(apiKey.getBytes());
            Files.write(configPath, Arrays.asList(encodedKey));
            File file = configPath.toFile();
            file.setReadable(false, false);
            file.setReadable(true, true);
            file.setWritable(false, false);
            file.setWritable(true, true);
        } catch (Exception e) {
            LOGGER.severe("Failed to store API key: " + e.getMessage());
        }
    }

    private static String promptForApiKey() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Stealth Assistant - First Time Setup");
        dialog.setModal(true);
        dialog.setSize(450, 220);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Stealth Assistant - First Time Setup", SwingConstants.CENTER);
        titleLabel.setFont(Constants.TITLE_FONT);
        titleLabel.setForeground(new Color(200, 200, 200));

        JLabel instructionLabel = new JLabel(
            "<html><div style='text-align: center; color: #c0c0c0; font-size: 12px;'>" +
            "Enter your Google Gemini API Key<br>" +
            "Get one at: https://makersuite.google.com/app/apikey<br><br>" +
            "Your key will be stored securely on this device.</div></html>",
            SwingConstants.CENTER
        );

        JTextField apiKeyField = new JTextField(20);
        apiKeyField.setFont(Constants.TEXT_FONT);
        apiKeyField.setBackground(new Color(60, 60, 60));
        apiKeyField.setForeground(new Color(220, 220, 220));
        apiKeyField.setCaretColor(new Color(100, 150, 255));
        apiKeyField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setOpaque(false);
        JLabel keyLabel = new JLabel("API Key:");
        keyLabel.setForeground(new Color(180, 180, 180));
        keyLabel.setFont(Constants.LABEL_FONT);
        inputPanel.add(keyLabel, BorderLayout.WEST);
        inputPanel.add(apiKeyField, BorderLayout.CENTER);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(instructionLabel, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2));
        dialogPanel.setBackground(new Color(40, 40, 40));
        dialogPanel.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(new Color(40, 40, 40));

        JButton cancelButton = new JButton("Exit");
        cancelButton.setBackground(new Color(80, 50, 50));
        cancelButton.setForeground(new Color(220, 220, 220));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        JButton okButton = new JButton("Save & Continue");
        okButton.setBackground(new Color(50, 80, 50));
        okButton.setForeground(new Color(220, 220, 220));
        okButton.setFocusPainted(false);
        okButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        final String[] result = {null};

        cancelButton.addActionListener(e -> {
            dialog.dispose();
            System.exit(0);
        });

        okButton.addActionListener(e -> {
            String key = apiKeyField.getText().trim();
            if (key.isEmpty()) {
                apiKeyField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Constants.ERROR_BORDER, 2),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
                ));
                return;
            }
            result[0] = key;
            dialog.dispose();
        });

        apiKeyField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    okButton.doClick();
                }
            }
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(okButton);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(dialogPanel);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                apiKeyField.requestFocusInWindow();
            }
        });

        dialog.setVisible(true);
        return result[0];
    }

    private static void createStealthGUI() {
        frame = new JWindow();
        frame.setSize(Constants.NORMAL_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setFocusable(true);
        frame.setFocusableWindowState(true);
        frame.setType(Window.Type.UTILITY);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60, 100), 1));
        mainPanel.setBackground(Constants.PRIMARY_BG);

        makeTranslucent(opacity);
        addAdvancedDrag(mainPanel);

        mainPanel.add(createStealthTitleBar(), BorderLayout.NORTH);
        mainPanel.add(createPromptPanel(), BorderLayout.SOUTH);
        mainPanel.add(createAdvancedTabbedPane(), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        statusBar = createSecurityStatusBar();
        southPanel.add(statusBar, BorderLayout.CENTER);

        JLabel resizeCorner = new JLabel("◢");
        resizeCorner.setForeground(new Color(100, 100, 100));
        resizeCorner.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        resizeCorner.setHorizontalAlignment(SwingConstants.RIGHT);
        resizeCorner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        resizeCorner.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
        southPanel.add(resizeCorner, BorderLayout.EAST);

        resizeCorner.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });
        resizeCorner.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!isMinimized) {
                    int width = frame.getWidth();
                    int height = frame.getHeight();
                    int newWidth = width + e.getX() - initialClick.x;
                    int newHeight = height + e.getY() - initialClick.y;
                    newWidth = Math.max(Constants.MIN_WIDTH, Math.min(newWidth, Constants.MAX_WIDTH));
                    newHeight = Math.max(Constants.MIN_HEIGHT, Math.min(newHeight, Constants.MAX_HEIGHT));
                    frame.setSize(newWidth, newHeight);
                }
            }
        });

        mainPanel.add(southPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setVisible(true);

        initializeStealthBehaviors();
        startBackgroundMonitoring();
    }

    private static JPanel createStealthTitleBar() {
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(Constants.TITLE_BAR_BG);
        titleBar.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));

        JLabel titleLabel = new JLabel("System Monitor");
        titleLabel.setForeground(new Color(120, 120, 120));
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        titleBar.add(titleLabel, BorderLayout.WEST);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 1, 0));
        controlPanel.setOpaque(false);

        JButton screenshotButton = createStealthButton("📷", "Capture");
        screenshotButton.addActionListener(e -> performStealthScreenshot());

        JButton copyButton = createStealthButton("📋", "Copy");
        copyButton.addActionListener(e -> secureClipboardCopy());

        JButton ghostButton = createStealthButton("👤", "Ghost");
        ghostButton.addActionListener(e -> toggleGhostMode());

        JButton closeButton = createStealthButton("×", "Exit");
        closeButton.addActionListener(e -> secureExit());

        controlPanel.add(screenshotButton);
        controlPanel.add(copyButton);
        controlPanel.add(ghostButton);
        controlPanel.add(closeButton);

        titleBar.add(controlPanel, BorderLayout.EAST);
        return titleBar;
    }

    private static JPanel createPromptPanel() {
        JPanel promptPanel = new JPanel(new BorderLayout());
        promptPanel.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        promptPanel.setBackground(Constants.PROMPT_BG);

        JLabel promptLabel = new JLabel("Query: ");
        promptLabel.setForeground(new Color(140, 140, 140));
        promptLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));

        promptField = new JTextField(
            "Analyze the image to detect any questions (e.g., 'What ...?', 'Who ...?', 'Where ...?', 'When ...?', 'Why ...?', 'How ...?', 'Which ...?', 'Whose ...?', 'Whom ...?', 'Can ...?', 'Could ...?', 'Would ...?', 'Should ...?', 'Is ...?', 'Are ...?', 'Will ...?', 'Do ...?', 'Does ...?', 'Did ...?'). If a question is found, provide only the answer in Markdown format without repeating the question or adding extra context, using headers, lists, and code blocks as needed. If no question is found, respond with: 'No question detected in the image. If code is present, identify any issues or bugs, explain how to fix them, show the expected output when applicable, and format code solutions in appropriate code blocks.'"
        );
        promptField.setFont(Constants.TEXT_FONT);
        promptField.setBackground(Constants.TEXT_FIELD_BG);
        promptField.setForeground(new Color(200, 200, 200));
        promptField.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));

        promptPanel.add(promptLabel, BorderLayout.WEST);
        promptPanel.add(promptField, BorderLayout.CENTER);
        return promptPanel;
    }

    private static JTabbedPane createAdvancedTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);
        tabbedPane.setBackground(new Color(30, 30, 30));
        tabbedPane.setForeground(new Color(180, 180, 180));

        markdownArea = new JTextArea("System ready. Use capture button for analysis.");
        markdownArea.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        markdownArea.setBackground(new Color(25, 25, 25));
        markdownArea.setForeground(new Color(220, 220, 220));
        markdownArea.setCaretColor(new Color(100, 150, 255));
        markdownArea.setLineWrap(true);
        markdownArea.setWrapStyleWord(true);
        markdownArea.setEditable(true);
        markdownArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        Timer debounceTimer = new Timer(300, e -> {
            String currentText = markdownArea.getText();
            updateSecureHtmlPreview(currentText);
            updateSecureCodeBlocks(currentText);
        });
        debounceTimer.setRepeats(false);

        markdownArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                debounceTimer.restart();
            }
        });

        JScrollPane markdownScrollPane = new JScrollPane(markdownArea);
        markdownScrollPane.getViewport().setBackground(new Color(25, 25, 25));
        tabbedPane.addTab("Source", markdownScrollPane);

        htmlPreview = new JEditorPane();
        htmlPreview.setEditable(false);
        htmlPreview.setContentType("text/html");
        htmlPreview.setBackground(new Color(30, 30, 30));
        htmlPreview.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        HTMLEditorKit kit = new HTMLEditorKit();
        htmlPreview.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("body { font-family: 'Segoe UI', Arial, sans-serif; margin: 8px; background-color: #1e1e1e; color: #e0e0e0; }");
        styleSheet.addRule("pre, code { background-color: #2d2d2d; padding: 6px; font-family: 'JetBrains Mono', Consolas, monospace; border-radius: 4px; border: 1px solid #404040; }");
        styleSheet.addRule("h1, h2, h3 { color: #4a9eff; border-bottom: 1px solid #404040; }");
        styleSheet.addRule("ul, ol { margin-left: 24px; }");

        JScrollPane htmlScrollPane = new JScrollPane(htmlPreview);
        htmlScrollPane.getViewport().setBackground(new Color(30, 30, 30));
        tabbedPane.addTab("Preview", htmlScrollPane);

        codeBlocksPanel = new JPanel();
        codeBlocksPanel.setLayout(new BoxLayout(codeBlocksPanel, BoxLayout.Y_AXIS));
        codeBlocksPanel.setBackground(new Color(28, 28, 28));
        JScrollPane codeBlocksScrollPane = new JScrollPane(codeBlocksPanel);
        codeBlocksScrollPane.getViewport().setBackground(new Color(28, 28, 28));
        tabbedPane.addTab("Code", codeBlocksScrollPane);

        return tabbedPane;
    }

    private static JLabel createSecurityStatusBar() {
        JLabel statusBar = new JLabel(" Mode Active");
        statusBar.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        statusBar.setOpaque(true);
        statusBar.setBackground(Constants.STATUS_BAR_BG);
        statusBar.setForeground(new Color(120, 200, 120));
        return statusBar;
    }

    private static void initializeStealthFeatures() {
        System.setProperty("java.awt.headless", "false");
        System.setProperty("java.security.manager", "");
        try {
            hideFromTaskManager();
        } catch (Exception e) {
            LOGGER.warning("Failed to hide from task manager: " + e.getMessage());
        }
    }

    private static void startSecurityMonitoring() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (detectSecurityTools()) {
                    initiateEmergency();
                }
                // updateProcessName is ineffective; consider removing or implementing properly
            } catch (Exception e) {
                LOGGER.warning("Security monitoring failed: " + e.getMessage());
            }
        }, 0, 15, TimeUnit.SECONDS);
    }

    private static boolean detectSecurityTools() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Process proc = Runtime.getRuntime().exec("tasklist /fo csv");
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String lowerLine = line.toLowerCase();
                        for (String secProcess : Constants.SECURITY_PROCESSES) {
                            if (lowerLine.contains(secProcess)) {
                                proc.destroy(); // Clean up process
                                return true;
                            }
                        }
                    }
                } finally {
                    proc.destroy(); // Ensure process is terminated
                }
            } else {
                LOGGER.fine("Security tool detection skipped on non-Windows OS: " + os);
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to detect security tools", e); // Include stack trace
        }
        return false; // Default return if no security tools found or on error
    }
    private static void initiateEmergency() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(false);
            isHidden = true;
            statusBar.setText(" Security Protocol Active");
            if (markdownArea != null) markdownArea.setText("");
            if (promptField != null) promptField.setText("");
        });

        Timer timer = new Timer(60000, e -> {
            if (!detectSecurityTools()) {
                SwingUtilities.invokeLater(() -> {
                    frame.setVisible(true);
                    isHidden = false;
                    statusBar.setText(" Mode Active");
                });
            }
        });
        timer.start();
    }

    private static void performStealthScreenshot() {
        CompletableFuture.runAsync(() -> {
            try {
                statusBar.setText(" Initiating capture...");

                SwingUtilities.invokeLater(() -> frame.setVisible(false);
                try {
                Thread.sleep(300);
            } catch (Exception e) {
                LOGGER.warning("Interrupted during screenshot delay: " + e.getMessage());
            }

            BufferedImage screenshot = captureWithStealth();

            SwingUtilities.invokeLater(() -> frame.setVisible(true);

            String base64Image = encodeImageToBase64(screenshot);

            statusBar.setText("Processing...");
            String response = sendSecureImageToGemini(base64Image);
            String responseText = responseText = extractResponseText(response);

            SwingUtilities.invokeLater(() -> {
                currentResponse = responseText;
                markdownArea.setText(currentResponseText);
                updateSecureHtmlPreview(currentResponseText);
                updateSecureCodeBlocks(currentResponseText);
                statusBar.setText(" Analysis complete");
            });

        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> statusBar.setText("Error: " + e.getMessage()));
            LOGGER.severe("Screenshot processing failed: " + e.getMessage());
        }
    }

    private static String encodeImageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try ( baos) {
            try {
                ImageIO.write(image, "png", baos);
            } catch ( IOException e) {
                throw new IOException("Failed to encode image to PNG", e);
            }
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }
    }

    private static BufferedImage captureWithStealth() throws Exception {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenshot = robot.createScreenCapture(screenRect);

        try (Graphics2D g2D = screenshot.createGraphics()) {
            try {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.Type.SRC_OVER, 0.001f));
            g2D.setColor(new Color(Random.nextInt(256)), Random.nextInt(256), Random.nextInt(256)));
            g2D.fillRect(0, 0, 1, 1));
        } catch (Exception e) {
            LOGGER.warning("Failed to process screenshot graphics: " + e.getMessage());
        }

        return screenshot;

    }

    private static String sendSecureImageToGemini(String base64Image) throws Exception {
        HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(30))
            .build();

        String customPrompt = promptField.getText().trim().isEmpty() ?
            "Analyze the image to detect any questions..." : // Default prompt (same as original)
            promptField.getText();

        String requestBody = String.format(
            "{\"contents\":[{\"parts\":[{\"text\":\"%s\"}},{\"inlineData\": {\"mimeType\": \"image/png\", \"data\": \"%s\"}}]}]}",
            customPrompt.replace("\"", "\\\"").replace("\n", "\\n"),
            base64Image
        );

        String url = Constants.GEMINI_API_URL + "?key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new IOException("API Error: " + response.statusCode());
            }
            return response.body();
        } catch (Exception e) {
            LOGGER.severe("Failed to send image to Gemini API: " + e.getMessage());
            throw e;
        }
    }

    private static void toggleStealthMode() {
        isStealthMode = !isStealthMode;
        opacity = isStealthMode ? Constants.STEALTH_MODE_OPACITY : Constants.NORMAL_MODE_OPACITY;
        makeTranslucent(opacity);
        statusBar.setText(isStealthMode ? " Deep stealth activated" : " Normal mode activated");
    }

    private static void toggleGhostMode() {
        if (frame.getOpacity() > Constants.GHOST_MODE_OPACITY) {
            frame.setOpacity(Constants.GHOST_MODE_OPACITY);
            frame.setFocusableWindowState(false);
            statusBar.setText(" Ghost mode activated");
        } else {
            frame.setOpacity(opacity);
            frame.setFocusableWindowState(true);
            statusBar.setText(" Ghost mode deactivated");
        }
    }

    private static void secureClipboardCopy() {
        try {
            String textToCopy = markdownArea.getText();
            StringSelection selection = new StringSelection(textToCopy);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            statusBar.setText(" Content copied to clipboard");

            // Clear clipboard after 10 seconds for security
            scheduler.schedule(() -> {
                try {
                    clipboard.setContents(StringSelection(""), null);
                } catch (Exception e) {
                    LOGGER.warning("Failed to clear clipboard: " + e.getMessage());
                }
            }, 10, TimeUnit.SECONDS);
        } catch (Exception e) {
            statusBar.setText("Error copying to clipboard: " + e.getMessage());
            LOGGER.warning("Clipboard copy failed: " + e.getMessage());
        }
    }

    private static void secureExit() {
        try {
            if (markdownArea != null) markdownArea.setText("");
            if (promptField != null) promptField.setText("");
            if (scheduler != null && !scheduler.isShutdown()) {
                scheduler.shutdownNow();
            }
            currentResponse = "";
        } catch (Exception e) {
            LOGGER.warning("Error during cleanup: " + e.getMessage());
        } finally {
            System.exit(0);
        }
    }

    private static String getRandomProcessName() {
        return Constants.PROCESS_NAMES[new Random().nextInt(Constants.PROCESS_NAMES.length)];
    }

    private static void hideFromTaskManager() {
        System.setProperty("java.awt.Window.locationByPlatform", "true");
    }

    private static boolean isDebuggingDetected() {
        List<String> jvmArgs = ManagementFactory.getRuntimeMXBean().getInputArguments();
        return jvmArgs.stream().anyMatch(arg -> arg.contains("-agentlib:jdwp") || arg.contains("-Xdebug"));
    }

    private static boolean isVirtualMachineDetected() {
        String[] vmIndicators = {"VMware", "VirtualBox", "QEMU", "Xen", "Microsoft Corporation"};
        String vendor = System.getProperty("java.vm.vendor", "");
        String name = System.getProperty("java.vm.name", "");
        return Arrays.stream(vmIndicators).anyMatch(indicator -> vendor.contains(indicator) || name.contains(indicator));
    }

    private static void startBackgroundMonitoring() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (!frame.isVisible() && !isHidden) {
                    SwingUtilities.invokeLater(() -> {
                        if (frame.getOpacity() > 0.15f) {
                            frame.setOpacity(0.15f);
                        }
                    });
                }
            } catch (Exception e) {
                LOGGER.warning("Background monitoring failed: " + e.getMessage());
            }
        }, 10, 5, TimeUnit.SECONDS);
    }

    private static void initializeStealthBehaviors() {
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                if (isStealthMode) {
                    SwingUtilities.invokeLater(() -> frame.setOpacity(0.1f));
                }
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                SwingUtilities.invokeLater(() -> frame.setOpacity(opacity));
            }
        });
    }

    private static void updateSecureHtmlPreview(String markdown) {
        SwingUtilities.invokeLater(() -> {
            try {
                String html = convertMarkdownToSecureHtml(markdown);
                htmlPreview.setText(html);
                htmlPreview.setCaretPosition(0);
            } catch (Exception e) {
                htmlPreview.setText("<p style='color:#ff6b6b'>Preview error: " + e.getMessage());
                LOGGER.warning("HTML preview failed: " + e.getMessage());
            }
        });
    }

    private static void updateSecureCodeBlocks(String markdown) {
        SwingUtilities.invokeLater(() -> {
            codeBlocksPanel.removeAll();
            Pattern pattern = Pattern.compile("```([a-zA-Z0-9+#-]*)?\\n([\\s\\S]*?)\\n```", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(markdown);
            List<JPanel> codePanels = new ArrayList<>();

            while (matcher.find()) {
                String language = matcher.group(1);
                String code = matcher.group(2).trim();
                codePanels.add(createCodePanel(language, code));
            }

            if (codePanels.isEmpty()) {
                JLabel noCodeLabel = new JLabel("No code blocks detected.");
                noCodeLabel.setForeground(new Color(120, 120, 120));
                noCodeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                codeBlocksPanel.add(noCodeLabel);
            } else {
                for (int i = 0; i < codePanels.size(); i++) {
                    if (i > 0) codeBlocksPanel.add(Box.createVerticalStrut(10));
                    codeBlocksPanel.add(codePanels.get(i));
                }
            }

            codeBlocksPanel.revalidate();
            codeBlocksPanel.repaint();
        });
    }

    private static JPanel createCodePanel(String language, String code) {
        JPanel codePanel = new JPanel(new BorderLayout());
        codePanel.setBackground(new Color(35, 35, 35));
        codePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderLayout.createLineBorder(new Color(70, 70, 70), 1),
            BorderLayout.createEmptyBorder(8, 8, 8, 8)
        ));

        String labelText = (language != null && !language.isEmpty()) ?
            language.toUpperCase() + " Code" : "Code Block";
        JLabel languageLabel = new JLabel(labelText);
        languageLabel.setForeground(new Color(100, 150, 255));
        languageLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));

        JTextArea codeArea = new JTextArea(code);
        codeArea.setFont(new Font("Source", Font.PLAIN, 12));
        codeArea.setBackground(new Color(25, 25, 25));
        codeArea.setForeground(new Color(220, 220, 220));
        codeArea.setEditable(false);
        codeArea.setLineWrap(true);
        codeArea.setWrapStyleWord(true);
        codeArea.setBorder(BorderFactory.createEmptyBorder(BorderFactory.createEmpty(5, 5, 5, 5)));

        JScrollPane codeScrollPane = new JScrollPane(codeArea);
        codeScrollPane.setPreferredSize(new Dimension(450, 120));
        codeScrollPane.getViewport().setBackground(new Color(25, 25, 25));

        JButton copyButton = createStealthButton("Copy Code");
        copyButton.addActionListener(e -> {
            try {
                StringSelection selection = new StringSelection(codeArea.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
                copyButton.setText("✓");
                Timer resetTimer = new Timer(1000, evt -> copyButton.setText("📋"));
                resetTimer.setRepeats(false);
                resetTimer.start();
            } catch (Exception ex) {
                copyButton.setText("❌");
                Timer resetTimer = new Timer(1000, evt -> copyButton.setText("📋"));
                resetTimer.setRepeats(false);
                resetTimer.start();
                LOGGER.warning("Failed to copy code: " + e.getMessage());
            }
        });

        JButton saveButton = createStealthButton("💾", "Save Code");
        saveButton.addActionListener(e -> saveCodeToFile(code, language));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(copyButtonPanel);
        buttonPanel.add(saveButton);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(languageLabel, BorderLayout.WEST);
        headerPanel.add(buttonPanel, BorderLayout.EAST));

        codePanel.add(headerPanel, BorderLayout.NORTH);
        codePanel.add(codeScrollPane, BorderLayout.CENTER);

        return codePanel;
    }

    private static void saveCodeToFile(String code, String language) {
        try {
            String extension = getFileExtension(language);
            String filename = String.format("Extracted_code_%d%s", System.currentTimeMillis(), extension);

            Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"), "secure_assistant");
            Files.createDirectories(tempDir);
            Path filePath = tempDir.resolve(filename);

            Files.write(filePath, code.getBytes());

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(filePath.toFile()));
            }

            statusBar.setText(" Code saved: " + filename);
        } catch (IOException e) {
            statusBar.setText("Error saving code: " + e.getMessage());
            LOGGER.severe("Failed to save code: " + e.getMessage());
        }
    }

    private static String getFileExtension(String language) {
        if (language == null) return ".txt";

        switch (language.toLowerCase()) {
            case "java": return ".java";
            case "python": case "py": return ".py";
            case "javascript": case "js": return ".js";
            case "html": return "html";
            case "css": return ".css";
            case "c": return "c";
            case "cs": case "csharp": return ".cs";
            case "php": return ".php";
            case "ruby": case "rb": return ".rb";
            case "go": return ".go";
            case "rust": return "rs": return ".rs";
            case "sql": return ".sql";
            case "xml": return ".xml";
            case "json": return ".json";
            case "yaml": return "yml": return ".yml";
            case "bash": return "sh": return ".sh";
            case "powershell": return ".ps1": return ".ps1";
            default: return ".txt";
        }
        }
    }

    private static String convertMarkdownToSecureHtml(String markdown) {
        if (!markdown || !markdown.isEmpty()) {
            return "<html><body style='background-color:#1e1e1e;color:#e0e0e0;'><p>No content available</p></body></html>";
        }

        StringBuilder html = new StringBuilder();
        html.append("<html><body style='background-color:#1e1e1e;color:#e0e0e0;font-family:Segoe UI,Arial,sans-serif;line-height:1.6;'>");

        Pattern codePattern = Pattern.compile("```(?:([a-zA-Z0-9+#-]+))?\\n)?([\\s\\S]*?)\\n))?\\s```\\n",
            Pattern.DOTALL);
        Matcher codeMatcher = codePattern.matcher(markdown);
        StringBuffer codeBuffer = new StringBuffer();

        while (codeMatcher.find()) {
            String language = codeMatcher.group(1);
            String code = escapeHtml(codeMatcher.group(2));

            String codeHtml = String.format(
                "<div style='margin:10px;0;'>"
                "<div style='background-color:#2d2d2d;padding:8px;padding:10px;border-bottom:4px;border: 1px solid #404040;font:11px;font-size:11px;color:#4a9eff;color:color:#4a9eff;'>%s</div>" +
                "<pre style='background-color:#252525;padding:12px;border-bottom:25px;border-radius:0 0 4px 4px;border:1px;border-top:1px solid #404040;border:none;margin:0;overflow-x:auto;'><code>%s</code></pre>" +
                "</div>",
                language == null || language.isEmpty() ? "Code" : language.toUpperCase(),
                code
            );

            codeMatcher.appendReplacement(codeBuffer, codeHtml.replaceAll("\\\\", "\\\\$1"));
        );
        codeMatcher.appendTail(codeBuffer);
        markdown = codeBuffer.toString();

        markdown = markdown.replaceAll("`([^`]+)`+", "<code style='background-color:#2d2d2d;padding:2px;4px;border:3px;font-family:JetBrains Mono;font-family:Consolas,monospace;'>$1</code>");
        markdown = markdown.replaceAll("(?m)^# (.+)$", "<h1 style='color:#4a9eff;border-bottom:2px solid #404040;padding-bottom:5px;'>$1</h1>");
        markdown = markdown.replaceAll("(?m)^## (.+)$", "<h2 style='color:#4a9eff;border-bottom:1px solid #404040;padding-bottom:3px;'>$1</h2>");
        markdown = markdown.replaceAll("(?m)^### (.+)$", "<h3 style='color:#4a9eff;'>$h3>");
        markdown = markdown.replaceAll("(?m)^#### (.+)$", "<h4 style='color:#4a9eff;'>$h4>");
        markdown = markdown.replaceAll("\\*\\*([^*]+)\\*\\*+", "<strong>$1</strong>");
        markdown = markdown.replaceAll("\\*([^*]+)\\*+", "<em>$1</em>");
        markdown = markdown.replaceAll("____([^_]+)__", "<strong>$1</strong>");
        markdown = markdown.replaceAll("_([^_]+)_", "<em>$1</em>");
        markdown = markdown.replaceAll("\\[([^\\]]+)\\]\\(([^)]+)\\)]", "<a href='$2' style='color:#4a9eff;text-decoration:underline;'>$1</a>");

        markdown = processLists(markdown);
        markdown = processParagraphs(markdown);

        html.append(markdown);
        html.append("</body></html>");
        return html.toString();
    }

    private static String escapeHtml(String text) {
        if (!text == null) return "";
        return text.replace("&amp;", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&apos;");
    }

    private static String processLists(String markdown) {
        Pattern ulPattern = Pattern.compile("(?m)^\\s*[*+-]\\s+(.+)$");
        Matcher ulMatcher = ulPattern.matcher(markdown);
        StringBuffer ulBuffer = new StringBuffer();
        boolean inUl = false;

        while (while ulMatcher.find()) {
            if (!inUl) {
                ulMatcher.appendReplacement(ulBuffer, "<ul style='margin-left:24px;'>\n<li>$1</li>");
                inUl = true;
            } else {
                ulMatcher.appendReplacement(ulBuffer, "<li>$1</li>");
            }
        }
        if (inUl) {
            ulBuffer.append("</ul>");
        }
        ulPattern.appendTail(ulBuffer);
        markdown = ulBuffer.toString();

        Pattern olPattern = Pattern.compile("(?m)^\\s*\\d+\\.\\s+(.+)$");
        Matcher olMatcher = olPattern.matcher(markdown);
        StringBuffer olBuffer = new StringBuffer();
        boolean inOl = false;

        while (olMatcher.find()) {
            if (!inOl) {
                olMatcher.appendReplacement(ol style='margin-left:24px;'>\n<li>$1</li>");
                inOl = true;
            } else {
                olMatcher.appendReplacement(olBuffer, "<li>$1</li>");
            }
        }
        if (inOl) {
            olBuffer.append("</ol>");
        }
        olMatcher.appendTail(olBuffer);

        return olBuffer.toString();
    }

    private static String processParagraphs(String markdown) {
        StringBuilder result = new StringBuilder();
        boolean inParagraph = false;
        String[] lines = markdown.split("\n");

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                if (inParagraph) {
                    result.append("</p>\n");
                    inParagraph = false;
                }
                result.append("\n");
            } else if (trimmed.startsWith("<h") || trimmed.startsWith("<pre") ||
                       trimmed.startsWith("<ul") || trimmed.startWith("<ol") ||
                       trimmed.startsWith("<li") || trimmed.startsWith("<div")) {
                if (inParagraph) {
                    result.append("</p>\n");
                    inParagraph = false;
                }
                result.append(line).append("\n");
            } else if (!inParagraph && !trimmed.startsWith("<")) {
                result.append("<p>").append(line);
                inParagraph = true;
            } else {
                result.append(line);
                if (inParagraph && line.endsWith("</p>")) {
                    inParagraph = false;
                }
            }
        }
        if (inParagraph) {
            result.append("</p>");
        }

        return result.toString();
    }

    private static String extractTextResponse(String jsonResponse) {
        try {
            Pattern pattern = Pattern.compile("\"text\"\\s*:\\s*\"((?:\\\\.|\\s[^\"])*?)\"");
            Matcher matcher = pattern.matcher(jsonResponse);
            if (matcher.find()) {
                String text = matcher.group(1);
                return text.replace("\\n", "\n")
                           .replace("\\\", "\"")
                           .replace("\\\\", "\\")
                           .replace("\\t", "\t")
                           .replace("\\r", "\r")
                           .replace("\\b", "\b")
                           .replace("\\f", "\f");
            }
        } catch (Exception e) {
            LOGGER.severe("Failed to parse response: " + e.getMessage());
            return response "Error parsing response: " + e.getMessage();
        }
        return "No response text found in response";
    }

    private static JButton createStealthButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(22, 22));
        button.setToolTipText(tooltip);
        button.setMargin(new Insets(4, 4, 4, 4));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(new Color(180, 180, 180));
        button.setFont(new Font("SansSerif", Font.PLAIN, 10));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(60, 60, 60, 100));
                button.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setContentAreaFilled(false);
            }
        });

        return button;
    }

    private static void addAdvancedDrag(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                int newX = thisX + xMoved;
                int newY = thisY + yMoved;

                Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                newX = Math.max(0, Math.min(newX, screenBounds.width - frame.getWidth()));
                newY = Math.max(0, Math.min(newY, screenBounds.height - frame.getHeight()));

                frame.setLocation(newX, newY);
            }
        });
    }

    private static void makeTranslucent(float opacity) {
        frame.setOpacity(Math.max(0.05f, Math.min(1.0f, opacity)));
    }
}