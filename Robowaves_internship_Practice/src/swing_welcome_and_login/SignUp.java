package swing_welcome_and_login;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignUp extends JFrame {

 private JTextField usernameField, mobnoField;

 public SignUp() {
  
  setUpFrame();
  initializeComponents();
  addComponents();

 }

 private void setUpFrame() {
  setSize(800, 500);
  setVisible(true);
  setContentPane(createBackgroundPanel());
 }

 private JPanel createBackgroundPanel() {
  return new JPanel() {
   @Override
   protected void paintComponent(Graphics g) {

		ImageIcon icons = new ImageIcon("res/linkdein2.jpg");
    Image image = icons.getImage();
    double panelWidth = getWidth();
    double panelHeight = getHeight();

    double imageWidth = image.getWidth(this);
    double imageHeigth = image.getHeight(this);

    double scale = (Math.max(panelWidth / imageWidth, panelHeight / imageHeigth));

    int scaledWidth = ((int) (imageWidth * scale)); // draw image can have proper int value so casting

    int scaledHeight = ((int) (imageHeigth * scale));

    int x = ((int) (panelWidth - scaledWidth) / 2);

    int y = ((int) (panelHeight - scaledHeight) / 2);

    g.drawImage(image, x, y, scaledWidth, scaledHeight, this);

    g.setColor(new Color(0, 0, 0, 100));

    g.fillRect(0, 0, getWidth(), getHeight());

   }

  };

 }

 private void addComponents() {
  JPanel mainPanel = new JPanel();
  mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
  mainPanel.setBackground(new Color(0,0,0,0));

  JLabel title = new JLabel("User Create Account", SwingConstants.CENTER);
  title.setFont(new Font("Arial", Font.BOLD, 28));
  title.setForeground(new Color(25,194,246));
  
  mainPanel.add(title);
  mainPanel.add(Box.createVerticalStrut(310));
  
  addRowForm("Username: ", usernameField, mainPanel);
  mainPanel.add(Box.createVerticalStrut(50));
  addRowForm("Mob.No: ", mobnoField, mainPanel);
  
  add(mainPanel);
 }

 private JTextField createStyledTextField(int width) {

  JTextField textField = new JTextField(width);
  textField.setFont(new Font("Arial", Font.BOLD, 18));
  textField.setForeground(Color.white);
  textField.setBackground(new Color(30, 30, 30));
  return textField;

 }

 private void initializeComponents() {

  usernameField = createStyledTextField(20);
  mobnoField = createStyledTextField(10);

 }

 private void addRowForm(String title, JComponent theComponent, JPanel formPanel) {
  JPanel panel = new JPanel();
  panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
  panel.setBackground(new Color(0,0,0,0));

  JLabel text = new JLabel(title);
  text.setFont(new Font("Arial", Font.BOLD, 18));
  text.setForeground(Color.white);
  
  
  panel.add(text);
  panel.add(Box.createHorizontalStrut(30));
  panel.add(theComponent);
  
  formPanel.add(panel);
  
  add(formPanel);
 }

 public static void main(String[] args) {

  new SignUp();
 }

}