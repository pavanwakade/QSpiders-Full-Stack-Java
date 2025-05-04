import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class D {
    private static String API_KEY;
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private static final String MODELS_API_URL = "https://generativelanguage.googleapis.com/v1beta/models";
    private static int SCREENSHOT_INTERVAL_MS;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final List<String> CONVERSATION = new ArrayList<>();
    private static ScheduledExecutorService scheduler;

    public static void main(String[] args) {
        // Load configuration from properties file or defaults
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("Config file not found, using defaults or environment variables.");
        }

        API_KEY = props.getProperty("api_key", System.getenv("GEMINI_API_KEY") != null ? System.getenv("GEMINI_API_KEY") : "");
        SCREENSHOT_INTERVAL_MS = Integer.parseInt(props.getProperty("screenshot_interval_ms", "10000"));

        System.out.println("Using API Key: " + (System.getenv("GEMINI_API_KEY") != null || props.containsKey("api_key") ? "Configured" : API_KEY));
        System.out.println("To customize, set GEMINI_API_KEY environment variable or use config.properties.");
        System.out.println("Commands: Type a prompt to send to Gemini, 'exit' to stop, or press Enter to skip.");

        // Validate API key
        try {
            validateApiKey();
        } catch (Exception e) {
            System.err.println("API Key Validation Failed: " + e.getMessage());
            System.exit(1);
        }

        // Start screenshot scheduler
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                BufferedImage screenshot = takeScreenshot();
                String base64Image = convertToBase64(screenshot);
                String response = sendImageToGemini(base64Image);
                String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
                System.out.println("[" + timestamp + "] [IMAGE] " + extractModelResponse(response));
            } catch (Exception e) {
                System.err.println("Screenshot Error: " + e.getMessage());
            }
        }, 0, SCREENSHOT_INTERVAL_MS, TimeUnit.MILLISECONDS);

        // Handle user input for text prompts
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter prompt (or 'exit'): ");
            String prompt = scanner.nextLine();
            if ("exit".equalsIgnoreCase(prompt)) {
                scheduler.shutdown();
                System.out.println("Program stopped.");
                scanner.close();
                System.exit(0);
            } else if (!prompt.trim().isEmpty()) {
                try {
                    CONVERSATION.add("user: " + prompt);
                    String response = sendTextToGemini(CONVERSATION);
                    String modelResponse = extractModelResponse(response);
                    CONVERSATION.add("model: " + modelResponse);
                    String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
                    System.out.println("[" + timestamp + "] [TEXT] " + modelResponse);
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
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException(buildErrorMessage(response));
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
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    private static String sendImageToGemini(String base64Image) throws IOException, InterruptedException {
        String requestBody = "{\"contents\":[{\"parts\":[{\"text\":\"Analyze this image\"},{\"inlineData\":{\"mimeType\":\"image/png\",\"data\":\"" + base64Image + "\"}}]}]}";
        return sendRequest(requestBody);
    }

    private static String sendTextToGemini(List<String> conversation) throws IOException, InterruptedException {
        StringBuilder conversationString = new StringBuilder();
        for (String turn : conversation) {
            conversationString.append(turn).append("\n");
        }
        String requestBody = "{\"contents\":[{\"parts\":[{\"text\":\"" + conversationString.toString().replace("\"", "\\\"") + "\"}]}]}";
        return sendRequest(requestBody);
    }

    private static String sendRequest(String requestBody) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GEMINI_API_URL + "?key=" + API_KEY))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(requestBody))
                .build();

        int retries = 3;
        int delayMs = 1000;
        for (int i = 0; i < retries; i++) {
            try {
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    return response.body();
                } else if (response.statusCode() == 429 && i < retries - 1) {
                    Thread.sleep(delayMs * (i + 1)); // Exponential backoff
                    continue;
                }
                throw new IOException(buildErrorMessage(response));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw e;
            }
        }
        throw new IOException("Failed after retries.");
    }

    private static String extractModelResponse(String responseBody) {
        // Basic string manipulation to extract the model's response
        // This assumes a specific structure and may need adjustment based on actual response
        int start = responseBody.indexOf("\"text\": \"") + 9;
        int end = responseBody.indexOf("\"", start);
        if (start > 8 && end > start) {
            return responseBody.substring(start, end).replace("\\n", "\n");
        }
        return "No response";
    }

    private static String buildErrorMessage(HttpResponse<String> response) {
        String base = "HTTP Error " + response.statusCode() + ": " + response.body();
        if (response.statusCode() == 400 && response.body().contains("API_KEY_INVALID")) {
            return base + "\nInvalid API key. Verify in Google Cloud Console or Google AI Studio.";
        } else if (response.statusCode() == 403) {
            return base + "\nPermission denied. Check API key access or project billing/quota.";
        } else if (response.statusCode() == 429) {
            return base + "\nRate limit exceeded. Check quota in Google Cloud Console.";
        }
        return base;
    }
}