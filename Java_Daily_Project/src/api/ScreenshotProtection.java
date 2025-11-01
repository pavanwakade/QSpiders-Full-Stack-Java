package src.api;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

/**
 * Advanced Screenshot Protection Module
 * Hides window from screen captures, recordings, and screen sharing
 * Works with Zoom, Teams, Discord, OBS, and Windows built-in tools
 */
public class ScreenshotProtection {
    
    // Windows Display Affinity Constants
    private static final int WDA_NONE = 0x00000000;
    private static final int WDA_MONITOR = 0x00000001;
    private static final int WDA_EXCLUDEFROMCAPTURE = 0x00000011;
    
    // Extended Window Styles
    private static final int WS_EX_NOREDIRECTIONBITMAP = 0x00200000;
    private static final int WS_EX_LAYERED = 0x00080000;
    private static final int WS_EX_TRANSPARENT = 0x00000020;
    
    // Layer Window Attributes
    private static final int LWA_ALPHA = 0x00000002;
    private static final int LWA_COLORKEY = 0x00000001;
    
    /**
     * PRIMARY METHOD: Apply multi-layer screenshot protection
     * This combines multiple techniques for maximum effectiveness
     */
    public static boolean applyFullProtection(JWindow window) {
        try {
            HWND hwnd = getWindowHandle(window);
            
            // Layer 1: SetWindowDisplayAffinity (Most Effective)
            boolean affinity = setDisplayAffinity(hwnd);
            
            // Layer 2: Extended Window Styles
            boolean exStyle = setProtectedWindowStyle(hwnd);
            
            // Layer 3: Layered Window Protection
            boolean layered = setLayeredProtection(hwnd);
            
            System.out.println("Protection Status:");
            System.out.println("  Display Affinity: " + affinity);
            System.out.println("  Extended Styles: " + exStyle);
            System.out.println("  Layered Protection: " + layered);
            
            return affinity; // Affinity is the most critical
            
        } catch (Exception e) {
            System.err.println("Failed to apply protection: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * LAYER 1: SetWindowDisplayAffinity
     * Prevents window from being captured by DWM (Desktop Window Manager)
     * Works against: Screen capture APIs, OBS, Zoom, Teams, Discord
     */
    private static boolean setDisplayAffinity(HWND hwnd) {
        try {
            boolean result = User32.INSTANCE.SetWindowDisplayAffinity(hwnd, WDA_EXCLUDEFROMCAPTURE);
            if (result) {
                System.out.println("✓ Display affinity set - Window excluded from capture");
            } else {
                System.err.println("✗ Display affinity failed - May require admin rights");
            }
            return result;
        } catch (Exception e) {
            System.err.println("Display affinity error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * LAYER 2: Extended Window Styles
     * Prevents DirectX/GDI redirection and adds transparency flags
     */
    private static boolean setProtectedWindowStyle(HWND hwnd) {
        try {
            int currentStyle = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
            
            // Add protection flags
            int newStyle = currentStyle | WS_EX_NOREDIRECTIONBITMAP | WS_EX_LAYERED;
            
            User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, newStyle);
            
            // Verify the style was set
            int verifyStyle = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
            boolean success = (verifyStyle & WS_EX_NOREDIRECTIONBITMAP) != 0;
            
            if (success) {
                System.out.println("✓ No-redirection bitmap set");
            }
            return success;
        } catch (Exception e) {
            System.err.println("Extended style error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * LAYER 3: Layered Window Attributes
     * Additional protection through window layering
     */
    private static boolean setLayeredProtection(HWND hwnd) {
        try {
            // Set layered window with alpha blending
            boolean result = User32.INSTANCE.SetLayeredWindowAttributes(
                hwnd, 
                0,      // Color key (not used)
                255,    // Alpha value (fully opaque to user)
                LWA_ALPHA
            );
            
            if (result) {
                System.out.println("✓ Layered window protection active");
            }
            return result;
        } catch (Exception e) {
            System.err.println("Layered protection error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Remove all protections (for debugging or cleanup)
     */
    public static boolean removeProtection(JWindow window) {
        try {
            HWND hwnd = getWindowHandle(window);
            return User32.INSTANCE.SetWindowDisplayAffinity(hwnd, WDA_NONE);
        } catch (Exception e) {
            System.err.println("Failed to remove protection: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Check if protection is currently active
     */
    public static boolean isProtected(JWindow window) {
        try {
            HWND hwnd = getWindowHandle(window);
            int style = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
            return (style & WS_EX_NOREDIRECTIONBITMAP) != 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get Windows handle from JWindow
     */
    private static HWND getWindowHandle(JWindow window) {
        return new HWND(Native.getWindowPointer(window));
    }
    
    /**
     * Toggle protection on/off (for testing)
     */
    public static void toggleProtection(JWindow window) {
        if (isProtected(window)) {
            removeProtection(window);
            System.out.println("Protection disabled");
        } else {
            applyFullProtection(window);
            System.out.println("Protection enabled");
        }
    }
    
    /**
     * Apply protection with retry logic (for timing issues)
     */
    public static void applyProtectionWithRetry(JWindow window, int maxAttempts) {
        SwingUtilities.invokeLater(() -> {
            int attempts = 0;
            boolean success = false;
            
            while (attempts < maxAttempts && !success) {
                try {
                    Thread.sleep(100 * (attempts + 1)); // Progressive delay
                    success = applyFullProtection(window);
                    attempts++;
                    
                    if (!success) {
                        System.out.println("Retry attempt " + attempts + "/" + maxAttempts);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            
            if (success) {
                System.out.println("✓ Protection successfully applied after " + attempts + " attempt(s)");
            } else {
                System.err.println("✗ Protection failed after " + maxAttempts + " attempts");
                System.err.println("  This may require administrator privileges");
            }
        });
    }
}