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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import sun.awt.AWTUtilities;

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

    // Stylish Popup Method
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
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(76, 175, 80, 220),  // Material Green start
                        0, getHeight(), new Color(56, 142, 60, 220)  // Darker green end
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
            JLabel iconLabel = new JLabel("✓");
            iconLabel.setFont(new Font("Arial", Font.BOLD, 30));
            iconLabel.setForeground(Color.WHITE);

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
                    dialog.setLocation(
                        dialog.getLocation().x + e.getX() - offset.x,
                        dialog.getLocation().y + e.getY() - offset.y
                    );
                }
            });

            // Set dialog properties
            dialog.pack();
            dialog.setLocationRelativeTo(null);

            // Shadow effect
            AWTUtilities.setWindowOpacity(dialog, 0.9f);
            dialog.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 1, true));

            // Auto-close timer
            Timer closeTimer = new Timer(5000, e -> {
                // Fade out effect
                Timer fadeOut = new Timer(50, fadeEvent -> {
                    float opacity = AWTUtilities.getWindowOpacity(dialog);
                    opacity -= 0.1f;
                    if (opacity <= 0) {
                        dialog.dispose();
                        ((Timer)fadeEvent.getSource()).stop();
                    } else {
                        AWTUtilities.setWindowOpacity(dialog, opacity);
                    }
                });
                fadeOut.start();
            });
            closeTimer.setRepeats(false);
            closeTimer.start();

            // Show dialog
            dialog.setVisible(true);

            // Logging
            LOGGER.info("Popup: " + title + " - " + message);
        });
    }

    // Rest of the code remains the same as in the previous implementation...
    // (All other methods from the previous Every5min class should be included here)

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

    // (All other methods from the previous implementation)

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

// Note: You would also need to create a GitAutoCommitAdmin class 
// which is referenced in the main method but not provided in the original code
class GitAutoCommitAdmin extends JFrame {
    public GitAutoCommitAdmin() {
        // Implement the admin page UI for configuring repositories
        setTitle("Git Auto Commit Admin");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Basic layout and components would go here
        // This is a placeholder implementation
    }
}