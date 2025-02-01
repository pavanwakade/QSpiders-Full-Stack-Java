package practice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class panal extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public panal() throws HeadlessException {
		super();
		createFrame();
	}

	void createFrame() {
		setSize(700, 500);
		setLayout(null); // Set layout to null for absolute positioning

		JPanel mainp = new JPanel();
		mainp.setBackground(Color.black);
		mainp.setLayout(null); // Set layout to null for absolute positioning
		mainp.setSize(700, 500);
		setLayout(new BorderLayout());
		
		

		JPanel o = new JPanel();
		o.setBackground(Color.yellow);
		o.setLayout(null);
		o.setSize(500, 500);
//		o.setAlignmentX(CENTER_ALIGNMENT);
		
//        mainp.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Center components with gaps

		
		
		mainp.add(o);

		
		JPanel s = new JPanel();
		s.setBackground(Color.blue);
		s.setSize(300, 300);
		s.setLayout(null);
		o.add(s);
		
		
		
		
		JPanel t = new JPanel();
		t.setBackground(Color.gray);
		t.setSize(150, 150);
//		t.setLayout(null);
		s.add(t);
		
		JButton m=new JButton("hello");
		m.setBackground(Color.blue);
		
		t.add(m);
		
		
	

//		add(mainp);
		add(mainp,BorderLayout.CENTER);
		
		mainp.setLayout(new FlowLayout(FlowLayout.CENTER));
		o.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));
//		o.setAlignmentY(BOTTOM_ALIGNMENT);
		s.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));
		t.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // Set visibility at the end after adding components
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new panal();
			}
		});
	}
}
