package API;

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
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class AdvancedStealthAssistant1 {
    private static JWindow frame;
    private static float opacity = 0.3f;
    private static Point initialClick;
    private static final Dimension NORMAL_SIZE = new Dimension(500, 400);
    private static final Dimension MINIMIZED_SIZE = new Dimension(15, 15);
    private static final String API_KEY = "AIzaSyDaa5ZGb7kRHknvtAXrW8ppbSF86t-CTOs";
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private static JTextArea markdownArea;
    private static JEditorPane htmlPreview;
    private static JPanel codeBlocksPanel;
    private static String currentResponse = "";
    private static JLabel statusBar;
    private static JTextField promptField;
    private static ScheduledExecutorService scheduler;
    private static Robot robot;
    private static boolean isStealthMode = true;
    private static boolean isHidden = false;

    // Common system process names for obfuscation
    private static final String[] PROCESS_NAMES = {
        "svchost", "dwm", "winlogon", "csrss", "lsass", "explorer", "dllhost",
        "conhost", "taskhostw", "RuntimeBroker", "spoolsv", "wininit", "fontdrvhost"
    };
    private static String currentProcessName = getRandomProcessName();

    // Security and interview platform process detection
    private static final Set<String> SECURITY_PROCESSES = new HashSet<>(Arrays.asList(
        "procmon", "procexp", "taskmgr", "wireshark", "fiddler", "cheatengine", "ollydbg",
        "x64dbg", "ida", "vmware", "virtualbox", "sandboxie", "avp", "mcshield", "windefend"
    ));
    private static final Set<String> INTERVIEW_PLATFORMS = new HashSet<>(Arrays.asList(
        "zoom", "msedge", "msteams", "chrome", "firefox", "skype", "webex"
    ));

    static {
        try {
            robot = new Robot();
            scheduler = Executors.newScheduledThreadPool(3);
            initializeStealthFeatures();
        } catch (AWTException e) {
            System.err.println("Initialization failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            System.err.println("API Key missing.");
            System.exit(1);
        }

        try {
            if (isDebuggingDetected() || isVirtualMachineDetected()) {
                System.exit(0); // Exit if debuggers or VMs are detected
            }
            obfuscateProcessName();
            startSecurityAndPlatformMonitoring();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(0);
        }

        SwingUtilities.invokeLater(AdvancedStealthAssistant1::createStealthGUI);
    }

//    ### GUI Creation
    private static void createStealthGUI() {
        frame = new JWindow();
        frame.setSize(NORMAL_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setType(Window.Type.UTILITY); // Avoids Taskbar visibility
        frame.setFocusable(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60, 100), 1));
        mainPanel.setBackground(new Color(20, 20, 20, 200));

        makeTranslucent(opacity);
        addAdvancedDragCapability(mainPanel);

        mainPanel.add(createStealthTitleBar(), BorderLayout.NORTH);
        mainPanel.add(createPromptPanel(), BorderLayout.SOUTH);
        mainPanel.add(createAdvancedTabbedPane(), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        statusBar = createSecurityStatusBar();
        southPanel.add(statusBar, BorderLayout.CENTER);

        JLabel resizeCorner = new JLabel("◢");
        resizeCorner.setForeground(new Color(100, 100, 100));
        resizeCorner.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        resizeCorner.setHorizontalAlignment(SwingConstants.RIGHT);
        resizeCorner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        resizeCorner.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
        southPanel.add(resizeCorner, BorderLayout.EAST);

        addResizeListener(resizeCorner);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        initializeStealthBehaviors();
        startBackgroundMonitoring();
    }

//    ### Stealth Title Bar
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

        controlPanel.add(createStealthButton("c", "Capture", e -> performStealthScreenshot()));
        controlPanel.add(createStealthButton("👻", "Stealth Mode", e -> toggleStealthMode()));
        controlPanel.add(createStealthButton("🫥", "Deep Hide", e -> activateDeepHide()));
        controlPanel.add(createStealthButton("📋", "Copy", e -> secureClipboardCopy()));
        controlPanel.add(createStealthButton("👤", "Ghost", e -> toggleGhostMode()));
        controlPanel.add(createStealthButton("×", "Exit", e -> secureExit()));

        titleBar.add(controlPanel, BorderLayout.EAST);
        return titleBar;
    }

//    ### Prompt Panel
    private static JPanel createPromptPanel() {
        JPanel promptPanel = new JPanel(new BorderLayout());
        promptPanel.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        promptPanel.setBackground(new Color(25, 25, 25, 150));

        JLabel promptLabel = new JLabel("Query: ");
        promptLabel.setForeground(new Color(140, 140, 140));
        promptLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));

        promptField = new JTextField("Analyze the image to detect any questions (e.g., 'What ...?', 'Who ...?', 'Where ...?'). If a question is found, provide only the answer in Markdown format without extra context. If no question is found, respond with: 'No question detected in the image. If code is present, identify issues, explain fixes, and show expected output.'");
        promptField.setFont(new Font("Consolas", Font.PLAIN, 11));
        promptField.setBackground(new Color(40, 40, 40));
        promptField.setForeground(new Color(200, 200, 200));
        promptField.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));

        promptPanel.add(promptLabel, BorderLayout.WEST);
        promptPanel.add(promptField, BorderLayout.CENTER);
        return promptPanel;
    }

//    ### Tabbed Pane
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

        tabbedPane.addTab("Source", new JScrollPane(markdownArea));

        htmlPreview = new JEditorPane();
        htmlPreview.setEditable(false);
        htmlPreview.setContentType("text/html");
        htmlPreview.setBackground(new Color(30, 30, 30));
        htmlPreview.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        HTMLEditorKit kit = new HTMLEditorKit();
        htmlPreview.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("body { font-family: 'Segoe UI', sans-serif; margin: 8px; background-color: #1e1e1e; color: #e0e0e0; }");
        styleSheet.addRule("pre, code { background-color: #2d2d2d; padding: 6px; font-family: 'JetBrains Mono', monospace; border-radius: 4px; }");

        tabbedPane.addTab("Preview", new JScrollPane(htmlPreview));

        codeBlocksPanel = new JPanel();
        codeBlocksPanel.setLayout(new BoxLayout(codeBlocksPanel, BoxLayout.Y_AXIS));
        codeBlocksPanel.setBackground(new Color(28, 28, 28));
        tabbedPane.addTab("Code", new JScrollPane(codeBlocksPanel));

        return tabbedPane;
    }

//    ### Status Bar
    private static JLabel createSecurityStatusBar() {
        JLabel statusBar = new JLabel(" Mode Active");
        statusBar.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        statusBar.setOpaque(true);
        statusBar.setBackground(new Color(20, 40, 20));
        statusBar.setForeground(new Color(120, 200, 120));
        return statusBar;
    }

//    ### Stealth Features
    private static void initializeStealthFeatures() {
        System.setProperty("java.awt.headless", "false");
        if (frame != null) {
            frame.setType(Window.Type.UTILITY);
            frame.setFocusableWindowState(false);
        }
        hideFromTaskManager();
    }

    private static void startSecurityAndPlatformMonitoring() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (detectSecurityTools() || detectInterviewPlatforms()) {
                    initiateEmergencyProtocol();
                }
                updateProcessName();
                cleanMemoryFootprint();
            } catch (Exception ignored) {}
        }, 5, 10, TimeUnit.SECONDS);
    }

    private static boolean detectSecurityTools() {
        return checkRunningProcesses(SECURITY_PROCESSES);
    }

    private static boolean detectInterviewPlatforms() {
        return checkRunningProcesses(INTERVIEW_PLATFORMS);
    }

    private static boolean checkRunningProcesses(Set<String> processSet) {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Process proc = Runtime.getRuntime().exec("tasklist /fo csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    String lowerLine = line.toLowerCase();
                    for (String process : processSet) {
                        if (lowerLine.contains(process)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception ignored) {}
        return false;
    }

    private static void initiateEmergencyProtocol() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(false);
            frame.setOpacity(0.0f);
            isHidden = true;
            statusBar.setText(" Hidden - Interview/Security Detected");
        });
        Timer timer = new Timer(30000, e -> {
            if (!detectSecurityTools() && !detectInterviewPlatforms()) {
                SwingUtilities.invokeLater(() -> {
                    frame.setVisible(true);
                    frame.setOpacity(opacity);
                    isHidden = false;
                    statusBar.setText(" Mode Active");
                });
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

//    ### Screenshot and API Integration
    private static void performStealthScreenshot() {
        CompletableFuture.runAsync(() -> {
            try {
                statusBar.setText(" Capturing...");
                frame.setVisible(false);
                Thread.sleep(200);
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
                    statusBar.setText(" Analysis Complete");
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> statusBar.setText(" Error: " + e.getMessage()));
            }
        });
    }

    private static BufferedImage captureWithStealth() throws AWTException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(screenRect);
    }

    private static String encodeImageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    private static String sendSecureImageToGemini(String base64Image) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        String prompt = promptField.getText().isEmpty() ? "Analyze the image..." : promptField.getText();
        String requestBody = String.format(
            "{\"contents\":[{\"parts\":[{\"text\":\"%s\"},{\"inlineData\":{\"mimeType\":\"image/png\",\"data\":\"%s\"}}]}]}",
            prompt.replace("\"", "\\\""), base64Image
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(GEMINI_API_URL + "?key=" + API_KEY))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) throw new IOException("API Error: " + response.statusCode());
        return response.body();
    }

    private static String extractResponseText(String jsonResponse) {
        Pattern pattern = Pattern.compile("\"text\"\\s*:\\s*\"((?:\\\\\"|[^\"])*?)\"");
        Matcher matcher = pattern.matcher(jsonResponse);
        if (matcher.find()) {
            return matcher.group(1).replace("\\n", "\n").replace("\\\"", "\"");
        }
        return "No response text found.";
    }

//    ### Stealth Modes
    private static void toggleStealthMode() {
        isStealthMode = !isStealthMode;
        opacity = isStealthMode ? 0.2f : 0.8f;
        makeTranslucent(opacity);
        statusBar.setText(isStealthMode ? " Stealth Activated" : " Normal Mode");
    }

    private static void activateDeepHide() {
        frame.setVisible(false);
        isHidden = true;
        statusBar.setText(" Deep Hide - Move mouse to top-left to restore");

        Timer hideTimer = new Timer(100, e -> {
            Point mouse = MouseInfo.getPointerInfo().getLocation();
            if (mouse.x == 0 && mouse.y == 0) {
                frame.setVisible(true);
                isHidden = false;
                statusBar.setText(" Restored");
                ((Timer) e.getSource()).stop();
            }
        });
        hideTimer.start();
    }

    private static void toggleGhostMode() {
        frame.setOpacity(frame.getOpacity() > 0.1f ? 0.05f : 0.7f);
        frame.setFocusableWindowState(frame.getOpacity() > 0.1f);
        statusBar.setText(frame.getOpacity() > 0.1f ? " Ghost Mode" : " Ghost Disabled");
    }

//    ### Utility Functions
    private static void secureClipboardCopy() {
        try {
            StringSelection selection = new StringSelection(markdownArea.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            statusBar.setText(" Copied to Clipboard");
        } catch (Exception e) {
            statusBar.setText(" Copy Failed: " + e.getMessage());
        }
    }

    private static void secureExit() {
        try {
            markdownArea.setText("");
            promptField.setText("");
            scheduler.shutdownNow();
            System.gc();
        } finally {
            System.exit(0);
        }
    }

    private static String getRandomProcessName() {
        return PROCESS_NAMES[new Random().nextInt(PROCESS_NAMES.length)];
    }

    private static void obfuscateProcessName() {
        System.setProperty("sun.java.command", currentProcessName);
    }

    private static void updateProcessName() {
        currentProcessName = getRandomProcessName();
        obfuscateProcessName();
    }

    private static void hideFromTaskManager() {
        System.setProperty("java.awt.Window.locationByPlatform", "true");
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
        String[] vmIndicators = {"VMware", "VirtualBox", "QEMU"};
        String vendor = System.getProperty("java.vm.vendor", "");
        return Arrays.stream(vmIndicators).anyMatch(vendor::contains);
    }

    private static void startBackgroundMonitoring() {
        scheduler.scheduleAtFixedRate(() -> {
            if (!frame.isFocused() && !isHidden) {
                SwingUtilities.invokeLater(() -> frame.setOpacity(0.15f));
            }
            cleanMemoryFootprint();
        }, 10, 5, TimeUnit.SECONDS);
    }

    private static void initializeStealthBehaviors() {
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                if (isStealthMode) frame.setOpacity(0.1f);
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                frame.setOpacity(opacity);
            }
        });
    }

//    ### HTML and Code Block Updates
    private static void updateSecureHtmlPreview(String markdown) {
        SwingUtilities.invokeLater(() -> {
            String html = "<html><body style='background-color:#1e1e1e;color:#e0e0e0;'>" + markdown.replace("\n", "<br>") + "</body></html>";
            htmlPreview.setText(html);
        });
    }

    private static void updateSecureCodeBlocks(String markdown) {
        codeBlocksPanel.removeAll();
        Pattern pattern = Pattern.compile("```([a-zA-Z0-9+#-]*)?\\n([\\s\\S]*?)\\n```", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(markdown);

        while (matcher.find()) {
            String language = matcher.group(1);
            String code = matcher.group(2).trim();
            codeBlocksPanel.add(createSecureCodePanel(language, code));
        }

        codeBlocksPanel.revalidate();
        codeBlocksPanel.repaint();
    }

    private static JPanel createSecureCodePanel(String language, String code) {
        JPanel codePanel = new JPanel(new BorderLayout());
        codePanel.setBackground(new Color(35, 35, 35));
        codePanel.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 1));

        JLabel languageLabel = new JLabel(language != null ? language.toUpperCase() : "CODE");
        languageLabel.setForeground(new Color(100, 150, 255));

        JTextArea codeArea = new JTextArea(code);
        codeArea.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        codeArea.setBackground(new Color(25, 25, 25));
        codeArea.setForeground(new Color(220, 220, 220));
        codeArea.setEditable(false);

        codePanel.add(languageLabel, BorderLayout.NORTH);
        codePanel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return codePanel;
    }

//    ### Event Handlers
    private static JButton createStealthButton(String text, String tooltip, ActionListener action) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(22, 22));
        button.setToolTipText(tooltip);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(new Color(180, 180, 180));
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 10));
        button.addActionListener(action);

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
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = frame.getLocation().x + e.getX() - initialClick.x;
                int y = frame.getLocation().y + e.getY() - initialClick.y;
                frame.setLocation(x, y);
            }
        });
    }

    private static void addResizeListener(JLabel resizeCorner) {
        resizeCorner.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        resizeCorner.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int newWidth = frame.getWidth() + e.getX() - initialClick.x;
                int newHeight = frame.getHeight() + e.getY() - initialClick.y;
                frame.setSize(Math.max(300, newWidth), Math.max(200, newHeight));
            }
        });
    }

    private static void makeTranslucent(float opacity) {
        frame.setOpacity(Math.max(0.05f, Math.min(1.0f, opacity)));
    }
}