package AWT_Log_in;

import javax.swing.SwingUtilities;


public class LoginDriver {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Login();
			}
		});
	}
}
