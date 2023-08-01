package com.rd.email;

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
public class ReservationEmailService {
   private final EmailService emailService;
    @Value("${base.url}")
    private final String baseUrl;


    public void sendEmailReservation(Reservation reservation){
        String to = reservation.getUser().getEmail();
        String subject = "The reservation is almost finished";
        String text = "If you delivered the vehicle, please click on the next link." + baseUrl +
                "/idUser=" + reservation.getUser().getId() + "&idVehicle=" + reservation.getVehicle().getId();

        emailService.sendEmail(to, subject, text);
    }


}
