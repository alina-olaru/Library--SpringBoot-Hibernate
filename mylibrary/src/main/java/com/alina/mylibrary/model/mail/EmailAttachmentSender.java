package com.alina.mylibrary.model.mail;

import com.alina.mylibrary.model.db.BookOrder;
import com.alina.mylibrary.service.Services.Pdf.GeneratePdfReport;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class EmailAttachmentSender {

//    spring.mail.host = smtp.gmail.com
//    spring.mail.port = 587
//    spring.mail.properties.mail.smtp.starttls.enable = true
//    spring.mail.username = edenlibrary17@gmail.com
//    spring.mail.password = Alina@123
//    spring.mail.properties.mail.smtp.starttls.required = true
//    spring.mail.properties.mail.smtp.auth = true
//    spring.mail.properties.mail.smtp.connectiontimeout = 5000
//    spring.mail.properties.mail.smtp.timeout = 5000
//    spring.mail.properties.mail.smtp.writetimeout = 5000

    public static void sendEmailWithAttachments(String host, String port,
                                                final String userName, final String password, String toAddress,
                                                String subject, String message, InputStreamResource[] attachFiles)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (InputStreamResource filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();

                try {
                    attachPart.attachFile(filePath.getFile());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(attachPart);
            }
        }

        // sets the multi-part as e-mail's content
        msg.setContent(multipart);

        // sends the e-mail
        Transport.send(msg);

    }

    /**
     * Test sending e-mail with attachments
     */

//    spring.mail.host = smtp.gmail.com
//    spring.mail.port = 587
//    spring.mail.properties.mail.smtp.starttls.enable = true
//    spring.mail.username = edenlibrary17@gmail.com
//    spring.mail.password = Alina@123
//    spring.mail.properties.mail.smtp.starttls.required = true
//    spring.mail.properties.mail.smtp.auth = true
//    spring.mail.properties.mail.smtp.connectiontimeout = 5000
//    spring.mail.properties.mail.smtp.timeout = 5000
//    spring.mail.properties.mail.smtp.writetimeout = 5000
   // public static void main(String[] args) {
//        // SMTP info
//        String host = "smtp.gmail.com";
//        String port = "587";
//        String mailFrom = "edenlibrary17@gmail.com";
//        String password = "Alina@123";
//
//        // message info
//        String mailTo = "olarualina01@gmail.com";
//        String subject = "New email with attachments";
//        String message = "I have some attachments for you.";
//
//        BookOrder order = new BookOrder();
//        ByteArrayInputStream bis = GeneratePdfReport.generateInvoiceForUser(order);
//
//        var headers = new HttpHeaders();
//        //By setting the Content-Disposition to inline, the PDF file is shown directly in browser.
//        headers.add("Content-Disposition", "inline; filename=factura.pdf");
//
//       var atach= ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
//
//        // attachments
//        //        ResponseEntity<ByteArrayInputStream[]> attachFiles = new ResponseEntity<ByteArrayInputStream>();
//        InputStreamResource[] attachFiles = new InputStreamResource[3];
//       attachFiles[1]=atach.getBody();
//
//        try {
//            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
//                    subject, message, attachFiles);
//            System.out.println("Email sent.");
//        } catch (Exception ex) {
//            System.out.println("Could not send email.");
//            ex.printStackTrace();
//        }
//    }
}
