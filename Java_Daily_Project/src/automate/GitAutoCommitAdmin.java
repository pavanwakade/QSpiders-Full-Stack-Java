package automate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class GitAutoCommitAdmin extends JFrame {
    private List<String> repositories;
    private int commitInterval;
    private String defaultCommitMessage;
    private static final String CONFIG_FILE = System.getProperty("user.home") + File.separator + ".gitautocommit"
            + File.separator + "config.properties";

    // UI Components
    private JTable repositoryTable;
    private DefaultTableModel tableModel;
    private JTextField intervalField;
    private JTextField commitMessageField;
    private JButton addRepoButton;
    private JButton removeRepoButton;
    private JButton saveButton;

    public GitAutoCommitAdmin() {
        // Set up the look and feel
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Initialize configuration
        repositories = new ArrayList<>();
        loadConfiguration();

        // Set up the frame
        setTitle("Git Auto Commit - Admin Panel");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        
        // Custom colors
        Color bgColor = new Color(245, 245, 245);
        Color accentColor = new Color(33, 150, 243);
        Color textColor = new Color(33, 33, 33);

        getContentPane().setBackground(bgColor);

        // Title Panel
        JPanel titlePanel = createTitlePanel(accentColor, textColor);
        add(titlePanel, BorderLayout.NORTH);

        // Repository Table
        JPanel tablePanel = createRepositoryTablePanel(accentColor, bgColor);
        add(tablePanel, BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = createControlPanel(accentColor, bgColor, textColor);
        add(controlPanel, BorderLayout.SOUTH);

        // Add padding
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private JPanel createTitlePanel(Color accentColor, Color textColor) {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, accentColor),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel titleLabel = new JLabel("Git Auto Commit Configuration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(accentColor);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        titlePanel.add(titleLabel, BorderLayout.WEST);
        return titlePanel;
    }

    private JPanel createRepositoryTablePanel(Color accentColor, Color bgColor) {
        String[] columnNames = { "Repositories" };
        tableModel = new DefaultTableModel(columnNames, 0);
        repositoryTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Customize table appearance
        repositoryTable.setRowHeight(30);
        repositoryTable.setSelectionBackground(new Color(accentColor.getRed(), accentColor.getGreen(), accentColor.getBlue(), 100));
        repositoryTable.setFont(new Font("Arial", Font.PLAIN, 14));

        // Customize table header
        JTableHeader header = repositoryTable.getTableHeader();
        header.setBackground(accentColor);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));

        // Populate table with existing repositories
        for (String repo : repositories) {
            tableModel.addRow(new Object[] { repo });
        }

        JScrollPane tableScrollPane = new JScrollPane(repositoryTable);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(accentColor, 1));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(bgColor);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createControlPanel(Color accentColor, Color bgColor, Color textColor) {
        JPanel controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBackground(bgColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Styling for buttons and text fields
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // Add Repository Button
        addRepoButton = createStyledButton("Add Repository", accentColor);
        addRepoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRepository();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(addRepoButton, gbc);

        // Remove Repository Button
        removeRepoButton = createStyledButton("Remove Repository", accentColor);
        removeRepoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeRepository();
            }
        });
        gbc.gridx = 1;
        controlPanel.add(removeRepoButton, gbc);

        // Interval Label and Field
        JLabel intervalLabel = new JLabel("Commit Interval (minutes):");
        intervalLabel.setFont(labelFont);
        intervalLabel.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        controlPanel.add(intervalLabel, gbc);

        intervalField = new JTextField(String.valueOf(commitInterval), 10);
        intervalField.setFont(fieldFont);
        intervalField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1;
        controlPanel.add(intervalField, gbc);

        // Commit Message Label and Field
        JLabel commitMessageLabel = new JLabel("Default Commit Message:");
        commitMessageLabel.setFont(labelFont);
        commitMessageLabel.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        controlPanel.add(commitMessageLabel, gbc);

        commitMessageField = new JTextField(defaultCommitMessage, 20);
        commitMessageField.setFont(fieldFont);
        commitMessageField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1;
        controlPanel.add(commitMessageField, gbc);

        // Save Button
        saveButton = createStyledButton("Save Configuration", accentColor);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConfiguration();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        controlPanel.add(saveButton, gbc);

        return controlPanel;
    }

    private JButton createStyledButton(String text, Color accentColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(accentColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(accentColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(accentColor);
            }
        });

        return button;
    }

    private void addRepository() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Git Repository");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDir = fileChooser.getSelectedFile();
            String repoPath = selectedDir.getAbsolutePath();

            // Check if it's a valid git repository
            File gitDir = new File(repoPath, ".git");
            if (gitDir.exists() && gitDir.isDirectory()) {
                // Check if repository is already added
                if (!repositories.contains(repoPath)) {
                    repositories.add(repoPath);
                    tableModel.addRow(new Object[] { repoPath });
                } else {
                    JOptionPane.showMessageDialog(this, "Repository already exists in the list.",
                            "Duplicate Repository", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selected directory is not a valid Git repository.",
                        "Invalid Repository", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Please select a repository to remove.", "No Repository Selected",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadConfiguration() {
        Properties props = new Properties();
        File configFile = new File(CONFIG_FILE);

        // Create config directory if it doesn't exist
        configFile.getParentFile().mkdirs();

        // Default values
        commitInterval = 5;
        defaultCommitMessage = "Auto commit";

        try {
            if (configFile.exists()) {
                try (FileInputStream fis = new FileInputStream(configFile)) {
                    props.load(fis);

                    // Load repositories
                    String repoList = props.getProperty("repositories", "");
                    if (!repoList.isEmpty()) {
                        repositories = new ArrayList<>(Arrays.asList(repoList.split(File.pathSeparator)));
                    }

                    // Load interval
                    commitInterval = Integer.parseInt(props.getProperty("interval", "5"));

                    // Load commit message
                    defaultCommitMessage = props.getProperty("commitMessage", "Auto commit");
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading configuration: " + e.getMessage(), "Configuration Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveConfiguration() {
        try {
            // Validate interval
            int newInterval;
            try {
                newInterval = Integer.parseInt(intervalField.getText().trim());
                if (newInterval <= 0) {
                    throw new NumberFormatException("Interval must be positive");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid interval. Please enter a positive number.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate commit message
            String newCommitMessage = commitMessageField.getText().trim();
            if (newCommitMessage.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Commit message cannot be empty.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Prepare properties
            Properties props = new Properties();

            // Save repositories
            String repoListString = String.join(File.pathSeparator, repositories);
            props.setProperty("repositories", repoListString);

            // Save interval
            props.setProperty("interval", String.valueOf(newInterval));

            // Save commit message
            props.setProperty("commitMessage", newCommitMessage);

            // Write to config file
            try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
                props.store(fos, "Git Auto Commit Configuration");
            }

            // Update current configuration
            commitInterval = newInterval;
            defaultCommitMessage = newCommitMessage;

            JOptionPane.showMessageDialog(this, "Configuration saved successfully!", "Save Successful",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving configuration: " + e.getMessage(), "Save Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GitAutoCommitAdmin admin = new GitAutoCommitAdmin();
            admin.setVisible(true);
        });
    }
}