//Github Desktop Automate
package automate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class OpenGitHubDesktop {
    public static void main(String[] args) {
        openSoftware("GitHubDesktop");
    }

    public static void openSoftware(String keyword) {
        List<String> foundSoftware = new ArrayList<>();
        String[] directories = {
            "C:\\Program Files",
            "C:\\Program Files (x86)",
            System.getenv("LOCALAPPDATA")
        };

        for (String dir : directories) {
            searchFiles(new File(dir), keyword, foundSoftware);
        }

        if (!foundSoftware.isEmpty()) {
            System.out.println("Opening: " + foundSoftware.get(0));
            try {
                ProcessBuilder pb = new ProcessBuilder(foundSoftware.get(0));
                pb.start();
                Thread.sleep(8000); // Wait for GitHub Desktop to fully open
                typeInSummaryAndCommit("updated");
                pushToOrigin(); // Push to origin
                closeApplication(); // Close the application after pushing
            } catch (IOException | InterruptedException | AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("GitHub Desktop not found!");
        }
    }

    private static void searchFiles(File folder, String keyword, List<String> result) {
        if (folder != null && folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        searchFiles(file, keyword, result);
                    } else if (file.getName().toLowerCase().contains(keyword.toLowerCase()) && file.getName().endsWith(".exe")) {
                        result.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private static void typeInSummaryAndCommit(String message) throws AWTException {
        Robot robot = new Robot();
        // Ensure GitHub Desktop is in focus (Alt + Tab)
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.delay(5000); // Ensure focus on GitHub Desktop

        // Move the mouse to the "Summary (required)" field and click
        robot.mouseMove(220, 540); // Adjust these coordinates if necessary
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
        robot.delay(1000);

        // Clear existing text (Ctrl + A, Backspace)
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        robot.delay(500);

        // Type the commit message
        for (char c : message.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException("Cannot type character: " + c);
            }
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            robot.delay(100);
        }
        System.out.println("Typed in Summary: " + message);

        // Move to the "Commit to main" button and click
        robot.mouseMove(300, 700); // Adjust these coordinates if necessary
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
        System.out.println("Commit button pressed!");
        
        // Wait for commit process to complete
        robot.delay(3000);
    }
    
    private static void pushToOrigin() throws AWTException {
        Robot robot = new Robot();
        System.out.println("Looking for Push to origin button using keyboard navigation...");
        
        // Wait for the commit to finish and Push to origin button to be available
        robot.delay(2000);
        
        // Use Shift+Tab 8 times to navigate to the Push to origin button
        System.out.println("Pressing Shift+Tab 8 times to navigate to Push to origin button");
        for (int i = 0; i < 8; i++) {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.delay(500); // Small delay between key presses
        }
        
        // Press Enter to activate the Push to origin button
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        System.out.println("Push to origin initiated!");
        
        // Wait for push to complete
        robot.delay(5000);
    }
    
    private static void closeApplication() throws AWTException {
        Robot robot = new Robot();
        System.out.println("Closing GitHub Desktop application...");
        
        // Wait to ensure push is complete
        robot.delay(2000);
        
        // Press Alt+F4 to close the application
robot.keyPress(KeyEvent.VK_ALT);

        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
        
        System.out.println("GitHub Desktop closed successfully!");
        System.out.println("Commit successfully!");
        JOptionPane.showMessageDialog(null, "Commit successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

    }
}