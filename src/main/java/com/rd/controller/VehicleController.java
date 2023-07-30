package com.rd.controller;

import com.rd.DTO.VehicleDTO;
import com.rd.enums.EStatus;
import com.rd.enums.ETypeVehicle;
import com.rd.service.impl.VehicleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/vehicle")
@RestController
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleServiceImpl vehicleService;

    @GetMapping("/page")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
       Page<VehicleDTO> pageVehicle = vehicleService.findAllPage(page, size);
        Map<String, Object> response = new HashMap<>();
        response.put("vehicles", pageVehicle.getContent());
        response.put("currentPage", pageVehicle.getNumber());
        response.put("totalItems", pageVehicle.getTotalElements());
        response.put("totalPages", pageVehicle.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll(){
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<VehicleDTO>> findByStatus(@PathVariable EStatus status){
        return ResponseEntity.ok(vehicleService.findByStatus(status));
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<VehicleDTO>> findByColor(@PathVariable String color){
        return ResponseEntity.ok(vehicleService.findByColor(color));
    }

    @GetMapping("/color/{color}/type/{type}")
    public ResponseEntity<List<VehicleDTO>> findByColorAndType(@PathVariable String color, @PathVariable ETypeVehicle type){
        return ResponseEntity.ok(vehicleService.findByColorAndType(color, type));
    }

    @GetMapping("/color/{color}/type/{type}/make/{make}")
    public ResponseEntity<List<VehicleDTO>> findByMakeColorAndType(@PathVariable String color, @PathVariable ETypeVehicle type, @PathVariable String make){
        return ResponseEntity.ok(vehicleService.findByColorTypeAndMake(color, type, make));
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<List<VehicleDTO>> findByMake(@PathVariable String make){
        return ResponseEntity.ok(vehicleService.findByMake(make));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<VehicleDTO>> findByType(@PathVariable ETypeVehicle type){
        return ResponseEntity.ok(vehicleService.findByType(type));
    }
    @GetMapping("/id")
    public ResponseEntity<VehicleDTO> findById(@RequestParam Integer id){
        return ResponseEntity.ok(vehicleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicle){
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicle));
    }

    @PutMapping
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicle, @RequestParam Integer id){
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicle, id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteVehicle(@RequestParam Integer id){
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>("Vehicle eliminated", HttpStatus.OK);
    }

}
