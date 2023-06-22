package com.rd.service;

import com.rd.DTO.UserRegisterDTO;
import com.rd.DTO.VehicleRegisterDTO;
import com.rd.entity.*;
import com.rd.enums.EStatus;
import com.rd.enums.ETypeVehicle;
import com.rd.exception.DataNotFoundException;
import com.rd.repository.*;
import com.rd.utils.ListValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImp implements VehicleService{
    private final VehicleRepository vehicleRepository;
    private final VehicleServiceHelper vehicleServiceHelper;

    @Override
    public Page<Vehicle> findAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return vehicleRepository.findAll(paging);
    }

    @Override
    public List<Vehicle> findByStatus(EStatus status) {
        List<Vehicle> vehicles = vehicleRepository.findByStatus_Status(status);
        return ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with status: " + status);
    }

    @Override
    public List<Vehicle> findByColor(String color) {
        List<Vehicle> vehicles = vehicleRepository.findByColor(color);
        return ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with color: " + color);
    }

    @Override
    public List<Vehicle> findByColorAndType(String color, ETypeVehicle typeVehicle) {
        List<Vehicle> vehicles = vehicleRepository.findByColorAndType_Type(color, typeVehicle);
        return ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with color and type: " + color + ", " + typeVehicle );
    }

    @Override
    public List<Vehicle> findAllByMakeColorAndType(String make, String color, ETypeVehicle typeVehicle) {
        List<Vehicle> vehicles = vehicleRepository.findAllByMake_NameAndColorAndType_Type(make, color, typeVehicle);
        return ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found wih make: " + make + ", color: " + color + " and type: "+ typeVehicle);
    }

    @Override
    public List<Vehicle> findAllByMake(String make) {
        List<Vehicle> vehicles = vehicleRepository.findAllByMake_Name(make);
        return ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with make: " + make);
    }

    @Transactional
    @Override
    public VehicleRegisterDTO saveVehicle(VehicleRegisterDTO vehicleRegisterDTO) {
        Make make = vehicleServiceHelper.findOrCreateMake(vehicleRegisterDTO.getMakeName());
        Model model = vehicleServiceHelper.findOrCreateModel(vehicleRegisterDTO.getModelName(), make);
        VehicleStatus vehicleStatus = vehicleServiceHelper.findOrCreateVehicleStatus(vehicleRegisterDTO.getStatus());
        TypeVehicle typeVehicle = vehicleServiceHelper.findOrCreateTypeVehicle(vehicleRegisterDTO.getType());

        Vehicle vehicle = buildVehicleObject(vehicleRegisterDTO, make, model, vehicleStatus, typeVehicle);
        vehicleServiceHelper.createStatusHistory(vehicle);
        vehicleRepository.save(vehicle);
        return vehicleRegisterDTO;
    }

    private Vehicle buildVehicleObject(VehicleRegisterDTO vehicleRegisterDTO, Make make, Model model, VehicleStatus vehicleStatus, TypeVehicle typeVehicle) {
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

    @Override
    public Vehicle findById(Integer id) {
        return vehicleRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Vehicle not found with id: " + id));
    }

    @Override
    public Vehicle UpdateVehicle(VehicleRegisterDTO vehicle, Integer id) {

    }
}
