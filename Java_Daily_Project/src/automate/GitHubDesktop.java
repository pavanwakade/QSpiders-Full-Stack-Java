//kjejfhuifh
//ifhfh
package automate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GitHubDesktop {
    // You can change this to your repository path
    private static final String DEFAULT_REPO_PATH = "C:\\Users\\Admin\\Desktop\\Qspiders\\QSpiders-Full-Stack-Java";
    
    public static void main(String[] args) {
        String repoPath = DEFAULT_REPO_PATH;
        String commitMessage = "updated";
        
        // Allow command-line arguments to override defaults
        if (args.length >= 1) {
            repoPath = args[0];
        }
        if (args.length >= 2) {
            commitMessage = args[1];
        }
        
        try {
            commitAndPushChanges(repoPath, commitMessage);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void commitAndPushChanges(String repoPath, String commitMessage) throws IOException, InterruptedException {
        // Check if the directory exists and contains a .git folder
        File repoDir = new File(repoPath);
        File gitDir = new File(repoPath + File.separator + ".git");
        
        if (!repoDir.exists() || !repoDir.isDirectory()) {
            throw new IOException("Repository directory does not exist: " + repoPath);
        }
        
        if (!gitDir.exists() || !gitDir.isDirectory()) {
            throw new IOException("Not a git repository: " + repoPath);
        }
        
        // Find GitHub CLI executable (gh)
        String ghPath = findGitHubCLI();
        if (ghPath == null) {
            // If GitHub CLI not found, try to use git directly
            String gitPath = findGitExecutable();
            if (gitPath == null) {
                throw new IOException("Neither GitHub CLI nor Git executable found!");
            }
            useGitCommandLine(gitPath, repoPath, commitMessage);
        } else {
            useGitHubCLI(ghPath, repoPath, commitMessage);
        }
        
        System.out.println("Commit and push completed successfully!");
        JOptionPane.showMessageDialog(null, "Commit successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String findGitHubCLI() {
        List<String> possiblePaths = new ArrayList<>();
        
        // Common installation paths for GitHub CLI
        possiblePaths.add(System.getenv("LOCALAPPDATA") + "\\GitHub CLI\\gh.exe");
        possiblePaths.add("C:\\Program Files\\GitHub CLI\\gh.exe");
        possiblePaths.add("C:\\Program Files (x86)\\GitHub CLI\\gh.exe");
        
        for (String path : possiblePaths) {
            File file = new File(path);
            if (file.exists() && file.canExecute()) {
                return path;
            }
        }
        
        // Try to find in PATH
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
            // Ignore and continue to next method
        }
        
        return null;
    }
    
    private static String findGitExecutable() {
        List<String> possiblePaths = new ArrayList<>();
        
        // Common installation paths for Git
        possiblePaths.add("C:\\Program Files\\Git\\bin\\git.exe");
        possiblePaths.add("C:\\Program Files (x86)\\Git\\bin\\git.exe");
        
        for (String path : possiblePaths) {
            File file = new File(path);
            if (file.exists() && file.canExecute()) {
                return path;
            }
        }
        
        // Try to find in PATH
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
        // Use GitHub CLI for operations
        System.out.println("Using GitHub CLI to commit and push changes...");
        
        // Stage all changes
        runCommand(new String[]{ghPath, "repo", "sync"}, repoPath);
        
        // Create commit and push using GitHub CLI
        runCommand(new String[]{ghPath, "api", "repos/{owner}/{repo}/git/commits", "--method", "POST", 
                               "-f", "message=" + commitMessage}, repoPath);
        
        System.out.println("Changes committed and pushed using GitHub CLI.");
    }
    
    private static void useGitCommandLine(String gitPath, String repoPath, String commitMessage) throws IOException, InterruptedException {
        System.out.println("Using Git command line to commit and push changes...");
        
        // Stage all changes
        runCommand(new String[]{gitPath, "add", "."}, repoPath);
        
        // Create commit
        runCommand(new String[]{gitPath, "commit", "-m", commitMessage}, repoPath);
        
        // Push changes
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
        
        // Read and display output
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