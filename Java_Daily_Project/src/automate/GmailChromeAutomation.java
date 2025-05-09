package automate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

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
    
    // Hardcoded coordinates as per user request
    private final int COMPOSE_BUTTON_X = 95;
    private final int COMPOSE_BUTTON_Y = 224;
    private final int ATTACH_BUTTON_X = 959;
    private final int ATTACH_BUTTON_Y = 687;
    private final int SEND_BUTTON_X = 841;
    private final int SEND_BUTTON_Y = 692;

    public GmailChromeAutomation() {
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
        setSize(700, 650);
        setLocationRelativeTo(null);

        // Create tabbed pane for organization
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Basic settings panel
        JPanel basicPanel = createBasicPanel();
        tabbedPane.addTab("Basic Settings", basicPanel);
        
        // Status panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel(" ");
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        
        sendButton = new JButton("Open Gmail in Chrome & Send Emails");
        sendButton.addActionListener(e -> new Thread(this::sendEmails).start());
        statusPanel.add(sendButton, BorderLayout.SOUTH);
        
        add(tabbedPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
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
        chromePathField = new JTextField("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
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
        
        // Coordinates Information
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JTextArea coordsInfo = new JTextArea(
            "Using hardcoded coordinates:\n" +
            "- Compose Button: X=" + COMPOSE_BUTTON_X + ", Y=" + COMPOSE_BUTTON_Y + "\n" +
            "- Attachment Button: X=" + ATTACH_BUTTON_X + ", Y=" + ATTACH_BUTTON_Y + "\n" +
            "- Send Button: X=" + SEND_BUTTON_X + ", Y=" + SEND_BUTTON_Y
        );
        coordsInfo.setEditable(false);
        coordsInfo.setLineWrap(true);
        coordsInfo.setWrapStyleWord(true);
        coordsInfo.setBackground(panel.getBackground());
        panel.add(coordsInfo, gbc);
        
        return panel;
    }

    private void selectChromePath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Chrome Executable");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // Set initial directory to common Chrome locations
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

        try {
            // Launch Chrome with Gmail
            openChromeWithGmail(chromePath);
            
            // Allow time for Gmail to load
            updateStatus("Waiting for Gmail to load in Chrome...");
            sleepSafe(10000);  // Increased wait time for Gmail to fully load
            
            for (int i = 0; i < recipients.size(); i++) {
                String recipient = recipients.get(i);
                updateStatus("Sending email to " + recipient + " (" + (i+1) + "/" + recipients.size() + ")");
                
                // Click compose button using fixed coordinates
                clickComposeButton();
                sleepSafe(2000);
                
                // Fill recipient
                pasteText(recipient);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                sleepSafe(500);
                
                // Fill subject
                pasteText(subject);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                sleepSafe(500);
                
                // Fill body
                pasteText(body);
                sleepSafe(500);
                
                // Attach file if any
                if (selectedFile != null) {
                    attachFile();
                    sleepSafe(3000); // Wait longer for attachment to upload
                }
                
                // Send email using fixed coordinates
                clickSendButton();
                
                // Wait before starting next email
                sleepSafe(3000);
            }
            
            updateStatus("All emails sent successfully!");
        } catch (Exception e) {
            updateStatus("Error: " + e.getMessage());
        }
    }
    
    private void openChromeWithGmail(String chromePath) throws Exception {
        try {
            File chromeFile = new File(chromePath);
            if (!chromeFile.exists() || !chromeFile.canExecute()) {
                throw new Exception("Chrome executable not found or cannot be executed");
            }
            
            updateStatus("Opening Gmail in Chrome...");
            // Launch Chrome with Gmail URL
            String command = "\"" + chromePath + "\" https://mail.google.com";
            Runtime.getRuntime().exec(command);
            
        } catch (Exception e) {
            throw new Exception("Failed to open Chrome: " + e.getMessage());
        }
    }
    
    private void clickComposeButton() {
        updateStatus("Clicking compose button...");
        // Use fixed coordinates for compose button
        robot.mouseMove(COMPOSE_BUTTON_X, COMPOSE_BUTTON_Y);
        sleepSafe(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    private void attachFile() {
        updateStatus("Attaching file: " + selectedFile.getName());
        
        // Click on attachment button using fixed coordinates
        robot.mouseMove(ATTACH_BUTTON_X, ATTACH_BUTTON_Y);
        sleepSafe(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        
        sleepSafe(1000);
        
        // Type the file path in the file dialog
        pasteText(selectedFile.getAbsolutePath());
        sleepSafe(500);
        
        // Press Enter to confirm file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        // Wait for upload to complete
        sleepSafe(3000);
    }
    
    private void clickSendButton() {
        updateStatus("Clicking send button...");
        // Use fixed coordinates for send button
        robot.mouseMove(SEND_BUTTON_X, SEND_BUTTON_Y);
        sleepSafe(500);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
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
    
    private void sleepSafe(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        try {
            // Set Windows look and feel
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