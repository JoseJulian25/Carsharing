package com.rd.controller;

import com.rd.DTO.ReservationDTO;
import com.rd.entity.Reservation;
import com.rd.service.impl.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/reservation")
@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationServiceImpl reservationService;

    @PostMapping
    private ResponseEntity<ReservationDTO> saveReservation(@RequestBody Reservation reservation, @RequestParam Integer userId, @RequestParam Integer vehicleId){
        return ResponseEntity.ok(reservationService.saveReservation(reservation, userId, vehicleId));
    }
}
