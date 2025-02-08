package practice;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class RandomQuestionPopup {
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
                UIManager.put("OptionPane.messageForeground", Color.WHITE);
                UIManager.put("Button.background", new Color(52, 152, 219)); // Light Blue
                UIManager.put("Button.foreground", Color.WHITE);
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
//                                     make popup more stylish and colorfull