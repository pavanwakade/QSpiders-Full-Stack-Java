package src.api;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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
import javax.swing.JDialog;
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
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;


public class AdvancedStealthAssistant3 {
    private static JWindow mainWindow;
    private static float opacity = Float.parseFloat(System.getProperty("stealth.opacity", "0.2"));
    private static final float STEALTH_OPACITY = Float.parseFloat(System.getProperty("stealth.opacity", "0.2"));
    private static final float NORMAL_OPACITY = Float.parseFloat(System.getProperty("normal.opacity", "0.3"));
    private static Point initialClick;
    private static boolean isMinimized = false;
    private static boolean isStealthMode = true;
    private static boolean isHidden = false;
    private static final Dimension NORMAL_SIZE = new Dimension(200, 50);
    private static final Dimension MINIMIZED_SIZE = new Dimension(10, 30);
    private static String apiKey = null;
    private static final String CONFIG_FILE = "stealth_config.dat";
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private static JTextArea markdownArea;
    private static JEditorPane htmlPreview;
    private static JPanel codeBlocksPanel;
    private static String currentResponse = "";
    private static JLabel statusLabel;
    private static JTextArea promptArea;
    private static boolean isMultipleCaptureMode = false;
    private static List<BufferedImage> capturedImages = new ArrayList<>();
    private static JPanel controlPanel;
    private static JButton sendButton;
    private static boolean isFocused = false;
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
        apiKey = getStoredApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            apiKey = promptForApiKey();
            if (apiKey == null || apiKey.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "API Key is required to run the application. Exiting.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            storeApiKey(apiKey);
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

        SwingUtilities.invokeLater(AdvancedStealthAssistant3::createStealthGUI);
    }

    private static void createStealthGUI() {
        mainWindow = new JWindow();
        mainWindow.setSize(NORMAL_SIZE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setAlwaysOnTop(true);
        mainWindow.setFocusableWindowState(false);
        mainWindow.setType(Window.Type.UTILITY);

        hideFromTaskbar(mainWindow);

        JPanel mainPanel = new JPanel(new BorderLayout());
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
        statusLabel = createSecurityStatusBar();
        southPanel.add(statusLabel, BorderLayout.CENTER);

        JLabel resizeCorner = new JLabel("◢");
        resizeCorner.setForeground(new Color(100, 100, 100));
        resizeCorner.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
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
                    int width = mainWindow.getWidth();
                    int height = mainWindow.getHeight();
                    int newWidth = width + e.getX() - initialClick.x;
                    int newHeight = height + e.getY() - initialClick.y;
                    newWidth = Math.max(200, Math.min(newWidth, 1200));
                    newHeight = Math.max(100, Math.min(newHeight, 800));
                    mainWindow.setSize(newWidth, newHeight);
                }
            }
        });

        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainWindow.add(mainPanel);
        mainWindow.setVisible(true);
    ScreenshotProtection.applyProtectionWithRetry(mainWindow, 3);

        initializeStealthBehaviors();
        startBackgroundMonitoring();
    }

    // private static void hideFromTaskbar(JWindow window) {
    //     try {
    //         HWND hwnd = new HWND(Native.getWindowPointer(window));
    //         int exStyle = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
    //         exStyle |= WinUser.WS_EX_NOACTIVATE | WinUser.WS_EX_TOOLWINDOW;
    //         User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, exStyle);
    //     } catch (Exception e) {
    //         System.err.println("Failed to hide from taskbar: " + e.getMessage());
    //         statusLabel.setText("Taskbar hiding failed: " + e.getMessage());
    //     }
    // }

    private static void hideFromTaskbar(JWindow window) {
    try {
        // Define constants manually if not available in JNA version
        final int WS_EX_NOACTIVATE = 0x08000000;
        final int WS_EX_TOOLWINDOW = 0x00000080;
        
        HWND hwnd = new HWND(Native.getWindowPointer(window));
        int exStyle = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
        exStyle |= WS_EX_NOACTIVATE | WS_EX_TOOLWINDOW;
        User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, exStyle);
    } catch (Exception e) {
        System.err.println("Failed to hide from taskbar: " + e.getMessage());
        if (statusLabel != null) {
            statusLabel.setText("Taskbar hiding failed: " + e.getMessage());
        }
    }
}

    private static JPanel createStealthTitleBar() {
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(15, 15, 15, 180));
        titleBar.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));

        JLabel titleLabel = new JLabel("System Monitor");
        titleLabel.setForeground(new Color(120, 120, 120));
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        titleBar.add(titleLabel, BorderLayout.WEST);

        controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 1, 0));
        controlPanel.setOpaque(false);

        JButton screenshotButton = createStealthButton("📷", "Capture");
        screenshotButton.addActionListener(e -> {
            if (isMultipleCaptureMode) {
                captureSingleScreenshotForMultiple();
            } else {
                performStealthScreenshot();
            }
        });

        JButton multiCaptureButton = createStealthButton("📸", "Multiple Capture");
        multiCaptureButton.addActionListener(e -> toggleMultipleCaptureMode());

        JButton copyButton = createStealthButton("📋", "Copy");
        copyButton.addActionListener(e -> secureClipboardCopy());

        JButton ghostButton = createStealthButton("👤", "Ghost");
        ghostButton.addActionListener(e -> toggleGhostMode());
JButton protectionButton = createStealthButton("🛡️", "Toggle Protection");
protectionButton.addActionListener(e -> {
    ScreenshotProtection.toggleProtection(mainWindow);
    statusLabel.setText(ScreenshotProtection.isProtected(mainWindow) 
        ? " Protected from capture" 
        : " Protection disabled");
});
controlPanel.add(protectionButton);
        JButton closeButton = createStealthButton("×", "Exit");
        closeButton.addActionListener(e -> secureExit());

        controlPanel.add(screenshotButton);
        controlPanel.add(multiCaptureButton);
        controlPanel.add(copyButton);
        controlPanel.add(ghostButton);
        controlPanel.add(closeButton);

        titleBar.add(controlPanel, BorderLayout.EAST);
        return titleBar;
    }

    private static void toggleMultipleCaptureMode() {
        isMultipleCaptureMode = !isMultipleCaptureMode;
        capturedImages.clear();
        if (isMultipleCaptureMode) {
            statusLabel.setText(" Multiple capture mode: Use 📷 to capture, Send to process");
            sendButton = createStealthButton(">>>", "Send Captures");
            sendButton.addActionListener(e -> sendMultipleScreenshots());
            controlPanel.add(sendButton, 2);
            controlPanel.revalidate();
            controlPanel.repaint();
        } else {
            statusLabel.setText(" Multiple capture mode disabled");
            if (sendButton != null) {
                controlPanel.remove(sendButton);
                controlPanel.revalidate();
                controlPanel.repaint();
                sendButton = null;
            }
        }
    }

    private static void captureSingleScreenshotForMultiple() {
        CompletableFuture.runAsync(() -> {
            try {
                statusLabel.setText(" Capturing screenshot...");
                SwingUtilities.invokeAndWait(() -> mainWindow.setVisible(false));
                Thread.sleep(500);
                BufferedImage screenshot = captureWithStealth();
                SwingUtilities.invokeAndWait(() -> mainWindow.setVisible(true));
                capturedImages.add(screenshot);
                SwingUtilities.invokeLater(() -> statusLabel.setText(" Captured " + capturedImages.size() + " screenshot(s)"));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> statusLabel.setText(" Capture error: " + e.getMessage()));
            }
        });
    }

    private static void sendMultipleScreenshots() {
        if (capturedImages.isEmpty()) {
            statusLabel.setText(" No screenshots captured");
            return;
        }

        CompletableFuture.runAsync(() -> {
            try {
                statusLabel.setText(" Processing " + capturedImages.size() + " screenshots...");
                StringBuilder combinedResponse = new StringBuilder();
                for (int i = 0; i < capturedImages.size(); i++) {
                    BufferedImage screenshot = capturedImages.get(i);
                    String base64Image = encodeImageToBase64(screenshot);
                    String response = sendSecureImageToGemini(base64Image);
                    String responseText = extractResponseText(response);
                    combinedResponse.append("### Screenshot ").append(i + 1).append("\n\n").append(responseText).append("\n\n");
                }

                String finalResponse = combinedResponse.toString();
                SwingUtilities.invokeLater(() -> {
                    currentResponse = finalResponse;
                    markdownArea.setText(currentResponse);
                    updateSecureHtmlPreview(currentResponse);
                    updateSecureCodeBlocks(currentResponse);
                    statusLabel.setText(" Analysis of " + capturedImages.size() + " screenshots complete");
                    toggleMultipleCaptureMode();
                });

            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> statusLabel.setText(" Error: " + e.getMessage()));
            }
        });
    }

    private static JPanel createPromptPanel() {
        JPanel promptPanel = new JPanel(new BorderLayout());
        promptPanel.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        promptPanel.setBackground(new Color(25, 25, 25, 150));

        JLabel promptLabel = new JLabel("Query: ");
        promptLabel.setForeground(new Color(140, 140, 140));
        promptLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));

        promptArea = new JTextArea(getDefaultPrompt());
        promptArea.setFont(new Font("Consolas", Font.PLAIN, 11));
        promptArea.setBackground(new Color(40, 40, 40));
        promptArea.setForeground(new Color(200, 200, 200));
        promptArea.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));
        promptArea.setLineWrap(true);
        promptArea.setWrapStyleWord(true);

        JScrollPane promptScrollPane = new JScrollPane(promptArea);
        promptScrollPane.setPreferredSize(new Dimension(200, 50));

        promptPanel.add(promptLabel, BorderLayout.WEST);
        promptPanel.add(promptScrollPane, BorderLayout.CENTER);
        return promptPanel;
    }

    private static String getDefaultPrompt() {
        return "Analyze the image carefully. Your task is to:\n\n" +
        	    "1. Detect and recognize any **questions** present in the image. This includes:\n" +
        	    "   - WH-questions: Who, What, Where, When, Why, Which, Whose, Whom, How\n" +
        	    "   - Yes/No questions: Is, Are, Was, Were, Will, Do, Does, Did, Can, Could, Would, Should\n\n" +
        	    "2. If **any question** is found:\n" +
        	    "   - Provide **only the correct and short answer**.\n" +
        	    "   - Format the answer in **Markdown**.\n" +
        	    "   - You may use headers, lists, or code blocks if the answer is structured.\n" +
        	    "   - **Do NOT** repeat the question or explain the answer.\n\n" +
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
                    statusLabel.setText("Failed to open link: " + ex.getMessage());
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

                 if (!ScreenshotProtection.isProtected(mainWindow)) {
                ScreenshotProtection.applyFullProtection(mainWindow);
                statusLabel.setText(" Protection restored");
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
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
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
            }
        } catch (Exception e) {
            // Return false on error
        }
        return false;
    }

    private static void initiateEmergencyProtocol() {
        SwingUtilities.invokeLater(() -> {
            mainWindow.setVisible(false);
            isHidden = true;
            statusLabel.setText(" Security Protocol Active");
            if (markdownArea != null) markdownArea.setText("");
            if (promptArea != null) promptArea.setText("");
        });

        Timer emergencyTimer = new Timer(60000, e -> {
            if (!detectSecurityTools()) {
                SwingUtilities.invokeLater(() -> {
                    mainWindow.setVisible(true);
                    isHidden = false;
                    statusLabel.setText(" Mode Active");
                });
            }
        });
        emergencyTimer.setRepeats(false);
        emergencyTimer.start();
    }

    private static void performStealthScreenshot() {
        CompletableFuture.runAsync(() -> {
            try {
                statusLabel.setText(" Initiating capture...");
                SwingUtilities.invokeAndWait(() -> mainWindow.setVisible(false));
                Thread.sleep(500);
                BufferedImage screenshot = captureWithStealth();
                SwingUtilities.invokeAndWait(() -> mainWindow.setVisible(true));
                String base64Image = encodeImageToBase64(screenshot);
                statusLabel.setText(" Processing...");
                String response = sendSecureImageToGemini(base64Image);
                String responseText = extractResponseText(response);
                SwingUtilities.invokeLater(() -> {
                    currentResponse = responseText;
                    markdownArea.setText(currentResponse);
                    updateSecureHtmlPreview(currentResponse);
                    updateSecureCodeBlocks(currentResponse);
                    statusLabel.setText(" Analysis complete");
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> statusLabel.setText(" Error: " + e.getMessage()));
            }
        });
    }

    private static String encodeImageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        Arrays.fill(imageBytes, (byte) 0);
        baos.reset();
        return base64Image;
    }

    private static BufferedImage captureWithStealth() throws AWTException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenshot = robot.createScreenCapture(screenRect);
        Graphics2D g2d = screenshot.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.001f));
        g2d.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        g2d.fillRect(0, 0, 1, 1);
        g2d.dispose();
        return screenshot;
    }

    private static String sendSecureImageToGemini(String base64Image) throws Exception {
        long timeoutSeconds = Long.parseLong(System.getProperty("gemini.api.timeout", "30"));
        HttpClient client = HttpClient.newBuilder()
            .connectTimeout(java.time.Duration.ofSeconds(timeoutSeconds))
            .build();

        String customPrompt = promptArea.getText().isEmpty() ? getDefaultPrompt() : promptArea.getText();

        String requestBody = String.format(
            "{\"contents\":[{\"parts\":[{\"text\":\"%s\"},{\"inlineData\":{\"mimeType\":\"image/png\",\"data\":\"%s\"}}]}]}",
            customPrompt.replace("\"", "\\\"").replace("\n", "\\n"),
            base64Image
        );

        String url = GEMINI_API_URL + "?key=" + apiKey;
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
        statusLabel.setText(isStealthMode ? " Deep stealth activated" : " Normal mode active");
    }

    private static void toggleGhostMode() {
        if (mainWindow.getOpacity() > 0.1f) {
            mainWindow.setOpacity(0.05f);
            mainWindow.setFocusableWindowState(false);
            statusLabel.setText(" Ghost mode - Nearly invisible");
        } else {
            mainWindow.setOpacity(opacity);
            mainWindow.setFocusableWindowState(true);
            statusLabel.setText(" Ghost mode disabled");
        }
    }

    private static void secureClipboardCopy() {
        try {
            String textToCopy = markdownArea.getText();
            StringSelection selection = new StringSelection(textToCopy);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            statusLabel.setText(" Content copied to clipboard");
        } catch (Exception e) {
            statusLabel.setText(" Copy failed: " + e.getMessage());
        }
    }

    private static void secureExit() {
        try {
            if (markdownArea != null) markdownArea.setText("");
            if (promptArea != null) promptArea.setText("");
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
        return PROCESS_NAMES[new Random().nextInt(PROCESS_NAMES.length)];
    }

    private static void obfuscateProcessName() {
        currentProcessName = getRandomProcessName();
    }

    private static void updateProcessName() {
        currentProcessName = getRandomProcessName();
    }

    private static void hideFromTaskManager() {
        // Placeholder: Native code required for full Task Manager hiding
        // Consider compiling with GraalVM to avoid java.exe
        statusLabel.setText("Task Manager hiding not fully supported in Java");
    }

    private static void cleanMemoryFootprint() {
        System.gc();
        Runtime.getRuntime().runFinalization();
    }

    private static boolean isDebuggingDetected() {
        return ManagementFactory.getRuntimeMXBean().getInputArguments().stream()
            .anyMatch(arg -> arg.contains("-agentlib:jdwp") || arg.contains("-Xdebug"));
    }

    private static boolean isVirtualMachineDetected() {
        String[] vmIndicators = {"VMware", "VirtualBox", "QEMU", "Xen", "Microsoft Corporation", "Oracle Corporation"};
        String vendor = System.getProperty("java.vm.vendor", "");
        String name = System.getProperty("java.vm.name", "");
        return Arrays.stream(vmIndicators).anyMatch(indicator ->
            vendor.contains(indicator) || name.contains(indicator));
    }

    private static void startBackgroundMonitoring() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (!mainWindow.isFocused() && !isHidden) {
                    SwingUtilities.invokeLater(() -> {
                        if (mainWindow.getOpacity() > 0.3f) {
                            mainWindow.setOpacity(0.15f);
                        }
                        if (!isFocused) mainWindow.setFocusableWindowState(false);
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
        mainWindow.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                if (isStealthMode && !isFocused) {
                    SwingUtilities.invokeLater(() -> mainWindow.setOpacity(0.1f));
                }
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                SwingUtilities.invokeLater(() -> mainWindow.setOpacity(opacity));
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
            codePanels.add(createSecureCodePanel(language, code));
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
                new Timer(1000, evt -> copyButton.setText("📋")).start();
            } catch (Exception ex) {
                copyButton.setText("✗");
                new Timer(1000, evt -> copyButton.setText("📋")).start();
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
            statusLabel.setText(" Code saved: " + filename);
        } catch (Exception e) {
            statusLabel.setText(" Save failed: " + e.getMessage());
        }
    }

    private static String getFileExtension(String language) {
        if (language == null) return ".txt";

        String lang = language.toLowerCase();
        switch (lang) {
            case "java":
                return ".java";
            case "python":
            case "py":
                return ".py";
            case "javascript":
            case "js":
                return ".js";
            case "html":
                return ".html";
            case "css":
                return ".css";
            case "cpp":
            case "c++":
                return ".cpp";
            case "c":
                return ".c";
            case "cs":
            case "csharp":
                return ".cs";
            case "php":
                return ".php";
            case "ruby":
            case "rb":
                return ".rb";
            case "go":
                return ".go";
            case "rust":
            case "rs":
                return ".rs";
            case "sql":
                return ".sql";
            case "xml":
                return ".xml";
            case "json":
                return ".json";
            case "yaml":
            case "yml":
                return ".yml";
            case "bash":
            case "sh":
                return ".sh";
            case "powershell":
            case "ps1":
                return ".ps1";
            default:
                return ".txt";
        }
    }


    private static String convertMarkdownToSecureHtml(String markdown) {
        if (markdown == null) {
            return "<html><body style='background-color:#1e1e1e;color:#e0e0e0;'><p>No content available</p></body></html>";
        }

        StringBuilder html = new StringBuilder("<html><body style='background-color:#1e1e1e;color:#e0e0e0;font-family:Segoe UI,Arial,sans-serif;line-height:1.6;'>");

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
                language.isEmpty() ? "Code" : language.toUpperCase(), code
            );
            codeMatcher.appendReplacement(codeBuffer, Matcher.quoteReplacement(codeHtml));
        }
        codeMatcher.appendTail(codeBuffer);
        markdown = codeBuffer.toString();

        markdown = markdown.replaceAll("`([^`]+)`", "<code style='background-color:#2d2d2d;padding:2px 4px;border-radius:3px;font-family:JetBrains Mono,Consolas,monospace;'>$1</code>")
            .replaceAll("(?m)^# (.+)$", "<h1 style='color:#4a9eff;border-bottom:2px solid #404040;padding-bottom:5px;'>$1</h1>")
            .replaceAll("(?m)^## (.+)$", "<h2 style='color:#4a9eff;border-bottom:1px solid #404040;padding-bottom:3px;'>$1</h2>")
            .replaceAll("(?m)^### (.+)$", "<h3 style='color:#4a9eff;'>$1</h3>")
            .replaceAll("(?m)^#### (.+)$", "<h4 style='color:#4a9eff;'>$1</h4>")
            .replaceAll("\\*\\*([^*]+)\\*\\*", "<strong>$1</strong>")
            .replaceAll("\\*([^*]+)\\*", "<em>$1</em>")
            .replaceAll("__([^_]+)__", "<strong>$1</strong>")
            .replaceAll("_([^_]+)_", "<em>$1</em>")
            .replaceAll("\\[([^\\]]+)\\]\\(([^)]+)\\)", "<a href='$2' style='color:#4a9eff;text-decoration:underline;'>$1</a>");

        markdown = processLists(markdown);
        markdown = processParagraphs(markdown);

        html.append(markdown).append("</body></html>");
        return html.toString();
    }

    private static String escapeHtml(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
            .replace("\"", "&quot;").replace("'", "&#39;");
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
        if (inUl) ulBuffer.append("</ul>");
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
        if (inOl) olBuffer.append("</ol>");
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
                if (inParagraph && line.endsWith("</p>")) inParagraph = false;
            }
        }
        if (inParagraph) result.append("</p>");
        return result.toString();
    }

    private static String extractResponseText(String jsonResponse) {
        try {
            Pattern pattern = Pattern.compile("\"text\"\\s*:\\s*\"((?:\\\\\"|[^\"])*?)\"");
            Matcher matcher = pattern.matcher(jsonResponse);
            if (matcher.find()) {
                return matcher.group(1).replace("\\n", "\n")
                    .replace("\\\"", "\"").replace("\\\\", "\\")
                    .replace("\\t", "\t").replace("\\r", "\r")
                    .replace("\\b", "\b").replace("\\f", "\f");
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
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                isFocused = true;
                mainWindow.setFocusableWindowState(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isFocused = false;
                mainWindow.setFocusableWindowState(false);
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = mainWindow.getLocation().x;
                int thisY = mainWindow.getLocation().y;
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                int X = thisX + xMoved;
                int Y = thisY + yMoved;

                Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                X = Math.max(0, Math.min(X, screenBounds.width - mainWindow.getWidth()));
                Y = Math.max(0, Math.min(Y, screenBounds.height - mainWindow.getHeight()));
                mainWindow.setLocation(X, Y);
            }
        });
    }

    private static void makeTranslucent(float opacity) {
        mainWindow.setOpacity(Math.max(0.05f, Math.min(1.0f, opacity)));
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

    private static void storeApiKey(String key) {
        try {
            Path configDir = Paths.get(System.getProperty("user.home"), ".stealth_assistant");
            Files.createDirectories(configDir);
            Path configPath = configDir.resolve(CONFIG_FILE);
            String encodedKey = Base64.getEncoder().encodeToString(key.getBytes());
            Files.write(configPath, List.of(encodedKey));
            File file = configPath.toFile();
            file.setReadable(false, false);
            file.setReadable(true, true);
            file.setWritable(false, false);
            file.setWritable(true, true);
        } catch (Exception e) {
            System.err.println("Failed to store API key: " + e.getMessage());
        }
    }

    private static String promptForApiKey() {
        JDialog dialog = new JDialog((Frame) null, "Stealth Assistant - First Time Setup", true);
        dialog.setSize(450, 250);
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
            "This application captures screenshots and sends them to the Google Gemini API for analysis.<br>" +
            "By continuing, you consent to this data processing.<br><br>" +
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

        cancelButton.addActionListener(e -> dialog.dispose());

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