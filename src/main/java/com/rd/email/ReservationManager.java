package com.rd.email;

import com.rd.entity.Reservation;
import com.rd.repository.ReservationRepository;
import com.rd.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationManager {
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;
    private final List<Reservation> activeReservations = new ArrayList<>();
    private final ReservationEmailService reservationEmailService;
    private final String EVERY_HOUR = "0 0 * * * *";
    private final String EVERY_TEN_MINUTES = "0 */10 * * * *";

    @Scheduled(cron = EVERY_HOUR)
    public void refreshActiveReservations() {
        activeReservations.clear();
        activeReservations.addAll(reservationRepository.findActiveReservations());
    }

    @Scheduled(cron = EVERY_TEN_MINUTES)
    public void processCompletedReservation() {
        List<Reservation> completedReservations = new ArrayList<>();

        for (Reservation reservation : activeReservations) {
            long minutesRemaining = ChronoUnit.MINUTES.between(LocalDateTime.now(),reservation.getEndDate());

            if (minutesRemaining > 0 && minutesRemaining <= 120 && !reservation.isEmailSent()) {
                    sendEmailForReservation(reservation);
                    reservation.setEmailSent(true);
            }else if(minutesRemaining <= 0){
                    reservationService.updateCompletedReservation(reservation.getId());
                    completedReservations.add(reservation);
            }
        }
           activeReservations.removeAll(completedReservations);
    }

    private void sendEmailForReservation(Reservation reservation){
        reservationEmailService.sendEmailReservation(reservation);
        reservationService.setReservationEmailSent(reservation.getId());
    }
}
