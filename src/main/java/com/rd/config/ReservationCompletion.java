package com.rd.config;

import com.rd.entity.Reservation;
import com.rd.service.impl.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ReservationCompletion {
    private final ReservationServiceImpl reservationService;
    private final List<Reservation> activeReservations = new ArrayList<>();

    @Scheduled(cron = "@hourly")
    public void initializeActiveReservations() {
        List<Reservation> activeReservationsFromDB = reservationService.findActiveReservations();
        activeReservations.addAll(activeReservationsFromDB);
    }

    @Scheduled(cron = "0 */2 * * * *")
    public void updateCompletedReservations() {
        Date currentDate = new Date();
        List<Reservation> completedReservations = new ArrayList<>();

        for (Reservation reservation : activeReservations) {
            long timeRemaining = reservation.getEndDate().getTime() - currentDate.getTime();
            long minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(timeRemaining);

            if (minutesRemaining <= 30) {
                reservationService.updateCompletedReservations(reservation.getId());

                completedReservations.add(reservation);
            }
        }
        for (Reservation completedReservation : completedReservations) {
            activeReservations.remove(completedReservation);
        }
        System.out.println("Reservas actualizadas");
    }
}
