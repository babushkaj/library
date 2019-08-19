package com.kotenkov.mail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class MailSender {

    Properties properties;

    public MailSender() {
        try {
            properties = new Properties();
            properties.load(MailSender.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(List<String> emailAddress, String topic, String text) throws MessagingException {
        Transport transport = null;
        try {
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("cutedeepblueeyes@gmail.com"));
            for (String email:emailAddress) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            }
            message.setSubject(topic);
            message.setText(text);

            transport = mailSession.getTransport();
            transport.connect("cutedeepblueeyes@gmail.com","remembertheseeyes");
            transport.sendMessage(message, message.getAllRecipients());
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }

    }



}
