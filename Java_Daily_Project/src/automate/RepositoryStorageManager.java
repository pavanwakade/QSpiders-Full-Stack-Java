package automate;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class RepositoryStorageManager {
    private static final String CONFIG_DIR = System.getProperty("user.home") + File.separator + ".gitautocommit";
    private static final String REPOSITORIES_FILE = CONFIG_DIR + File.separator + "repositories.txt";
    private static final Logger LOGGER = Logger.getLogger(RepositoryStorageManager.class.getName());

    static {
        try {
            // Ensure configuration directory exists
            Files.createDirectories(Paths.get(CONFIG_DIR));
        } catch (IOException e) {
            System.err.println("Could not create configuration directory: " + e.getMessage());
        }
    }

    /**
     * Saves a repository path to the permanent storage
     * @param repositoryPath Path of the repository to be saved
     * @return true if successfully saved, false otherwise
     */
    public static boolean saveRepository(String repositoryPath) {
        try {
            // Validate repository
            if (!isValidGitRepository(repositoryPath)) {
                showNotification("Invalid Repository", "The path is not a valid Git repository: " + repositoryPath);
                return false;
            }

            // Check if repository already exists
            List<String> existingRepositories = loadRepositories();
            if (existingRepositories.contains(repositoryPath)) {
                showNotification("Duplicate Repository", "Repository already stored: " + repositoryPath);
                return false;
            }

            // Append new repository
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPOSITORIES_FILE, true))) {
                writer.write(repositoryPath);
                writer.newLine();
            }

            showNotification("Repository Saved", "Successfully stored repository: " + repositoryPath);
            return true;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving repository", e);
            showNotification("Error", "Could not save repository: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads all saved repositories
     * @return List of repository paths
     */
    public static List<String> loadRepositories() {
        List<String> repositories = new ArrayList<>();
        
        try {
            if (!Files.exists(Paths.get(REPOSITORIES_FILE))) {
                return repositories;
            }

            repositories = Files.readAllLines(Paths.get(REPOSITORIES_FILE))
                .stream()
                .filter(path -> isValidGitRepository(path))
                .toList();

        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error reading repositories file", e);
        }

        return repositories;
    }

    /**
     * Removes a repository from permanent storage
     * @param repositoryPath Path of the repository to remove
     * @return true if successfully removed, false otherwise
     */
    public static boolean removeRepository(String repositoryPath) {
        try {
            List<String> repositories = loadRepositories();
            boolean removed = repositories.removeIf(repo -> repo.equals(repositoryPath));

            if (removed) {
                // Rewrite the file without the removed repository
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPOSITORIES_FILE))) {
                    for (String repo : repositories) {
                        writer.write(repo);
                        writer.newLine();
                    }
                }

                showNotification("Repository Removed", "Successfully removed: " + repositoryPath);
                return true;
            }

            showNotification("Remove Failed", "Repository not found: " + repositoryPath);
            return false;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error removing repository", e);
            showNotification("Error", "Could not remove repository: " + e.getMessage());
            return false;
        }
    }

    /**
     * Interactive method to add repositories
     */
    public static void addRepositoriesInteractively() {
        boolean addMore = true;
        while (addMore) {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Select Git Repository");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();
                saveRepository(selectedPath);
            }

            int response = JOptionPane.showConfirmDialog(
                null, 
                "Do you want to add another repository?", 
                "Add Repository", 
                JOptionPane.YES_NO_OPTION
            );
            addMore = (response == JOptionPane.YES_OPTION);
        }
    }

    /**
     * Validates if a path is a Git repository
     * @param path Repository path to validate
     * @return true if valid Git repository, false otherwise
     */
    private static boolean isValidGitRepository(String path) {
        Path gitDir = Paths.get(path, ".git");
        return Files.exists(gitDir) && Files.isDirectory(gitDir);
    }

    /**
     * Shows a notification popup
     * @param title Notification title
     * @param message Notification message
     */
    private static void showNotification(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Example usage
        addRepositoriesInteractively();
        
        // List all stored repositories
        List<String> storedRepos = loadRepositories();
        System.out.println("Stored Repositories:");
        storedRepos.forEach(System.out::println);
    }
}