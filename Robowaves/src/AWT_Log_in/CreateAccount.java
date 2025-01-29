package AWT_Log_in;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccount {
	static ArrayList<User> tempdb;
	static {
		tempdb = DataBase.db;
	}
	JFrame frame;

	JTextField gUsername;
	JTextField gemail;
	JPasswordField gPassword;
	JTextField gDob;
	JTextField gGender;
	JTextField gPhone;

	static String userName;
	static String passWord;

	/**
	 * constructor
	 */
	public CreateAccount() {
		createFrame();
		createLable();
		loginuttons();

	}

	public void createFrame() {
		frame = new JFrame("Create Account");
		frame.setLayout(null);
		Image logo = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\download (2).jpeg");
		frame.setIconImage(logo);
		frame.setSize(500, 500);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void createLable() {
		JLabel main = new JLabel("Registration");
		main.setBounds(165, 30, 208, 60);
		main.setFont(new Font("Verdana", Font.BOLD, 20));
		frame.add(main);

		JLabel Lbl_userName = new JLabel("Username:");
		Lbl_userName.setBounds(120, 100, 80, 20);
		gUsername = new JTextField();
		gUsername.setBounds(190, 100, 150, 20);
		frame.add(Lbl_userName);
		frame.add(gUsername);

		JLabel Lbl_email = new JLabel("Email:");
		Lbl_email.setBounds(147, 140, 80, 20);
		frame.add(Lbl_email);
		gemail = new JTextField();
		gemail.setBounds(190, 140, 150, 20);
		frame.add(gemail);

		JLabel Lbl_Phone = new JLabel("Mobile Number:");
		Lbl_Phone.setBounds(93, 180, 89, 20);
		frame.add(Lbl_Phone);
		gPhone = new JTextField();
		gPhone.setBounds(190, 180, 150, 20);
		frame.add(gPhone);

		JLabel Lbl_DOB = new JLabel("Date Of Birth:");
		Lbl_DOB.setBounds(105, 220, 80, 20);
		frame.add(Lbl_DOB);
		gDob = new JTextField();
		gDob.setBounds(190, 220, 150, 20);
		frame.add(gDob);

		JLabel Lbl_gender = new JLabel("Gender:");
		Lbl_gender.setBounds(136, 260, 80, 20);
		frame.add(Lbl_gender);
		gGender = new JTextField();
		gGender.setBounds(190, 260, 150, 20);
		frame.add(gGender);
		
		
		JLabel Lbl_Password = new JLabel("Password:");
		Lbl_Password.setBounds(120, 300, 80, 20);
		frame.add(Lbl_Password);
		gPassword = new JPasswordField();
		gPassword.setBounds(190, 300, 150, 20);
		frame.add(gPassword);

	}

	public void loginuttons() {
		JButton login;
		JButton cancle;
		login = new JButton("Submit");
		login.setBounds(170, 340, 80, 20);
		frame.add(login);

//		login.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				frame.dispose();
//			}
//		});

		cancle = new JButton("cancle");
		cancle.setBounds(280, 340, 80, 20);
		frame.add(cancle);
		cancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});

		JLabel createAccount = new JLabel("I have an account?");
		createAccount.setBounds(210, 370, 200, 20);
		frame.add(createAccount);
		createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createAccount.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Login cr = new Login();
				frame.dispose();
			}
		});

		ActionListener ac1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String username = gUsername.getText();
				String dob = gDob.getText();
				String email = gemail.getText();
				String gender = gGender.getText();

				char pass[] = gPassword.getPassword();

				String ph = gPhone.getText();

				if (validUserName(username)) {

					if (validationPassword(pass)) {

						if (validatePhno(ph)) {

							if (DataBase.addUser(username, ph, new String(pass), email, dob, gender)) {
								writeDB(DataBase.db);
								System.out.println(username);
								System.out.println(pass);
								System.out.println(ph);

								System.out.println(DataBase.db);
								JOptionPane.showMessageDialog(frame, "Account Created Successfully!", "SUCCESS",
										JOptionPane.INFORMATION_MESSAGE);

								frame.dispose();
							} else {
								JOptionPane.showMessageDialog(frame, "Account Already Exists!", "Failed",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(frame, "Invalid Phone Number", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Invalid Password", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "INVALID USERNAME!", "ERROR", JOptionPane.ERROR_MESSAGE);
					;
				}

			}
		};
		login.addActionListener(ac1);

	}

	public boolean validationPassword(char[] pass) {

		boolean upper, lower, special, digit;
		upper = lower = special = digit = false;

		if (pass.length >= 8) {
			for (int i = 0; i < pass.length; i++) {
				char ch = pass[i];

				if (ch >= 'a' && ch <= 'z') {

					lower = true;

				}

				else if (ch >= 'A' && ch <= 'Z') {
					upper = true;

				}

				else if (ch >= '0' && ch <= '9') {

					digit = true;
				}

				else {
					special = true;
				}
				if (lower && upper && special && digit) {
					break;
				}

			}
			return lower && upper && special && digit;
		}
		return false;

	}

	public boolean validUserName(String name) {
		if (name != null) {
			for (int i = 0; i < name.length(); i++) {
				char ch = name.charAt(i);
				if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	
	
	public boolean validatePhno(String no) {
		if (no.length() == 10) {
			char ch = no.charAt(0);
			if (ch == '9' || ch == '8' || ch == '7' || ch == '6') {

				return true;
			}

		}
		return false;
	}
	
	public boolean writeDB(ArrayList<User> db) {
		try (FileOutputStream fOut = new FileOutputStream("D:\\coding with pavan\\Qspiders\\java\\internship\\src\\gui\\userdb.txt")) {

			ObjectOutputStream oOut = new ObjectOutputStream(fOut);

			oOut.writeObject(db);

			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Account could not be created!", "Failure", JOptionPane.ERROR_MESSAGE);

			return false;

		}
	}
}
