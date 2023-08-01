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
public class MailService {
    private final JavaMailSender emailSender;
    @Value("${base.url}")
    private String baseUrl;
    @Value("EMAIL_MAIL")
    private String email;

    public void sendEmailReservation(Reservation reservation){
        try{
            MimeMessage message = createMessageReservation(reservation);
            emailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException("Failed to send email");
        }
    }

    public MimeMessage createMessageReservation(Reservation reservation) throws MessagingException {
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

    public MimeMessage createEmailConfirmationUser(){

    }

    public String buildEmailConfirmationUser(String name){
         return  "<div style=\\\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\\\">\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"<span style=\\\"display:none;font-size:1px;color:#fff;max-height:0\\\"></span>\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"  <table role=\\\"presentation\\\" width=\\\"100%\\\" style=\\\"border-collapse:collapse;min-width:100%;width:100%!important\\\" cellpadding=\\\"0\\\" cellspacing=\\\"0\\\" border=\\\"0\\\">\\n\" +\n" +
                "                \"    <tbody><tr>\\n\" +\n" +
                "                \"      <td width=\\\"100%\\\" height=\\\"53\\\" bgcolor=\\\"#0b0c0c\\\">\\n\" +\n" +
                "                \"        \\n\" +\n" +
                "                \"        <table role=\\\"presentation\\\" width=\\\"100%\\\" style=\\\"border-collapse:collapse;max-width:580px\\\" cellpadding=\\\"0\\\" cellspacing=\\\"0\\\" border=\\\"0\\\" align=\\\"center\\\">\\n\" +\n" +
                "                \"          <tbody><tr>\\n\" +\n" +
                "                \"            <td width=\\\"70\\\" bgcolor=\\\"#0b0c0c\\\" valign=\\\"middle\\\">\\n\" +\n" +
                "                \"                <table role=\\\"presentation\\\" cellpadding=\\\"0\\\" cellspacing=\\\"0\\\" border=\\\"0\\\" style=\\\"border-collapse:collapse\\\">\\n\" +\n" +
                "                \"                  <tbody><tr>\\n\" +\n" +
                "                \"                    <td style=\\\"padding-left:10px\\\">\\n\" +\n" +
                "                \"                  \\n\" +\n" +
                "                \"                    </td>\\n\" +\n" +
                "                \"                    <td style=\\\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\\\">\\n\" +\n" +
                "                \"                      <span style=\\\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\\\">Confirm your email</span>\\n\" +\n" +
                "                \"                    </td>\\n\" +\n" +
                "                \"                  </tr>\\n\" +\n" +
                "                \"                </tbody></table>\\n\" +\n" +
                "                \"              </a>\\n\" +\n" +
                "                \"            </td>\\n\" +\n" +
                "                \"          </tr>\\n\" +\n" +
                "                \"        </tbody></table>\\n\" +\n" +
                "                \"        \\n\" +\n" +
                "                \"      </td>\\n\" +\n" +
                "                \"    </tr>\\n\" +\n" +
                "                \"  </tbody></table>\\n\" +\n" +
                "                \"  <table role=\\\"presentation\\\" class=\\\"m_-6186904992287805515content\\\" align=\\\"center\\\" cellpadding=\\\"0\\\" cellspacing=\\\"0\\\" border=\\\"0\\\" style=\\\"border-collapse:collapse;max-width:580px;width:100%!important\\\" width=\\\"100%\\\">\\n\" +\n" +
                "                \"    <tbody><tr>\\n\" +\n" +
                "                \"      <td width=\\\"10\\\" height=\\\"10\\\" valign=\\\"middle\\\"></td>\\n\" +\n" +
                "                \"      <td>\\n\" +\n" +
                "                \"        \\n\" +\n" +
                "                \"                <table role=\\\"presentation\\\" width=\\\"100%\\\" cellpadding=\\\"0\\\" cellspacing=\\\"0\\\" border=\\\"0\\\" style=\\\"border-collapse:collapse\\\">\\n\" +\n" +
                "                \"                  <tbody><tr>\\n\" +\n" +
                "                \"                    <td bgcolor=\\\"#1D70B8\\\" width=\\\"100%\\\" height=\\\"10\\\"></td>\\n\" +\n" +
                "                \"                  </tr>\\n\" +\n" +
                "                \"                </tbody></table>\\n\" +\n" +
                "                \"        \\n\" +\n" +
                "                \"      </td>\\n\" +\n" +
                "                \"      <td width=\\\"10\\\" valign=\\\"middle\\\" height=\\\"10\\\"></td>\\n\" +\n" +
                "                \"    </tr>\\n\" +\n" +
                "                \"  </tbody></table>\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"  <table role=\\\"presentation\\\" class=\\\"m_-6186904992287805515content\\\" align=\\\"center\\\" cellpadding=\\\"0\\\" cellspacing=\\\"0\\\" border=\\\"0\\\" style=\\\"border-collapse:collapse;max-width:580px;width:100%!important\\\" width=\\\"100%\\\">\\n\" +\n" +
                "                \"    <tbody><tr>\\n\" +\n" +
                "                \"      <td height=\\\"30\\\"><br></td>\\n\" +\n" +
                "                \"    </tr>\\n\" +\n" +
                "                \"    <tr>\\n\" +\n" +
                "                \"      <td width=\\\"10\\\" valign=\\\"middle\\\"><br></td>\\n\" +\n" +
                "                \"      <td style=\\\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\\\">\\n\" +\n" +
                "                \"        \\n\" +\n" +
                "                \"            <p style=\\\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\\\">Hi \" + name + \",</p><p style=\\\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\\\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\\\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\\\"><p style=\\\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\\\"> <a href=\\\"\" + link + \"\\\">Activate Now</a> </p></blockquote>\\n Link will expire in 15 minutes. <p>See you soon</p>\" +\n" +
                "                \"        \\n\" +\n" +
                "                \"      </td>\\n\" +\n" +
                "                \"      <td width=\\\"10\\\" valign=\\\"middle\\\"><br></td>\\n\" +\n" +
                "                \"    </tr>\\n\" +\n" +
                "                \"    <tr>\\n\" +\n" +
                "                \"      <td height=\\\"30\\\"><br></td>\\n\" +\n" +
                "                \"    </tr>\\n\" +\n" +
                "                \"  </tbody></table><div class=\\\"yj6qo\\\"></div><div class=\\\"adL\\\">\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"</div></div>\";";
    }

}
