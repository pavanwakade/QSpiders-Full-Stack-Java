package automate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

public class Every5min {
    private static final String DEFAULT_REPO_PATH = "C:\\Users\\Admin\\Desktop\\Qspiders\\QSpiders-Full-Stack-Java";
    private static final int COMMIT_INTERVAL_MINUTES = 10;

    public static void main(String[] args) {
        final String repoPath;  // Made final
        final String commitMessage;  // Made final
        
        // Initialize with defaults
        if (args.length >= 1) {
            repoPath = args[0];
        } else {
            repoPath = DEFAULT_REPO_PATH;
        }
        
        if (args.length >= 2) {
            commitMessage = args[1];
        } else {
            commitMessage = "updated";
//            commitMessage = "auto-update " + System.currentTimeMillis();

        }

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        
        scheduler.scheduleAtFixedRate(() -> {
            try {
                commitAndPushChanges(repoPath, commitMessage);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }, 0, COMMIT_INTERVAL_MINUTES, TimeUnit.MINUTES);

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            scheduler.shutdown();
            e.printStackTrace();
        }
    }

    public static void commitAndPushChanges(String repoPath, String commitMessage) throws IOException, InterruptedException {
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
            if (ghPath == null) {
                useGitCommandLine(gitPath, repoPath, commitMessage);
            } else {
                useGitHubCLI(ghPath, repoPath, commitMessage);
            }
            System.out.println("Commit and push completed successfully at " + 
                java.time.LocalDateTime.now());
            JOptionPane.showMessageDialog(null, "Commit successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("No changes to commit at " + 
                java.time.LocalDateTime.now());
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
    
    private static void useGitHubCLI(String ghPath, String repoPath, String commitMessage) throws IOException, InterruptedException {
        System.out.println("Using GitHub CLI to commit and push changes...");
        
        runCommand(new String[]{ghPath, "repo", "sync"}, repoPath);
        runCommand(new String[]{ghPath, "api", "repos/{owner}/{repo}/git/commits", "--method", "POST", 
                               "-f", "message=" + commitMessage}, repoPath);
        
        System.out.println("Changes committed and pushed using GitHub CLI.");
    }
    
    private static void useGitCommandLine(String gitPath, String repoPath, String commitMessage) throws IOException, InterruptedException {
        System.out.println("Using Git command line to commit and push changes...");
        
        runCommand(new String[]{gitPath, "add", "."}, repoPath);
        runCommand(new String[]{gitPath, "commit", "-m", commitMessage}, repoPath);
        runCommand(new String[]{gitPath, "push"}, repoPath);
        
        System.out.println("Changes committed and pushed using Git.");
    }
    
    private static void runCommand(String[] command, String directory) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(new File(directory));
        pb.redirectErrorStream(true);
        
        System.out.println("Executing command in directory: " + directory);
        System.out.print("Command: ");
        for (String part : command) {
            System.out.print(part + " ");
        }
        System.out.println();
        
        Process process = pb.start();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Command failed with exit code: " + exitCode);
        }
    }
}