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
    private JLabel statusLabel;
    private JLabel attachmentLabel;
    private File selectedFile;
    private Robot robot;
    private JTextField chromePathField;
    private JTextField recipientsFileField;
    private JLabel recipientsFileLabel;
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

        // Recipients File
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Recipients File:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        recipientsFileField = new JTextField(30);
        recipientsFileField.setEditable(false);
        JPanel recipientsFilePanel = new JPanel(new BorderLayout());
        recipientsFileLabel = new JLabel("No file selected");
        JButton browseRecipientsButton = new JButton("Browse");
        browseRecipientsButton.addActionListener(e -> selectRecipientsFile());
        recipientsFilePanel.add(recipientsFileLabel, BorderLayout.CENTER);
        recipientsFilePanel.add(browseRecipientsButton, BorderLayout.EAST);
        panel.add(recipientsFilePanel, gbc);

        // Recipients Text Field (Optional)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Or enter recipients (comma separated):"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        recipientsField = new JTextField(30);
        panel.add(recipientsField, gbc);

        // Subject
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Subject:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        subjectField = new JTextField(30);
        panel.add(subjectField, gbc);

        // Body
        gbc.gridx = 0;
        gbc.gridy = 4;
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
        gbc.gridy = 5;
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
            recipientsFileLabel.setText(selectedRecipientsFile.getName());
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

        try {
            openChromeWithGmail(chromePath);
            updateStatus("Waiting for Gmail to load in Chrome...");
            sleepSafe(10000);
            
            for (int i = 0; i < recipients.size(); i++) {
                String recipient = recipients.get(i);
                updateStatus("Sending email to " + recipient + " (" + (i+1) + "/" + recipients.size() + ")");
                
                clickComposeButton();
                sleepSafe(2000);
                
                pasteText(recipient);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                sleepSafe(500);
                
                pasteText(subject);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                sleepSafe(500);
                
                pasteText(body);
                sleepSafe(500);
                
                if (selectedFile != null) {
                    attachFile();
                    sleepSafe(3000);
                }
                
                clickSendButton();
                sleepSafe(3000);
                
                // Log the sent recipient to the file
                try (PrintWriter writer = new PrintWriter(new FileWriter(SENT_EMAILS_FILE, true))) {
                    writer.println(recipient);
                } catch (IOException e) {
                    updateStatus("Error writing to file: " + e.getMessage());
                }
            }
            
            updateStatus("All emails sent successfully!");
        } catch (Exception e) {
            updateStatus("Error: " + e.getMessage());
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