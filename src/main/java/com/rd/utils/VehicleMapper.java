package com.rd.utils;

import com.rd.DTO.VehicleDTO;
import com.rd.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.ArrayList;
import java.util.List;

public class VehicleMapper {

    public static VehicleDTO buildVehicleDTO(Vehicle vehicle){
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

    public static List<VehicleDTO> buildListVehicleDTO(List<Vehicle> vehicles){
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
         vehicles.forEach(vehicleDTO -> vehicleDTOS.add(buildVehicleDTO(vehicleDTO)));
        return vehicleDTOS;
    }

    public static Page<VehicleDTO> buildPageVehicleDTO(Page<Vehicle> vehicles){
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        vehicles.forEach( vehicle -> vehicleDTOS.add(buildVehicleDTO(vehicle)));
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
