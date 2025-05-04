import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class jj {
    
    private static JFrame frame;
    private static float opacity = 0.7f;
    private static Point initialClick;
    private static final int MINIMAL_SIZE = 50;
    
    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        // Create and set up the window without decorations
        frame = new JFrame();
        frame.setUndecorated(true); // Remove title bar and borders
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(150, 100);
        frame.setLocationRelativeTo(null); // Center on screen
        
        // Set always on top by default
        frame.setAlwaysOnTop(true);
        
        // Create panel with a layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        panel.setBackground(new Color(240, 240, 240, 200)); // Semi-transparent background
        
        // Make window semi-transparent
        makeTranslucent(opacity);
        
        // Add capability to drag window
        addDragCapability(panel);
        
        // Create a minimal control panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        controlPanel.setOpaque(false);
        
        // Create small buttons with icons
        JButton closeButton = createButton("×", "Close application");
        closeButton.addActionListener(e -> System.exit(0));
        
        JButton hideButton = createButton("_", "Minimize");
        hideButton.addActionListener(e -> minimizeApp());
        
        JButton opacityButton = createButton("○", "Toggle opacity");
        opacityButton.addActionListener(e -> toggleOpacity());
        
        controlPanel.add(opacityButton);
        controlPanel.add(hideButton);
        controlPanel.add(closeButton);
        
        panel.add(controlPanel, BorderLayout.NORTH);
        
        // Add resize capability
        addResizeCapability(panel);
        
        // Add a tray icon
        if (SystemTray.isSupported()) {
            setupTrayIcon();
        }
        
        // Add panel to frame
        frame.add(panel);
        
        // Add global hotkey listener
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Alt+Shift+T to toggle always on top
                if (e.isAltDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_T) {
                    frame.setAlwaysOnTop(!frame.isAlwaysOnTop());
                }
                // Alt+Shift+H to hide
                if (e.isAltDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_H) {
                    frame.setVisible(!frame.isVisible());
                }
            }
        });
        
        // Display the window
        frame.setVisible(true);
    }
    
    private static void addResizeCapability(JPanel panel) {
        // Add resize capability at bottom-right corner
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
                // Get current frame size
                int width = frame.getWidth();
                int height = frame.getHeight();
                
                // Calculate new width and height
                int newWidth = width + e.getX() - initialClick.x;
                int newHeight = height + e.getY() - initialClick.y;
                
                // Ensure minimum size
                newWidth = Math.max(MINIMAL_SIZE, newWidth);
                newHeight = Math.max(MINIMAL_SIZE, newHeight);
                
                // Set new size
                frame.setSize(newWidth, newHeight);
            }
        });
    }
    
    private static void addDragCapability(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                // No need to call getComponentAt here
            }
        });
        
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Get current location of window
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;
                
                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                
                // Move window to this position
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
    
    private static void minimizeApp() {
        frame.setState(Frame.ICONIFIED);
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
    
    private static void setupTrayIcon() {
        try {
            // Create a system tray icon
            SystemTray tray = SystemTray.getSystemTray();
            Image image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) image.getGraphics();
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, 16, 16);
            g2d.dispose();
            
            // Create a popup menu
            PopupMenu popup = new PopupMenu();
            
            // Create menu items
            MenuItem showItem = new MenuItem("Show/Hide");
            showItem.addActionListener(e -> frame.setVisible(!frame.isVisible()));
            
            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(e -> System.exit(0));
            
            // Add items to popup menu
            popup.add(showItem);
            popup.addSeparator();
            popup.add(exitItem);
            
            // Create tray icon
            TrayIcon trayIcon = new TrayIcon(image, "Discrete App", popup);
            trayIcon.setImageAutoSize(true);
            
            // Add tray icon to system tray
            tray.add(trayIcon);
            
            // Add double-click action
            trayIcon.addActionListener(e -> frame.setVisible(!frame.isVisible()));
            
        } catch (Exception e) {
            System.err.println("TrayIcon could not be added: " + e.getMessage());
        }
    }
}


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SystemService {
    
    private static JFrame frame;
    
    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread (EDT) for Swing thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        // Create a minimal JFrame (no UI elements)
        frame = new JFrame();
        frame.setUndecorated(true); // Remove title bar and borders
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(150, 100); // Minimal size, not visible
        frame.setLocationRelativeTo(null); // Center on screen (irrelevant since not visible)
        
        // Set always on top (optional, can be toggled via hotkeys if implemented)
        frame.setAlwaysOnTop(true);
        
        // Note: Hotkey listener is commented out because it requires a visible frame or
        // an external library (e.g., JIntellitype) for global hotkeys.
        /*
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Alt+Shift+T to toggle always on top
                if (e.isAltDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_T) {
                    frame.setAlwaysOnTop(!frame.isAlwaysOnTop());
                }
                // Alt+Shift+H to toggle visibility
                if (e.isAltDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_H) {
                    frame.setVisible(!frame.isVisible());
                }
            }
        });
        */
        
        // Do not set frame visible to avoid taskbar and visual detection
        // frame.setVisible(true);
        
        // Optional: Add background logic here (e.g., logging, monitoring)
        // For now, the app runs silently in the background
    }
}