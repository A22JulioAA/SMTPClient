package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class SMTPClient {
    public static void main(String[] args) {

        String sender = "lorchosgaelicos@yopmail.com";
        String receiver = "pruebassmtp@yopmail.com";

        try (InputStream input = new FileInputStream("src/main/resources/smtp.properties")) {
            Properties properties = new Properties();

            properties.load(input);

            Session session = Session.getInstance(properties);

            try {
                Message message = new MimeMessage(session);

                message.setFrom(new InternetAddress(sender, "Prueba"));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver, "Julio Acuña"));
                message.setSubject("Hello from Java");
                message.setText("Email sent from Java app and campured by Mailsurper");

                Transport.send(message);

                System.out.println("Email sent.");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        
    }
}