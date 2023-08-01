package com.rd.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService implements EmailService{
    private final JavaMailSender mailSender;
    @Value("EMAIL_MAIL")
    private String email;


    @Async
    @Override
    public void sendEmail(String to, String subject, String text) {
        try{
            MimeMessage message = createMessage(to, subject, text);
            mailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException("failed to send email");
        }
    }

    private MimeMessage createMessage(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        helper.setTo(to);
        helper.setFrom(email);
        helper.setSubject(subject);
        helper.setText(text, true);

        return message;
    }
}
