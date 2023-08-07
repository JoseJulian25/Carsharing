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


    public void sendEmailReservation(Reservation reservation){
        String to = reservation.getUser().getEmail();
        String subject = "Reservation  Almost Ending";
        String text = buildEmail(reservation, reservation.getUser().getName());

        emailService.sendEmail(to, subject, text);
    }

    String buildEmail(Reservation reservation, String nameUser){
        return "<div style=\"font-family: Arial, sans-serif; line-height: 1.6; margin: 0; padding: 0; max-width: 600px; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px;\">\n" +
                "  <div style=\"background-color: #f0f0f0; padding: 10px; text-align: center;\">\n" +
                "    <h1>Reservation Almost Ending</h1>\n" +
                "  </div>\n" +
                "  <div style=\"margin-top: 20px;\">\n" +
                "    <p>Dear " + nameUser+ "," +"</p>\n" +
                "    <p>We would like to inform you that your reservation is about to end. The end date of your reservation is " + reservation.getEndDate() +".</p>\n" +
                "    <p>Please ensure to take the necessary steps to complete your reservation before the deadline.</p>\n" +
                "    <p>We are looking forward to having you soon!</p>\n" +
                "    <p>Best regards,</p>\n" +
                "    <p>The Reservation Team</p>\n" +
                "  </div>\n" +
                "  <div style=\"margin-top: 20px; text-align: center; color: #888;\">\n" +
                "    <p>Do not reply to this email. It is an automatically generated message.</p>\n" +
                "  </div>\n" +
                "</div>";
    }


}
