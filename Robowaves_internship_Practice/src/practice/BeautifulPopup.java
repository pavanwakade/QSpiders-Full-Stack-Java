package practice;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javazoom.jl.player.Player;

public class BeautifulPopup {
    private static final int POPUP_INTERVAL = 45 * 60 * 1000; // 45 minutes in milliseconds
    
    // Audio player class for MP3
    static class MP3Player {
        public static void playGreeting(String timeOfDay) {
            new Thread(() -> {
                try {
                    String audioFile = "C:\\Users\\Admin\\Desktop\\Qspiders\\QSpiders-Full-Stack-Java\\Robowaves_internship_Practice\\res\\greetings\\" + timeOfDay.toLowerCase() + ".mp3";
                    FileInputStream fis = new FileInputStream(audioFile);
                    Player player = new Player(fis);
                    player.play();
                    fis.close();
                } catch (Exception e) {
                    System.err.println("Error playing audio: " + e.getMessage());
                }
            }).start();
        }
    }

    static class CustomPopup extends JDialog {
        public CustomPopup(String message, String title) {
            setUndecorated(true);
            setBackground(new Color(0, 0, 0, 0));

            // Create main panel with rounded corners and gradient
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Create gradient background
                    GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(70, 130, 180),
                        0, getHeight(), new Color(100, 149, 237)
                    );
                    g2d.setPaint(gradient);
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                    // Add subtle border
                    g2d.setColor(new Color(255, 255, 255, 100));
                    g2d.setStroke(new BasicStroke(2f));
                    g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 18, 18);

                    g2d.dispose();
                }
            };
            panel.setLayout(new BorderLayout(10, 10));
            panel.setOpaque(false);

            // Create title label
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));

            // Create message label
            JLabel messageLabel = new JLabel(message);
            messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            messageLabel.setForeground(Color.WHITE);
            messageLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 15));

            // Create close button
            JButton closeButton = new JButton("×");
            closeButton.setFont(new Font("Arial", Font.BOLD, 16));
            closeButton.setForeground(Color.WHITE);
            closeButton.setContentAreaFilled(false);
            closeButton.setBorderPainted(false);
            closeButton.setFocusPainted(false);
            closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            closeButton.addActionListener(e -> dispose());

            // Add components to panel
            JPanel titlePanel = new JPanel(new BorderLayout());
            titlePanel.setOpaque(false);
            titlePanel.add(titleLabel, BorderLayout.WEST);
            titlePanel.add(closeButton, BorderLayout.EAST);

            panel.add(titlePanel, BorderLayout.NORTH);
            panel.add(messageLabel, BorderLayout.CENTER);

            add(panel);
            pack();

            // Adjust width based on message length
            int messageWidth = messageLabel.getPreferredSize().width + 50;
            int minWidth = 300;
            int maxWidth = 600;
            int finalWidth = Math.min(maxWidth, Math.max(minWidth, messageWidth));

            setSize(finalWidth, 100);
            setLocationRelativeTo(null);

            // Add fade-in effect
            Timer fadeTimer = new Timer();
            fadeTimer.scheduleAtFixedRate(new TimerTask() {
                float opacity = 0;

                @Override
                public void run() {
                    opacity += 0.1;
                    if (opacity > 1) {
                        opacity = 1;
                        fadeTimer.cancel();
                    }
                    SwingUtilities.invokeLater(() -> setOpacity(opacity));
                }
            }, 0, 20);

            // Auto-close after 5 seconds
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(() -> dispose());
                }
            }, 5000);
        }
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        showTimeBasedGreeting();
        Questions();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    CustomPopup popup = new CustomPopup("It's time to take a break, Sir! Relax and refresh yourself. 😊☕", "Reminder");
                    popup.setVisible(true);
                    MP3Player.playGreeting("reminder");
                });
            }
        }, POPUP_INTERVAL, POPUP_INTERVAL);
    }

    private static void showTimeBasedGreeting() {
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        String greeting;
        String emoji;
        String audioGreeting;

        if (hour >= 5 && hour < 12) {
            greeting = "Good morning Sir Have nice day!";
            emoji = "🌅";
            audioGreeting = "morning";
//            Questions();
        } else if (hour >= 12 && hour < 17) {
            greeting = "Good afternoon, Sir. Hope you are having a great day!!";
            emoji = "☀️";
            audioGreeting = "afternoon";
//            Questions();
        } else if (hour >= 17 && hour < 22) {
            greeting = "Good evening, Sir. Hope you had a wonderful day!!";
            emoji = "🌅";
            audioGreeting = "evening";
//            Questions();
        } else {
            greeting = "You need to sleep, Sir! Have a restful night!";
            emoji = "🌙";
            audioGreeting = "night";
//            Questions();
        }

        SwingUtilities.invokeLater(() -> {
            CustomPopup popup = new CustomPopup(greeting + " " + emoji, "Welcome");
            popup.setVisible(true);
            MP3Player.playGreeting(audioGreeting);

        });
    }
    
    public static void Questions() {
        String filePath = "C:\\Users\\Admin\\Desktop\\questions.txt"; // Updated path to the text file
        
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (lines.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The file is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Random random = new Random();
            while (true) {
                String randomQuestion = lines.get(random.nextInt(lines.size()));
                
                UIManager.put("OptionPane.background", new Color(44, 62, 80)); // Dark Blue-Grey
                UIManager.put("Panel.background", new Color(44, 62, 80));
                UIManager.put("OptionPane.messageForeground", Color.BLACK);
                UIManager.put("Button.background", new Color(52, 152, 219)); // Light Blue
                UIManager.put("Button.foreground", Color.BLACK);
                UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));
                
                Object[] options = {"Yes", "No"};
                JButton yesButton = new JButton("Yes");
                yesButton.setBackground(Color.GREEN);
                yesButton.setForeground(Color.WHITE);
                
                JButton noButton = new JButton("No");
                noButton.setBackground(Color.RED);
                noButton.setForeground(Color.WHITE);
                
                Object[] buttons = {yesButton, noButton};
                int response = JOptionPane.showOptionDialog(null,  "<html><h2 style='color:white; font-family:Arial;'>"+randomQuestion+"</h2></html>", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                
                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null,  "<html><h2 style='color:white;'>Nice! You Need To Study More.....</h2></html>");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
