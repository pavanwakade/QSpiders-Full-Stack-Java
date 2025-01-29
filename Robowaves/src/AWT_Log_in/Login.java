package AWT_Log_in;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Login {
	
	JFrame frame;
	JTextField text1;
	JPasswordField text2;

	public Login() {

		createFrame();
		createFields();
		createButtons();
	}

	public void createFrame() {
		frame = new JFrame("Login");

		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\download (2).jpeg");

		frame.setIconImage(icon);
		frame.setLayout(null);

		frame.setSize(450, 300);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void createFields() {
		JLabel label1 = new JLabel("Phone Number: ");

		label1.setBounds(60, 50, 120, 20);

		frame.add(label1);

		JLabel label2 = new JLabel("Password: ");

		label2.setBounds(83, 90, 80, 20);

		frame.add(label2);

		text1 = new JTextField();

		text1.setBounds(150, 53, 150, 20);
		frame.add(text1);

		text2 = new JPasswordField();

		text2.setBounds(150, 93, 150, 20);
//		 
		frame.add(text2);

		JLabel createAccount = new JLabel("Don't have an account?");

		createAccount.setBounds(140, 160, 200, 20);

		frame.add(createAccount);

		createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		createAccount.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				CreateAccount cr = new CreateAccount();
				frame.dispose();
			}
		});
	}

	public void createButtons() {
		JButton button1 = new JButton("Login");

		button1.setBounds(120, 130, 80, 20);
		frame.add(button1);

//		button1.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				frame.dispose();
//			}
//		});

		JButton button2 = new JButton("Cancel");
		button2.setBounds(220, 130, 80, 20);
		frame.add(button2);
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		ActionListener ac1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String phone = text1.getText();

				char pass[] = text2.getPassword();
				System.out.println(phone);
				System.out.println(pass);

				for (User user : DataBase.getDb()) {
					if (user.getPhno().equals(phone)) {
						if (user.getPassword().equals(new String(pass))) {

				

							JOptionPane.showMessageDialog(frame, "Login Successful!", "SUCCESS!",
									JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						} else {

							JOptionPane.showMessageDialog(frame, "Incorrect password", "Login Failed!",
									JOptionPane.ERROR_MESSAGE);

						}
					}
					else {
						JOptionPane.showMessageDialog(frame, "Incorrect Phone Number", "Login Failed!",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
		button1.addActionListener(ac1);

	}

}