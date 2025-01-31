package swing_welcome_and_login;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SignUp extends JFrame {

 private JTextField usernameField, mobnoField;

 private JCheckBox checkbox;
 
 private JButton signup,cancle;
 
 
 
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



 private void addComponents() {
  JPanel mainPanel = new JPanel();
  mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
  mainPanel.setBackground(new Color(0,0,0,0));

  JLabel title = new JLabel("User Create Account", SwingConstants.CENTER);
  title.setFont(new Font("Arial", Font.BOLD, 28));
  title.setForeground(new Color(25,194,246));
  
//  mainPanel.add(Box.createVerticalStrut(220));
  mainPanel.add(title);
  
  
  mainPanel.add(Box.createVerticalStrut(310));
  addRowForm("Username: ", usernameField, mainPanel);
  
  mainPanel.add(Box.createVerticalStrut(50));
  addRowForm("Mob.No: ", mobnoField, mainPanel);
  
  mainPanel.add(Box.createVerticalStrut(10));
  mainPanel.add(checkbox);
  
  
  mainPanel.add(Box.createVerticalStrut(10));
  mainPanel.add(signup);
  
  
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
  
  checkbox=createStyleCheckbox("Agree terms to terms & conditions");
  
  signup=createStyleButton("Create Account");

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

 private JCheckBox createStyleCheckbox(String text) {
		JCheckBox checkbox = new JCheckBox(text);
		 checkbox.setBackground(new Color(30, 30, 30));
		checkbox.setFont(new Font("Arial", Font.BOLD, 15));
		checkbox.setForeground(new Color(47,129,229));
		checkbox.setOpaque(false);

		return checkbox;
	}
 

	private JButton createStyleButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setForeground(Color.WHITE);
//		button.setBackground(Color.BLUE);
		  button.setBackground(new Color(30, 30, 30));

		button.setOpaque(false);
		button.setFocusPainted(false);

		
		winIcon();
		
		return button;
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
 
 
 public static void winIcon() {
	 SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getLookAndFeel());
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
 
 
 
 public static void main(String[] args) {

	 SwingUtilities.invokeLater(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			new SignUp();
		}
	});
  
 }

}