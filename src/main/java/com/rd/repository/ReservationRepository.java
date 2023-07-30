package com.rd.repository;

import com.rd.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findById(Integer id);

    @Query("SELECT r FROM Reservation r WHERE r.statusReservation = 'ACTIVE' AND r.endDate <:currentDate")
    List<Reservation> findCompletedReservations(Date currentDate);

    @Query("SELECT r FROM Reservation r WHERE r.statusReservation = 'ACTIVE'")
    List<Reservation> findActiveReservations();

    List<Reservation> findByUser_Id(Integer id);
}
