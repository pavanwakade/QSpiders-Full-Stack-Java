//package automate;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//import javax.swing.JOptionPane;
//
//public class Every5min {
//    private static final String DEFAULT_REPO_PATH = "C:\\Users\\Admin\\Desktop\\Qspiders\\QSpiders-Full-Stack-Java";
//    private static final int COMMIT_INTERVAL_MINUTES = 40;
//
//    public static void main(String[] args) {
//        final String repoPath;  // Made final
//        final String commitMessage;  // Made final
//        
//        // Initialize with defaults
//        if (args.length >= 1) {
//            repoPath = args[0];
//        } else {
//            repoPath = DEFAULT_REPO_PATH;
//        }
//        
//        if (args.length >= 2) {
//            commitMessage = args[1];
//        } else {
//            commitMessage = "updated";
////            commitMessage = "auto-update " + System.currentTimeMillis();
//
//        }
//
//        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//        
//        scheduler.scheduleAtFixedRate(() -> {
//            try {
//                commitAndPushChanges(repoPath, commitMessage);
//            } catch (Exception e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), 
//                    "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }, 0, COMMIT_INTERVAL_MINUTES, TimeUnit.MINUTES);
//
//        try {
//            Thread.sleep(Long.MAX_VALUE);
//        } catch (InterruptedException e) {
//            scheduler.shutdown();
//            e.printStackTrace();
//        }
//    }
//
//    public static void commitAndPushChanges(String repoPath, String commitMessage) throws IOException, InterruptedException {
//        File repoDir = new File(repoPath);
//        File gitDir = new File(repoPath + File.separator + ".git");
//        
//        if (!repoDir.exists() || !repoDir.isDirectory()) {
//            throw new IOException("Repository directory does not exist: " + repoPath);
//        }
//        
//        if (!gitDir.exists() || !gitDir.isDirectory()) {
//            throw new IOException("Not a git repository: " + repoPath);
//        }
//        
//        String gitPath = findGitExecutable();
//        if (gitPath == null) {
//            throw new IOException("Git executable not found!");
//        }
//        
//        if (hasChanges(gitPath, repoPath)) {
//            String ghPath = findGitHubCLI();
//            if (ghPath == null) {
//                useGitCommandLine(gitPath, repoPath, commitMessage);
//            } else {
//                useGitHubCLI(ghPath, repoPath, commitMessage);
//            }
//            System.out.println("Commit and push completed successfully at " + 
//                java.time.LocalDateTime.now());
//            JOptionPane.showMessageDialog(null, "Commit successfully!", 
//                "Success", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            System.out.println("No changes to commit at " + 
//                java.time.LocalDateTime.now());
//        }
//    }
//
//    private static boolean hasChanges(String gitPath, String repoPath) throws IOException, InterruptedException {
//        ProcessBuilder pb = new ProcessBuilder(gitPath, "status", "--porcelain");
//        pb.directory(new File(repoPath));
//        Process process = pb.start();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        boolean hasChanges = reader.readLine() != null;
//        reader.close();
//        process.waitFor();
//        return hasChanges;
//    }
//
//    private static String findGitHubCLI() {
//        List<String> possiblePaths = new ArrayList<>();
//        
//        possiblePaths.add(System.getenv("LOCALAPPDATA") + "\\GitHub CLI\\gh.exe");
//        possiblePaths.add("C:\\Program Files\\GitHub CLI\\gh.exe");
//        possiblePaths.add("C:\\Program Files (x86)\\GitHub CLI\\gh.exe");
//        
//        for (String path : possiblePaths) {
//            File file = new File(path);
//            if (file.exists() && file.canExecute()) {
//                return path;
//            }
//        }
//        
//        try {
//            ProcessBuilder pb = new ProcessBuilder("where", "gh");
//            Process process = pb.start();
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
//                String line = reader.readLine();
//                if (line != null && new File(line).exists()) {
//                    return line;
//                }
//            }
//        } catch (IOException e) {
//            // Ignore and continue
//        }
//        
//        return null;
//    }
//    
//    private static String findGitExecutable() {
//        List<String> possiblePaths = new ArrayList<>();
//        
//        possiblePaths.add("C:\\Program Files\\Git\\bin\\git.exe");
//        possiblePaths.add("C:\\Program Files (x86)\\Git\\bin\\git.exe");
//        
//        for (String path : possiblePaths) {
//            File file = new File(path);
//            if (file.exists() && file.canExecute()) {
//                return path;
//            }
//        }
//        
//        try {
//            ProcessBuilder pb = new ProcessBuilder("where", "git");
//            Process process = pb.start();
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
//                String line = reader.readLine();
//                if (line != null && new File(line).exists()) {
//                    return line;
//                }
//            }
//        } catch (IOException e) {
//            // Ignore and try next method
//        }
//        
//        return null;
//    }
//    
//    private static void useGitHubCLI(String ghPath, String repoPath, String commitMessage) throws IOException, InterruptedException {
//        System.out.println("Using GitHub CLI to commit and push changes...");
//        
//        runCommand(new String[]{ghPath, "repo", "sync"}, repoPath);
//        runCommand(new String[]{ghPath, "api", "repos/{owner}/{repo}/git/commits", "--method", "POST", 
//                               "-f", "message=" + commitMessage}, repoPath);
//        
//        System.out.println("Changes committed and pushed using GitHub CLI.");
//    }
//    
//    private static void useGitCommandLine(String gitPath, String repoPath, String commitMessage) throws IOException, InterruptedException {
//        System.out.println("Using Git command line to commit and push changes...");
//        
//        runCommand(new String[]{gitPath, "add", "."}, repoPath);
//        runCommand(new String[]{gitPath, "commit", "-m", commitMessage}, repoPath);
//        runCommand(new String[]{gitPath, "push"}, repoPath);
//        
//        System.out.println("Changes committed and pushed using Git.");
//    }
//    
//    private static void runCommand(String[] command, String directory) throws IOException, InterruptedException {
//        ProcessBuilder pb = new ProcessBuilder(command);
//        pb.directory(new File(directory));
//        pb.redirectErrorStream(true);
//        
//        System.out.println("Executing command in directory: " + directory);
//        System.out.print("Command: ");
//        for (String part : command) {
//            System.out.print(part + " ");
//        }
//        System.out.println();
//        
//        Process process = pb.start();
//        
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
//        
//        int exitCode = process.waitFor();
//        if (exitCode != 0) {
//            throw new IOException("NO Changes Found: " + exitCode);
//        }
//    }
//}
package automate;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class Every5min {
    private static final String CONFIG_DIR = System.getProperty("user.home") + File.separator + ".gitautocommit";
    private static final String CONFIG_FILE = CONFIG_DIR + File.separator + "repositories.config";
    private static final String LOG_FILE = CONFIG_DIR + File.separator + "gitautocommit.log";
    private static final int COMMIT_INTERVAL_MINUTES = 1;
    private static final String DEFAULT_COMMIT_MESSAGE = "Automated commit";

    private static final Logger LOGGER = Logger.getLogger(Every5min.class.getName());
    private static List<String> savedRepositories = new ArrayList<>();

    static {
        try {
            // Ensure config directory exists
            Files.createDirectories(Paths.get(CONFIG_DIR));
            
            // Setup logging
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);

            // Load saved repositories
            loadSavedRepositories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Persistent Repository Management
    private static void loadSavedRepositories() {
        try {
            Path configPath = Paths.get(CONFIG_FILE);
            if (Files.exists(configPath)) {
                savedRepositories = Files.readAllLines(configPath);
                // Validate repositories
                savedRepositories.removeIf(repo -> !isValidGitRepository(repo));
                LOGGER.info("Loaded " + savedRepositories.size() + " repositories");
            }
        } catch (IOException e) {
            LOGGER.severe("Error loading repositories: " + e.getMessage());
        }
    }

    private static void saveRepositories() {
        try {
            Files.write(Paths.get(CONFIG_FILE), savedRepositories, 
                StandardOpenOption.CREATE, 
                StandardOpenOption.TRUNCATE_EXISTING);
            LOGGER.info("Saved " + savedRepositories.size() + " repositories");
        } catch (IOException e) {
            LOGGER.severe("Error saving repositories: " + e.getMessage());
            showNotification("Save Error", "Could not save repositories");
        }
    }

    // Repository Management GUI
    private static void manageRepositories() {
        JFrame frame = new JFrame("Manage Git Repositories");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        savedRepositories.forEach(listModel::addElement);

        JList<String> repoList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(repoList);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Repository");
        JButton removeButton = new JButton("Remove Selected");
        
        addButton.addActionListener(e -> {
            String newRepo = browseForRepository();
            if (newRepo != null && !savedRepositories.contains(newRepo)) {
                savedRepositories.add(newRepo);
                listModel.addElement(newRepo);
                saveRepositories();
                showNotification("Repository Added", "Added: " + newRepo);
            }
        });

        removeButton.addActionListener(e -> {
            int selectedIndex = repoList.getSelectedIndex();
            if (selectedIndex != -1) {
                String removedRepo = savedRepositories.remove(selectedIndex);
                listModel.remove(selectedIndex);
                saveRepositories();
                showNotification("Repository Removed", "Removed: " + removedRepo);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Repository Selection Methods
    private static String browseForRepository() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select Git Repository Folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int returnValue = fileChooser.showOpenDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String repoPath = selectedFile.getAbsolutePath();
            
            if (isValidGitRepository(repoPath)) {
                return repoPath;
            } else {
                showNotification("Repository Error", "Not a valid Git repository");
                return null;
            }
        }
        return null;
    }

    private static boolean isValidGitRepository(String directoryPath) {
        try {
            Path gitDir = Paths.get(directoryPath, ".git");
            return Files.exists(gitDir) && Files.isDirectory(gitDir);
        } catch (InvalidPathException | SecurityException e) {
            LOGGER.warning("Invalid repository path: " + directoryPath);
            return false;
        }
    }

    // Notification Method
    private static void showNotification(String title, String message) {
        JWindow window = new JWindow();
        window.setBackground(new Color(0, 0, 0, 0));

        JLabel label = new JLabel("<html><body style='background-color: #4CAF50; color: white; padding: 10px;'>" +
                "<b>" + title + "</b><br>" + message + "</body></html>");
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setOpaque(true);
        label.setBackground(new Color(76, 175, 80, 230));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        window.add(label);
        window.pack();

        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        
        int x = screenBounds.width - window.getWidth() - 20;
        int y = 20;
        window.setLocation(x, y);

        window.setVisible(true);

        Timer timer = new Timer(5000, e -> window.dispose());
        timer.setRepeats(false);
        timer.start();

        LOGGER.info("Notification: " + title + " - " + message);
    }

    // Main Application Menu
    private static void showMainMenu() {
        JFrame frame = new JFrame("Git Auto-Commit");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        
        JButton manageReposButton = new JButton("Manage Repositories");
        manageReposButton.addActionListener(e -> manageRepositories());

        JButton startAutoCommitButton = new JButton("Start Auto-Commit");
        startAutoCommitButton.addActionListener(e -> {
            if (savedRepositories.isEmpty()) {
                showNotification("Error", "No repositories configured");
                manageRepositories();
            } else {
                startAutoCommit();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(manageReposButton);
        panel.add(startAutoCommitButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Auto-Commit Method
    private static void startAutoCommit() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(savedRepositories.size());
        
        for (String repoPath : savedRepositories) {
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    commitRepository(repoPath);
                } catch (Exception e) {
                    LOGGER.severe("Error in repository: " + repoPath);
                    showNotification("Commit Error", "Failed to commit " + repoPath);
                }
            }, 0, COMMIT_INTERVAL_MINUTES, TimeUnit.MINUTES);
        }
    }

    // Commit Method (Simplified for demonstration)
    private static void commitRepository(String repoPath) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("git", "-C", repoPath, "add", ".");
        pb.start().waitFor();

        pb = new ProcessBuilder("git", "-C", repoPath, "commit", "-m", DEFAULT_COMMIT_MESSAGE);
        pb.start().waitFor();

        pb = new ProcessBuilder("git", "-C", repoPath, "push");
        pb.start().waitFor();

        showNotification("Commit Success", "Committed: " + repoPath);
    }

    // Application Entry Point
    public static void main(String[] args) {
        // Set system look and feel for a native appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LOGGER.warning("Could not set system look and feel");
        }

        // Show main application menu
        SwingUtilities.invokeLater(Every5min::showMainMenu);
    }
}