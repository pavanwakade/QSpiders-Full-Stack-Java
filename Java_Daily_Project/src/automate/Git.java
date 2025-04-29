//git
package automate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class Git {
	// Configuration Constants
	private static final String CONFIG_DIR = System.getProperty("user.home") + File.separator + ".gitautocommit";
	private static final String CONFIG_FILE = CONFIG_DIR + File.separator + "config.properties";
	private static final String LOG_FILE = CONFIG_DIR + File.separator + "gitautocommit.log";

	// Default Configuration Values
	private static final int DEFAULT_COMMIT_INTERVAL_MINUTES = 1;
	private static final String DEFAULT_COMMIT_MESSAGE = "Updated";

	// Logging
	private static final Logger LOGGER = Logger.getLogger(Git.class.getName());
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

	private static void showPopup(String title, String message) {
		SwingUtilities.invokeLater(() -> {
			// Create a custom JDialog with a more modern look
			JDialog dialog = new JDialog();
			dialog.setUndecorated(true);
			dialog.setAlwaysOnTop(true);

			// Main panel with gradient background
			JPanel mainPanel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g.create();
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

					// Gradient background
					GradientPaint gradient = new GradientPaint(0, 0, new Color(25, 118, 210, 100), // Darker blue start
																									// (90% opacity =
																									// 229)
							0, getHeight(), new Color(33, 150, 243, 100) // Lighter blue end (90% opacity = 229)
					);
					g2d.setPaint(gradient);
					g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
					g2d.dispose();
				}
			};
			mainPanel.setLayout(new BorderLayout(10, 10));
			mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

			// Title label
			JLabel titleLabel = new JLabel(title);
			titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
			titleLabel.setForeground(Color.WHITE);

			// Message label
			JLabel messageLabel = new JLabel(message);
			messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			messageLabel.setForeground(Color.WHITE);

			// Icon (optional placeholder)
			JLabel iconLabel = new JLabel("pavan");
			iconLabel.setFont(new Font("Arial", Font.BOLD, 15));
			iconLabel.setForeground(Color.BLACK);

			// Layout components
			JPanel textPanel = new JPanel(new GridLayout(2, 1, 5, 5));
			textPanel.setOpaque(false);
			textPanel.add(titleLabel);
			textPanel.add(messageLabel);

			mainPanel.add(iconLabel, BorderLayout.WEST);
			mainPanel.add(textPanel, BorderLayout.CENTER);
			mainPanel.setBackground(new Color(0, 0, 0, 0));
			mainPanel.setOpaque(false);

			dialog.getContentPane().add(mainPanel);
			dialog.getContentPane().setBackground(new Color(0, 0, 0, 0));
			dialog.setBackground(new Color(0, 0, 0, 0));

			// Make dialog draggable
			Point offset = new Point();
			mainPanel.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					offset.x = e.getX();
					offset.y = e.getY();
				}
			});
			mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					dialog.setLocation(dialog.getLocation().x + e.getX() - offset.x,
							dialog.getLocation().y + e.getY() - offset.y);
				}
			});

			// Set dialog properties
			dialog.pack();

			// Position the dialog in the top-right corner
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int x = screenSize.width - dialog.getWidth() - 20; // 20 pixels from right edge
			int y = 20; // 20 pixels from top edge
			dialog.setLocation(x, y);

			// Soft shadow (using Border instead of opacity manipulation)
			dialog.getRootPane()
					.setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 1),
							BorderFactory.createEmptyBorder(5, 5, 5, 5)));

			// Opacity management using a float array
			float[] opacity = { 0.9f };
			Timer fadeOutTimer = new Timer(50, null);
			fadeOutTimer.addActionListener(e -> {
				opacity[0] -= 0.1f;
				if (opacity[0] <= 0) {
					dialog.dispose();
					fadeOutTimer.stop();
				} else {
					dialog.setOpacity(opacity[0]);
				}
			});

			Timer closeTimer = new Timer(5000, e -> {
				fadeOutTimer.start();
			});
			closeTimer.setRepeats(false);

			// Ensure the dialog is fully opaque before showing
			dialog.setOpacity(0.9f);
			dialog.setVisible(true);

			// Start timers
			closeTimer.start();

			// Logging
			LOGGER.info("Popup: " + title + " - " + message);
		});
	}

	private static boolean isInternetAvailable() {
		// Multiple reliable hosts for redundancy
		String[] checkUrls = { "https://www.google.com", "https://www.microsoft.com", "https://www.cloudflare.com",
				"https://1.1.1.1", // Cloudflare DNS
				"https://8.8.8.8" // Google DNS
		};

		// Timeout settings
		int connectTimeout = 7000; // 5 seconds
		int readTimeout = 7000; // 5 seconds

		for (String urlString : checkUrls) {
			HttpURLConnection connection = null;
			try {
				// Create URL connection
				URL url = new URL(urlString);
				connection = (HttpURLConnection) url.openConnection();

				// Set timeouts
				connection.setConnectTimeout(connectTimeout);
				connection.setReadTimeout(readTimeout);

				// Use HEAD request for lightweight check
				connection.setRequestMethod("HEAD");

				// Add User-Agent to prevent potential blocking
				connection.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

				// Attempt connection
				int responseCode = connection.getResponseCode();

				// Check for successful responses
				boolean isConnected = (responseCode == HttpURLConnection.HTTP_OK)
						|| (responseCode == HttpURLConnection.HTTP_MOVED_TEMP)
						|| (responseCode == HttpURLConnection.HTTP_MOVED_PERM);

				if (isConnected) {
					LOGGER.info("Internet connection verified through: " + urlString);
					return true;
				}
			} catch (Exception e) {
				// Log each failed attempt
				LOGGER.warning("Connection check failed for " + urlString + ": " + e.getMessage());
			} finally {
				// Ensure connection is closed
				if (connection != null) {
					connection.disconnect();
				}
			}
		}

		// Additional network interface check
		try {
			// Check network interfaces
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface iface = interfaces.nextElement();
				if (iface.isLoopback() || !iface.isUp())
					continue;

				// Check for at least one active interface
				return true;
			}
		} catch (SocketException e) {
			LOGGER.warning("Network interface check failed: " + e.getMessage());
		}

		// Comprehensive logging for no connection
		LOGGER.severe("No internet connection could be established");
		return false;
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

	// Save Configuration to Properties File
	private static void saveConfiguration() {
		Properties props = new Properties();

		try {
			// Ensure config directory exists
			Files.createDirectories(Paths.get(CONFIG_DIR));

			// Prepare properties
			if (!monitoredRepositories.isEmpty()) {
				props.setProperty("repositories", String.join(File.pathSeparator, monitoredRepositories));
			}
			props.setProperty("interval", String.valueOf(commitIntervalMinutes));
			props.setProperty("commitMessage", commitMessage);

			// Save to file
			try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
				props.store(fos, "Git Auto Commit Configuration");
				LOGGER.info("Configuration saved successfully");
			}
		} catch (IOException e) {
			LOGGER.severe("Error saving configuration: " + e.getMessage());
			showPopup("Configuration Error", "Could not save configuration: " + e.getMessage());
		}
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
	
	
	

	// Commit and Push Changes
	private static void commitAndPushChanges(String repoPath) throws IOException, InterruptedException {

		File lockFile = new File(repoPath + File.separator + ".git" + File.separator + "index.lock");
		if (lockFile.exists()) {
			LOGGER.warning("Git lock file found. Attempting to remove: " + lockFile.getPath());
			if (lockFile.delete()) {
				LOGGER.info("Successfully removed lock file");
			} else {
				LOGGER.severe("Could not remove lock file");
				showPopup("Git Error", "Unable to remove repository lock file. Manual intervention required.");
				return;
			}
		}

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
			try {
				// First pull changes from remote
				pullChanges(gitPath, repoPath);
				
				// Then check if there are local changes to commit
				if (hasChanges(gitPath, repoPath)) {

			// Proceed with commit and push
			try {
				// Git commands
				runCommand(new String[] { gitPath, "add", "." }, repoPath);
				runCommand(new String[] { gitPath, "commit", "-m", commitMessage }, repoPath);
				runCommand(new String[] { gitPath, "push" }, repoPath);

				showPopup("Commited", "Committed and pushed changes in " + repoPath);
				LOGGER.info("Committed and pushed changes in " + repoPath);
			} catch (IOException | InterruptedException e) {
				// Handle potential push failures
				showPopup("Commit Error", "Failed to push changes. Check internet connection.");
				LOGGER.severe("Failed to push changes: " + e.getMessage());
				
			}
			
		} else {
			LOGGER.info("No changes to commit for " + repoPath);
		}
				
	}catch (IOException | InterruptedException e) {
        // Handle pull failures
        showPopup("Pull Error", "Failed to pull changes from remote. Check network connectivity.");
        LOGGER.severe("Failed to pull changes: " + e.getMessage());
	}
    }
	}
	
			
			
			
			
			
			
			
		
	
	private static void pullChanges(String gitPath, String repoPath) throws IOException, InterruptedException {
	    LOGGER.info("Pulling changes from remote repository: " + repoPath);
	    try {
	        // Git pull command
	        runCommand(new String[] { gitPath, "pull" }, repoPath);
	        LOGGER.info("Successfully pulled changes from remote for " + repoPath);
	    } catch (IOException | InterruptedException e) {
	        LOGGER.severe("Failed to pull changes: " + e.getMessage());
	        throw e; // Re-throw to handle in calling method
	    }
	}
	
	// Schedule Repository Monitoring
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

	// Exception logging method
	private static void logException(String message, Throwable e) {
		LOGGER.log(Level.SEVERE, message, e);
		showPopup("Error", message + ": " + e.getMessage());
	}

	// Main method
	public static void main(String[] args) {
		// Set look and feel to improve popup appearance
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// Log error but continue
			LOGGER.warning("Could not set system look and feel: " + e.getMessage());
		}

		// Set up uncaught exception handler
		Thread.setDefaultUncaughtExceptionHandler(
				(thread, throwable) -> logException("Uncaught exception in thread " + thread.getName(), throwable));

		try {
			// Load configuration
			loadConfiguration();

			// If no repositories configured, open admin page
			if (monitoredRepositories == null || monitoredRepositories.isEmpty()) {
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
