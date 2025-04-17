package automate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GmailChromeAutomation extends JFrame {
    private JTextField recipientsField;
    private JTextField subjectField;
    private JTextArea bodyArea;
    private JButton attachmentButton;
    private JButton sendButton;
    private JLabel statusLabel;
    private JLabel attachmentLabel;
    private File selectedFile;
    private Robot robot;
    private JTextField chromePathField;
    private JProgressBar progressBar;
    
    // UI controls for automation settings
    private JSpinner composeXSpinner;
    private JSpinner composeYSpinner;
    private JSpinner attachmentXSpinner;
    private JSpinner attachmentYSpinner;
    private JSpinner sendXSpinner;
    private JSpinner sendYSpinner;
    private JCheckBox useKeyboardShortcutCheckbox;
    private JButton calibrateButton;
    private JButton testComposeButton;
    private JButton testAttachButton;
    private JButton testSendButton;
    
    // Settings for automation
    private int delayBetweenActions = 1000; // milliseconds
    private int delayAfterSend = 3000; // milliseconds
    private int delayForGmailLoad = 8000; // milliseconds
    private AtomicBoolean stopRequested = new AtomicBoolean(false);
    private JButton stopButton;

    public GmailChromeAutomation() {
        super("Gmail Chrome Automation Tool");
        initUI();
        initRobot();
    }

    private void initRobot() {
        try {
            robot = new Robot();
            robot.setAutoDelay(50);
        } catch (AWTException e) {
            JOptionPane.showMessageDialog(this, "Robot initialization failed: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        // Create tabbed pane for organization
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Basic settings panel
        JPanel basicPanel = createBasicPanel();
        tabbedPane.addTab("Basic Settings", basicPanel);
        
        // Advanced settings panel
        JPanel advancedPanel = createAdvancedPanel();
        tabbedPane.addTab("Advanced Settings", advancedPanel);
        
        // Calibration panel
        JPanel calibrationPanel = createCalibrationPanel();
        tabbedPane.addTab("Calibration", calibrationPanel);
        
        // Status panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel(" ");
        statusPanel.add(statusLabel, BorderLayout.NORTH);
        
        // Progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        statusPanel.add(progressBar, BorderLayout.CENTER);
        
        // Control buttons panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sendButton = new JButton("Start Automation");
        sendButton.addActionListener(e -> new Thread(this::sendEmails).start());
        
        stopButton = new JButton("Stop Automation");
        stopButton.addActionListener(e -> stopRequested.set(true));
        stopButton.setEnabled(false);
        
        controlPanel.add(sendButton);
        controlPanel.add(stopButton);
        statusPanel.add(controlPanel, BorderLayout.SOUTH);
        
        add(tabbedPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        
        // Load saved coordinates if available
        loadSavedCoordinates();
    }
    
    private JPanel createBasicPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Chrome Path
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Chrome Path:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        chromePathField = new JTextField(getDefaultChromePath());
        JPanel chromePanel = new JPanel(new BorderLayout());
        chromePanel.add(chromePathField, BorderLayout.CENTER);
        JButton browseChromeButton = new JButton("Browse");
        browseChromeButton.addActionListener(e -> selectChromePath());
        chromePanel.add(browseChromeButton, BorderLayout.EAST);
        panel.add(chromePanel, gbc);

        // Recipients
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Recipients (comma separated):"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        recipientsField = new JTextField(30);
        panel.add(recipientsField, gbc);

        // Subject
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Subject:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        subjectField = new JTextField(30);
        panel.add(subjectField, gbc);

        // Body
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Body:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        bodyArea = new JTextArea(10, 30);
        bodyArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        panel.add(scrollPane, gbc);
        gbc.weighty = 0.0;

        // Attachment
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JLabel("Attachment:"), gbc);
        
        gbc.gridx = 1;
        JPanel attachmentPanel = new JPanel(new BorderLayout());
        attachmentLabel = new JLabel("No file selected");
        attachmentButton = new JButton("Browse");
        attachmentButton.addActionListener(e -> selectAttachment());
        attachmentPanel.add(attachmentLabel, BorderLayout.CENTER);
        attachmentPanel.add(attachmentButton, BorderLayout.EAST);
        panel.add(attachmentPanel, gbc);
        
        return panel;
    }
    
    private String getDefaultChromePath() {
        // Check common Chrome installation locations based on OS
        String os = System.getProperty("os.name").toLowerCase();
        
        if (os.contains("win")) {
            // Windows paths
            String[] commonPaths = {
                "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"
            };
            
            for (String path : commonPaths) {
                if (new File(path).exists()) {
                    return path;
                }
            }
            return "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        } else if (os.contains("mac")) {
            // macOS
            return "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
        } else {
            // Linux and others
            return "/usr/bin/google-chrome";
        }
    }
    
    private JPanel createAdvancedPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Controls for timing delays
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Delay between actions (ms):"), gbc);
        
        gbc.gridx = 1;
        JSpinner delaySpinner = new JSpinner(new SpinnerNumberModel(delayBetweenActions, 100, 10000, 100));
        delaySpinner.addChangeListener(e -> delayBetweenActions = (Integer) delaySpinner.getValue());
        panel.add(delaySpinner, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Delay after sending email (ms):"), gbc);
        
        gbc.gridx = 1;
        JSpinner afterSendSpinner = new JSpinner(new SpinnerNumberModel(delayAfterSend, 500, 10000, 500));
        afterSendSpinner.addChangeListener(e -> delayAfterSend = (Integer) afterSendSpinner.getValue());
        panel.add(afterSendSpinner, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Gmail load delay (ms):"), gbc);
        
        gbc.gridx = 1;
        JSpinner gmailLoadSpinner = new JSpinner(new SpinnerNumberModel(delayForGmailLoad, 1000, 30000, 1000));
        gmailLoadSpinner.addChangeListener(e -> delayForGmailLoad = (Integer) gmailLoadSpinner.getValue());
        panel.add(gmailLoadSpinner, gbc);
        
        // Keyboard vs. Mouse option
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Automation Method:"), gbc);
        
        gbc.gridx = 1;
        useKeyboardShortcutCheckbox = new JCheckBox("Use keyboard shortcuts when possible");
        useKeyboardShortcutCheckbox.setSelected(true);
        useKeyboardShortcutCheckbox.addActionListener(e -> updateUIBasedOnAutomationMethod());
        panel.add(useKeyboardShortcutCheckbox, gbc);
        
        // Helper instructions
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JTextArea helpText = new JTextArea(
            "Automation Tips:\n" +
            "1. Before starting automation, make sure you're already logged into Gmail\n" +
            "2. Keep the application running and don't move mouse during automation\n" +
            "3. Use the Calibration tab to set up your mouse coordinates\n" +
            "4. If automation fails, try increasing the delay values above\n" +
            "5. You can press the 'Stop Automation' button to halt the process"
        );
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setBackground(panel.getBackground());
        panel.add(helpText, gbc);
        
        return panel;
    }
    
    private JPanel createCalibrationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Calibration instruction
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JTextArea calibrationInstructions = new JTextArea(
            "For proper mouse automation, you need to set the exact coordinates of buttons in Gmail:\n" +
            "1. Open Gmail in Chrome first\n" +
            "2. Position Gmail window where it will be during automation\n" +
            "3. For each button, click the corresponding 'Calibrate' button below\n" +
            "4. After 5 seconds, move your mouse to the center of the target button in Gmail\n" +
            "5. The coordinates will be captured automatically\n" +
            "6. You can test each position using the 'Test' buttons"
        );
        calibrationInstructions.setEditable(false);
        calibrationInstructions.setLineWrap(true);
        calibrationInstructions.setWrapStyleWord(true);
        calibrationInstructions.setBackground(panel.getBackground());
        panel.add(calibrationInstructions, gbc);
        
        // Calibration for Compose Button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Compose Button Position:"), gbc);
        
        gbc.gridx = 1;
        JPanel composePanel = new JPanel(new GridBagLayout());
        GridBagConstraints innerGbc = new GridBagConstraints();
        innerGbc.fill = GridBagConstraints.HORIZONTAL;
        
        innerGbc.gridx = 0;
        innerGbc.gridy = 0;
        composePanel.add(new JLabel("X:"), innerGbc);
        
        innerGbc.gridx = 1;
        composeXSpinner = new JSpinner(new SpinnerNumberModel(120, 0, 3000, 1));
        composePanel.add(composeXSpinner, innerGbc);
        
        innerGbc.gridx = 2;
        composePanel.add(new JLabel("Y:"), innerGbc);
        
        innerGbc.gridx = 3;
        composeYSpinner = new JSpinner(new SpinnerNumberModel(200, 0, 2000, 1));
        composePanel.add(composeYSpinner, innerGbc);
        
        innerGbc.gridx = 4;
        JButton calibrateComposeBtn = new JButton("Calibrate");
        calibrateComposeBtn.addActionListener(e -> calibratePosition("Compose Button", composeXSpinner, composeYSpinner));
        composePanel.add(calibrateComposeBtn, innerGbc);
        
        innerGbc.gridx = 5;
        testComposeButton = new JButton("Test");
        testComposeButton.addActionListener(e -> testPosition("Testing compose button", 
                (Integer)composeXSpinner.getValue(), (Integer)composeYSpinner.getValue()));
        composePanel.add(testComposeButton, innerGbc);
        
        panel.add(composePanel, gbc);
        
        // Calibration for Attachment Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Attachment Button Position:"), gbc);
        
        gbc.gridx = 1;
        JPanel attachPanel = new JPanel(new GridBagLayout());
        
        innerGbc.gridx = 0;
        innerGbc.gridy = 0;
        attachPanel.add(new JLabel("X:"), innerGbc);
        
        innerGbc.gridx = 1;
        attachmentXSpinner = new JSpinner(new SpinnerNumberModel(120, 0, 3000, 1));
        attachPanel.add(attachmentXSpinner, innerGbc);
        
        innerGbc.gridx = 2;
        attachPanel.add(new JLabel("Y:"), innerGbc);
        
        innerGbc.gridx = 3;
        attachmentYSpinner = new JSpinner(new SpinnerNumberModel(400, 0, 2000, 1));
        attachPanel.add(attachmentYSpinner, innerGbc);
        
        innerGbc.gridx = 4;
        JButton calibrateAttachBtn = new JButton("Calibrate");
        calibrateAttachBtn.addActionListener(e -> calibratePosition("Attachment Button", attachmentXSpinner, attachmentYSpinner));
        attachPanel.add(calibrateAttachBtn, innerGbc);
        
        innerGbc.gridx = 5;
        testAttachButton = new JButton("Test");
        testAttachButton.addActionListener(e -> testPosition("Testing attachment button", 
                (Integer)attachmentXSpinner.getValue(), (Integer)attachmentYSpinner.getValue()));
        attachPanel.add(testAttachButton, innerGbc);
        
        panel.add(attachPanel, gbc);
        
        // Calibration for Send Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Send Button Position:"), gbc);
        
        gbc.gridx = 1;
        JPanel sendPanel = new JPanel(new GridBagLayout());
        
        innerGbc.gridx = 0;
        innerGbc.gridy = 0;
        sendPanel.add(new JLabel("X:"), innerGbc);
        
        innerGbc.gridx = 1;
        sendXSpinner = new JSpinner(new SpinnerNumberModel(120, 0, 3000, 1));
        sendPanel.add(sendXSpinner, innerGbc);
        
        innerGbc.gridx = 2;
        sendPanel.add(new JLabel("Y:"), innerGbc);
        
        innerGbc.gridx = 3;
        sendYSpinner = new JSpinner(new SpinnerNumberModel(600, 0, 2000, 1));
        sendPanel.add(sendYSpinner, innerGbc);
        
        innerGbc.gridx = 4;
        JButton calibrateSendBtn = new JButton("Calibrate");
        calibrateSendBtn.addActionListener(e -> calibratePosition("Send Button", sendXSpinner, sendYSpinner));
        sendPanel.add(calibrateSendBtn, innerGbc);
        
        innerGbc.gridx = 5;
        testSendButton = new JButton("Test");
        testSendButton.addActionListener(e -> testPosition("Testing send button", 
                (Integer)sendXSpinner.getValue(), (Integer)sendYSpinner.getValue()));
        sendPanel.add(testSendButton, innerGbc);
        
        panel.add(sendPanel, gbc);
        
        // Auto-calibration button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        calibrateButton = new JButton("Auto-Calibrate All Buttons (Sequence)");
        calibrateButton.addActionListener(e -> startSequentialCalibration());
        panel.add(calibrateButton, gbc);
        
        // Save coordinates button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton saveCoordinatesBtn = new JButton("Save Current Coordinates as Default");
        saveCoordinatesBtn.addActionListener(e -> saveCoordinates());
        panel.add(saveCoordinatesBtn, gbc);
        
        return panel;
    }
    
    private void updateUIBasedOnAutomationMethod() {
        boolean useKeyboard = useKeyboardShortcutCheckbox.isSelected();
        
        // If using keyboard shortcuts, disable the mouse coordinate test buttons
        testComposeButton.setEnabled(!useKeyboard);
        testAttachButton.setEnabled(!useKeyboard);
        testSendButton.setEnabled(!useKeyboard);
    }
    
    private void startSequentialCalibration() {
        new Thread(() -> {
            try {
                updateStatus("Starting sequential calibration in 5 seconds. Switch to Gmail...");
                Thread.sleep(1000);
                
                // First Compose Button
                calibratePosition("Compose Button", composeXSpinner, composeYSpinner);
                Thread.sleep(6000); // 5 seconds + 1 second buffer
                
                // Next Attachment Button
                updateStatus("Now move mouse to Attachment Button in 5 seconds...");
                Thread.sleep(5000);
                captureCurrentMousePosition(attachmentXSpinner, attachmentYSpinner);
                
                // Finally Send Button
                updateStatus("Now move mouse to Send Button in 5 seconds...");
                Thread.sleep(5000);
                captureCurrentMousePosition(sendXSpinner, sendYSpinner);
                
                updateStatus("Calibration complete! All button positions have been set.");
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                updateStatus("Calibration interrupted.");
            }
        }).start();
    }
    
    private void calibratePosition(String buttonName, JSpinner xSpinner, JSpinner ySpinner) {
        new Thread(() -> {
            try {
                for (int i = 5; i > 0; i--) {
                    updateStatus("Move mouse to " + buttonName + " in " + i + " seconds...");
                    Thread.sleep(1000);
                }
                
                captureCurrentMousePosition(xSpinner, ySpinner);
                updateStatus(buttonName + " position set to X:" + xSpinner.getValue() + ", Y:" + ySpinner.getValue());
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    
    private void captureCurrentMousePosition(JSpinner xSpinner, JSpinner ySpinner) {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.invokeLater(() -> {
            xSpinner.setValue(mousePos.x);
            ySpinner.setValue(mousePos.y);
        });
    }
    
    private void testPosition(String action, int x, int y) {
        new Thread(() -> {
            try {
                updateStatus(action + " in 3 seconds...");
                Thread.sleep(3000);
                
                robot.mouseMove(x, y);
                sleepSafe(500);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                
                updateStatus("Clicked at position X:" + x + ", Y:" + y);
            } catch (Exception e) {
                updateStatus("Test failed: " + e.getMessage());
            }
        }).start();
    }
    
    private void saveCoordinates() {
        // Here you would save the coordinates to a preferences file
        // This is a simplified version - in a real app you'd use Preferences API
        updateStatus("Coordinates saved as default (would be persisted in a full implementation)");
    }
    
    private void loadSavedCoordinates() {
        // In a real app, you'd load from Preferences
        // This method would restore saved coordinates
    }

    private void selectChromePath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Chrome Executable");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // Set initial directory to common Chrome locations
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            File defaultDir = new File("C:\\Program Files\\Google\\Chrome\\Application");
            if (defaultDir.exists()) {
                fileChooser.setCurrentDirectory(defaultDir);
            }
        }
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File chromePath = fileChooser.getSelectedFile();
            chromePathField.setText(chromePath.getAbsolutePath());
        }
    }

    private void selectAttachment() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            attachmentLabel.setText(selectedFile.getName());
        }
    }

    private void sendEmails() {
        String chromePath = chromePathField.getText().trim();
        String recipientsText = recipientsField.getText().trim();
        String subject = subjectField.getText().trim();
        String body = bodyArea.getText().trim();

        if (chromePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please specify Chrome executable path.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (recipientsText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter at least one recipient email.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> recipients = parseRecipients(recipientsText);
        if (recipients.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No valid recipients found.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Reset stop flag
        stopRequested.set(false);
        
        // Update UI
        sendButton.setEnabled(false);
        stopButton.setEnabled(true);
        progressBar.setValue(0);
        
        new Thread(() -> {
            try {
                // Launch Chrome with Gmail
                openChromeWithGmail(chromePath);
                
                // Allow time for Gmail to load
                updateStatus("Waiting for Gmail to load in Chrome...");
                updateProgress(0, recipients.size());
                sleepSafe(delayForGmailLoad);
                
                int successCount = 0;
                for (int i = 0; i < recipients.size(); i++) {
                    // Check if stop requested
                    if (stopRequested.get()) {
                        updateStatus("Automation stopped by user after sending " + successCount + " emails.");
                        break;
                    }
                    
                    String recipient = recipients.get(i);
                    updateStatus("Sending email to " + recipient + " (" + (i+1) + "/" + recipients.size() + ")");
                    updateProgress(i, recipients.size());
                    
                    try {
                        // Complete email sending sequence
                        boolean success = sendEmailToRecipient(recipient, subject, body);
                        if (success) {
                            successCount++;
                        }
                    } catch (Exception e) {
                        updateStatus("Error sending to " + recipient + ": " + e.getMessage());
                    }
                    
                    // Wait before starting next email
                    sleepSafe(delayAfterSend);
                }
                
                updateStatus("Automation complete! Successfully sent " + successCount + " out of " + recipients.size() + " emails.");
                updateProgress(recipients.size(), recipients.size());
                
            } catch (Exception e) {
                updateStatus("Error: " + e.getMessage());
            } finally {
                // Re-enable UI
                SwingUtilities.invokeLater(() -> {
                    sendButton.setEnabled(true);
                    stopButton.setEnabled(false);
                });
            }
        }).start();
    }
    
    private boolean sendEmailToRecipient(String recipient, String subject, String body) {
        try {
            // Click compose button using selected method
            clickComposeButton();
            sleepSafe(delayBetweenActions);
            
            // Fill recipient
            pasteText(recipient);
            sleepSafe(delayBetweenActions / 2);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            sleepSafe(delayBetweenActions / 2);
            
            // Fill subject
            pasteText(subject);
            sleepSafe(delayBetweenActions / 2);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            sleepSafe(delayBetweenActions / 2);
            
            // Fill body
            pasteText(body);
            sleepSafe(delayBetweenActions);
            
            // Attach file if any
            if (selectedFile != null) {
                attachFile();
                sleepSafe(delayBetweenActions * 2); // Extra time for attachment
            }
            
            // Send email
            sendEmail();
            
            return true;
        } catch (Exception e) {
            updateStatus("Error in email sequence: " + e.getMessage());
            return false;
        }
    }
    
    private void openChromeWithGmail(String chromePath) throws Exception {
        try {
            File chromeFile = new File(chromePath);
            if (!chromeFile.exists()) {
                throw new Exception("Chrome executable not found at: " + chromePath);
            }
            
            updateStatus("Opening Gmail in Chrome...");
            
            // Handle OS-specific launch
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder processBuilder;
            
            if (os.contains("win")) {
                // Windows
                processBuilder = new ProcessBuilder("\"" + chromePath + "\"", "https://mail.google.com");
            } else if (os.contains("mac")) {
                // macOS
                processBuilder = new ProcessBuilder("open", "-a", chromePath, "https://mail.google.com");
            } else {
                // Linux and others
                processBuilder = new ProcessBuilder(chromePath, "https://mail.google.com");
            }
            
            processBuilder.start();
            
        } catch (Exception e) {
            throw new Exception("Failed to open Chrome: " + e.getMessage());
        }
    }
    
    private void clickComposeButton() {
        updateStatus("Initiating compose action...");
        
        if (useKeyboardShortcutCheckbox.isSelected()) {
            // Use keyboard shortcut 'c' for compose in Gmail
            sleepSafe(delayBetweenActions / 2);
            robot.keyRelease(KeyEvent.VK_C);
            updateStatus("Used keyboard shortcut 'c' to compose");
        } else {
            // Use mouse click at specified coordinates
            int x = (Integer) composeXSpinner.getValue();
            int y = (Integer) composeYSpinner.getValue();
            
            robot.mouseMove(x, y);
            sleepSafe(500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            updateStatus("Clicked compose button at X:" + x + ", Y:" + y);
        }
    }
    
    private void attachFile() {
        updateStatus("Attaching file: " + selectedFile.getName());
        
        if (useKeyboardShortcutCheckbox.isSelected()) {
            // Use Gmail keyboard shortcut for attachment
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } else {
            // Use mouse to click on attachment button
            int x = (Integer) attachmentXSpinner.getValue();
            int y = (Integer) attachmentYSpinner.getValue();
            
            robot.mouseMove(x, y);
            sleepSafe(500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
        
        sleepSafe(delayBetweenActions);
        
        // Handle file selection dialog
        handleFileDialog();
    }
    
    private void handleFileDialog() {
        // Wait for file dialog to appear
        sleepSafe(delayBetweenActions);
        
        // Type the file path
        pasteText(selectedFile.getAbsolutePath());
        sleepSafe(500);
        
        // Press Enter to confirm file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        // Wait for upload to complete - this might need to be longer for large files
        sleepSafe(delayBetweenActions * 2);
    }
    
    private void sendEmail() {
        updateStatus("Sending email...");
        
        if (useKeyboardShortcutCheckbox.isSelected()) {
            // Use keyboard shortcut Ctrl+Enter to send in Gmail
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            updateStatus("Email sent using keyboard shortcut Ctrl+Enter");
        } else {
            // Use mouse to click on send button
            int x = (Integer) sendXSpinner.getValue();
            int y = (Integer) sendYSpinner.getValue();
            
            robot.mouseMove(x, y);
            sleepSafe(500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            updateStatus("Email sent by clicking send button at X:" + x + ", Y:" + y);
        }
    }
    
    private void pasteText(String text) {
        // Copy text to clipboard
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        
        // Paste using Ctrl+V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }
    
    private List<String> parseRecipients(String recipientsText) {
        String[] emails = recipientsText.split(",");
        List<String> recipients = new ArrayList<>();
        
        for (String email : emails) {
            String trimmed = email.trim();
            if (!trimmed.isEmpty()) {
                recipients.add(trimmed);
            }
        }
        
        return recipients;
    }
    
    private void updateStatus(String message) {
        SwingUtilities.invokeLater(() -> statusLabel.setText(message));
    }
    
    private void updateProgress(int current, int total) {
        int percentage = (total > 0) ? (current * 100) / total : 0;
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(percentage);
            progressBar.setString(current + "/" + total + " (" + percentage + "%)");
        });
    }
    
    private void sleepSafe(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        try {
            // Try to set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            GmailChromeAutomation app = new GmailChromeAutomation();
            app.setVisible(true);
        });
    }
}