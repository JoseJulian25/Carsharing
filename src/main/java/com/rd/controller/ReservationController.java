package com.rd.controller;

import com.rd.DTO.request.ReservationRequestDTO;
import com.rd.DTO.response.ReservationResponseDTO;
import com.rd.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/reservation")
@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @Secured({"USER", "ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @Secured("ADMIN")
    @GetMapping()
    public ResponseEntity<List<ReservationResponseDTO>> findAll(){
        return ResponseEntity.ok(reservationService.findAll());
    }

    @Secured("ADMIN")
    @GetMapping("/active")
    public ResponseEntity<List<ReservationResponseDTO>> findActiveReservations(){
        return ResponseEntity.ok(reservationService.findActiveReservations());
    }

    @Secured({"USER", "ADMIN"})
    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReservationResponseDTO>> findByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(reservationService.findByUserId(id));
    }

    @Secured({"USER", "ADMIN"})
    @PostMapping
    public ResponseEntity<ReservationResponseDTO> saveReservation(@RequestBody ReservationRequestDTO reservation, @RequestParam Integer userId, @RequestParam Integer vehicleId){
        return ResponseEntity.ok(reservationService.saveReservation(reservation, userId, vehicleId));
    }

    @Secured({"USER", "ADMIN"})
    @DeleteMapping
    public ResponseEntity<String> deleteReservation(@RequestParam Integer id){
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation eliminated");
    }
}
