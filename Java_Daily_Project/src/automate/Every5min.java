package automate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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
	private static final int DEFAULT_COMMIT_INTERVAL_MINUTES = 1;
	private static final String DEFAULT_COMMIT_MESSAGE = "Updated";

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

	private static boolean isInternetAvailable() {
		try {
			// Attempt to connect to a reliable host
			URL url = new URL("https://www.google.com");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(3000);
			connection.setRequestMethod("HEAD");

			int responseCode = connection.getResponseCode();
			return (responseCode == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			LOGGER.warning("Internet connection check failed: " + e.getMessage());
			return false;
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
						monitoredRepositories = new ArrayList<>(Arrays.asList(repoList.split(File.pathSeparator)));
					}

					// Load commit interval
					try {
						commitIntervalMinutes = Integer.parseInt(
								props.getProperty("interval", String.valueOf(DEFAULT_COMMIT_INTERVAL_MINUTES)));
					} catch (NumberFormatException e) {
						LOGGER.warning("Invalid interval in config, using default");
						commitIntervalMinutes = DEFAULT_COMMIT_INTERVAL_MINUTES;
					}

					// Load commit message
					commitMessage = props.getProperty("commitMessage", DEFAULT_COMMIT_MESSAGE);

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

	// Popup method to replace showNotification
	private static void showPopup(String title, String message) {
		SwingUtilities.invokeLater(() -> {
			// Create a custom JOptionPane with a green theme
			UIManager.put("OptionPane.background", new Color(76, 175, 80));
			UIManager.put("Panel.background", new Color(76, 175, 80));

			JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.DEFAULT_OPTION);

			JDialog dialog = optionPane.createDialog(null, title);
			dialog.setModal(false);
			dialog.setVisible(true);

			// Auto-close dialog after 5 seconds
			Timer timer = new Timer(5000, e -> dialog.dispose());
			timer.setRepeats(false);
			timer.start();

			LOGGER.info("Popup: " + title + " - " + message);
		});
	}

	// Exception logging method
	private static void logException(String message, Throwable e) {
		LOGGER.log(Level.SEVERE, message, e);
		showPopup("Error", message + ": " + e.getMessage());
	}

	// Find Git executable
	private static String findGitExecutable() {
		List<String> possiblePaths = Arrays.asList("C:\\Program Files\\Git\\bin\\git.exe",
				"C:\\Program Files (x86)\\Git\\bin\\git.exe", "/usr/bin/git", "/usr/local/bin/git");

		for (String path : possiblePaths) {
			File file = new File(path);
			if (file.exists() && file.canExecute()) {
				return path;
			}
		}

		// Try which/where command
		try {
			ProcessBuilder pb = new ProcessBuilder(
					System.getProperty("os.name").toLowerCase().contains("win") ? "where" : "which", "git");
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

	// Modify commitAndPushChanges to check internet connectivity
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

		// Check if there are changes to commit
		if (hasChanges(gitPath, repoPath)) {
			// Check internet connectivity
			if (!isInternetAvailable()) {
				// Show popup about no internet connection
				showPopup("No Internet", "Unable to push changes. Internet connection is offline.");
				LOGGER.warning("No internet connection. Skipping push for repository: " + repoPath);
				return;
			}

			// Proceed with commit and push
			try {
				// Git commands
				runCommand(new String[] { gitPath, "add", "." }, repoPath);
				runCommand(new String[] { gitPath, "commit", "-m", commitMessage }, repoPath);
				runCommand(new String[] { gitPath, "push" }, repoPath);

				showPopup("Git Commit", "Committed and pushed changes in " + repoPath);
				LOGGER.info("Committed and pushed changes in " + repoPath);
			} catch (IOException | InterruptedException e) {
				// Handle potential push failures
				showPopup("Commit Error", "Failed to push changes. Check internet connection.");
				LOGGER.severe("Failed to push changes: " + e.getMessage());
			}
		} else {
			LOGGER.info("No changes to commit for " + repoPath);
		}
	}

	// Modify scheduleRepositoryMonitoring to handle internet connectivity
	private static void scheduleRepositoryMonitoring() {
		if (monitoredRepositories == null || monitoredRepositories.isEmpty()) {
			showPopup("Configuration Error", "No repositories configured for monitoring");
			return;
		}

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(monitoredRepositories.size(),
				runnable -> {
					Thread thread = new Thread(runnable);
					thread.setUncaughtExceptionHandler(
							(t, e) -> logException("Error in repository monitoring thread", e));
					return thread;
				});

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
		Thread.setDefaultUncaughtExceptionHandler(
				(thread, throwable) -> logException("Uncaught exception in thread " + thread.getName(), throwable));

		try {
			// Load configuration
			loadConfiguration();

			// If no repositories configured, open admin page
			if (monitoredRepositories == null || monitoredRepositories.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No repositories configured. Please add repositories.",
						"Configuration Required", JOptionPane.INFORMATION_MESSAGE);

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