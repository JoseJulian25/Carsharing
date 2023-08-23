package com.rd.controller;

import com.rd.DTO.request.ReservationRequestDTO;
import com.rd.DTO.response.ReservationResponseDTO;
import com.rd.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reservation Controller", description = "Endpoints to managing reservations")
@RequestMapping("/api/reservation")
@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @Operation(summary = "Find reservation by ID", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @Operation(summary = "Find all reservations", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @GetMapping()
    public ResponseEntity<List<ReservationResponseDTO>> findAll(){
        return ResponseEntity.ok(reservationService.findAll());
    }

    @Operation(summary = "Find active reservations",
            description = "This endpoints is used to find reservations don't finish " +
                    "<br> Requires rol: ADMIN")
    @Secured("ADMIN")
    @GetMapping("/active")
    public ResponseEntity<List<ReservationResponseDTO>> findActiveReservations(){
        return ResponseEntity.ok(reservationService.findActiveReservations());
    }

    @Operation(summary = "Find reservations by userID", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReservationResponseDTO>> findByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(reservationService.findByUserId(id));
    }

    @Operation(summary = "Save reservation", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @PostMapping
    public ResponseEntity<ReservationResponseDTO> saveReservation(@RequestBody @Valid ReservationRequestDTO reservation, @RequestParam Integer userId, @RequestParam Integer vehicleId){
        return ResponseEntity.ok(reservationService.saveReservation(reservation, userId, vehicleId));
    }

    @Operation(summary = "Delete reservation by ID", description = "Requires roles: ADMIN")
    @Secured({"ADMIN"})
    @DeleteMapping
    public ResponseEntity<String> deleteReservation(@RequestParam Integer id){
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation eliminated");
    }
}
