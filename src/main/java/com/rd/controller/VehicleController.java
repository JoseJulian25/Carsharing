package com.rd.controller;

import com.rd.DTO.VehicleRegisterDTO;
import com.rd.entity.Vehicle;
import com.rd.enums.EStatus;
import com.rd.service.VehicleServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/vehicle")
@RestController
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleServiceImp vehicleService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
       Page<Vehicle> pageVehicle = vehicleService.findAll(page, size);
        Map<String, Object> response = new HashMap<>();
        response.put("vehicles", pageVehicle.getContent());
        response.put("currentPage", pageVehicle.getNumber());
        response.put("totalItems", pageVehicle.getTotalElements());
        response.put("totalPages", pageVehicle.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Vehicle>> findByStatus(@RequestParam EStatus status){
        return ResponseEntity.ok(vehicleService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<VehicleRegisterDTO> saveVehicle(@RequestBody VehicleRegisterDTO vehicle){
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicle));
    }

}
