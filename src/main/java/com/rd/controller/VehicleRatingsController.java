package com.rd.controller;

import com.rd.DTO.VehicleRatingRequestDTO;
import com.rd.DTO.VehicleRatingResponseDTO;
import com.rd.service.impl.VehicleRatingsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/rating")
@RestController
@RequiredArgsConstructor
public class VehicleRatingsController {
    private final VehicleRatingsServiceImpl vehicleRatingsService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<VehicleRatingResponseDTO> findByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(vehicleRatingsService.findByUser(userId));
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<VehicleRatingResponseDTO> findByVehicleId(@PathVariable Integer vehicleId){
        return ResponseEntity.ok(vehicleRatingsService.findByVehicle(vehicleId));
    }

    @PostMapping()
    public ResponseEntity<VehicleRatingResponseDTO> saveRating(@RequestBody VehicleRatingRequestDTO vehicleRatingRequestDTO, @RequestParam Integer userId, @RequestParam Integer vehicleId){
        return ResponseEntity.ok(vehicleRatingsService.saveRating(vehicleRatingRequestDTO, userId, vehicleId));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRating(@RequestParam Integer id){
        return ResponseEntity.ok(vehicleRatingsService.delete(id));
    }

}
