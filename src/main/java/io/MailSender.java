package io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

/**
 * Used to send emails, f.e. crash reports to the developer
 */
public abstract class MailSender {

    private static final Logger LOGGER = LogManager.getLogger(MailSender.class.getName());

    private static final String SMTP_HOST = "smtp.web.de";
    private static final String SMTP_PORT = "587";
    private static final String MAIL_USER = "captainsengineer";
    private static final String MAIL_PASSWORD = "casimirstinkt";
    private static final String MAIL_SENDER = "captainsengineer@web.de";

    private MailSender() {
    }

    /**
     * Sends an email from a preconfigured server
     * @param recepient receiver
     * @param subject subject
     * @param message message body plain text
     * @param appendix files to append
     * @return {@code null} if successfull, Error text if failed
     */
    public static String sendMail(String recepient, String subject, String message, File[] appendix) {

        LOGGER.debug("Sending mail to " + recepient + ", subject: " + subject + ", appended files: " + appendix.length);

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", SMTP_HOST);
        prop.put("mail.smtp.port", SMTP_PORT);
        prop.put("mail.smtp.ssl.trust", SMTP_HOST);

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_USER, MAIL_PASSWORD);
            }
        });

        try {
            Message content = new MimeMessage(session);
            content.setFrom(new InternetAddress(MAIL_SENDER));
            content.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recepient));
            content.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(message, "text/html; charset=utf-8");


            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            for (File f : appendix) {
                if (f.exists()) {
                    MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                    LOGGER.debug("attaching " + f.getName());
                    attachmentBodyPart.attachFile(f);
                    multipart.addBodyPart(attachmentBodyPart);
                }
            }


            content.setContent(multipart);

            Transport.send(content);
        } catch (Exception e) {
            LOGGER.warn("Mail sending failed: ", e);
            return e.getMessage();
        }

        return null;
    }



}
