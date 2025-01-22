package newInternship;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Window1 extends JFrame {

	/**
	 * 
	 */
	public Window1() {
		createFrame();
		createButton();
		addComponants();
	}

	public void createFrame() {
		setSize(700, 700);
		setVisible(true);
	}

	private JButton SignIn, SignUp, AdminSignIn;
	Color SignInColor = Color.BLACK;
	Color SignUpColor = Color.BLACK;
	Color AdminSignInColor = Color.BLACK;

	public void addComponants() {
		
		JPanel contentPanal = new JPanel();

		contentPanal.setLayout(new BoxLayout(contentPanal, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Linkedin - Job web Portel", SwingConstants.CENTER);
		
		title.setFont(new Font("Arial", Font.BOLD, 21));
		title.setForeground(Color.BLACK);
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
		buttonpanal.add(SignIn);
		buttonpanal.add(SignUp);
		buttonpanal.add(AdminSignIn);

		contentPanal.add(Box.createVerticalGlue());

		contentPanal.add(title);
		contentPanal.add(buttonpanal);
		add(contentPanal);
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Window1();
			}
		});
	}
}
