package API;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.JOptionPane;

public class AdvancedStealthAssistant1 {
    private static JWindow frame;
    private static float opacity = 0.3f;
    private static Point initialClick;
    private static final int MINIMAL_SIZE = 30;
    private static boolean isMinimized = false;
    private static boolean isStealthMode = true;
    private static final Dimension NORMAL_SIZE = new Dimension(200, 150);
    private static final Dimension MINIMIZED_SIZE = new Dimension(10, 10);
    private static String API_KEY;
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private static JTextArea markdownArea;
    private static JEditorPane htmlPreview;
    private static JPanel codeBlocksPanel;
    private static String currentResponse = "";
    private static JLabel statusBar;
    private static JTextField promptField;
    private static ScheduledExecutorService scheduler;
    private static final String[] PROCESS_NAMES = {
        "svchost", "dwm", "winlogon", "csrss", "lsass", 
        "explorer", "dllhost", "conhost", "taskhostw", "RuntimeBroker",
        "spoolsv", "wininit", "fontdrvhost", "smss", "services",
        "ctfmon", "sihost", "searchapp", "msedge", "msteams",
        "wmpnetwk", "audiodg", "msiexec", "dashost", "searchindexer"
    };
    private static String currentProcessName;
    private static Timer stealthTimer;
    private static boolean isHidden = false;
    private static Robot robot;
    private static final Set<String> SECURITY_PROCESSES = new HashSet<>(Arrays.asList(
        "procmon", "procexp", "taskmgr", "wireshark", "fiddler", "cheatengine", "ollydbg",
        "x64dbg", "ida", "vmware", "virtualbox", "sandboxie", "avp", "mcshield", "windefend",
        "malwarebytes", "avgui", "avguard", "ekrn", "eset", "fsav", "sophosui", "trendmicro"
    ));

    static {
        try {
            robot = new Robot();
            scheduler = Executors.newScheduledThreadPool(3);
            currentProcessName = getRandomProcessName();
            initializeStealthFeatures();
        } catch (Exception e) {
            System.err.println("Initialization failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        API_KEY = getApiKey();
        if (API_KEY == null || API_KEY.isEmpty()) {
            System.err.println("Configuration error: API key not provided.");
            System.exit(1);
        }

        try {
            if (isDebuggingDetected() || isVirtualMachineDetected()) {
                System.exit(0);
            }
            obfuscateProcessName();
            startSecurityMonitoring();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(0);
        }

        SwingUtilities.invokeLater(() -> createStealthGUI());
    }

    private static String getApiKey() {
        String homeDir = System.getProperty("user.home");
        File configDir = new File(homeDir, ".advancedstealthassistant");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
        File configFile = new File(configDir, "config.properties");
        Properties props = new Properties();

        // Check if config file exists and load the API key
        if (configFile.exists()) {
            try (FileInputStream fis = new FileInputStream(configFile)) {
                props.load(fis);
                String apiKey = props.getProperty("api_key");
                if (apiKey != null && !apiKey.isEmpty()) {
                    return apiKey;
                }
            } catch (IOException e) {
                System.err.println("Error loading config: " + e.getMessage());
            }
        }

        // API key not found or empty, prompt user
        String apiKey = JOptionPane.showInputDialog(null, "Please enter your Gemini API key:", 
            "API Key Required", JOptionPane.PLAIN_MESSAGE);
        if (apiKey != null && !apiKey.isEmpty()) {
            props.setProperty("api_key", apiKey);
            try (FileOutputStream fos = new FileOutputStream(configFile)) {
                props.store(fos, "API Key");
            } catch (IOException e) {
                System.err.println("Error saving config: " + e.getMessage());
            }
            return apiKey;
        }
        return null; // User canceled or entered an empty key
    }

    private static void createStealthGUI() {
        frame = new JWindow();
        frame.setSize(NORMAL_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setFocusable(true);
        frame.setFocusableWindowState(true);
        frame.setType(Window.Type.UTILITY);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60, 100), 1));
        mainPanel.setBackground(new Color(20, 20, 20, 200));

        makeTranslucent(opacity);
        addAdvancedDragCapability(mainPanel);

        JPanel titleBar = createStealthTitleBar();
        mainPanel.add(titleBar, BorderLayout.NORTH);

        JPanel promptPanel = createPromptPanel();
        mainPanel.add(promptPanel, BorderLayout.SOUTH);

        JTabbedPane tabbedPane = createAdvancedTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // --- Combine status bar and resize handle ---
        JPanel southPanel = new JPanel(new BorderLayout());
        statusBar = createSecurityStatusBar();
        southPanel.add(statusBar, BorderLayout.CENTER);

        // Add resize handle to the right of the status bar
        JLabel resizeCorner = new JLabel("◢");
        resizeCorner.setForeground(new Color(100, 100, 100));
        resizeCorner.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        resizeCorner.setHorizontalAlignment(SwingConstants.RIGHT);
        resizeCorner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        resizeCorner.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
        southPanel.add(resizeCorner, BorderLayout.EAST);

        // Add resize listeners
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
                    newWidth = Math.max(300, Math.min(newWidth, 1200));
                    newHeight = Math.max(200, Math.min(newHeight, 800));
                    frame.setSize(newWidth, newHeight);
                }
            }
        });

        mainPanel.add(southPanel, BorderLayout.SOUTH);
        // --- End combine ---

        frame.add(mainPanel);
        frame.setVisible(true);

        initializeStealthBehaviors();
        startBackgroundMonitoring();
    }

    private static JPanel createStealthTitleBar() {
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(15, 15, 15, 180));
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
        promptPanel.setBackground(new Color(25, 25, 25, 150));

        JLabel promptLabel = new JLabel("Query: ");
        promptLabel.setForeground(new Color(140, 140, 140));
        promptLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));

        promptField = new JTextField("Analyze the image to detect any questions (e.g., 'What ...?', 'Who ...?', 'Where ...?', 'When ...?', 'Why ...?', 'How ...?', 'Which ...?', 'Whose ...?', 'Whom ...?', 'Can ...?', 'Could ...?', 'Would ...?', 'Should ...?', 'Is ...?', 'Are ...?', 'Will ...?', 'Do ...?', 'Does ...?', 'Did ...?'). If a question is found, provide only the answer in Markdown format without repeating the question or adding extra context, using headers, lists, and code blocks as needed. If no question is found, respond with: 'No question detected in the image. If code is present, identify any issues or bugs, explain how to fix them, show the expected output when applicable, and format code solutions in appropriate code blocks.'");
        promptField.setFont(new Font("Consolas", Font.PLAIN, 11));
        promptField.setBackground(new Color(40, 40, 40));
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

        markdownArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String currentText = markdownArea.getText();
                updateSecureHtmlPreview(currentText);
                updateSecureCodeBlocks(currentText);
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
        statusBar.setBackground(new Color(20, 40, 20));
        statusBar.setForeground(new Color(120, 200, 120));
        return statusBar;
    }

    private static void initializeStealthFeatures() {
        System.setProperty("java.awt.headless", "false");
        System.setProperty("java.security.manager", "");
        try {
            hideFromTaskManager();
        } catch (Exception e) {
            // Silently continue
        }
    }

    private static void startSecurityMonitoring() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (detectSecurityTools()) {
                    initiateEmergencyProtocol();
                }
                updateProcessName();
                cleanMemoryFootprint();
            } catch (Exception e) {
                // Continue silently
            }
        }, 5, 15, TimeUnit.SECONDS);
    }

    private static boolean detectSecurityTools() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Process proc = Runtime.getRuntime().exec("tasklist /fo csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    String lowerLine = line.toLowerCase();
                    for (String secProcess : SECURITY_PROCESSES) {
                        if (lowerLine.contains(secProcess)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Return false on any error
        }
        return false;
    }

    private static void initiateEmergencyProtocol() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(false);
            isHidden = true;
            statusBar.setText(" Security Protocol Active");
        });

        if (markdownArea != null) {
            markdownArea.setText("");
        }
        if (promptField != null) {
            promptField.setText("");
        }

        Timer emergencyTimer = new Timer(60000, e -> {
            if (!detectSecurityTools()) {
                SwingUtilities.invokeLater(() -> {
                    frame.setVisible(true);
                    isHidden = false;
                    statusBar.setText(" Mode Active");
                });
            }
        });
        emergencyTimer.start();
    }

    private static void performStealthScreenshot() {
        CompletableFuture.runAsync(() -> {
            try {
                statusBar.setText(" Initiating capture...");

                frame.setVisible(false);
                Thread.sleep(300);

                BufferedImage screenshot = captureWithStealth();

                frame.setVisible(true);

                String base64Image = encodeImageToBase64(screenshot);

                statusBar.setText(" Processing...");
                String response = sendSecureImageToGemini(base64Image);
                String responseText = extractResponseText(response);

                SwingUtilities.invokeLater(() -> {
                    currentResponse = responseText;
                    markdownArea.setText(currentResponse);
                    updateSecureHtmlPreview(currentResponse);
                    updateSecureCodeBlocks(currentResponse);
                    statusBar.setText(" Analysis complete");
                });

            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    statusBar.setText(" Error: " + e.getMessage());
                });
            }
        });
    }

    private static String encodeImageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private static BufferedImage captureWithStealth() throws AWTException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenshot = robot.createScreenCapture(screenRect);

        Graphics2D g2d = screenshot.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.001f));
        g2d.setColor(new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
        g2d.fillRect(0, 0, 1, 1);
        g2d.dispose();

        return screenshot;
    }

    private static String sendSecureImageToGemini(String base64Image) throws Exception {
        HttpClient client = HttpClient.newBuilder()
            .connectTimeout(java.time.Duration.ofSeconds(30))
            .build();

        String customPrompt = promptField.getText().isEmpty() ?
                        "Analyze the image to detect any questions (e.g., 'What ...?', 'Who ...?', 'Where ...?', 'When ...?', 'Why ...?', 'How ...?', 'Which ...?', 'Whose ...?', 'Whom ...?', 'Can ...?', 'Could ...?', 'Would ...?', 'Should ...?', 'Is ...?', 'Are ...?', 'Will ...?', 'Do ...?', 'Does ...?', 'Did ...?'). If a question is found, provide only the answer in Markdown format without repeating the question or adding extra context, using headers, lists, and code blocks as needed. If no question is found, respond with: 'No question detected in the image. If code is present, identify any issues or bugs, explain how to fix them, show the expected output when applicable, and format code solutions in appropriate code blocks.'" :
                    promptField.getText();

        String requestBody = String.format(
            "{\"contents\":[{\"parts\":[{\"text\":\"%s\"},{\"inlineData\":{\"mimeType\":\"image/png\",\"data\":\"%s\"}}]}]}",
            customPrompt.replace("\"", "\\\"").replace("\n", "\\n"),
            base64Image
        );

        String url = GEMINI_API_URL + "?key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("API Error: " + response.statusCode());
        }

        return response.body();
    }

    private static void toggleStealthMode() {
        isStealthMode = !isStealthMode;
        if (isStealthMode) {
            opacity = 0.2f;
            statusBar.setText(" Deep stealth activated");
        } else {
            opacity = 0.8f;
            statusBar.setText(" Normal mode active");
        }
        makeTranslucent(opacity);
    }

    private static void activateDeepHide() {
        frame.setVisible(false);
        isHidden = true;

        Timer hideTimer = new Timer(100, new ActionListener() {
            private boolean ctrlPressed = false;
            private boolean shiftPressed = false;
            private boolean altPressed = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (robot != null) {
                        Point mouse = MouseInfo.getPointerInfo().getLocation();
                        if (mouse.x == 0 && mouse.y == 0) {
                            frame.setVisible(true);
                            isHidden = false;
                            statusBar.setText(" Unhidden from deep stealth");
                            ((Timer)e.getSource()).stop();
                        }
                    }
                } catch (Exception ex) {
                    // Continue silently
                }
            }
        });
        hideTimer.start();

        statusBar.setText(" Deep hide active - Move mouse to top-left to restore");
    }

    private static void toggleGhostMode() {
        if (frame.getOpacity() > 0.1f) {
            frame.setOpacity(0.05f);
            frame.setFocusableWindowState(false);
            statusBar.setText(" Ghost mode - Nearly invisible");
        } else {
            frame.setOpacity(0.7f);
            frame.setFocusableWindowState(true);
            statusBar.setText(" Ghost mode disabled");
        }
    }

    private static void secureClipboardCopy() {
        try {
            String textToCopy = markdownArea.getText();
            StringSelection selection = new StringSelection(textToCopy);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            statusBar.setText(" Content copied to clipboard");
        } catch (Exception e) {
            statusBar.setText(" Copy failed: " + e.getMessage());
        }
    }

    private static void secureExit() {
        try {
            if (markdownArea != null) markdownArea.setText("");
            if (promptField != null) promptField.setText("");
            if (scheduler != null && !scheduler.isShutdown()) {
                scheduler.shutdownNow();
            }
            System.gc();
            currentResponse = "";
        } catch (Exception e) {
            // Silently continue
        } finally {
            System.exit(0);
        }
    }

    private static String getRandomProcessName() {
        Random rand = new Random();
        return PROCESS_NAMES[rand.nextInt(PROCESS_NAMES.length)];
    }

    private static void obfuscateProcessName() {
        try {
            System.setProperty("java.awt.headless", "false");
        } catch (Exception e) {
            // Continue silently
        }
    }

    private static void updateProcessName() {
        currentProcessName = getRandomProcessName();
    }

    private static void hideFromTaskManager() {
        System.setProperty("java.awt.Window.locationByPlatform", "true");
    }

    private static void cleanMemoryFootprint() {
        System.gc();
        Runtime.getRuntime().runFinalization();
    }

    private static boolean isDebuggingDetected() {
        List<String> jvmArgs = ManagementFactory.getRuntimeMXBean().getInputArguments();
        for (String arg : jvmArgs) {
            if (arg.contains("-agentlib:jdwp") || arg.contains("-Xdebug")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isVirtualMachineDetected() {
        String[] vmIndicators = {"VMware", "VirtualBox", "QEMU", "Xen", "Microsoft Corporation"};
        String vendor = System.getProperty("java.vm.vendor", "");
        String name = System.getProperty("java.vm.name", "");

        for (String indicator : vmIndicators) {
            if (vendor.contains(indicator) || name.contains(indicator)) {
                return true;
            }
        }
        return false;
    }

    private static void startBackgroundMonitoring() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (!frame.isFocused() && !isHidden) {
                    SwingUtilities.invokeLater(() -> {
                        if (frame.getOpacity() > 0.3f) {
                            frame.setOpacity(0.15f);
                        }
                    });
                }
                if (Math.random() < 0.1) {
                    cleanMemoryFootprint();
                }
            } catch (Exception e) {
                // Continue silently
            }
        }, 10, 5, TimeUnit.SECONDS);
    }

    private static void initializeStealthBehaviors() {
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                if (isStealthMode) {
                    SwingUtilities.invokeLater(() -> {
                        frame.setOpacity(0.1f);
                    });
                }
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    frame.setOpacity(opacity);
                });
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
                htmlPreview.setText("<p style='color:#ff6b6b'>Preview error: " + e.getMessage() + "</p>");
            }
        });
    }

    private static void updateSecureCodeBlocks(String markdown) {
        codeBlocksPanel.removeAll();
        Pattern pattern = Pattern.compile("```([a-zA-Z0-9+#-]*)?\\n([\\s\\S]*?)\\n```", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(markdown);
        List<JPanel> codePanels = new ArrayList<>();

        while (matcher.find()) {
            String language = matcher.group(1);
            String code = matcher.group(2).trim();
            JPanel codePanel = createSecureCodePanel(language, code);
            codePanels.add(codePanel);
        }

        if (codePanels.isEmpty()) {
            JLabel noCodeLabel = new JLabel("No code blocks detected.");
            noCodeLabel.setForeground(new Color(120, 120, 120));
            noCodeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            codeBlocksPanel.add(noCodeLabel);
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

    private static JPanel createSecureCodePanel(String language, String code) {
        JPanel codePanel = new JPanel(new BorderLayout());
        codePanel.setBackground(new Color(35, 35, 35));
        codePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 70, 70), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        String labelText = (language != null && !language.isEmpty()) ?
            language.toUpperCase() + " Code" : "Code Block";
        JLabel languageLabel = new JLabel(labelText);
        languageLabel.setForeground(new Color(100, 150, 255));
        languageLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));

        JTextArea codeArea = new JTextArea(code);
        codeArea.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        codeArea.setBackground(new Color(25, 25, 25));
        codeArea.setForeground(new Color(220, 220, 220));
        codeArea.setEditable(false);
        codeArea.setLineWrap(true);
        codeArea.setWrapStyleWord(true);
        codeArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane codeScrollPane = new JScrollPane(codeArea);
        codeScrollPane.setPreferredSize(new Dimension(450, 120));
        codeScrollPane.getViewport().setBackground(new Color(25, 25, 25));

        JButton copyButton = createStealthButton("📋", "Copy Code");
        copyButton.addActionListener(e -> {
            try {
                StringSelection selection = new StringSelection(codeArea.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
                copyButton.setText("✓");
                Timer resetTimer = new Timer(1000, evt -> copyButton.setText("📋"));
                resetTimer.setRepeats(false);
                resetTimer.start();
            } catch (Exception ex) {
                copyButton.setText("✗");
                Timer resetTimer = new Timer(1000, evt -> copyButton.setText("📋"));
                resetTimer.setRepeats(false);
                resetTimer.start();
            }
        });

        JButton saveButton = createStealthButton("💾", "Save Code");
        saveButton.addActionListener(e -> saveCodeToFile(code, language));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(copyButton);
        buttonPanel.add(saveButton);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(languageLabel, BorderLayout.WEST);
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        codePanel.add(headerPanel, BorderLayout.NORTH);
        codePanel.add(codeScrollPane, BorderLayout.CENTER);

        return codePanel;
    }

    private static void saveCodeToFile(String code, String language) {
        try {
            String extension = getFileExtension(language);
            String filename = "extracted_code_" + System.currentTimeMillis() + extension;

            Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"), "secure_assistant");
            Files.createDirectories(tempDir);
            Path filePath = tempDir.resolve(filename);

            Files.write(filePath, code.getBytes());

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(filePath.toFile());
            }

            statusBar.setText(" Code saved: " + filename);

        } catch (Exception e) {
            statusBar.setText(" Save failed: " + e.getMessage());
        }
    }

    private static String getFileExtension(String language) {
        if (language == null) return ".txt";

        switch (language.toLowerCase()) {
            case "java": return ".java";
            case "python": case "py": return ".py";
            case "javascript": case "js": return ".js";
            case "html": return ".html";
            case "css": return ".css";
            case "cpp": case "c++": return ".cpp";
            case "c": return ".c";
            case "cs": case "csharp": return ".cs";
            case "php": return ".php";
            case "ruby": case "rb": return ".rb";
            case "go": return ".go";
            case "rust": case "rs": return ".rs";
            case "sql": return ".sql";
            case "xml": return ".xml";
            case "json": return ".json";
            case "yaml": case "yml": return ".yml";
            case "bash": case "sh": return ".sh";
            case "powershell": case "ps1": return ".ps1";
            default: return ".txt";
        }
    }

    private static String convertMarkdownToSecureHtml(String markdown) {
        if (markdown == null || markdown.isEmpty()) {
            return "<html><body style='background-color:#1e1e1e;color:#e0e0e0;'><p>No content available</p></body></html>";
        }

        StringBuilder html = new StringBuilder();
        html.append("<html><body style='background-color:#1e1e1e;color:#e0e0e0;font-family:Segoe UI,Arial,sans-serif;line-height:1.6;'>");

        Pattern codePattern = Pattern.compile("```(?:([a-zA-Z0-9+#-]+)?\\n)?([\\s\\S]*?)```");
        Matcher codeMatcher = codePattern.matcher(markdown);
        StringBuffer codeBuffer = new StringBuffer();

        while (codeMatcher.find()) {
            String language = codeMatcher.group(1) != null ? codeMatcher.group(1) : "";
            String code = escapeHtml(codeMatcher.group(2));

            String codeHtml = String.format(
                "<div style='margin:10px 0;'>" +
                "<div style='background-color:#2d2d2d;padding:8px;border-radius:4px 4px 0 0;border:1px solid #404040;font-size:11px;color:#4a9eff;'>%s</div>" +
                "<pre style='background-color:#252525;padding:12px;border-radius:0 0 4px 4px;border:1px solid #404040;border-top:none;margin:0;overflow-x:auto;'><code>%s</code></pre>" +
                "</div>",
                language.isEmpty() ? "Code" : language.toUpperCase(),
                code
            );

            codeMatcher.appendReplacement(codeBuffer, codeHtml);
        }
        codeMatcher.appendTail(codeBuffer);
        markdown = codeBuffer.toString();

        markdown = markdown.replaceAll("`([^`]+)`", "<code style='background-color:#2d2d2d;padding:2px 4px;border-radius:3px;font-family:JetBrains Mono,Consolas,monospace;'>$1</code>");
        markdown = markdown.replaceAll("(?m)^# (.+)$", "<h1 style='color:#4a9eff;border-bottom:2px solid #404040;padding-bottom:5px;'>$1</h1>");
        markdown = markdown.replaceAll("(?m)^## (.+)$", "<h2 style='color:#4a9eff;border-bottom:1px solid #404040;padding-bottom:3px;'>$1</h2>");
        markdown = markdown.replaceAll("(?m)^### (.+)$", "<h3 style='color:#4a9eff;'>$1</h3>");
        markdown = markdown.replaceAll("(?m)^#### (.+)$", "<h4 style='color:#4a9eff;'>$1</h4>");
        markdown = markdown.replaceAll("\\*\\*([^*]+)\\*\\*", "<strong>$1</strong>");
        markdown = markdown.replaceAll("\\*([^*]+)\\*", "<em>$1</em>");
        markdown = markdown.replaceAll("__([^_]+)__", "<strong>$1</strong>");
        markdown = markdown.replaceAll("_([^_]+)_", "<em>$1</em>");
        markdown = markdown.replaceAll("\\[([^\\]]+)\\]\\(([^)]+)\\)", "<a href='$2' style='color:#4a9eff;text-decoration:underline;'>$1</a>");

        markdown = processLists(markdown);
        markdown = processParagraphs(markdown);

        html.append(markdown);
        html.append("</body></html>");
        return html.toString();
    }

    private static String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }

    private static String processLists(String markdown) {
        Pattern ulPattern = Pattern.compile("(?m)^[*+-] (.+)$");
        Matcher ulMatcher = ulPattern.matcher(markdown);
        StringBuffer ulBuffer = new StringBuffer();
        boolean inUl = false;

        while (ulMatcher.find()) {
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
        ulMatcher.appendTail(ulBuffer);
        markdown = ulBuffer.toString();

        Pattern olPattern = Pattern.compile("(?m)^\\d+\\. (.+)$");
        Matcher olMatcher = olPattern.matcher(markdown);
        StringBuffer olBuffer = new StringBuffer();
        boolean inOl = false;

        while (olMatcher.find()) {
            if (!inOl) {
                olMatcher.appendReplacement(olBuffer, "<ol style='margin-left:24px;'>\n<li>$1</li>");
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
                       trimmed.startsWith("<ul") || trimmed.startsWith("<ol") ||
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
            return "Error parsing response: " + e.getMessage();
        }
        return "No response text found in: " + jsonResponse;
    }

    private static JButton createStealthButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(22, 22));
        button.setToolTipText(tooltip);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(new Color(180, 180, 180));
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 10));

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

    private static void addAdvancedDragCapability(JPanel panel) {
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

                Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                X = Math.max(0, Math.min(X, screenBounds.width - frame.getWidth()));
                Y = Math.max(0, Math.min(Y, screenBounds.height - frame.getHeight()));

                frame.setLocation(X, Y);
            }
        });
    }

    private static void addAdvancedResizeCapability(JPanel panel) {
        JLabel resizeCorner = new JLabel("◢");
        resizeCorner.setForeground(new Color(100, 100, 100));
        resizeCorner.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        resizeCorner.setHorizontalAlignment(SwingConstants.RIGHT);
        resizeCorner.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JPanel cornerPanel = new JPanel(new BorderLayout());
        cornerPanel.setOpaque(false);
        cornerPanel.add(resizeCorner, BorderLayout.EAST);
        panel.add(cornerPanel, BorderLayout.SOUTH);

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

                    newWidth = Math.max(300, Math.min(newWidth, 1200));
                    newHeight = Math.max(200, Math.min(newHeight, 800));

                    frame.setSize(newWidth, newHeight);
                }
            }
        });
    }

    private static void makeTranslucent(float opacity) {
        frame.setOpacity(Math.max(0.05f, Math.min(1.0f, opacity)));
    }
}