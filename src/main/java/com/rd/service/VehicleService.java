package com.rd.service;

import com.rd.DTO.VehicleRegisterDTO;
import com.rd.entity.Vehicle;
import com.rd.enums.EStatus;
import com.rd.enums.ETypeVehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VehicleService {
    Map<String, Object> findAll(int pageable, int size);
    List<Vehicle> findByStatus(EStatus status);
    List<Vehicle> findByColor(String color);
    List<Vehicle> findByColorAndType(String color, ETypeVehicle typeVehicle);
    List<Vehicle> findAllByMakeColorAndType(String make, String color, ETypeVehicle typeVehicle );
    List<Vehicle> findAllByMake(String make);
    VehicleRegisterDTO saveVehicle(VehicleRegisterDTO vehicleRegisterDTO);
    Vehicle findById(Integer id);
    Vehicle UpdateVehicle(Vehicle vehicle, Integer id);

}
