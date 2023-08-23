package com.rd.controller;

import com.rd.DTO.request.VehicleRatingRequestDTO;
import com.rd.DTO.response.VehicleRatingResponseDTO;
import com.rd.service.VehicleRatingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Tag(name = "Rating's vehicles Controller", description = "Endpoints to manage ratings of vehicles")
@RequestMapping("api/rating")
@RestController
@RequiredArgsConstructor
public class VehicleRatingsController {
    private final VehicleRatingsService vehicleRatingsService;

    @Operation(summary = "Find ratings by userID", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VehicleRatingResponseDTO>> findByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(vehicleRatingsService.findByUser(userId));
    }

    @Operation(summary = "Find ratings by VehicleID", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<VehicleRatingResponseDTO>> findByVehicleId(@PathVariable Integer vehicleId){
        return ResponseEntity.ok(vehicleRatingsService.findByVehicle(vehicleId));
    }

    @Operation(summary = "Save rating", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @PostMapping()
    public ResponseEntity<VehicleRatingResponseDTO> saveRating(@RequestBody @Valid VehicleRatingRequestDTO vehicleRatingRequestDTO, @RequestParam Integer userId, @RequestParam Integer vehicleId){
        return ResponseEntity.ok(vehicleRatingsService.saveRating(vehicleRatingRequestDTO, userId, vehicleId));
    }

    @Operation(summary = "Delete rating by ID", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @DeleteMapping
    public ResponseEntity<String> deleteRating(@RequestParam Integer id){
        return ResponseEntity.ok(vehicleRatingsService.delete(id));
    }

}
