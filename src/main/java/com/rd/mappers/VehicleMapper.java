package com.rd.mappers;

import com.rd.DTO.VehicleDTO;
import com.rd.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.ArrayList;
import java.util.List;

public class VehicleMapper {

    public static VehicleDTO buildDTO(Vehicle vehicle){
        return VehicleDTO.builder()
                .id(vehicle.getId())
                .serialNumber(vehicle.getSerialNumber())
                .makeName(vehicle.getMake().getName())
                .modelName(vehicle.getModel().getName())
                .type(vehicle.getType().getType())
                .status(vehicle.getStatus().getStatus())
                .price(vehicle.getPrice())
                .color(vehicle.getColor())
                .additionalNotes(vehicle.getAdditionalNotes())
                .build();
    }

    public static List<VehicleDTO> buildListDTO(List<Vehicle> vehicles){
        return vehicles.stream().map(VehicleMapper::buildDTO).toList();
    }

    public static Page<VehicleDTO> buildPageDTO(Page<Vehicle> vehicles){
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        vehicles.forEach( vehicle -> vehicleDTOS.add(buildDTO(vehicle)));
        return new PageImpl<>(vehicleDTOS, vehicles.getPageable(), vehicles.getTotalElements());
    }

    public static Vehicle buildVehicleObject(VehicleDTO vehicleDTO, Make make, Model model, VehicleStatus vehicleStatus, TypeVehicle typeVehicle) {
        return Vehicle.builder()
                .id(vehicleDTO.getId())
                .serialNumber(vehicleDTO.getSerialNumber())
                .make(make)
                .model(model)
                .type(typeVehicle)
                .price(vehicleDTO.getPrice())
                .color(vehicleDTO.getColor())
                .status(vehicleStatus)
                .additionalNotes(vehicleDTO.getAdditionalNotes())
                .build();
    }
}
