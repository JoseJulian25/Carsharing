package com.rd.config;

import com.rd.service.impl.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ReservationCompletion {
    private final ReservationServiceImpl reservationService;

    @Scheduled(cron = "0 */2 * * * *")
    public void updateCompletedReservations(){
        Date currentDate = new Date();
        reservationService.updateCompletedReservations(currentDate);
        System.out.println("Reservas actualizadas");
    }
}
