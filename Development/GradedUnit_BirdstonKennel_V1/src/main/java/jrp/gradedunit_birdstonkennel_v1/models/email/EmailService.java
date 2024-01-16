package jrp.gradedunit_birdstonkennel_v1.models.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * This class handles all the logic for sending emails to users
 */
@Data
@Service
@RequiredArgsConstructor
public class EmailService implements EmailSender{

    /**
     * Stores the logger object
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    /**
     * Stores the mail sender object
     */
    private final JavaMailSender mailSender;
    /**
     * Stores the subject for the next outgoing email
     */
    private String subject;


    /**
     * Sends a html formatted email to a specified user
     *
     * @param to    The email recipient
     * @param email The email being sent
     */
    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(this.subject);
            helper.setFrom("service@birdstonkennels.com");
            this.mailSender.send(mimeMessage);
            this.subject = null;
        } catch(MessagingException e){
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }

}
