/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.utils;

import com.khanhbdb.controllers.MainController;
import java.io.Serializable;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import org.apache.log4j.Logger;

public class CommonUltil implements Serializable {

    private final static Logger LOGGER = Logger.getLogger(MainController.class.getName());

    public static String generateVerifyCode(int len) {

        String alphaNum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0;
                i < len;
                i++) {
            sb.append(alphaNum.charAt(rnd.nextInt(alphaNum.length())));
        }
        return sb.toString();
    }

    public static Date getCurrentDateSql() {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        return date;
    }

    public static void sendVerificationCode(String email, String code) {

        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", MailConfig.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", MailConfig.SSL_PORT);

        // get Session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
            }
        });

        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Verification Email");
            message.setText("Your verification code is: " + code);

            // send message
            Transport.send(message);

            LOGGER.info("Message sent successfully! - Info from SendMailSSL.java");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
