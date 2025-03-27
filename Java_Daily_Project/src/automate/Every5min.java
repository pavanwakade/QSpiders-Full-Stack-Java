package automate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Every5min {
    // Existing code remains the same, but modify the static fields
    private static int COMMIT_INTERVAL_MINUTES = 5; // Default value
    private static String DEFAULT_COMMIT_MESSAGE = "updated";
    private static final String CONFIG_FILE = System.getProperty("user.home") + "/.gitautocommit/config.properties";

    // Add a method to load configuration
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
            // Use default values if configuration can't be loaded
            LOGGER.warning("Could not load configuration: " + e.getMessage());
        }
    }

    // Modify main method to load configuration first
    public static void main(String[] args) {
        // Load configuration before processing
        loadConfiguration();

        // Rest of the existing main method remains the same
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

    // Rest of the code remains the same as in the original Every40min class
}