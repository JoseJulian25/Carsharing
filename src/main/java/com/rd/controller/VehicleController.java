package com.rd.controller;

import com.rd.DTO.VehicleDTO;
import com.rd.entity.enums.StatusVehicle;
import com.rd.entity.enums.ETypeVehicle;
import com.rd.service.VehicleService;
import com.rd.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Vehicle Controller", description = "Endpoints to manage Vehicles")
@RequestMapping("/api/vehicle")
@RestController
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @Operation(summary = "Find vehicles using pageable",
            description = "Default value of page is 0, default value of size is 10" +
                    "<br>Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/page")
    public ResponseEntity<Map<String, Object>> findAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
       Page<VehicleDTO> pageVehicle = vehicleService.findAllPage(page, size);
        return ResponseEntity.ok(ResponseUtils.createPageResponse(pageVehicle, "vehicles"));
    }

    @Operation(summary = "Find all vehicles", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll(){
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @Operation(summary = "Find vehicles by Status", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/status/{status}")
    public ResponseEntity<List<VehicleDTO>> findByStatus(@PathVariable StatusVehicle status){
        return ResponseEntity.ok(vehicleService.findByStatus(status));
    }

    @Operation(summary = "Find vehicles by color", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/color/{color}")
    public ResponseEntity<List<VehicleDTO>> findByColor(@PathVariable String color){
        return ResponseEntity.ok(vehicleService.findByColor(color));
    }

    @Operation(summary = "Find vehicles by color and Type", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/color/{color}/type/{type}")
    public ResponseEntity<List<VehicleDTO>> findByColorAndType(@PathVariable String color, @PathVariable ETypeVehicle type){
        return ResponseEntity.ok(vehicleService.findByColorAndType(color, type));
    }

    @Operation(summary = "Find vehicles by color, type and make", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/color/{color}/type/{type}/make/{make}")
    public ResponseEntity<List<VehicleDTO>> findByMakeColorAndType(@PathVariable String color, @PathVariable ETypeVehicle type, @PathVariable String make){
        return ResponseEntity.ok(vehicleService.findByColorTypeAndMake(color, type, make));
    }

    @Operation(summary = "Find vehicles by make", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/make/{make}")
    public ResponseEntity<List<VehicleDTO>> findByMake(@PathVariable String make){
        return ResponseEntity.ok(vehicleService.findByMake(make));
    }

    @Operation(summary = "Find vehicles by type", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/type/{type}")
    public ResponseEntity<List<VehicleDTO>> findByType(@PathVariable ETypeVehicle type){
        return ResponseEntity.ok(vehicleService.findByType(type));
    }

    @Operation(summary = "Find vehicle by ID", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/id")
    public ResponseEntity<VehicleDTO> findById(@RequestParam Integer id){
        return ResponseEntity.ok(vehicleService.findById(id));
    }

    @Operation(summary = "save vehicle", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @PostMapping
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicle){
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicle));
    }

    @Operation(summary = "update vehicle", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @PutMapping
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody @Valid VehicleDTO vehicle, @RequestParam Integer id){
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicle, id));
    }

    @Operation(summary = "Delete vehicle by ID", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @DeleteMapping
    public ResponseEntity<String> deleteVehicle(@RequestParam Integer id){
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>("Vehicle eliminated", HttpStatus.OK);
    }

}
