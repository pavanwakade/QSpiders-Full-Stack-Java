package AWT_Log_in;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPage {

	static JFrame frame;

//	constructor
	public RegisterPage() {
		createWindow();
		registor();
		button();

	}

	public static void main(String[] args) {

		RegisterPage window = new RegisterPage();
	}

	public static void createWindow() {
		frame = new JFrame("Register Page");
		frame.setLayout(new FlowLayout(1));
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Pictures\\photos\\download (2).jpeg");
		frame.setIconImage(img);
		frame.setVisible(true);
	}

	public static void registor() {
		JLabel jname = new JLabel("Name");
		frame.add(jname);
		JTextField jtname = new JTextField(20);
		frame.add(jtname);

		JLabel jdob = new JLabel("Date of Birth");
		frame.add(jdob);
		JTextField jtdob = new JTextField(20);
		frame.add(jtdob);

		JLabel jgender = new JLabel("Gender");
		frame.add(jgender);
		JTextField jtgender = new JTextField(20);
		frame.add(jtgender);

		JLabel jmobile = new JLabel("Mobile Number");
		frame.add(jmobile);
		JTextField jtmobile = new JTextField(20);
		frame.add(jtmobile);

		JLabel passwordLable = new JLabel("Password");
		frame.add(passwordLable);
		JPasswordField password = new JPasswordField(20);
		frame.add(password);
	}

	public static void button() {
		JButton Button = new JButton("Login");
		frame.add(Button);

		JButton cancle = new JButton("cancle");
		frame.add(cancle);

	}

}
