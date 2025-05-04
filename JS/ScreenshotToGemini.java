// Source code is decompiled from a .class file using FernFlower decompiler.
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
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;
import java.util.Scanner;
import java.util.Timer;

import javax.imageio.ImageIO;

public class ScreenshotToGemini {
   private static final String API_KEY = System.getenv("GEMINI_API_KEY") != null ? System.getenv("GEMINI_API_KEY") : "siYDAXuulx4ZElYnTkzuYNoG3MKKY6Q";
   private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
   private static final String MODELS_API_URL = "https://generativelanguage.googleapis.com/v1beta/models";
   private static final int SCREENSHOT_INTERVAL_MS = 10000;

   public ScreenshotToGemini() {
   }

   public static void main(String[] var0) {
      System.out.println("Using API Key: " + (System.getenv("GEMINI_API_KEY") != null ? "Environment Variable" : API_KEY));
      System.out.println("To use a different key, set the GEMINI_API_KEY environment variable.");
      System.out.println("Commands: Type a prompt to send to Gemini, 'exit' to stop, or press Enter to skip.");

      try {
         validateApiKey();
      } catch (Exception var7) {
         System.err.println("API Key Validation Failed: " + var7.getMessage());
         System.exit(1);
      }

      Timer var1 = new Timer();
      ScreenshotToGemini var2 = new ScreenshotToGemini();
      var1.schedule(var2, 0L, 10000L);
      Scanner var3 = new Scanner(System.in);

      while(true) {
         while(true) {
            System.out.print("Enter prompt (or 'exit'): ");
            String var4 = var3.nextLine();
            if ("exit".equalsIgnoreCase(var4)) {
               var1.cancel();
               System.out.println("Program stopped.");
               var3.close();
               System.exit(0);
            } else if (!var4.trim().isEmpty()) {
               try {
                  String var5 = sendTextToGemini(var4);
                  System.out.println("Text Prompt Response: " + var5);
               } catch (Exception var6) {
                  System.err.println("Text Prompt Error: " + var6.getMessage());
               }
            }
         }
      }
   }

   private static void validateApiKey() throws IOException, InterruptedException {
      HttpClient var0 = HttpClient.newHttpClient();
      HttpRequest var1 = HttpRequest.newBuilder().uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models?key=" + API_KEY)).header("Content-Type", "application/json").GET().build();
      HttpResponse var2 = var0.send(var1, BodyHandlers.ofString());
      if (var2.statusCode() == 200) {
         System.out.println("API Key validated successfully.");
      } else {
         int var10000 = var2.statusCode();
         String var3 = "HTTP Error " + var10000 + ": " + (String)var2.body();
         if (var2.statusCode() == 400 && ((String)var2.body()).contains("API_KEY_INVALID")) {
            var3 = var3 + "\nInvalid API key. Verify in Google Cloud Console (https://console.cloud.google.com/apis/credentials) or Google AI Studio (https://aistudio.google.com/). Ensure the Generative Language API is enabled (https://console.cloud.google.com/apis/library/generativelanguage.googleapis.com).";
         } else if (var2.statusCode() == 403) {
            var3 = var3 + "\nPermission denied. Check API key access or project billing/quota.";
         } else if (var2.statusCode() == 429) {
            var3 = var3 + "\nRate limit exceeded. Check quota in Google Cloud Console.";
         }

         throw new IOException(var3);
      }
   }

   private static BufferedImage takeScreenshot() throws AWTException {
      Robot var0 = new Robot();
      Rectangle var1 = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
      return var0.createScreenCapture(var1);
   }

   private static String convertToBase64(BufferedImage var0) throws IOException {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      ImageIO.write(var0, "png", var1);
      byte[] var2 = var1.toByteArray();
      return Base64.getEncoder().encodeToString(var2);
   }

   private static String sendImageToGemini(String var0) throws IOException, InterruptedException {
      HttpClient var1 = HttpClient.newHttpClient();
      String var2 = String.format("{\"contents\":[{\"parts\":[{\"text\":\"Analyze this image\"},{\"inlineData\":{\"mimeType\":\"image/png\",\"data\":\"%s\"}}]}]}", var0);
      HttpRequest var3 = HttpRequest.newBuilder().uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY)).header("Content-Type", "application/json").POST(BodyPublishers.ofString(var2)).build();
      HttpResponse var4 = var1.send(var3, BodyHandlers.ofString());
      if (var4.statusCode() == 200) {
         return (String)var4.body();
      } else {
         int var10000 = var4.statusCode();
         String var5 = "HTTP Error " + var10000 + ": " + (String)var4.body();
         if (var4.statusCode() == 400 && ((String)var4.body()).contains("API_KEY_INVALID")) {
            var5 = var5 + "\nInvalid API key. Verify in Google Cloud Console or Google AI Studio.";
         } else if (var4.statusCode() == 403) {
            var5 = var5 + "\nPermission denied. Check API key access or project billing/quota.";
         } else if (var4.statusCode() == 429) {
            var5 = var5 + "\nRate limit exceeded. Check quota in Google Cloud Console.";
         }

         throw new IOException(var5);
      }
   }

   private static String sendTextToGemini(String var0) throws IOException, InterruptedException {
      HttpClient var1 = HttpClient.newHttpClient();
      String var2 = String.format("{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}", var0.replace("\"", "\\\""));
      HttpRequest var3 = HttpRequest.newBuilder().uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY)).header("Content-Type", "application/json").POST(BodyPublishers.ofString(var2)).build();
      HttpResponse var4 = var1.send(var3, BodyHandlers.ofString());
      if (var4.statusCode() == 200) {
         return (String)var4.body();
      } else {
         int var10000 = var4.statusCode();
         String var5 = "HTTP Error " + var10000 + ": " + (String)var4.body();
         if (var4.statusCode() == 400 && ((String)var4.body()).contains("API_KEY_INVALID")) {
            var5 = var5 + "\nInvalid API key. Verify in Google Cloud Console or Google AI Studio.";
         } else if (var4.statusCode() == 403) {
            var5 = var5 + "\nPermission denied. Check API key access or project billing/quota.";
         } else if (var4.statusCode() == 429) {
            var5 = var5 + "\nRate limit exceeded. Check quota in Google Cloud Console.";
         }

         throw new IOException(var5);
      }
   }
}
