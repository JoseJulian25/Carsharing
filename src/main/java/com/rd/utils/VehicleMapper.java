package com.rd.utils;

import com.rd.DTO.VehicleRegisterDTO;
import com.rd.entity.*;

public class VehicleMapper {

    public static VehicleRegisterDTO buildVehicleRegisterDTO(Vehicle vehicle){
        return VehicleRegisterDTO.builder()
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

    public static Vehicle buildVehicleObject(VehicleRegisterDTO vehicleRegisterDTO, Make make, Model model, VehicleStatus vehicleStatus, TypeVehicle typeVehicle) {
        return Vehicle.builder()
                .serialNumber(vehicleRegisterDTO.getSerialNumber())
                .make(make)
                .model(model)
                .type(typeVehicle)
                .price(vehicleRegisterDTO.getPrice())
                .color(vehicleRegisterDTO.getColor())
                .status(vehicleStatus)
                .additionalNotes(vehicleRegisterDTO.getAdditionalNotes())
                .build();
    }
}
