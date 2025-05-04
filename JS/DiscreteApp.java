import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class DiscreteApp {
    
    private static JWindow frame;
    private static float opacity = 0.4f;
    private static Point initialClick;
    private static final int MINIMAL_SIZE = 50;
    private static boolean isMinimized = false;
    private static final Dimension NORMAL_SIZE = new Dimension(200, 150);
    private static final Dimension MINIMIZED_SIZE = new Dimension(20, 20);
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        frame = new JWindow();
        frame.setSize(NORMAL_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        panel.setBackground(new Color(240, 240, 240, 200));
        
        makeTranslucent(opacity);
        addDragCapability(panel);
        
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        controlPanel.setOpaque(false);
        
        JButton closeButton = createButton("×", "Close application");
        closeButton.addActionListener(e -> System.exit(0));
        
        JButton hideButton = createButton("_", "Minimize/Restore");
        hideButton.addActionListener(e -> toggleMinimize());
        
        JButton opacityButton = createButton("○", "Toggle opacity");
        opacityButton.addActionListener(e -> toggleOpacity());
        
        controlPanel.add(opacityButton);
        controlPanel.add(hideButton);
        controlPanel.add(closeButton);
        
        panel.add(controlPanel, BorderLayout.NORTH);
        
        JLabel label = new JLabel("Hello, World!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        panel.add(label, BorderLayout.CENTER);
        
        addResizeCapability(panel);
        frame.add(panel);
        
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isMinimized) {
                    toggleMinimize();
                }
            }
        });
        
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isAltDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_T) {
                    frame.setAlwaysOnTop(!frame.isAlwaysOnTop());
                }
                if (e.isAltDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_H) {
                    toggleMinimize();
                }
            }
        });
        
        frame.setVisible(true);
    }
    
    private static void addResizeCapability(JPanel panel) {
        JLabel resizeCorner = new JLabel("⌟");
        resizeCorner.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        resizeCorner.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(resizeCorner, BorderLayout.SOUTH);
        
        resizeCorner.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });
        
        resizeCorner.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (!isMinimized) {
                    int width = frame.getWidth();
                    int height = frame.getHeight();
                    int newWidth = width + e.getX() - initialClick.x;
                    int newHeight = height + e.getY() - initialClick.y;
                    newWidth = Math.max(MINIMAL_SIZE, newWidth);
                    newHeight = Math.max(MINIMAL_SIZE, newHeight);
                    frame.setSize(newWidth, newHeight);
                }
            }
        });
    }
    
    private static void addDragCapability(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });
        
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                frame.setLocation(X, Y);
            }
        });
    }
    
    private static void makeTranslucent(float opacity) {
        frame.setOpacity(opacity);
    }
    
    private static void toggleOpacity() {
        opacity = (opacity == 1.0f) ? 0.7f : 1.0f;
        makeTranslucent(opacity);
    }
    
    private static void toggleMinimize() {
        isMinimized = !isMinimized;
        if (isMinimized) {
            frame.setSize(MINIMIZED_SIZE);
        } else {
            frame.setSize(NORMAL_SIZE);
        }
    }
    
    private static JButton createButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(20, 20));
        button.setToolTipText(tooltip);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
    }
}