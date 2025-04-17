package automate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

public class GmailAutomation extends JFrame {
    private JTextField recipientsField;
    private JTextField subjectField;
    private JTextArea bodyArea;
    private JButton attachmentButton;
    private JButton sendButton;
    private JButton stopButton;
    private JLabel statusLabel;
    private JLabel attachmentLabel;
    private File selectedFile;
    private Robot robot;
    private JTextField chromePathField;
    private JTextField recipientsFileField;
    private File selectedRecipientsFile;
    
    // Hardcoded coordinates as per user request
    private final int COMPOSE_BUTTON_X = 95;
    private final int COMPOSE_BUTTON_Y = 224;
    private final int ATTACH_BUTTON_X = 959;
    private final int ATTACH_BUTTON_Y = 687;
    private final int SEND_BUTTON_X = 841;
    private final int SEND_BUTTON_Y = 692;

    // File path for logging sent emails
    private static final String SENT_EMAILS_FILE = "C:\\Users\\Admin\\Desktop\\Gmails\\Gmails_sended.txt";

    // Speed settings
    private JSlider initialDelaySlider;
    private JSlider composeDelaySlider;
    private JSlider inputDelaySlider;
    private JSlider attachmentDelaySlider;
    private JSlider sendDelaySlider;

    // Flag to control automation
    private volatile boolean isRunning = false;

    public GmailAutomation() {
        super("Gmail Chrome Automation Tool");
        initUI();
        initRobot();
    }

    private void initRobot() {
        try {
            robot = new Robot();
            robot.setAutoDelay(100);
        } catch (AWTException e) {
            JOptionPane.showMessageDialog(this, "Robot initialization failed: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);

        // Tabbed pane for organization
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel basicPanel = createBasicPanel();
        JPanel advancedPanel = createAdvancedPanel();
        tabbedPane.addTab("Basic Settings", basicPanel);
        tabbedPane.addTab("Advanced Settings", advancedPanel);

        // Status panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Ready");
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sendButton = new JButton("Open Gmail in Chrome & Send Emails");
        sendButton.addActionListener(e -> new Thread(this::sendEmails).start());
        
        stopButton = new JButton("Stop Automation");
        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> stopAutomation());
        
        buttonPanel.add(sendButton);
        buttonPanel.add(stopButton);
        statusPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(tabbedPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createBasicPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Chrome Configuration Panel
        JPanel chromePanel = new JPanel(new GridBagLayout());
        chromePanel.setBorder(BorderFactory.createTitledBorder("Chrome Configuration"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        chromePanel.add(new JLabel("Chrome Path:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        chromePathField = new JTextField("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", 30);
        JPanel chromeFieldPanel = new JPanel(new BorderLayout());
        JButton browseChromeButton = new JButton("Browse");
        browseChromeButton.addActionListener(e -> selectChromePath());
        chromeFieldPanel.add(chromePathField, BorderLayout.CENTER);
        chromeFieldPanel.add(browseChromeButton, BorderLayout.EAST);
        chromePanel.add(chromeFieldPanel, gbc);

        // Recipients Panel
        JPanel recipientsPanel = new JPanel(new GridBagLayout());
        recipientsPanel.setBorder(BorderFactory.createTitledBorder("Recipients"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        recipientsPanel.add(new JLabel("Recipients File:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        recipientsFileField = new JTextField(20);
        recipientsFileField.setEditable(false);
        JPanel recipientsFilePanel = new JPanel(new BorderLayout());
        JButton browseRecipientsButton = new JButton("Browse");
        browseRecipientsButton.addActionListener(e -> selectRecipientsFile());
        recipientsFilePanel.add(recipientsFileField, BorderLayout.CENTER);
        recipientsFilePanel.add(browseRecipientsButton, BorderLayout.EAST);
        recipientsPanel.add(recipientsFilePanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        recipientsPanel.add(new JLabel("Or enter recipients (comma separated):"), gbc);
        
        gbc.gridy = 2;
        recipientsField = new JTextField(30);
        recipientsPanel.add(recipientsField, gbc);

        // Email Content Panel
        JPanel emailContentPanel = new JPanel(new GridBagLayout());
        emailContentPanel.setBorder(BorderFactory.createTitledBorder("Email Content"));
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        emailContentPanel.add(new JLabel("Subject:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        subjectField = new JTextField(30);
        emailContentPanel.add(subjectField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        emailContentPanel.add(new JLabel("Body:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        bodyArea = new JTextArea(10, 30);
        bodyArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        emailContentPanel.add(scrollPane, gbc);
        gbc.weighty = 0.0;
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        emailContentPanel.add(new JLabel("Attachment:"), gbc);
        
        gbc.gridx = 1;
        JPanel attachmentPanel = new JPanel(new BorderLayout());
        attachmentLabel = new JLabel("No file selected");
        attachmentButton = new JButton("Browse");
        attachmentButton.addActionListener(e -> selectAttachment());
        attachmentPanel.add(attachmentLabel, BorderLayout.CENTER);
        attachmentPanel.add(attachmentButton, BorderLayout.EAST);
        emailContentPanel.add(attachmentPanel, gbc);

        // Add panels to main panel
        panel.add(chromePanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(recipientsPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(emailContentPanel);

        return panel;
    }

    private JPanel createAdvancedPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Speed Settings Panel
        JPanel speedPanel = new JPanel(new GridBagLayout());
        speedPanel.setBorder(BorderFactory.createTitledBorder("Speed Settings (milliseconds)"));
        GridBagConstraints speedGbc = new GridBagConstraints();
        speedGbc.insets = new Insets(5, 5, 5, 5);
        speedGbc.fill = GridBagConstraints.HORIZONTAL;

        // Initial Gmail Load Delay
        speedGbc.gridx = 0;
        speedGbc.gridy = 0;
        speedPanel.add(new JLabel("Initial Gmail Load Delay:"), speedGbc);
        speedGbc.gridx = 1;
        initialDelaySlider = new JSlider(JSlider.HORIZONTAL, 5000, 20000, 10000);
        initialDelaySlider.setMajorTickSpacing(5000);
        initialDelaySlider.setMinorTickSpacing(1000);
        initialDelaySlider.setPaintTicks(true);
        initialDelaySlider.setPaintLabels(true);
        speedPanel.add(initialDelaySlider, speedGbc);

        // Compose Button Click Delay
        speedGbc.gridx = 0;
        speedGbc.gridy = 1;
        speedPanel.add(new JLabel("Compose Button Click Delay:"), speedGbc);
        speedGbc.gridx = 1;
        composeDelaySlider = new JSlider(JSlider.HORIZONTAL, 1000, 5000, 2000);
        composeDelaySlider.setMajorTickSpacing(1000);
        composeDelaySlider.setMinorTickSpacing(500);
        composeDelaySlider.setPaintTicks(true);
        composeDelaySlider.setPaintLabels(true);
        speedPanel.add(composeDelaySlider, speedGbc);

        // Input Field Delay
        speedGbc.gridx = 0;
        speedGbc.gridy = 2;
        speedPanel.add(new JLabel("Input Field Delay:"), speedGbc);
        speedGbc.gridx = 1;
        inputDelaySlider = new JSlider(JSlider.HORIZONTAL, 200, 2000, 500);
        inputDelaySlider.setMajorTickSpacing(400);
        inputDelaySlider.setMinorTickSpacing(100);
        inputDelaySlider.setPaintTicks(true);
        inputDelaySlider.setPaintLabels(true);
        speedPanel.add(inputDelaySlider, speedGbc);

        // Attachment Delay
        speedGbc.gridx = 0;
        speedGbc.gridy = 3;
        speedPanel.add(new JLabel("Attachment Delay:"), speedGbc);
        speedGbc.gridx = 1;
        attachmentDelaySlider = new JSlider(JSlider.HORIZONTAL, 1000, 5000, 3000);
        attachmentDelaySlider.setMajorTickSpacing(1000);
        attachmentDelaySlider.setMinorTickSpacing(500);
        attachmentDelaySlider.setPaintTicks(true);
        attachmentDelaySlider.setPaintLabels(true);
        speedPanel.add(attachmentDelaySlider, speedGbc);

        // Send Button Delay
        speedGbc.gridx = 0;
        speedGbc.gridy = 4;
        speedPanel.add(new JLabel("Send Button Delay:"), speedGbc);
        speedGbc.gridx = 1;
        sendDelaySlider = new JSlider(JSlider.HORIZONTAL, 1000, 5000, 3000);
        sendDelaySlider.setMajorTickSpacing(1000);
        sendDelaySlider.setMinorTickSpacing(500);
        sendDelaySlider.setPaintTicks(true);
        sendDelaySlider.setPaintLabels(true);
        speedPanel.add(sendDelaySlider, speedGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(speedPanel, gbc);

        return panel;
    }

    private void selectChromePath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Chrome Executable");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        File defaultDir = new File("C:\\Program Files\\Google\\Chrome\\Application");
        if (defaultDir.exists()) {
            fileChooser.setCurrentDirectory(defaultDir);
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

    private void selectRecipientsFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Recipients Text File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files", "txt"));
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedRecipientsFile = fileChooser.getSelectedFile();
            recipientsFileField.setText(selectedRecipientsFile.getAbsolutePath());
        }
    }

    private void stopAutomation() {
        isRunning = false;
        updateStatus("Automation stopped by user");
        JOptionPane.showMessageDialog(this, "Email automation has been stopped.",
                "Stopped", JOptionPane.INFORMATION_MESSAGE);
        sendButton.setEnabled(true);
        stopButton.setEnabled(false);
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

        List<String> recipients = new ArrayList<>();
        if (selectedRecipientsFile != null) {
            recipients = readRecipientsFromFile();
        }
        if (recipients.isEmpty() && !recipientsText.isEmpty()) {
            recipients = parseRecipients(recipientsText);
        }

        if (recipients.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a recipients file or enter at least one recipient email.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show warning before starting automation
        JOptionPane.showMessageDialog(this, "Please do not use the computer while emails are being sent.",
                "Warning", JOptionPane.WARNING_MESSAGE);

        try {
            isRunning = true;
            sendButton.setEnabled(false);
            stopButton.setEnabled(true);

            openChromeWithGmail(chromePath);
            updateStatus("Waiting for Gmail to load in Chrome...");
            sleepSafe(initialDelaySlider.getValue());
            
            for (int i = 0; i < recipients.size() && isRunning; i++) {
                String recipient = recipients.get(i);
                updateStatus("Sending email to " + recipient + " (" + (i+1) + "/" + recipients.size() + ")");
                
                clickComposeButton();
                sleepSafe(composeDelaySlider.getValue());
                
                pasteText(recipient);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                sleepSafe(inputDelaySlider.getValue());
                
                pasteText(subject);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                sleepSafe(inputDelaySlider.getValue());
                
                pasteText(body);
                sleepSafe(inputDelaySlider.getValue());
                
                if (selectedFile != null) {
                    attachFile();
                    sleepSafe(attachmentDelaySlider.getValue());
                }
                
                clickSendButton();
                sleepSafe(sendDelaySlider.getValue());
                
                try (PrintWriter writer = new PrintWriter(new FileWriter(SENT_EMAILS_FILE, true))) {
                    writer.println(recipient);
                } catch (IOException e) {
                    updateStatus("Error writing to file: " + e.getMessage());
                }
            }
            
            if (isRunning) {
                updateStatus("All emails sent successfully!");
                JOptionPane.showMessageDialog(this, "All emails sent successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            if (isRunning) {
                updateStatus("Error: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            isRunning = false;
            sendButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }
    
    private List<String> readRecipientsFromFile() {
        List<String> recipients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedRecipientsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    recipients.add(trimmed);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading recipients file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return recipients;
    }
    
    private void openChromeWithGmail(String chromePath) throws Exception {
        try {
            File chromeFile = new File(chromePath);
            if (!chromeFile.exists() || !chromeFile.canExecute()) {
                throw new Exception("Chrome executable not found or cannot be executed");
            }
            
            updateStatus("Opening Gmail in Chrome...");
            String command = "\"" + chromePath + "\" https://mail.google.com";
            Runtime.getRuntime().exec(command);
            
        } catch (Exception e) {
            throw new Exception("Failed to open Chrome: " + e.getMessage());
        }
    }
    
    private void clickComposeButton() {
        updateStatus("Clicking compose button...");
        robot.mouseMove(COMPOSE_BUTTON_X, COMPOSE_BUTTON_Y);
        sleepSafe(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    private void attachFile() {
        updateStatus("Attaching file: " + selectedFile.getName());
        
        robot.mouseMove(ATTACH_BUTTON_X, ATTACH_BUTTON_Y);
        sleepSafe(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        
        sleepSafe(1000);
        
        pasteText(selectedFile.getAbsolutePath());
        sleepSafe(500);
        
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        sleepSafe(3000);
    }
    
    private void clickSendButton() {
        updateStatus("Clicking send button...");
        robot.mouseMove(SEND_BUTTON_X, SEND_BUTTON_Y);
        sleepSafe(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    private void pasteText(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        
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
    
    private void sleepSafe(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            GmailAutomation app = new GmailAutomation();
            app.setVisible(true);
        });
    }
}