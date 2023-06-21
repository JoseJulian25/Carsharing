package com.rd.controller;

import com.rd.DTO.VehicleRegisterDTO;
import com.rd.entity.Vehicle;
import com.rd.service.VehicleServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/vehicle")
@RestController
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleServiceImp vehicleService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(vehicleService.findAll(page, size));
    }
    @PostMapping
    public ResponseEntity<VehicleRegisterDTO> saveVehicle(@RequestBody VehicleRegisterDTO vehicle){
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicle));
    }
}
