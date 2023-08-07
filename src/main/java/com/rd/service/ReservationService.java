package com.rd.service;

import com.rd.DTO.request.ReservationRequestDTO;
import com.rd.DTO.response.ReservationResponseDTO;
import java.util.List;


public interface ReservationService {
    ReservationResponseDTO findById(Integer id);
    List<ReservationResponseDTO> findAll();
    ReservationResponseDTO saveReservation(ReservationRequestDTO reservation, Integer vehicleId, Integer userId);
    List<ReservationResponseDTO> findActiveReservations();
    List<ReservationResponseDTO> findByUserId(Integer id);
    void deleteReservation(Integer id);
    void updateCompletedReservation(Integer id);
    void setReservationEmailSent(Integer id);
}
