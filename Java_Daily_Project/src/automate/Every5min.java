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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.time.LocalDateTime;

public class Every5min {
    private static final int COMMIT_INTERVAL_MINUTES = 1;
    private static final String DEFAULT_COMMIT_MESSAGE = "updated";

    // Method to send desktop notifications
//    private static void sendNotification(String title, String message) {
//        try {
//            if (!SystemTray.isSupported()) {
//                System.out.println("System tray is not supported.");
//                return;
//            }

            private static void sendNotification(String title, String message) {
                try {
                    if (!SystemTray.isSupported()) {
                        System.out.println("System tray is not supported.");
                        return;
                    }

                    // Load the icon
                    java.awt.Image icon = null;
                    try {
                        // Try loading from resources
                        java.net.URL iconURL = Every5min.class.getResource("/icon.png");
                        if (iconURL != null) {
                            icon = Toolkit.getDefaultToolkit().getImage(iconURL);
                        }
                    } catch (Exception e) {
                        // If resource loading fails, try default icon
                        try {
                            icon = Toolkit.getDefaultToolkit().getImage("icon.png");
                        } catch (Exception ex) {
                            System.out.println("Could not load icon");
                        }
                    }

                    // Use a default icon if no custom icon is found
                    if (icon == null) {
                        icon = Toolkit.getDefaultToolkit().getImage(
                            Every5min.class.getResource("/java-icon.png")
                        );
                    }

                    SystemTray tray = SystemTray.getSystemTray();
                    TrayIcon trayIcon = new TrayIcon(icon, "Repository Notifier");
                    trayIcon.setImageAutoSize(true);
                    tray.add(trayIcon);

                    trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
                } catch (AWTException e) {
                    System.err.println("Could not create system tray notification");
                    e.printStackTrace();
                }
            }

    // Overloaded methods for different notification types
    private static void notifyCommit(String repoName) {
        sendNotification("Repository Commit", 
            "Commit successful for repository: " + repoName + 
            " at " + LocalDateTime.now());
    }

    private static void notifyPush(String repoName) {
        sendNotification("Repository Push", 
            "Push successful for repository: " + repoName + 
            " at " + LocalDateTime.now());
    }

    private static void notifyDeleteFile(String repoName, String fileName) {
        sendNotification("File Deleted", 
            "File " + fileName + " deleted in repository: " + repoName + 
            " at " + LocalDateTime.now());
    }

    public static void main(String[] args) {
        List<String> inputRepoPaths = new ArrayList<>();
        String commitMessage = DEFAULT_COMMIT_MESSAGE;
        
        // Check if arguments are provided
        if (args.length >= 1) {
            // First argument can be either a path or a commit message
            if (args[0].startsWith("-m=")) {
                commitMessage = args[0].substring(3);
            } else {
                inputRepoPaths.addAll(Arrays.asList(args));
            }
            
            // Check for commit message in other arguments
            for (String arg : args) {
                if (arg.startsWith("-m=")) {
                    commitMessage = arg.substring(3);
                    break;
                }
            }
        }
        
        // If no repos specified, ask user to browse for them
        if (inputRepoPaths.isEmpty()) {
            inputRepoPaths = browseForRepositories();
            if (inputRepoPaths.isEmpty()) {
                System.out.println("No repositories selected. Exiting program.");
                return;
            }
        }
        
        // Prompt for commit message if not specified in args
        if (commitMessage.equals(DEFAULT_COMMIT_MESSAGE)) {
            String userMessage = JOptionPane.showInputDialog(
                "Enter commit message (or cancel for default):",
                DEFAULT_COMMIT_MESSAGE
            );
            if (userMessage != null && !userMessage.trim().isEmpty()) {
                commitMessage = userMessage.trim();
            }
        }
        
        // Create a final copy of the repositories list for use in the lambda
        final List<String> repoPaths = new ArrayList<>(inputRepoPaths);
        final String finalCommitMessage = commitMessage;
        
        System.out.println("Auto-commit will run every " + COMMIT_INTERVAL_MINUTES + " minutes for these repositories:");
        for (String repo : repoPaths) {
            System.out.println("- " + repo);
        }
        System.out.println("Using commit message: \"" + finalCommitMessage + "\"");
        
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        
        scheduler.scheduleAtFixedRate(() -> {
            for (String repoPath : repoPaths) {
                try {
                    System.out.println("\nChecking repository: " + repoPath);
                    commitAndPushChanges(repoPath, finalCommitMessage);
                } catch (Exception e) {
                    System.err.println("Error processing repository: " + repoPath);
                    e.printStackTrace();
                }
            }
        }, 0, COMMIT_INTERVAL_MINUTES, TimeUnit.MINUTES);

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            scheduler.shutdown();
            e.printStackTrace();
        }
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
                
                // Verify it's a git repository
                if (isGitRepository(repoPath)) {
                    repos.add(repoPath);
                    System.out.println("Added repository: " + repoPath);
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "The selected folder is not a Git repository.\nPlease select a folder containing a .git directory.",
                        "Not a Git Repository",
                        JOptionPane.ERROR_MESSAGE
                    );
                    // Continue to allow selecting a different folder
                    continue;
                }
            } else {
                // User canceled the file chooser
                break;
            }
            
            int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to add another repository?",
                "Add Another Repository",
                JOptionPane.YES_NO_OPTION
            );
            
            addMore = (response == JOptionPane.YES_OPTION);
        }
        
        return repos;
    }
    
    private static boolean isGitRepository(String directoryPath) {
        File gitDir = new File(directoryPath + File.separator + ".git");
        return gitDir.exists() && gitDir.isDirectory();
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
            System.out.println("Commit and push completed successfully for " + repoPath + " at " + 
                java.time.LocalDateTime.now());
        } else {
            System.out.println("No changes to commit for " + repoPath + " at " + 
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
        
        // Check for deleted files
        ProcessBuilder pbDeleted = new ProcessBuilder(findGitExecutable(), "ls-files", "--deleted");
        pbDeleted.directory(new File(repoPath));
        Process deletedProcess = pbDeleted.start();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(deletedProcess.getInputStream()))) {
            String deletedFile;
            while ((deletedFile = reader.readLine()) != null) {
                notifyDeleteFile(new File(repoPath).getName(), deletedFile);
            }
        }
        deletedProcess.waitFor();
        
        runCommand(new String[]{ghPath, "api", "repos/{owner}/{repo}/git/commits", "--method", "POST", 
                               "-f", "message=" + commitMessage}, repoPath);
        
        notifyCommit(new File(repoPath).getName());
        notifyPush(new File(repoPath).getName());
        
        System.out.println("Changes committed and pushed using GitHub CLI.");
    }
    
    private static void useGitCommandLine(String gitPath, String repoPath, String commitMessage) throws IOException, InterruptedException {
        System.out.println("Using Git command line to commit and push changes...");
        
        // Check for deleted files
        ProcessBuilder pbDeleted = new ProcessBuilder(gitPath, "ls-files", "--deleted");
        pbDeleted.directory(new File(repoPath));
        Process deletedProcess = pbDeleted.start();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(deletedProcess.getInputStream()))) {
            String deletedFile;
            while ((deletedFile = reader.readLine()) != null) {
                notifyDeleteFile(new File(repoPath).getName(), deletedFile);
            }
        }
        deletedProcess.waitFor();

        runCommand(new String[]{gitPath, "add", "."}, repoPath);
        runCommand(new String[]{gitPath, "commit", "-m", commitMessage}, repoPath);
        runCommand(new String[]{gitPath, "push"}, repoPath);
        
        notifyCommit(new File(repoPath).getName());
        notifyPush(new File(repoPath).getName());
        
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