package com.rd.service;

import com.rd.DTO.ReservationDTO;
import com.rd.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> findById(Integer id);
    ReservationDTO saveReservation(Reservation reservation, Integer vehicleId, Integer userId);
    List<Reservation> findActiveReservations();
}
