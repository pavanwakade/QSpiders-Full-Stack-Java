package com.qsp.springboot;

import org.springframework.http.MediaType;
// import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import jakarta.mail.internet.MimeMessage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@CrossOrigin(origins = "*")
public class MailController {
    // Not using the autowired JavaMailSender because we create a custom one per-request
    // kept here in case future endpoints want the default sender
    // @Autowired
    // private JavaMailSender javaMailSender;

    public static class EmailRequest {
        private List<String> to;
        private String subject;
        private String text;
        private String senderEmail;
        private String senderPassword;

        public List<String> getTo() { return to; }
        public void setTo(List<String> to) { this.to = to; }
        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
        public String getSenderEmail() { return senderEmail; }
        public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }
        public String getSenderPassword() { return senderPassword; }
        public void setSenderPassword(String senderPassword) { this.senderPassword = senderPassword; }
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

            // Validate sender credentials
            if (emailRequest.getSenderEmail() == null || emailRequest.getSenderEmail().isEmpty() ||
                emailRequest.getSenderPassword() == null || emailRequest.getSenderPassword().isEmpty()) {
                return "Error: Sender email and password are required";
            }

            // Create custom mail sender with user credentials
            JavaMailSenderImpl customMailSender = createMailSender(
                emailRequest.getSenderEmail(), 
                emailRequest.getSenderPassword()
            );

            // Send email to each recipient
            for (String recipient : emailRequest.getTo()) {
                try {
                    MimeMessage message = customMailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, file != null);
                    helper.setFrom(emailRequest.getSenderEmail());
                    helper.setTo(recipient);
                    helper.setSubject(emailRequest.getSubject());
                    helper.setText(emailRequest.getText());
                    if (file != null) {
                        String fname = file.getOriginalFilename();
                        if (fname == null || fname.trim().isEmpty()) {
                            fname = "attachment";
                        }
                        helper.addAttachment(fname, file);
                    }
                    customMailSender.send(message);
                    sentRecipients.add(recipient);
                } catch (Exception e) {
                    failedRecipients.add(recipient + " (" + e.getMessage() + ")");
                }
            }

            // Save to Excel
            saveToExcel(sentRecipients, timestamp, emailRequest.getSenderEmail());

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

    private JavaMailSenderImpl createMailSender(String email, String password) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(email);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return mailSender;
    }

    private void saveToExcel(List<String> recipients, String timestamp, String senderEmail) {
        String filePath = System.getProperty("user.home") + "/Desktop/sent_emails.xlsx";
        Workbook workbook = null;
        Sheet sheet = null;
        java.io.File file = new java.io.File(filePath);

        try {
            // Ensure recipients is not null
            if (recipients == null || recipients.isEmpty()) return;

            // Read existing workbook if present
            if (file.exists()) {
                try (var fis = new java.io.FileInputStream(file)) {
                    workbook = WorkbookFactory.create(fis);
                }
                sheet = workbook.getSheet("Sent Emails");
                if (sheet == null) {
                    sheet = workbook.createSheet("Sent Emails");
                    // create header
                    Row headerRow = sheet.createRow(0);
                    String[] headers = {"Sender", "Recipient", "Timestamp"};
                    for (int i = 0; i < headers.length; i++) {
                        Cell cell = headerRow.createCell(i);
                        cell.setCellValue(headers[i]);
                    }
                }
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Sent Emails");
                Row headerRow = sheet.createRow(0);
                String[] headers = {"Sender", "Recipient", "Timestamp"};
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }
            }

            // Read existing recipients into a Set to avoid duplicates
            DataFormatter formatter = new DataFormatter();
            java.util.Set<String> existing = new java.util.HashSet<>();
            int firstDataRow = 1; // row 0 is header
            int lastRow = sheet.getLastRowNum();
            for (int r = firstDataRow; r <= lastRow; r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                Cell recipientCell = row.getCell(1); // Recipient column
                if (recipientCell == null) continue;
                String val = formatter.formatCellValue(recipientCell).trim();
                if (!val.isEmpty()) existing.add(val);
            }

            // Filter incoming recipients to only those not already present
            java.util.List<String> toAppend = new java.util.ArrayList<>();
            for (String r : recipients) {
                if (r == null) continue;
                String trimmed = r.trim();
                if (trimmed.isEmpty()) continue;
                if (!existing.contains(trimmed)) {
                    existing.add(trimmed); // ensure unique within this save
                    toAppend.add(trimmed);
                }
            }

            if (toAppend.isEmpty()) {
                // Nothing new to write
                if (workbook != null) workbook.close();
                return;
            }

            // Determine the next row: if sheet has only header and getLastRowNum()==0 but no data, start at 1
            int nextRow = sheet.getLastRowNum();
            if (nextRow == 0) {
                // check if row 0 is header; next data row should be 1
                nextRow = 1;
            } else {
                // getLastRowNum returns index of last physical row; place next after it
                nextRow = sheet.getLastRowNum() + 1;
            }

            for (String recipient : toAppend) {
                Row row = sheet.createRow(nextRow++);
                row.createCell(0).setCellValue(senderEmail);
                row.createCell(1).setCellValue(recipient);
                row.createCell(2).setCellValue(timestamp);
            }

            // Auto-size columns
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file (create parent dirs if needed)
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            if (workbook != null) workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}