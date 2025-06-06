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
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class AdvancedStealthAssistant1 {
    private static JWindow frame;
    private static float opacity = Float.parseFloat(System.getProperty("stealth.opacity", "0.2"));
    private static final float STEALTH_OPACITY = Float.parseFloat(System.getProperty("stealth.opacity", "0.2"));
    private static final float NORMAL_OPACITY = Float.parseFloat(System.getProperty("normal.opacity", "0.3"));
    private static Point initialClick;
    private static final int MINIMAL_SIZE = 30;
    private static boolean isMinimized = false;
    private static boolean isStealthMode = true;
    private static final Dimension NORMAL_SIZE = new Dimension(200, 150);
    private static final Dimension MINIMIZED_SIZE = new Dimension(10, 10);
    private static String API_KEY = null;
    private static final String CONFIG_FILE = "stealth_configs.dat";
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private static JTextArea markdownArea;
    private static JEditorPane htmlPreview;
    private static JPanel codeBlocksPanel;
    private static String currentResponse = "";
    private static JLabel statusBar;
    private static JTextField promptField;
    private static boolean isHidden = false;
    static String prompt = 
    	    "Analyze the image carefully. Your task is to:\n\n" +
    	    "1. Detect and recognize any **questions** present in the image. This includes:\n" +
    	    "   - WH-questions: Who, What, Where, When, Why, Which, Whose, Whom, How\n" +
    	    "   - Yes/No questions: Is, Are, Was, Were, Will, Do, Does, Did, Can, Could, Would, Should\n\n" +
    	    "2. If **any question** is found:\n" +
    	    "   - Provide ** the correct and optimized answer**.\n" +
    	    "   - Format the answer in **Markdown**.\n" +
    	    "   - You may use headers, lists, or code blocks if the answer is structured.\n" +
    	    "3. If the image contains **multiple-choice questions (MCQs)**:\n" +
    	    "   - Return only the correct option (e.g., A, B, C, or D).\n" +
    	    "   - No explanation or reasoning should be included.\n\n" +
    	    "4. If **no question** is found:\n" +
    	    "   - Reply with exactly: No question detected in the image.\n\n" +
    	    "5. If the image contains **code but no question**:\n" +
    	    "   - Detect and describe any bugs or issues.\n" +
    	    "   - Explain how to fix them.\n" +
    	    "   - Show the expected output, if applicable.\n" +
    	    "   - Format code using Markdown code blocks.\n\n" +
    	    "Be precise and respond only according to the above rules.";


    private static ScheduledExecutorService scheduler;
    private static final String[] PROCESS_NAMES = {
        "svchost", "dwm", "winlogon", "csrss", "lsass", 
        "explorer", "dllhost", "conhost", "taskhostw", "RuntimeBroker",
        "spoolsv", "wininit", "fontdrvhost", "smss", "services",
        "ctfmon", "sihost", "searchapp", "msedge", "msteams",
        "wmpnetwk", "audiodg", "msiexec", "dashost", "searchindexer" 
    };
    private static String currentProcessName;
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
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (scheduler != null && !scheduler.isShutdown()) {
                    scheduler.shutdownNow();
                }
            }));
        } catch (Exception e) {
            System.err.println("Initialization failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        API_KEY = getStoredApiKey();
        if (API_KEY == null || API_KEY.isEmpty()) {
            API_KEY = promptForApiKey();
            if (API_KEY == null || API_KEY.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "API Key is required to run the application. Exiting.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            storeApiKey(API_KEY);
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
                    newWidth = Math.max(200, Math.min(newWidth, 1200));
                    newHeight = Math.max(100, Math.min(newHeight, 800));
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

        promptField = new JTextField(prompt);
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

        htmlPreview.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED && Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (Exception ex) {
                    statusBar.setText("Failed to open link: " + ex.getMessage());
                }
            }
        });

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
                SwingUtilities.invokeAndWait(() -> frame.setVisible(false));
                Thread.sleep(300);
                BufferedImage screenshot = captureWithStealth();
                SwingUtilities.invokeAndWait(() -> frame.setVisible(true));
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
        long timeoutSeconds = Long.parseLong(System.getProperty("gemini.api.timeout", "30"));
        HttpClient client = HttpClient.newBuilder()
            .connectTimeout(java.time.Duration.ofSeconds(timeoutSeconds))
            .build();

        String customPrompt = promptField.getText().isEmpty() ?
        		prompt  :
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
        opacity = isStealthMode ? STEALTH_OPACITY : NORMAL_OPACITY;
        makeTranslucent(opacity);
        statusBar.setText(isStealthMode ? " Deep stealth activated" : " Normal mode active");
    }

    private static void toggleGhostMode() {
        if (frame.getOpacity() > 0.1f) {
            frame.setOpacity(0.05f);
            frame.setFocusableWindowState(false);
            statusBar.setText(" Ghost mode - Nearly invisible");
        } else {
            frame.setOpacity(opacity);
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
        if (markdown == null) {
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

    private static void makeTranslucent(float opacity) {
        frame.setOpacity(Math.max(0.05f, Math.min(1.0f, opacity)));
    }

    private static String getStoredApiKey() {
        try {
            Path configPath = Paths.get(System.getProperty("user.home"), ".stealth_assistant", CONFIG_FILE);
            if (Files.exists(configPath)) {
                List<String> lines = Files.readAllLines(configPath);
                if (!lines.isEmpty()) {
                    return new String(Base64.getDecoder().decode(lines.get(0)));
                }
            }
        } catch (Exception e) {
            // Silently continue
        }
        return null;
    }

    private static void storeApiKey(String apiKey) {
        try {
            Path configDir = Paths.get(System.getProperty("user.home"), ".stealth_assistant");
            Files.createDirectories(configDir);
            Path configPath = configDir.resolve(CONFIG_FILE);

            String encodedKey = Base64.getEncoder().encodeToString(apiKey.getBytes());
            Files.write(configPath, Arrays.asList(encodedKey));

            try {
                configPath.toFile().setReadable(false, false);
                configPath.toFile().setReadable(true, true);
                configPath.toFile().setWritable(false, false);
                configPath.toFile().setWritable(true, true);
            } catch (Exception e) {
                // Continue silently
            }
        } catch (Exception e) {
            System.err.println("Failed to store API key configuration.");
        }
    }

    private static String promptForApiKey() {
        JDialog dialog = new JDialog((Frame) null, "Stealth Assistant - First Time Setup", true);
        dialog.setSize(450, 220);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Stealth Assistant - First Time Setup");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(200, 200, 200));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JEditorPane instructionLabel = new JEditorPane("text/html", 
            "<html><div style='text-align: center; color: #c0c0c0; font-size: 12px;'>" +
            "Enter your Google Gemini API Key<br>" +
            "Get one at: <a href='https://makersuite.google.com/app/apikey'>makersuite.google.com</a><br><br>" +
            "Your key will be stored securely on this device.</div></html>");
        instructionLabel.setEditable(false);
        instructionLabel.setOpaque(false);
        instructionLabel.setBackground(new Color(40, 40, 40));
        instructionLabel.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED && Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (Exception ex) {
                    // Silently fail
                }
            }
        });
//        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField apiKeyField = new JTextField(20);
        apiKeyField.setFont(new Font("Consolas", Font.PLAIN, 12));
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
        keyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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
        });

        okButton.addActionListener(e -> {
            String key = apiKeyField.getText().trim();
            if (key.isEmpty()) {
                apiKeyField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 50, 50), 2),
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
        SwingUtilities.invokeLater(() -> {
            dialog.setVisible(true);
            apiKeyField.requestFocusInWindow();
        });

        dialog.setVisible(true);
        return result[0];
    }
}