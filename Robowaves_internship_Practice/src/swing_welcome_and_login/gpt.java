package swing_welcome_and_login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gpt extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String IMAGE_PATH = "res/linkdein2.jpg";

    private JTextField usernameField, mobnoField;
    private JCheckBox termsCheckbox;
    private JButton signupButton, cancelButton;

    public gpt() {
        setupFrame();
        initializeComponents();
        addComponents();
        addEventListeners();
    }

    private void setupFrame() {
        setTitle("User Registration");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(createBackgroundPanel());
    }

    private void initializeComponents() {
        usernameField = createStyledTextField(20);
        mobnoField = createStyledTextField(20);

        termsCheckbox = createStyledCheckbox("I agree to the terms & conditions");

        signupButton = createStyledButton("Create Account");
        cancelButton = createStyledButton("Cancel");
    }

    private void addComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(25, 194, 246));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(30));

        // Form fields
        addFormRow("Username:", usernameField, mainPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        addFormRow("Mobile Number:", mobnoField, mainPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Checkbox Panel
        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        checkboxPanel.setOpaque(false);
        checkboxPanel.add(termsCheckbox);
        mainPanel.add(checkboxPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(signupButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel);

        mainPanel.add(Box.createVerticalStrut(30));

        add(mainPanel);
    }

    
    private void addEventListeners() {
        signupButton.addActionListener(e -> handleSignup());
        cancelButton.addActionListener(e -> dispose());

        // Restrict mobile number input to digits only (max 10 digits)
        mobnoField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || mobnoField.getText().length() >= 10) {
                    e.consume();
                }
            }
        });
    }

    private void handleSignup() {
        if (!validateForm()) {
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Account created successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });

        dispose();
    }

    private boolean validateForm() {
        if (usernameField.getText().trim().isEmpty()) {
            showError("Please enter a username");
            return false;
        }

        if (mobnoField.getText().length() != 10) {
            showError("Please enter a valid 10-digit mobile number");
            return false;
        }

        if (!termsCheckbox.isSelected()) {
            showError("Please accept the terms and conditions");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private JTextField createStyledTextField(int width) {
        JTextField textField = new JTextField(width);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setForeground(Color.WHITE);
        textField.setBackground(new Color(30, 30, 30));
        textField.setCaretColor(Color.WHITE);
        return textField;
    }

    private JCheckBox createStyledCheckbox(String text) {
        JCheckBox checkbox = new JCheckBox(text);
        checkbox.setFont(new Font("Arial", Font.PLAIN, 14));
        checkbox.setForeground(Color.WHITE);
        checkbox.setBackground(new Color(0, 0, 0, 0));
        checkbox.setOpaque(false);
        return checkbox;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(47, 129, 229));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    private void addFormRow(String labelText, JComponent component, JPanel container) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        row.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);

        row.add(label);
        row.add(component);
        container.add(row);
    }

    private JPanel createBackgroundPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon icon = new ImageIcon(IMAGE_PATH);
                    Image image = icon.getImage();

                    if (image.getWidth(null) <= 0) {
                        throw new RuntimeException("Failed to load image");
                    }

                    drawScaledImage(g, image);
                    addDarkOverlay(g);
                } catch (Exception e) {
                    g.setColor(new Color(30, 30, 30));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }

            private void drawScaledImage(Graphics g, Image image) {
                double scale = Math.max(
                        (double) getWidth() / image.getWidth(null),
                        (double) getHeight() / image.getHeight(null)
                );

                int scaledWidth = (int) (image.getWidth(null) * scale);
                int scaledHeight = (int) (image.getHeight(null) * scale);
                int x = (getWidth() - scaledWidth) / 2;
                int y = (getHeight() - scaledHeight) / 2;

                g.drawImage(image, x, y, scaledWidth, scaledHeight, null);
            }

            private void addDarkOverlay(Graphics g) {
                g.setColor(new Color(0, 0, 0, 180));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            gpt signup = new gpt();
            signup.setVisible(true);
        });
    }
}
