import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class ScreenshotToGemini {
    private static final String API_KEY = System.getenv("GEMINI_API_KEY") != null ? System.getenv("GEMINI_API_KEY") : "siYDAXuulx4ZElYnTkzuYNoG3MKKY6Q";
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private static final String MODELS_API_URL = "https://generativelanguage.googleapis.com/v1beta/models";
    private static final int SCREENSHOT_INTERVAL_MS = 10000; // 10 seconds

    public static void main(String[] args) {
        System.out.println("Using API Key: " + (System.getenv("GEMINI_API_KEY") != null ? "Environment Variable" : API_KEY));
        System.out.println("To use a different key, set the GEMINI_API_KEY environment variable.");
        System.out.println("Commands: Type a prompt to send to Gemini, 'exit' to stop, or press Enter to skip.");

        // Validate API key
        try {
            validateApiKey();
        } catch (Exception e) {
            System.err.println("API Key Validation Failed: " + e.getMessage());
            System.exit(1);
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    BufferedImage screenshot = takeScreenshot();
                    String base64Image = convertToBase64(screenshot);
                    String response = sendImageToGemini(base64Image);
                    System.out.println("Screenshot Analysis: " + response);
                } catch (Exception e) {
                    System.err.println("Screenshot Error: " + e.getMessage());
                }
            }
        };
        timer.schedule(task, 0, SCREENSHOT_INTERVAL_MS);

        // Handle console input for text prompts or exit
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter prompt (or 'exit'): ");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                timer.cancel();
                System.out.println("Program stopped.");
                scanner.close();
                System.exit(0);
            } else if (!input.trim().isEmpty()) {
                try {
                    String response = sendTextToGemini(input);
                    System.out.println("Text Prompt Response: " + response);
                } catch (Exception e) {
                    System.err.println("Text Prompt Error: " + e.getMessage());
                }
            }
        }
    }

    private static void validateApiKey() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(MODELS_API_URL + "?key=" + API_KEY))
            .header("Content-Type", "application/json")
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            String errorMessage = "HTTP Error " + response.statusCode() + ": " + response.body();
            if (response.statusCode() == 400 && response.body().contains("API_KEY_INVALID")) {
                errorMessage += "\nInvalid API key. Verify in Google Cloud Console (https://console.cloud.google.com/apis/credentials) or Google AI Studio (https://aistudio.google.com/). Ensure the Generative Language API is enabled (https://console.cloud.google.com/apis/library/generativelanguage.googleapis.com).";
            } else if (response.statusCode() == 403) {
                errorMessage += "\nPermission denied. Check API key access or project billing/quota.";
            } else if (response.statusCode() == 429) {
                errorMessage += "\nRate limit exceeded. Check quota in Google Cloud Console.";
            }
            throw new IOException(errorMessage);
        }
        System.out.println("API Key validated successfully.");
    }

    private static BufferedImage takeScreenshot() throws AWTException {
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(screenRect);
    }

    private static String convertToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private static String sendImageToGemini(String base64Image) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String requestBody = String.format(
            "{\"contents\":[{\"parts\":[{\"text\":\"Analyze this image\"},{\"inlineData\":{\"mimeType\":\"image/png\",\"data\":\"%s\"}}]}]}",
            base64Image
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(GEMINI_API_URL + "?key=" + API_KEY))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            String errorMessage = "HTTP Error " + response.statusCode() + ": " + response.body();
            if (response.statusCode() == 400 && response.body().contains("API_KEY_INVALID")) {
                errorMessage += "\nInvalid API key. Verify in Google Cloud Console or Google AI Studio.";
            } else if (response.statusCode() == 403) {
                errorMessage += "\nPermission denied. Check API key access or project billing/quota.";
            } else if (response.statusCode() == 429) {
                errorMessage += "\nRate limit exceeded. Check quota in Google Cloud Console.";
            }
            throw new IOException(errorMessage);
        }
        return response.body();
    }

    private static String sendTextToGemini(String text) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String requestBody = String.format(
            "{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}",
            text.replace("\"", "\\\"")
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(GEMINI_API_URL + "?key=" + API_KEY))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            String errorMessage = "HTTP Error " + response.statusCode() + ": " + response.body();
            if (response.statusCode() == 400 && response.body().contains("API_KEY_INVALID")) {
                errorMessage += "\nInvalid API key. Verify in Google Cloud Console or Google AI Studio.";
            } else if (response.statusCode() == 403) {
                errorMessage += "\nPermission denied. Check API key access or project billing/quota.";
            } else if (response.statusCode() == 429) {
                errorMessage += "\nRate limit exceeded. Check quota in Google Cloud Console.";
            }
            throw new IOException(errorMessage);
        }
        return response.body();
    }
}