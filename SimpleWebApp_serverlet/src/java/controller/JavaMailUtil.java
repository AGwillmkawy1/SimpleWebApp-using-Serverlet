package controller;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
    
    public static void sendEmail(String recipient, String subject, String body) throws MessagingException {
        
        // Configure the email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        // Configure the email authentication
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jesuisomega@gmail.com", "damon salvatore");
            }
        };
        
        // Create a new email session
        Session session = Session.getInstance(properties, auth);
        
        // Create a new email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("jesuisomega123@gmail.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setText(body);
        
        // Send the email message
        Transport.send(message);
    }
}
