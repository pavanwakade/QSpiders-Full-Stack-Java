package swing_welcome_and_login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class WelconePage extends JFrame {

	/**
	 * 
	 */
	public WelconePage() {

		createFrame();
		createButton();
		addComponants();
		setupListnearSignin();
		setupListnearSignup();
		setupListnearAdminSignin();
		winIcon();
	}

	public void createFrame() {
		setSize(700, 500);
		setVisible(true);
		setContentPane(createBAckgroundImage());
	}

	private JButton SignIn, SignUp, AdminSignIn;

	Color SignInColor = Color.BLACK;

	Color SignUpColor = Color.BLACK;

	Color AdminSignInColor = Color.BLACK;

	public void addComponants() {
		
	setLayout(new BorderLayout());

		JPanel contentPanal = new JPanel();

		contentPanal.setLayout(new BoxLayout(contentPanal, BoxLayout.Y_AXIS));

		contentPanal.setOpaque(false);

		JLabel title = new JLabel("Linkedin - Job web Portel", SwingConstants.CENTER);

		title.setFont(new Font("Arial", Font.BOLD, 26));

		title.setForeground(Color.BLUE);

		title.setAlignmentX(CENTER_ALIGNMENT);

		JPanel buttonpanal = new JPanel()

		{
			@Override
			protected void paintComponent(Graphics g) {

				g.setColor(new Color(0, 128, 128, 80));

				g.fillRect(0, 0, 10000, 70);
			}
		};

		buttonpanal.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		buttonpanal.setOpaque(false);

		buttonpanal.add(SignIn);

		buttonpanal.add(SignUp);

		buttonpanal.add(AdminSignIn);

		contentPanal.add(Box.createVerticalGlue());

		contentPanal.add(title);

		contentPanal.add(Box.createVerticalStrut(50));
		
		contentPanal.add(buttonpanal);

		add(contentPanal,BorderLayout.CENTER);
	}

	public JButton createStyleForButton(String text, Color color) {

		JButton button = new JButton(text);

		button.setFont(new Font("Arial", Font.BOLD, 15));

		button.setBackground(color);

		button.setForeground(Color.WHITE);

		button.setFocusPainted(false);

		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

				button.setBackground(color.blue);
			}

			@Override
			public void mouseExited(MouseEvent e) {

				button.setBackground(Color.BLACK);
			}
		});
		return button;
	}

	public void createButton() {

		this.SignIn = createStyleForButton("Sign-In", SignInColor);

		this.SignUp = createStyleForButton("Sign-Up", SignUpColor);

		this.AdminSignIn = createStyleForButton("Admin-SignIn", AdminSignInColor);
	}

	public void handleSignin() {

		int option = JOptionPane.showConfirmDialog(this, "Existing User", "conforn", JOptionPane.YES_NO_OPTION,

				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			
			new SignIn();
//			JOptionPane.showConfirmDialog(null, "SucessFull Loged", "Susess", JOptionPane.YES_NO_OPTION,
//					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void handleSignUp() {
		int option = JOptionPane.showConfirmDialog(this, "Existing User", "conforn", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.NO_OPTION) {

			new SignUp();

			JOptionPane.showConfirmDialog(null, "Well-Come to Registretion", "Susess", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);

		}
	}

	public void handleAdminSignIn() {
		int option = JOptionPane.showConfirmDialog(this, "Your Are Admin", "conforn", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {

			new AdminSignIn();

			JOptionPane.showConfirmDialog(null, "Well-Come to Admin", "Susess", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);

		}
	}
	
	
	void setupListnearSignin() {

		SignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				handleSignin();
			}
		});
	}

	void setupListnearSignup() {

		SignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				handleSignUp();
			}
		});
	}

	void setupListnearAdminSignin() {

		AdminSignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				handleAdminSignIn();
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

	
	public static void winIcon() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				
				new WelconePage();
			}
		});
	}
}
