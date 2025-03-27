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
import javax.swing.filechooser.FileSystemView;

public class GitAutoCommit {
    private static final int COMMIT_INTERVAL_MINUTES = 1;
    private static final String DEFAULT_COMMIT_MESSAGE = "updated";
    private static final String CONFIG_DIR = System.getProperty("user.home") + File.separator + ".gitautocommit";
    private static final String REPOSITORIES_FILE = CONFIG_DIR + File.separator + "repositories.txt";
    private static final Logger LOGGER = Logger.getLogger(GitAutoCommit.class.getName());
    private static FileHandler fileHandler;
    
    

    static {
        try {
            Files.createDirectories(Paths.get(CONFIG_DIR));
            fileHandler = new FileHandler(CONFIG_DIR + File.separator + "gitautocommit.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize logging", e);
        }
    }

    private static void showNotification(String title, String message) {
        JWindow window = new JWindow();
        JLabel label = new JLabel("<html><body style='padding: 10px;'><b>" + title + "</b><br>" + message + "</body></html>");
        label.setOpaque(true);
        label.setBackground(new Color(76, 175, 80, 230));
        label.setForeground(Color.WHITE);
        window.add(label);
        window.pack();

        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        window.setLocation(screenBounds.width - window.getWidth() - 20, 20);
        window.setVisible(true);

        new Timer(5000, e -> window.dispose()).start();
        LOGGER.info("Notification: " + title + " - " + message);
    }

    private static void logException(String message, Throwable e) {
        LOGGER.log(Level.SEVERE, message, e);
        showNotification("Error", message + ": " + e.getMessage());
    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> 
            logException("Uncaught exception in thread " + thread.getName(), throwable));

        try {
            String commitMessage = DEFAULT_COMMIT_MESSAGE;
            List<String> repoPaths = processArguments(args);
            
            if (repoPaths.isEmpty()) {
                handleInteractiveMode();
            } else {
                for (String path : repoPaths) {
                    saveRepository(path);
                }
            }

            List<String> validRepos = loadRepositories();
            if (validRepos.isEmpty()) {
                showNotification("No Repositories", "No valid Git repositories found.");
                return;
            }

            if (args.length > 0 && args[0].startsWith("-m=")) {
                commitMessage = args[0].substring(3);
            } else {
                String userMessage = JOptionPane.showInputDialog(
                    "Enter commit message (or cancel for default):", 
                    DEFAULT_COMMIT_MESSAGE
                );
                if (userMessage != null && !userMessage.trim().isEmpty()) {
                    commitMessage = userMessage.trim();
                }
            }

            scheduleRepositoryMonitoring(validRepos, commitMessage);

        } catch (Exception e) {
            logException("Unexpected error in main", e);
            System.exit(1);
        }
    }

    private static List<String> processArguments(String[] args) {
        List<String> repoPaths = new ArrayList<>();
        for (String arg : args) {
            if (!arg.startsWith("-m=")) {
                File dir = new File(arg);
                if (dir.exists() && dir.isDirectory()) {
                    repoPaths.add(dir.getAbsolutePath());
                }
            }
        }
        return repoPaths;
    }

    private static void handleInteractiveMode() {
        String[] options = {"Add Repository", "Remove Repository", "List Repositories", "Start Monitoring"};
        while (true) {
            String choice = (String) JOptionPane.showInputDialog(
                null, "Select an action:", "Git Auto Commit",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]
            );
            
            if (choice == null) break;
            
            switch (choice) {
                case "Add Repository": addRepositoriesInteractively(); break;
                case "Remove Repository": removeRepositoryInteractively(); break;
                case "List Repositories": showRepositories(); break;
                case "Start Monitoring": return;
            }
        }
    }

    private static void addRepositoriesInteractively() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select Git Repository");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            saveRepository(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private static void removeRepositoryInteractively() {
        List<String> repos = loadRepositories();
        if (repos.isEmpty()) {
            showNotification("No Repositories", "No repositories to remove.");
            return;
        }
        String repo = (String) JOptionPane.showInputDialog(
            null, "Select repository to remove:", "Remove Repository",
            JOptionPane.QUESTION_MESSAGE, null, repos.toArray(), repos.get(0)
        );
        if (repo != null) removeRepository(repo);
    }

    private static void showRepositories() {
        List<String> repos = loadRepositories();
        showNotification("Stored Repositories", repos.isEmpty() ? 
            "No repositories stored" : String.join("\n", repos));
    }

    private static boolean saveRepository(String repositoryPath) {
        if (!isValidGitRepository(repositoryPath)) {
            showNotification("Invalid Repository", "Not a valid Git repository: " + repositoryPath);
            return false;
        }

        List<String> existingRepos = loadRepositories();
        if (existingRepos.contains(repositoryPath)) return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPOSITORIES_FILE, true))) {
            writer.write(repositoryPath);
            writer.newLine();
            showNotification("Repository Saved", "Added: " + repositoryPath);
            return true;
        } catch (IOException e) {
            logException("Error saving repository", e);
            return false;
        }
    }

    private static List<String> loadRepositories() {
        List<String> repositories = new ArrayList<>();
        try {
            if (Files.exists(Paths.get(REPOSITORIES_FILE))) {
                repositories.addAll(Files.readAllLines(Paths.get(REPOSITORIES_FILE))
                    .stream()
                    .filter(GitAutoCommit::isValidGitRepository)
                    .toList());
            }
        } catch (IOException e) {
            logException("Error loading repositories", e);
        }
        return repositories;
    }

    private static boolean removeRepository(String repositoryPath) {
        List<String> repositories = loadRepositories();
        if (repositories.remove(repositoryPath)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPOSITORIES_FILE))) {
                for (String repo : repositories) {
                    writer.write(repo);
                    writer.newLine();
                }
                showNotification("Repository Removed", "Removed: " + repositoryPath);
                return true;
            } catch (IOException e) {
                logException("Error removing repository", e);
            }
        }
        return false;
    }

    private static boolean isValidGitRepository(String path) {
        Path gitDir = Paths.get(path, ".git");
        return Files.exists(gitDir) && Files.isDirectory(gitDir);
    }

    private static void scheduleRepositoryMonitoring(List<String> repositories, String commitMessage) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(repositories.size());
        
        for (String repoPath : repositories) {
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    commitAndPushChanges(repoPath, commitMessage);
                } catch (Exception e) {
                    logException("Error processing repository: " + repoPath, e);
                }
            }, 0, COMMIT_INTERVAL_MINUTES, TimeUnit.MINUTES);
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

    private static void commitAndPushChanges(String repoPath, String commitMessage) 
            throws IOException, InterruptedException {
        String gitPath = findGitExecutable();
        if (gitPath == null) throw new IOException("Git executable not found!");

        if (hasChanges(gitPath, repoPath)) {
            String ghPath = findGitHubCLI();
            if (ghPath == null) {
                useGitCommandLine(gitPath, repoPath, commitMessage);
            } else {
                useGitHubCLI(ghPath, repoPath, commitMessage);
            }
            showNotification("Commit Successful", "Changes committed in " + repoPath);
        }
    }

    private static boolean hasChanges(String gitPath, String repoPath) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(gitPath, "status", "--porcelain");
        pb.directory(new File(repoPath));
        Process process = pb.start();
        boolean hasChanges = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null;
        process.waitFor();
        return hasChanges;
    }

    private static String findGitExecutable() {
        String[] paths = {
            "C:\\Program Files\\Git\\bin\\git.exe",
            "C:\\Program Files (x86)\\Git\\bin\\git.exe"
        };
        for (String path : paths) {
            if (new File(path).exists()) return path;
        }
        try {
            Process p = new ProcessBuilder("where", "git").start();
            String line = new BufferedReader(new InputStreamReader(p.getInputStream())).readLine();
            if (line != null && new File(line).exists()) return line;
        } catch (IOException e) {}
        return null;
    }

    private static String findGitHubCLI() {
        String[] paths = {
            System.getenv("LOCALAPPDATA") + "\\GitHub CLI\\gh.exe",
            "C:\\Program Files\\GitHub CLI\\gh.exe",
            "C:\\Program Files (x86)\\GitHub CLI\\gh.exe"
        };
        for (String path : paths) {
            if (new File(path).exists()) return path;
        }
        try {
            Process p = new ProcessBuilder("where", "gh").start();
            String line = new BufferedReader(new InputStreamReader(p.getInputStream())).readLine();
            if (line != null && new File(line).exists()) return line;
        } catch (IOException e) {}
        return null;
    }

    private static void useGitCommandLine(String gitPath, String repoPath, String commitMessage) 
            throws IOException, InterruptedException {
        runCommand(new String[] {gitPath, "add", "."}, repoPath);
        runCommand(new String[] {gitPath, "commit", "-m", commitMessage}, repoPath);
        runCommand(new String[] {gitPath, "push"}, repoPath);
    }

    private static void useGitHubCLI(String ghPath, String repoPath, String commitMessage) 
            throws IOException, InterruptedException {
        runCommand(new String[] {ghPath, "repo", "sync"}, repoPath);
        runCommand(new String[] {ghPath, "api", "repos/{owner}/{repo}/git/commits", 
            "--method", "POST", "-f", "message=" + commitMessage}, repoPath);
    }

    private static void runCommand(String[] command, String directory) 
            throws IOException, InterruptedException {
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
        
        if (process.waitFor() != 0) {
            throw new IOException("Command failed: " + Arrays.toString(command));
        }
    }
}