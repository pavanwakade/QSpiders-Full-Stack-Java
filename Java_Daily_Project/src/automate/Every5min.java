package automate;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.filechooser.FileSystemView;

public class Every5min {
    private static final Logger LOGGER = Logger.getLogger(Every5min.class.getName());
    private static int COMMIT_INTERVAL_MINUTES = 5; // Default value
    private static String DEFAULT_COMMIT_MESSAGE = "Updated automatically";
    private static final String CONFIG_FILE = System.getProperty("user.home") + "/.gitautocommit/config.properties";

    // Method to load configuration
    private static void loadConfiguration() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);

            // Load repositories
            String repoList = props.getProperty("repositories", "");
            List<String> inputRepoPaths = new ArrayList<>(List.of(repoList.split(File.pathSeparator)));

            // Load commit interval
            COMMIT_INTERVAL_MINUTES = Integer.parseInt(props.getProperty("commitInterval", "5"));

            // Load commit message
            DEFAULT_COMMIT_MESSAGE = props.getProperty("commitMessage", "Updated automatically");
        } catch (IOException e) {
            LOGGER.warning("Could not load configuration: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Load configuration before processing
        loadConfiguration();

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            logException("Uncaught exception in thread " + thread.getName(), throwable);
        });

        try {
            List<String> inputRepoPaths = new ArrayList<>();
            String commitMessage = DEFAULT_COMMIT_MESSAGE;

            try {
                inputRepoPaths = processArguments(args, commitMessage);
            } catch (IllegalArgumentException e) {
                showNotification("Argument Error", e.getMessage());
                return;
            }

            List<String> validRepos = validateRepositories(inputRepoPaths);
            if (validRepos.isEmpty()) {
                showNotification("Repository Error", "No valid Git repositories selected.");
                return;
            }

            scheduleRepositoryMonitoring(validRepos, commitMessage);

        } catch (Exception e) {
            logException("Unexpected error in main method", e);
            System.exit(1);
        }
    }

    // Notification method
    private static void showNotification(String title, String message) {
        JWindow window = new JWindow();
        window.setBackground(new Color(0, 0, 0, 0));

        JLabel label = new JLabel("<html><body style='background-color: white; color: black; padding: 10px;'>" + "<b>"
                + title + "</b><br>" + message + "</body></html>");
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setOpaque(true);
        label.setBackground(new Color(76, 175, 80, 230)); // Material Green with transparency
        label.setForeground(Color.WHITE);

        window.add(label);
        window.pack();

        // Position notification at top-right of the screen
        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration().getBounds();

        int x = screenBounds.width - window.getWidth() - 20;
        int y = 20;
        window.setLocation(x, y);

        window.setVisible(true);

        // Auto-close notification after 5 seconds
        Timer timer = new Timer(5000, e -> window.dispose());
        timer.setRepeats(false);
        timer.start();

        LOGGER.info("Notification: " + title + " - " + message);
    }

    private static void logException(String message, Throwable e) {
        LOGGER.log(Level.SEVERE, message, e);
        showNotification("Error", message + ": " + e.getMessage());
    }

    private static List<String> processArguments(String[] args, String commitMessage) {
        List<String> inputRepoPaths = new ArrayList<>();

        if (args.length >= 1) {
            for (String arg : args) {
                if (arg.startsWith("-m=")) {
                    commitMessage = arg.substring(3);
                } else {
                    File potentialRepo = new File(arg);
                    if (potentialRepo.exists() && potentialRepo.isDirectory()) {
                        inputRepoPaths.add(potentialRepo.getAbsolutePath());
                    } else {
                        LOGGER.warning("Invalid repository path: " + arg);
                    }
                }
            }
        }

        if (inputRepoPaths.isEmpty()) {
            inputRepoPaths = browseForRepositories();
        }

        if (commitMessage.equals(DEFAULT_COMMIT_MESSAGE)) {
            String userMessage = JOptionPane.showInputDialog("Enter commit message (or cancel for default):",
                    DEFAULT_COMMIT_MESSAGE);
            if (userMessage != null && !userMessage.trim().isEmpty()) {
                commitMessage = userMessage.trim();
            }
        }

        return inputRepoPaths;
    }

    private static List<String> browseForRepositories() {
        List<String> repos = new ArrayList<>();
        boolean addMore = true;

        while (addMore) {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Select Git Repository Folder");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String repoPath = selectedFile.getAbsolutePath();

                if (isGitRepository(repoPath)) {
                    repos.add(repoPath);
                    showNotification("Repository Added", "Added: " + repoPath);
                } else {
                    showNotification("Repository Error", "Not a Git repository: " + repoPath);
                }
            } else {
                break;
            }

            int response = JOptionPane.showConfirmDialog(null, "Do you want to add another repository?",
                    "Add Another Repository", JOptionPane.YES_NO_OPTION);

            addMore = (response == JOptionPane.YES_OPTION);
        }

        return repos;
    }

    private static List<String> validateRepositories(List<String> repositories) {
        List<String> validRepos = new ArrayList<>();
        for (String repoPath : repositories) {
            try {
                Path gitDir = Paths.get(repoPath, ".git");
                if (Files.exists(gitDir) && Files.isDirectory(gitDir)) {
                    validRepos.add(repoPath);
                } else {
                    LOGGER.warning("Not a valid Git repository: " + repoPath);
                }
            } catch (SecurityException e) {
                LOGGER.severe("Security exception while checking repository: " + repoPath);
            }
        }
        return validRepos;
    }

    private static boolean isGitRepository(String directoryPath) {
        File gitDir = new File(directoryPath + File.separator + ".git");
        return gitDir.exists() && gitDir.isDirectory();
    }

    private static void scheduleRepositoryMonitoring(List<String> repositories, String commitMessage) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(repositories.size(), runnable -> {
            Thread thread = new Thread(runnable);
            thread.setUncaughtExceptionHandler((t, e) -> logException("Error in repository monitoring thread", e));
            return thread;
        });

        for (String repoPath : repositories) {
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    LOGGER.info("Checking repository: " + repoPath);
                    commitAndPushChanges(repoPath, commitMessage);
                } catch (Exception e) {
                    logException("Error processing repository: " + repoPath, e);
                }
            }, 0, COMMIT_INTERVAL_MINUTES, TimeUnit.MINUTES);
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            LOGGER.severe("Main thread interrupted: " + e.getMessage());
            scheduler.shutdownNow();
        }
    }

    public static void commitAndPushChanges(String repoPath, String commitMessage)
            throws IOException, InterruptedException {
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
            String ghPath = findGitHubCLI();
            if (ghPath != null) {
                useGitHubCLI(ghPath, repoPath, commitMessage);
                showNotification("GitHub CLI Commit", "Committed changes in " + repoPath);
            } else {
                useGitCommandLine(gitPath, repoPath, commitMessage);
                showNotification("Git Commit", "Changes pushed for " + repoPath);
            }
        } else {
            LOGGER.info("No changes to commit for " + repoPath);
        }
    }

    private static boolean hasChanges(String gitPath, String repoPath) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(gitPath, "status", "--porcelain");
        pb.directory(new File(repoPath));
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        boolean hasChanges = reader.readLine() != null;
        reader.close();
        process.waitFor();
        return hasChanges;
    }

    private static String findGitHubCLI() {
        List<String> possiblePaths = new ArrayList<>();

        possiblePaths.add(System.getenv("LOCALAPPDATA") + "\\GitHub CLI\\gh.exe");
        possiblePaths.add("C:\\Program Files\\GitHub CLI\\gh.exe");
        possiblePaths.add("C:\\Program Files (x86)\\GitHub CLI\\gh.exe");

        for (String path : possiblePaths) {
            File file = new File(path);
            if (file.exists() && file.canExecute()) {
                return path;
            }
        }

        try {
            ProcessBuilder pb = new ProcessBuilder("where", "gh");
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line = reader.readLine();
                if (line != null && new File(line).exists()) {
                    return line;
                }
            }
        } catch (IOException e) {
            // Ignore and continue
        }

        return null;
    }

    private static String findGitExecutable() {
        List<String> possiblePaths = new ArrayList<>();

        possiblePaths.add("C:\\Program Files\\Git\\bin\\git.exe");
        possiblePaths.add("C:\\Program Files (x86)\\Git\\bin\\git.exe");

        for (String path : possiblePaths) {
            File file = new File(path);
            if (file.exists() && file.canExecute()) {
                return path;
            }
        }

        try {
            ProcessBuilder pb = new ProcessBuilder("where", "git");
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line = reader.readLine();
                if (line != null && new File(line).exists()) {
                    return line;
                }
            }
        } catch (IOException e) {
            // Ignore and try next method
        }
        return null;
    }

    private static void useGitHubCLI(String ghPath, String repoPath, String commitMessage)
            throws IOException, InterruptedException {
        runCommand(new String[] { ghPath, "repo", "sync" }, repoPath);
        runCommand(new String[] { ghPath, "api", "repos/{owner}/{repo}/git/commits", "--method", "POST", "-f",
                "message=" + commitMessage }, repoPath);
        showNotification("GitHub CLI", "Commit successful for " + repoPath);
    }

    private static void useGitCommandLine(String gitPath, String repoPath, String commitMessage)
            throws IOException, InterruptedException {
        runCommand(new String[] { gitPath, "add", "." }, repoPath);
        runCommand(new String[] { gitPath, "commit", "-m", commitMessage }, repoPath);
        runCommand(new String[] { gitPath, "push" }, repoPath);
        showNotification("Git Commit", "Changes pushed for " + repoPath);
    }

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
}
