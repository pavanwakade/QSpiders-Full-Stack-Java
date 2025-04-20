package com.qsp.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);

        // Open browser automatically
        String url = "http://localhost:8080/index.html";
        try {
            // Option 1: Use Desktop to open the default browser
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                // Option 2: Fallback to open Chrome specifically (adjust path if needed)
                String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"; // Windows path
                // For macOS: "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"
                // For Linux: "/usr/bin/google-chrome"
                Runtime.getRuntime().exec(new String[]{chromePath, url});
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.err.println("Failed to open browser: " + e.getMessage());
        }
    }
}