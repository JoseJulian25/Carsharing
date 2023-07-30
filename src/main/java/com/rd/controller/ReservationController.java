package com.rd.controller;

import com.rd.DTO.request.ReservationRequestDTO;
import com.rd.DTO.response.ReservationResponseDTO;
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
    public ResponseEntity<ReservationResponseDTO> findById(@RequestParam Integer id){
        return ResponseEntity.ok(reservationService.findById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<ReservationResponseDTO>> findAll(){
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ReservationResponseDTO>> findActiveReservations(){
        return ResponseEntity.ok(reservationService.findActiveReservations());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReservationResponseDTO>> findByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(reservationService.findByUserId(id));
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> saveReservation(@RequestBody ReservationRequestDTO reservation, @RequestParam Integer userId, @RequestParam Integer vehicleId){
        return ResponseEntity.ok(reservationService.saveReservation(reservation, userId, vehicleId));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteReservation(@RequestParam Integer id){
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation eliminated");
    }
}
