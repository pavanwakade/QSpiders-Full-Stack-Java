package swing_welcome_and_login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignIn extends JFrame {
	JTextField userNameField, mobileNumberField;
	private JCheckBox termsCheckbox;
	private JButton signinButton, cancleButton;

	/**
	 * 
	 */
	public SignIn() {
		setupFrame();
		initializeComponant();
		addComponant();
		setupListner();

		setVisible(true);

	}

	public void setupFrame() {
		setSize(700, 500);
		setContentPane(createBAckgroundImage());
	}

	public void addComponant() {

		JPanel formepanal = new JPanel();
		formepanal.setLayout(new BoxLayout(formepanal, BoxLayout.Y_AXIS));
		formepanal.setBackground(new Color(0, 0, 0, 0));

		JLabel title = new JLabel("User Sign- in", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 25));
		title.setForeground(Color.BLUE);
		formepanal.add(title);
		formepanal.add(Box.createVerticalStrut(20));

		addFormRow("User Name :", userNameField, formepanal);
		addFormRow("Mobile number :", mobileNumberField, formepanal);

		formepanal.add(termsCheckbox);
		formepanal.add(signinButton);

		add(formepanal);

	}

	private void addFormRow(String labletext, JComponent componant, JPanel formepanal) {

		JPanel rowpanal = new JPanel();
		rowpanal.setLayout(new BoxLayout(rowpanal, BoxLayout.X_AXIS));
		rowpanal.setBackground(new Color(0, 0, 0, 0));

		JLabel lable = new JLabel(labletext);
		lable.setForeground(Color.WHITE);
		lable.setFont(new Font("Arial", Font.PLAIN, 15));
		rowpanal.add(lable);

		rowpanal.add(Box.createHorizontalStrut(10));
		rowpanal.add(componant);

		formepanal.add(rowpanal);
		formepanal.add(Box.createVerticalStrut(10));

	}

	private void initializeComponant() {
		userNameField = createStyedTextField(20);

		mobileNumberField = createStyedTextField(20);

		termsCheckbox = createStyleCheckbox("Agree terms to terms & conditions");

		signinButton = createStyleButton("Sign-In", Color.magenta);
	}

	private JTextField createStyedTextField(int widthCols) {
		JTextField textField = new JTextField(widthCols);
		textField.setBackground(new Color(20, 20, 20));
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Arial", Font.PLAIN, 15));

		return textField;
	}

	private JCheckBox createStyleCheckbox(String text) {
		JCheckBox checkbox = new JCheckBox(text);
		checkbox.setBackground(new Color(30, 30, 30));
		checkbox.setFont(new Font("Arial", Font.BOLD, 15));
		checkbox.setForeground(new Color(47, 129, 229));
		checkbox.setOpaque(false);

		return checkbox;
	}

	private JButton createStyleButton(String text, Color bgcolor) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setForeground(Color.WHITE);
		button.setBackground(bgcolor);
		button.setOpaque(false);
		button.setFocusPainted(false);

		return button;
	}

	private void showerrormessage(String msg) {
		JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	private void handleSignin() {

		if (!termsCheckbox.isSelected()) {
			showerrormessage("Agree terms and Conditions");
			return;
		}

		String name = userNameField.getText().strip();

		String no = mobileNumberField.getText().strip();
		long phoneNo = 0;

		if (!no.isEmpty()) {
			try {
				phoneNo = Long.parseLong(no);
			} catch (NumberFormatException e) {
				showerrormessage("Mobile number should contain only digits.");
				return;
			}
		}

		if (name.isEmpty() || no.isEmpty()) {
			showerrormessage("Please fill all required fields.");
			return;
		}

		String query = "SELECT * FROM users WHERE name='" + name + "' AND mobile_number=" + phoneNo;
//	    String query = "SELECT * FROM users WHERE name='" + name + "' AND mobile_number=" + mobile + " AND destination='" + dest + "'";

		ConeectionJDBC connection = new ConeectionJDBC();
		try {
			ResultSet rs = connection.s.executeQuery(query);

			if (rs.next()) {
				JOptionPane.showMessageDialog(this, "Wellcome Back " + name, "Success",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				showerrormessage("invalid Credentioal");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				connection.c.close();
				System.out.println("dbclode");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void setupListner() {

		signinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				handleSignin();
			}
		});
	}

	public JPanel createBAckgroundImage() {

		return new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {

//				ImageIcon icon = new ImageIcon("D:\\coding with pavan\\Qspiders\\java\\newInternship\\res\\linkdein2.jpg");
				ImageIcon icon = new ImageIcon("res/linkdein2.jpg");

				Image image = icon.getImage();

				double panalwidth = getWidth();

				double panalhight = getHeight();

				double imagewidh = image.getWidth(this);

				double imagehight = image.getHeight(this);

				double scale = (Math.max(panalwidth / imagewidh, panalhight / imagehight));

				int scaledWidth = ((int) (imagewidh * scale));

				int scaledHight = ((int) (imagehight * scale));

				int p = ((int) (panalwidth - scaledWidth) / 2);

				int q = ((int) (panalhight - scaledHight) / 2);

				g.drawImage(image, p, q, scaledWidth, scaledHight, this);

				g.setColor(new Color(0, 0, 0, 100));

				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
	}

	public static void main(String[] args) {
		new SignIn();
	}

}
