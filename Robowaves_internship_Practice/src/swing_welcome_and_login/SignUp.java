package swing_welcome_and_login;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class  SignUp extends JFrame{
	JTextField userNameField,MobileNumberField;

	/**
	 * @throws HeadlessException
	 */
	public SignUp() {
		setupFrame();
		initalizeComponant();
		
	}

	
	private void setupFrame() {
		
		setSize(700, 500);
		setVisible(true);
	}
	
	
	private void componant() {
		
		 
	}
	
	
	private JTextField createStyleField(int width) {
		
		JTextField field=new JTextField(width);
		field.setFont(new Font("Arial",Font.BOLD,18));
		field.setForeground(Color.WHITE);
		field.setBackground(new Color(30,30,30));
		return field;
		
		
	}
	
	private void initalizeComponant() {
		
		userNameField=createStyleField(20);
		MobileNumberField=createStyleField(10);
	}

	public static void main(String[] args) {
		new SignUp();
	}
	
}
