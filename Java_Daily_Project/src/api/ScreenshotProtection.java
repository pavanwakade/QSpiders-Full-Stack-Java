package src.api;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.W32APIOptions;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

/**
 * Advanced Screenshot Protection Module
 * Hides window from screen captures, recordings, and screen sharing
 * Works with Zoom, Teams, Discord, OBS, and Windows built-in tools
 */
public class ScreenshotProtection {
    
    // Extended User32 interface with additional methods
    public interface User32Extended extends User32 {
        User32Extended INSTANCE = Native.load("user32", User32Extended.class, W32APIOptions.DEFAULT_OPTIONS);
        
        /**
         * SetWindowDisplayAffinity function
         * Stores the display affinity setting in kernel mode on the hWnd associated with the window.
         */
        boolean SetWindowDisplayAffinity(HWND hWnd, int dwAffinity);
        
        /**
         * GetWindowDisplayAffinity function
         * Retrieves the current display affinity setting, from any process, for a given window.
         */
        boolean GetWindowDisplayAffinity(HWND hWnd, int[] pdwAffinity);
        
        /**
         * SetLayeredWindowAttributes function
         * Sets the opacity and transparency color key of a layered window.
         */
        boolean SetLayeredWindowAttributes(HWND hwnd, int crKey, byte bAlpha, int dwFlags);
    }
    
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
            e.printStackTrace();
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
            boolean result = User32Extended.INSTANCE.SetWindowDisplayAffinity(hwnd, WDA_EXCLUDEFROMCAPTURE);
            if (result) {
                System.out.println("✓ Display affinity set - Window excluded from capture");
                
                // Verify the setting
                int[] affinity = new int[1];
                if (User32Extended.INSTANCE.GetWindowDisplayAffinity(hwnd, affinity)) {
                    System.out.println("  Current affinity value: " + affinity[0]);
                }
            } else {
                int error = Native.getLastError();
                System.err.println("✗ Display affinity failed - Error code: " + error);
                System.err.println("  Common causes:");
                System.err.println("    - Requires Windows 10 version 1903 or later");
                System.err.println("    - May need administrator privileges");
                System.err.println("    - Window might not be fully initialized");
            }
            return result;
        } catch (UnsatisfiedLinkError e) {
            System.err.println("✗ SetWindowDisplayAffinity not available on this Windows version");
            System.err.println("  Requires Windows 10 (1903+) or Windows 11");
            return false;
        } catch (Exception e) {
            System.err.println("Display affinity error: " + e.getMessage());
            e.printStackTrace();
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
            } else {
                System.err.println("✗ No-redirection bitmap failed to set");
            }
            return success;
        } catch (Exception e) {
            System.err.println("Extended style error: " + e.getMessage());
            e.printStackTrace();
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
            boolean result = User32Extended.INSTANCE.SetLayeredWindowAttributes(
                hwnd, 
                0,           // Color key (not used)
                (byte) 255,  // Alpha value (fully opaque to user)
                LWA_ALPHA
            );
            
            if (result) {
                System.out.println("✓ Layered window protection active");
            } else {
                System.err.println("✗ Layered window protection failed");
            }
            return result;
        } catch (Exception e) {
            System.err.println("Layered protection error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Check if protection is currently active
     */
    public static boolean isProtected(JWindow window) {
        try {
            HWND hwnd = getWindowHandle(window);
            
            // Check display affinity
            int[] affinity = new int[1];
            if (User32Extended.INSTANCE.GetWindowDisplayAffinity(hwnd, affinity)) {
                return affinity[0] == WDA_EXCLUDEFROMCAPTURE;
            }
            
            // Fallback: check extended style
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
     * Continuously monitor and reapply protection if it's removed
     * This ensures protection stays active always
     */
    public static void startProtectionMonitoring(JWindow window, java.util.concurrent.ScheduledExecutorService scheduler) {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if (!isProtected(window)) {
                    System.out.println("⚠ Protection lost - Reapplying...");
                    applyFullProtection(window);
                }
            } catch (Exception e) {
                System.err.println("Protection monitoring error: " + e.getMessage());
            }
        }, 10, 5, java.util.concurrent.TimeUnit.SECONDS);
        
        System.out.println("✓ Protection monitoring started - Will maintain protection automatically");
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
                    
                    if (!success && attempts < maxAttempts) {
                        System.out.println("Retry attempt " + attempts + "/" + maxAttempts);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            
            if (success) {
                System.out.println("\n✓✓✓ Protection successfully applied after " + attempts + " attempt(s) ✓✓✓");
                System.out.println("Your window is now hidden from:");
                System.out.println("  • Screen captures (PrintScreen, Snipping Tool)");
                System.out.println("  • Screen recording (OBS, Bandicam, etc.)");
                System.out.println("  • Screen sharing (Zoom, Teams, Discord, Meet)");
            } else {
                System.err.println("\n✗✗✗ Protection failed after " + maxAttempts + " attempts ✗✗✗");
                System.err.println("Possible solutions:");
                System.err.println("  1. Run as Administrator");
                System.err.println("  2. Ensure Windows 10 (1903+) or Windows 11");
                System.err.println("  3. Check Windows Update status");
                System.err.println("  4. Try restarting the application");
            }
        });
    }
    
    /**
     * Get detailed protection status report
     */
    public static String getProtectionReport(JWindow window) {
        try {
            HWND hwnd = getWindowHandle(window);
            StringBuilder report = new StringBuilder();
            
            report.append("=== PROTECTION STATUS REPORT ===\n");
            
            // Check display affinity
            int[] affinity = new int[1];
            if (User32Extended.INSTANCE.GetWindowDisplayAffinity(hwnd, affinity)) {
                report.append("Display Affinity: ");
                switch (affinity[0]) {
                    case WDA_NONE:
                        report.append("NONE (not protected)\n");
                        break;
                    case WDA_MONITOR:
                        report.append("MONITOR\n");
                        break;
                    case WDA_EXCLUDEFROMCAPTURE:
                        report.append("EXCLUDE_FROM_CAPTURE (protected) ✓\n");
                        break;
                    default:
                        report.append("Unknown (" + affinity[0] + ")\n");
                }
            } else {
                report.append("Display Affinity: Failed to query\n");
            }
            
            // Check extended styles
            int style = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
            report.append("Extended Styles:\n");
            report.append("  WS_EX_NOREDIRECTIONBITMAP: " + 
                ((style & WS_EX_NOREDIRECTIONBITMAP) != 0 ? "YES ✓" : "NO") + "\n");
            report.append("  WS_EX_LAYERED: " + 
                ((style & WS_EX_LAYERED) != 0 ? "YES ✓" : "NO") + "\n");
            
            report.append("Overall Status: " + (isProtected(window) ? "PROTECTED ✓" : "NOT PROTECTED ✗"));
            
            return report.toString();
            
        } catch (Exception e) {
            return "Error generating report: " + e.getMessage();
        }
    }
}