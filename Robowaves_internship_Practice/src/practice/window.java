package practice;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class window extends JFrame {

    JButton login, cancel;
    JTextField usernamefield, passwordfield;
    JLabel userlabel, passwordLabel;
    JCheckBox checkbox;
    JComboBox<String> subject;

    /**
     * Constructor
     */
    public window() throws HeadlessException {
        createframe();
        initializeComponent();
        addComponent();
    }

    void createframe() {
        setTitle("Welcome");
        setSize(700, 500);
        Image logo = getToolkit().getImage("res/linkdein2.jpg");
        setIconImage(logo);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void initializeComponent() {
        usernamefield = createTextField(20);
        passwordfield=createTextField(20);
        add(Box.createHorizontalStrut(10));
        userlabel=creaJLabel("username :");
        passwordLabel=creaJLabel("password :");
    }

    void addComponent() {
        JPanel mpanel = new JPanel();
        mpanel.setLayout(new BoxLayout(mpanel,BoxLayout.X_AXIS));
        mpanel.setBackground(Color.yellow);
        
        
        JPanel field=new JPanel();
        field.add(usernamefield);
        field.setSize(300, 200);
       
        field.add(passwordfield);
        field.add(userlabel);
        field.add(passwordLabel);
        add(mpanel);
        mpanel.add(field);
    }

    
    JTextField createTextField(int width) {
        JTextField tfield = new JTextField(width);
        tfield.setForeground(Color.white);
        tfield.setBackground(Color.black);
        return tfield;
    }

    JLabel creaJLabel(String name) {
    	JLabel label=new JLabel(name);
    	
		return label;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new window();
            }
        });
    }
}
