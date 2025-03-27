// Main application class
package automate;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.time.LocalDateTime;

public class Every5min {
    // Configuration Constants
    private static final String CONFIG_DIR = System.getProperty("user.home") + File.separator + ".gitautocommit";
    private static final String CONFIG_FILE = CONFIG_DIR + File.separator + "config.properties";
    private static final String LOG_FILE = CONFIG_DIR + File.separator + "gitautocommit.log";
    
    // Default Configuration Values
    private static final int DEFAULT_COMMIT_INTERVAL_MINUTES = 5;
    private static final String DEFAULT_COMMIT_MESSAGE = "Auto commit";
    
    // Logging
    private static final Logger LOGGER = Logger.getLogger(Every5min.class.getName());
    private static FileHandler fileHandler;

    // Configuration Properties
    private static int commitIntervalMinutes;
    private static String commitMessage;
    private static List<String> monitoredRepositories;

    // Static Initialization Block for Logging
    static {
        try {
            // Create config and log directories
            Files.createDirectories(Paths.get(CONFIG_DIR));

            // Set up file logging
            fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load Configuration from Properties File
    private static void loadConfiguration() {
        Properties props = new Properties();
        
        try {
            File configFile = new File(CONFIG_FILE);
            
            // Set default values
            commitIntervalMinutes = DEFAULT_COMMIT_INTERVAL_MINUTES;
            commitMessage = DEFAULT_COMMIT_MESSAGE;
            monitoredRepositories = new ArrayList<>();

            if (configFile.exists()) {
                try (FileInputStream fis = new FileInputStream(configFile)) {
                    props.load(fis);

                    // Load repositories
                    String repoList = props.getProperty("repositories", "");
                    if (!repoList.isEmpty()) {
                        monitoredRepositories = new ArrayList<>(
                            Arrays.asList(repoList.split(File.pathSeparator))
                        );
                    }

                    // Load commit interval
                    try {
                        commitIntervalMinutes = Integer.parseInt(
                            props.getProperty("interval", 
                            String.valueOf(DEFAULT_COMMIT_INTERVAL_MINUTES))
                        );
                    } catch (NumberFormatException e) {
                        LOGGER.warning("Invalid interval in config, using default");
                        commitIntervalMinutes = DEFAULT_COMMIT_INTERVAL_MINUTES;
                    }

                    // Load commit message
                    commitMessage = props.getProperty(
                        "commitMessage", 
                        DEFAULT_COMMIT_MESSAGE
                    );

                    LOGGER.info("Configuration loaded successfully");
                    LOGGER.info("Repositories: " + monitoredRepositories);
                    LOGGER.info("Interval: " + commitIntervalMinutes + " minutes");
                    LOGGER.info("Commit Message: " + commitMessage);
                }
            } else {
                LOGGER.warning("No configuration file found. Using default settings.");
            }
        } catch (IOException e) {
            LOGGER.severe("Error loading configuration: " + e.getMessage());
        }
    }

    // Right-top notification method
    private static void showNotification(String title, String message) {
        SwingUtilities.invokeLater(() -> {
            JWindow window = new JWindow();
            window.setBackground(new Color(0, 0, 0, 0));

            JLabel label = new JLabel(
                "<html><body style='background-color: white; color: black; padding: 10px;'>" + 
                "<b>" + title + "</b><br>" + message + 
                "</body></html>"
            );
            label.setHorizontalAlignment(SwingConstants.LEFT);
            label.setOpaque(true);
            label.setBackground(new Color(76, 175, 80, 230)); // Material Green with transparency
            label.setForeground(Color.WHITE);

            window.add(label);
            window.pack();

            // Position notification at top-right of the screen
            Rectangle screenBounds = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .getBounds();

            int x = screenBounds.width - window.getWidth() - 20;
            int y = 20;
            window.setLocation(x, y);

            window.setVisible(true);

            // Auto-close notification after 5 seconds
            Timer timer = new Timer(5000, e -> window.dispose());
            timer.setRepeats(false);
            timer.start();

            LOGGER.info("Notification: " + title + " - " + message);
        });
    }

    // Exception logging method
    private static void logException(String message, Throwable e) {
        LOGGER.log(Level.SEVERE, message, e);
        showNotification("Error", message + ": " + e.getMessage());
    }

    // New method for displaying countdown
    private static void startCountdownClock(int intervalMinutes) {
        Thread countdownThread = new Thread(() -> {
            while (true) {
                try {
                    for (int remainingMinutes = intervalMinutes; remainingMinutes > 0; remainingMinutes--) {
                        for (int remainingSeconds = 59; remainingSeconds >= 0; remainingSeconds--) {
                            // Clear console and print countdown
                            clearConsole();
                            System.out.printf("Next auto-commit in: %02d:%02d\n", remainingMinutes, remainingSeconds);
                            
                            // Sleep for 1 second
                            Thread.sleep(1000);
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        countdownThread.setDaemon(true);
        countdownThread.start();
    }

    // Method to clear console
    private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix-like systems
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, just print some newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    // Find Git executable
    private static String findGitExecutable() {
        List<String> possiblePaths = Arrays.asList(
            "C:\\Program Files\\Git\\bin\\git.exe",
            "C:\\Program Files (x86)\\Git\\bin\\git.exe",
            "/usr/bin/git",
            "/usr/local/bin/git"
        );

        for (String path : possiblePaths) {
            File file = new File(path);
            if (file.exists() && file.canExecute()) {
                return path;
            }
        }

        // Try which/where command
        try {
            ProcessBuilder pb = new ProcessBuilder(
                System.getProperty("os.name").toLowerCase().contains("win") ? "where" : "which", 
                "git"
            );
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line = reader.readLine();
                if (line != null && new File(line).exists()) {
                    return line;
                }
            }
        } catch (IOException e) {
            LOGGER.warning("Could not find Git executable: " + e.getMessage());
        }

        return null;
    }

    // Check if repository has changes
    private static boolean hasChanges(String gitPath, String repoPath) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(gitPath, "status", "--porcelain");
        pb.directory(new File(repoPath));
        Process process = pb.start();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            boolean hasChanges = reader.readLine() != null;
            process.waitFor();
            return hasChanges;
        }
    }

    // Run Git command
    private static void runCommand(String[] command, String directory) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(new File(directory));
        pb.redirectErrorStream(true);

        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LOGGER.info(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Command failed with exit code: " + exitCode);
        }
    }

    // Commit and push changes
    private static void commitAndPushChanges(String repoPath) throws IOException, InterruptedException {
        File repoDir = new File(repoPath);
        File gitDir = new File(repoPath + File.separator + ".git");

        if (!repoDir.exists() || !repoDir.isDirectory()) {
            throw new IOException("Repository directory does not exist: " + repoPath);
        }

        if (!gitDir.exists() || !gitDir.isDirectory()) {
            throw new IOException("Not a git repository: " + repoPath);
        }

        String gitPath = findGitExecutable();
        if (gitPath == null) {
            throw new IOException("Git executable not found!");
        }

        if (hasChanges(gitPath, repoPath)) {
            // Git commands
            runCommand(new String[]{gitPath, "add", "."}, repoPath);
            runCommand(new String[]{gitPath, "commit", "-m", commitMessage}, repoPath);
            runCommand(new String[]{gitPath, "push"}, repoPath);

            showNotification("Git Commit", "Committed changes in " + repoPath);
            LOGGER.info("Committed changes in " + repoPath);
        } else {
            LOGGER.info("No changes to commit for " + repoPath);
        }
    }

    // Schedule repository monitoring
    private static void scheduleRepositoryMonitoring() {
        if (monitoredRepositories == null || monitoredRepositories.isEmpty()) {
            showNotification("Configuration Error", "No repositories configured for monitoring");
            return;
        }

        // Start the countdown clock
        startCountdownClock(commitIntervalMinutes);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(
            monitoredRepositories.size(), 
            runnable -> {
                Thread thread = new Thread(runnable);
                thread.setUncaughtExceptionHandler((t, e) -> 
                    logException("Error in repository monitoring thread", e)
                );
                return thread;
            }
        );

        for (String repoPath : monitoredRepositories) {
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    LOGGER.info("Checking repository: " + repoPath);
                    commitAndPushChanges(repoPath);
                } catch (Exception e) {
                    logException("Error processing repository: " + repoPath, e);
                }
            }, 0, commitIntervalMinutes, TimeUnit.MINUTES);
        }
    }

    // Main method
    public static void main(String[] args) {
        // Set up uncaught exception handler
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> 
            logException("Uncaught exception in thread " + thread.getName(), throwable)
        );

        try {
            // Load configuration
            loadConfiguration();

            // If no repositories configured, open admin page
            if (monitoredRepositories == null || monitoredRepositories.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null, 
                    "No repositories configured. Please add repositories.", 
                    "Configuration Required", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                // Launch admin page
                SwingUtilities.invokeLater(() -> {
                    GitAutoCommitAdmin adminPage = new GitAutoCommitAdmin();
                    adminPage.setVisible(true);
                });
                
                return;
            }

            // Schedule repository monitoring
            scheduleRepositoryMonitoring();

            // Keep the application running
            Thread.currentThread().join();

        } catch (Exception e) {
            logException("Unexpected error in main method", e);
            System.exit(1);
        }
    }
}

//// Stub for GitAutoCommitAdmin (minimal implementation)
//package automate;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class GitAutoCommitAdmin extends JFrame {
//    public GitAutoCommitAdmin() {
//        setTitle("Git Auto Commit Admin");
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Create a panel with a message
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        
//        JLabel messageLabel = new JLabel("Configure Git Auto Commit Repositories", SwingConstants.CENTER);
//        panel.add(messageLabel, BorderLayout.CENTER);
//
//        JTextArea instructionsArea = new JTextArea(
//            "To configure repositories:\n" +
//            "1. Edit the config.properties file in " + 
//            System.getProperty("user.home") + "/.gitautocommit/\n" +
//            "2. Add repositories separated by path separator\n" +
//            "3. Specify commit interval and message"
//        );
//        instructionsArea.setEditable(false);
//        panel.add(new JScrollPane(instructionsArea), BorderLayout.SOUTH);
//
//        add(panel);
//    }
//}