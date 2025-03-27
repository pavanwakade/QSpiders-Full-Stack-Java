package automate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GitAutoCommit {
    private static final int COMMIT_INTERVAL_MINUTES = 5;
    private static final String DEFAULT_COMMIT_MESSAGE = "Auto commit";
    private static final Logger LOGGER = Logger.getLogger(GitAutoCommit.class.getName());
    private static final List<String> repositories = new ArrayList<>();

    static {
        try {
            File logDir = new File(System.getProperty("user.home"), ".gitautocommit");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            FileHandler fileHandler = new FileHandler(
                new File(logDir, "gitautocommit.log").getAbsolutePath(), true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            for (String arg : args) {
                if (isGitRepository(arg)) {
                    repositories.add(arg);
                }
            }
        }

        if (repositories.isEmpty()) {
            LOGGER.info("No repositories provided via arguments. Starting auto-commit process and waiting for admin panel.");
        } else {
            LOGGER.info("Starting auto-commit with " + repositories.size() + " initial repositories.");
        }

        startAutoCommit();
    }

    public static void startAutoCommit() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            synchronized (repositories) {
                for (String repoPath : repositories) {
                    try {
                        commitAndPushChanges(repoPath, DEFAULT_COMMIT_MESSAGE);
                        LOGGER.info("Successfully committed changes in: " + repoPath);
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Error committing repository: " + repoPath, e);
                    }
                }
            }
        }, 0, COMMIT_INTERVAL_MINUTES, TimeUnit.MINUTES);
    }

    public static void commitAndPushChanges(String repoPath, String commitMessage) 
            throws IOException, InterruptedException {
        String gitPath = findGitExecutable();
        if (gitPath == null) {
            throw new IOException("Git executable not found!");
        }

        if (hasChanges(gitPath, repoPath)) {
            runCommand(new String[] { gitPath, "add", "." }, repoPath);
            runCommand(new String[] { gitPath, "commit", "-m", commitMessage }, repoPath);
            runCommand(new String[] { gitPath, "push" }, repoPath);
        } else {
            LOGGER.info("No changes to commit in: " + repoPath);
        }
    }

    private static boolean hasChanges(String gitPath, String repoPath) 
            throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(gitPath, "status", "--porcelain");
        pb.directory(new File(repoPath));
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        boolean hasChanges = reader.readLine() != null;
        reader.close();
        process.waitFor();
        return hasChanges;
    }

    private static String findGitExecutable() {
        List<String> possiblePaths = List.of(
            "C:\\Program Files\\Git\\bin\\git.exe",
            "C:\\Program Files (x86)\\Git\\bin\\git.exe"
        );

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
            // Ignore and return null
        }
        return null;
    }

    private static void runCommand(String[] command, String directory) 
            throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(new File(directory));
        pb.redirectErrorStream(true);
        Process process = pb.start();
        process.waitFor();
    }

    private static boolean isGitRepository(String directoryPath) {
        File gitDir = new File(directoryPath + File.separator + ".git");
        return gitDir.exists() && gitDir.isDirectory();
    }

    public static void addRepository(String repoPath) {
        synchronized (repositories) {
            if (isGitRepository(repoPath) && !repositories.contains(repoPath)) {
                repositories.add(repoPath);
                LOGGER.info("Added repository: " + repoPath);
                try {
                    commitAndPushChanges(repoPath, DEFAULT_COMMIT_MESSAGE);
                    LOGGER.info("Initial commit performed for: " + repoPath);
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error during initial commit for: " + repoPath, e);
                }
            } else if (!isGitRepository(repoPath)) {
                LOGGER.warning("Not a valid Git repository: " + repoPath);
            }
        }
    }

    public static void removeRepository(String repoPath) {
        synchronized (repositories) {
            repositories.remove(repoPath);
            LOGGER.info("Removed repository: " + repoPath);
        }
    }

    public static List<String> getRepositories() {
        synchronized (repositories) {
            return new ArrayList<>(repositories);
        }
    }
}