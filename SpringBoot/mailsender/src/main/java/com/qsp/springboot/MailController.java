package com.qsp.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.mail.internet.MimeMessage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;

    public static class EmailRequest {
        private List<String> to;
        private String subject;
        private String text;

        public List<String> getTo() { return to; }
        public void setTo(List<String> to) { this.to = to; }
        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }

    @PostMapping(value = "/maile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String sendMail(@RequestPart(value = "email", required = true) EmailRequest emailRequest,
                           @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            List<String> sentRecipients = new ArrayList<>();
            List<String> failedRecipients = new ArrayList<>();
            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Validate recipients
            if (emailRequest.getTo() == null || emailRequest.getTo().isEmpty()) {
                return "Error: At least one recipient email is required";
            }

            // Send email to each recipient
            for (String recipient : emailRequest.getTo()) {
                try {
                    MimeMessage message = javaMailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, file != null);
                    helper.setTo(recipient);
                    helper.setSubject(emailRequest.getSubject());
                    helper.setText(emailRequest.getText());
                    if (file != null) {
                        helper.addAttachment(file.getOriginalFilename(), file);
                    }
                    javaMailSender.send(message);
                    sentRecipients.add(recipient);
                } catch (Exception e) {
                    failedRecipients.add(recipient + " (" + e.getMessage() + ")");
                }
            }

            // Save to Excel
            saveToExcel(sentRecipients, timestamp);

            // Prepare response
            StringBuilder response = new StringBuilder();
            if (!sentRecipients.isEmpty()) {
                response.append("Email sent successfully to: ").append(String.join(", ", sentRecipients));
                if (file != null) {
                    response.append(" with attachment: ").append(file.getOriginalFilename());
                }
            }
            if (!failedRecipients.isEmpty()) {
                if (response.length() > 0) response.append("; ");
                response.append("Failed to send to: ").append(String.join(", ", failedRecipients));
            }
            return response.length() > 0 ? response.toString() : "No emails sent";
        } catch (Exception e) {
            return "Failed to process request: " + e.getMessage();
        }
    }

    private void saveToExcel(List<String> recipients, String timestamp) {
        // Save to project root; change to absolute path if needed, e.g.:
        // String filePath = System.getProperty("user.home") + "/Desktop/sent_emails.xlsx";
    	String filePath = System.getProperty("user.home") + "/Desktop/sent_emails.xlsx";
        Workbook workbook;
        Sheet sheet;
        boolean fileExists = new java.io.File(filePath).exists();

        try {
            if (fileExists) {
                try (var fis = new java.io.FileInputStream(filePath)) {
                    workbook = WorkbookFactory.create(fis);
                }
                sheet = workbook.getSheet("Sent Emails");
                if (sheet == null) {
                    sheet = workbook.createSheet("Sent Emails");
                }
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Sent Emails");

                // Create header row
                Row headerRow = sheet.createRow(0);
                String[] headers = {"Recipient", "Timestamp"};
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }
            }

            // Find the next available row
            int rowNum = sheet.getLastRowNum() + (sheet.getLastRowNum() == 0 ? 1 : 0);

            // Add data for each recipient
            for (String recipient : recipients) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(recipient);
                row.createCell(1).setCellValue(timestamp);
            }

            // Auto-size columns
            for (int i = 0; i < 2; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}