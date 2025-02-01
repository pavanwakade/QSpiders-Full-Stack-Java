package swing_welcome_and_login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SignUp extends JFrame {

	private JTextField usernameField, mobnoField;

	private JCheckBox checkbox;

	private JButton signup, cancle;
	private JComboBox<String> destination;

	static ConeectionJDBC connection = new ConeectionJDBC();

	public SignUp() {

		setUpFrame();
		initializeComponents();
		setupActionListnear();
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
		mainPanel.setBackground(new Color(0, 0, 0, 0));

		JLabel title = new JLabel("Create User Account", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 28));
		title.setForeground(new Color(25, 194, 246));

		mainPanel.add(Box.createVerticalStrut(220));
		mainPanel.add(title);

		mainPanel.add(Box.createVerticalStrut(10));
		addRowForm("Username: ", usernameField, mainPanel);

		mainPanel.add(Box.createVerticalStrut(15));
		addRowForm("Mob.No: ", mobnoField, mainPanel);

		mainPanel.add(Box.createVerticalStrut(10));

		mainPanel.add(destination);
		mainPanel.add(Box.createVerticalStrut(10));

		mainPanel.add(checkbox);

		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(signup);

		add(mainPanel);
	}

	private void initializeComponents() {

		usernameField = createStyledTextField(20);

		mobnoField = createStyledTextField(10);

		checkbox = createStyleCheckbox("Agree terms to terms & conditions");

		signup = createStyleButton("Create Account");

		String[] combo = { "Select Destination","Software Developer/Engineer", "Full Stack Developer", "Front End Developer", "Back End Developer", "Mobile App Developer", "DevOps Engineer", "Cybersecurity Engineer", "Data Analyst", "Database Administrator", "UI/UX Designer", "AI/Machine Learning Engineer","Tester"};

		destination = new JComboBox<String>(combo);
		createStyleCombobox(destination);

	}

	private JTextField createStyledTextField(int width) {

		JTextField textField = new JTextField(width);
		textField.setFont(new Font("Arial", Font.PLAIN, 18));
		textField.setForeground(Color.white);
		textField.setBackground(new Color(30, 30, 30));
		textField.setCaretColor(Color.WHITE);
		return textField;

	}

	private void addRowForm(String title, JComponent theComponent, JPanel formPanel) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(new Color(0, 0, 0, 0));

		JLabel text = new JLabel(title);
		text.setFont(new Font("Arial", Font.BOLD, 18));
		text.setForeground(Color.white);
		panel.add(text);
		panel.add(Box.createHorizontalStrut(30));
		panel.add(theComponent);

		formPanel.add(panel);

		add(formPanel);
	}

	
	

	private void createStyleCombobox(JComboBox<String> dest) {

		dest.setFont(new Font("Arial", Font.BOLD, 19));
		dest.setBackground(new Color(33, 171, 230));
		dest.setForeground(Color.WHITE);
	}

	private JCheckBox createStyleCheckbox(String text) {
		JCheckBox checkbox = new JCheckBox(text);
		checkbox.setFont(new Font("Arial", Font.BOLD, 15));
		checkbox.setForeground(new Color(47, 129, 229));
		checkbox.setBackground(new Color(30, 30, 30));
		checkbox.setOpaque(false);

		return checkbox;
	}

	private JButton createStyleButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setForeground(Color.magenta);
		button.setBackground(Color.BLACK);
//		button.setBackground(new Color(30, 30, 30));

//		button.setOpaque(false);  
		button.setFocusPainted(false);

		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(new Color(33, 171, 230));
			}
		});

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(33, 171, 230));
			}
		});

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
	
	private void handlesignup() {

		String name = usernameField.getText().trim();
		String mob = mobnoField.getText().trim();
		String dest = (String) destination.getSelectedItem();
		long mobile = 0;

		if (!mob.isEmpty()) {
			try {
				mobile = Long.parseLong(mob);
			} catch (NumberFormatException e) {
				showErrorMessage("Mobile number should contain only digits.");
				return;
			}
		}

		if (!checkbox.isSelected()) {
			showErrorMessage("Agree to the terms and conditions.");
			return;
		}

		if (name.isEmpty() || mob.isEmpty() || dest.equals("Select Destination")) {
			showErrorMessage("Please fill all required fields.");
			return;
		}

		String query = "SELECT * FROM users WHERE name='" + name + "' AND mobile_number=" + mobile
				+ " AND destination='" + dest + "'";

		try {
			ResultSet rs = connection.s.executeQuery(query);

			if (rs.next()) {
				showSuccessMessage("Account Already Present");
			} else {
				String insert = "INSERT INTO users (name, destination, mobile_number) VALUES (?, ?, ?)";
				PreparedStatement conn = connection.c.prepareStatement(insert);
				conn.setString(1, name);
				conn.setString(2, dest);
				conn.setLong(3, mobile);
				conn.executeUpdate();

				ResultSet rsCheck = connection.s.executeQuery(query);

				if (rsCheck.next()) {
					showSuccessMessage("Account Created Successfully!");
					dispose();
				} else {
					showErrorMessage("Failed to create account. Please try again.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection.c != null) {
					connection.c.close();
					System.out.println("Database connection closed.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	void setupActionListnear() {
		signup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handlesignup();

			}
		});
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

	
	private void showSuccessMessage(String msg) {

		JOptionPane.showConfirmDialog(null, msg, "Susess", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
	}

	private void showErrorMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
		
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