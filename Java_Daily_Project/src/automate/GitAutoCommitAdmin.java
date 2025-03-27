package automate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GitAutoCommitAdmin extends JFrame {
    private static final String CONFIG_FILE = System.getProperty("user.home") + File.separator + ".gitautocommit" + File.separator + "config.properties";
    private List<String> repositories;
    private int commitInterval;
    private String commitMessage;

    private JTable repositoryTable;
    private DefaultTableModel tableModel;
    private JTextField commitMessageField;
    private JSpinner intervalSpinner;

    public GitAutoCommitAdmin() {
        setIconImage(createAppIcon());
        repositories = new ArrayList<>();
        loadConfiguration();
        initializeUI();
    }

    private ImageIcon createAppIcon() {
        // Create a simple app icon
        BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(new Color(51, 105, 232));
        g2d.fillRect(0, 0, 32, 32);
        g2d.setColor(Color.WHITE);
        g2d.drawString("GA", 5, 22);
        g2d.dispose();
        return new ImageIcon(image);
    }

    private void loadConfiguration() {
        Properties props = new Properties();
        File configFile = new File(CONFIG_FILE);
        
        try {
            // Ensure config directory exists
            configFile.getParentFile().mkdirs();
            
            if (configFile.exists()) {
                try (FileInputStream fis = new FileInputStream(configFile)) {
                    props.load(fis);
                    
                    // Load repositories
                    String repoList = props.getProperty("repositories", "");
                    repositories = new ArrayList<>(List.of(repoList.split(File.pathSeparator)));
                    
                    // Load commit interval
                    commitInterval = Integer.parseInt(props.getProperty("commitInterval", "5"));
                    
                    // Load commit message
                    commitMessage = props.getProperty("commitMessage", "Updated automatically");
                }
            } else {
                // First-time setup defaults
                commitInterval = 5;
                commitMessage = "Updated automatically";
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error reading configuration: " + e.getMessage(), 
                "Configuration Error", 
                JOptionPane.ERROR_MESSAGE);
            
            // Set defaults
            commitInterval = 5;
            commitMessage = "Updated automatically";
        }
    }

    private void saveConfiguration() {
        // Update values before saving
        updateConfigurationValues();

        Properties props = new Properties();
        props.setProperty("repositories", String.join(File.pathSeparator, repositories));
        props.setProperty("commitInterval", String.valueOf(commitInterval));
        props.setProperty("commitMessage", commitMessage);

        try {
            File configFile = new File(CONFIG_FILE);
            configFile.getParentFile().mkdirs(); // Ensure directory exists

            try (FileOutputStream fos = new FileOutputStream(configFile)) {
                props.store(fos, "Git AutoCommit Configuration");
                JOptionPane.showMessageDialog(this, 
                    "Configuration saved successfully!", 
                    "Save Successful", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error saving configuration: " + e.getMessage(), 
                "Configuration Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeUI() {
        setTitle("Git AutoCommit Admin");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Repository Table
        String[] columnNames = {"Repository Path"};
        tableModel = new DefaultTableModel(columnNames, 0);
        repositoryTable = new JTable(tableModel);
        repositories.forEach(repo -> tableModel.addRow(new Object[]{repo}));

        JScrollPane tableScrollPane = new JScrollPane(repositoryTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Monitored Repositories"));
        add(tableScrollPane, BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add Repository Button
        JButton addRepoButton = new JButton("Add Repository");
        addRepoButton.setToolTipText("Select a Git repository to monitor");
        addRepoButton.addActionListener(e -> addRepository());
        controlPanel.add(addRepoButton);

        // Remove Repository Button
        JButton removeRepoButton = new JButton("Remove Repository");
        removeRepoButton.setToolTipText("Remove selected repository from monitoring");
        removeRepoButton.addActionListener(e -> removeRepository());
        controlPanel.add(removeRepoButton);

        // Commit Message
        controlPanel.add(new JLabel("Commit Message:"));
        commitMessageField = new JTextField(commitMessage);
        controlPanel.add(commitMessageField);

        // Commit Interval
        controlPanel.add(new JLabel("Commit Interval (minutes):"));
        SpinnerNumberModel intervalModel = new SpinnerNumberModel(
            commitInterval, 1, 60, 1);
        intervalSpinner = new JSpinner(intervalModel);
        controlPanel.add(intervalSpinner);

        // Save Configuration Button
        JButton saveButton = new JButton("Save Configuration");
        saveButton.setToolTipText("Save current configuration");
        saveButton.addActionListener(e -> saveConfiguration());
        controlPanel.add(saveButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void addRepository() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Git Repository");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedRepo = fileChooser.getSelectedFile();
            String repoPath = selectedRepo.getAbsolutePath();

            // Check if it's a git repository
            File gitDir = new File(repoPath, ".git");
            if (gitDir.exists() && gitDir.isDirectory()) {
                if (!repositories.contains(repoPath)) {
                    repositories.add(repoPath);
                    tableModel.addRow(new Object[]{repoPath});
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Repository already exists in the list.", 
                        "Duplicate Repository", 
                        JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Selected directory is not a Git repository.", 
                    "Invalid Repository", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeRepository() {
        int selectedRow = repositoryTable.getSelectedRow();
        if (selectedRow != -1) {
            String repoToRemove = (String) tableModel.getValueAt(selectedRow, 0);
            repositories.remove(repoToRemove);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Please select a repository to remove.", 
                "No Repository Selected", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public void updateConfigurationValues() {
        // Update values before saving
        commitMessage = commitMessageField.getText();
        commitInterval = (Integer) intervalSpinner.getValue();
    }

    public static void main(String[] args) {
        try {
            // Set system look and feel for a native appearance
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // If setting look and feel fails, continue with default
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            GitAutoCommitAdmin admin = new GitAutoCommitAdmin();
            admin.setVisible(true);
        });
    }
}