package com.rd.service;

import com.rd.entity.Reservation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender emailSender;
    @Value("${base.url}")
    private String baseUrl;
    @Value("EMAIL_MAIL")
    private String email;

    public void sendEmail(Reservation reservation){
        try{
            MimeMessage message = createMessage(reservation);
            emailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public MimeMessage createMessage(Reservation reservation) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(reservation.getUser().getEmail());
        System.out.println("EMAIL: " + reservation.getUser().getEmail());
        helper.setFrom(email);
        helper.setSubject("The reservation is almost finished");
        String text = "If you delivered the vehicle, please click on the next link." + baseUrl + "/idUser="
                + reservation.getUser().getId() + "&idVehicle=" + reservation.getVehicle().getId();

        helper.setText(text);
        return message;
    }
}
