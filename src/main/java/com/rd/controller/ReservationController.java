package com.rd.controller;

import com.rd.DTO.ReservationDTO;
import com.rd.entity.Reservation;
import com.rd.service.impl.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/reservation")
@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationServiceImpl reservationService;

    @GetMapping
    public ResponseEntity<ReservationDTO> findById(@RequestParam Integer id){
        return ResponseEntity.ok(reservationService.findById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<ReservationDTO>> findAll(){
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ReservationDTO>> findActiveReservations(){
        return ResponseEntity.ok(reservationService.findActiveReservations());
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> saveReservation(@RequestBody Reservation reservation, @RequestParam Integer userId, @RequestParam Integer vehicleId){
        return ResponseEntity.ok(reservationService.saveReservation(reservation, userId, vehicleId));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteReservation(@RequestParam Integer id){
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation eliminated");
    }
}
