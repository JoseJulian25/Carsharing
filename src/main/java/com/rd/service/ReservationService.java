package com.rd.service;

import com.rd.DTO.ReservationDTO;
import com.rd.entity.Reservation;
import java.util.List;


public interface ReservationService {
    ReservationDTO findById(Integer id);
    List<ReservationDTO> findAll();
    ReservationDTO saveReservation(Reservation reservation, Integer vehicleId, Integer userId);
    List<ReservationDTO> findActiveReservations();
    void deleteReservation(Integer id);
}
