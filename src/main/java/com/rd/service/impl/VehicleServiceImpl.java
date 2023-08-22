package com.rd.service.impl;

import com.rd.DTO.VehicleDTO;
import com.rd.entity.*;
import com.rd.entity.enums.StatusVehicle;
import com.rd.entity.enums.TypeVehicle;
import com.rd.exception.DataNotFoundException;
import com.rd.repository.*;
import com.rd.service.VehicleService;
import com.rd.service.VehicleServiceHelper;
import com.rd.utils.ListValidation;
import com.rd.mappers.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleServiceHelper vehicleServiceHelper;
    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;

    @Override
    public Page<VehicleDTO> findAllPage(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Vehicle> vehicles = vehicleRepository.findAll(paging);
        return VehicleMapper.buildPageDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        ListValidation.checkNonEmptyList(vehicles, () -> "Not Found");
        return VehicleMapper.buildListDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByStatus(StatusVehicle status) {
        List<Vehicle> vehicles = vehicleRepository.findByStatus_Status(status);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with status: " + status);
        return VehicleMapper.buildListDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByColor(String color) {
        List<Vehicle> vehicles = vehicleRepository.findByColor(color);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with color: " + color);
        return VehicleMapper.buildListDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByColorAndType(String color, TypeVehicle typeVehicle) {
        List<Vehicle> vehicles = vehicleRepository.findByColorAndType_Type(color, typeVehicle);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with color and type: " + color + ", " + typeVehicle );
        return VehicleMapper.buildListDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByColorTypeAndMake(String color, TypeVehicle typeVehicle, String make) {
        List<Vehicle> vehicles = vehicleRepository.findAllByMake_NameAndColorAndType_Type(make, color, typeVehicle);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found wih make: " + make + ", color: " + color + " and type: "+ typeVehicle);
        return VehicleMapper.buildListDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByMake(String make) {
        List<Vehicle> vehicles = vehicleRepository.findAllByMake_Name(make);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with make: " + make);
        return VehicleMapper.buildListDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByType(TypeVehicle typeVehicle) {
        List<Vehicle> vehicles = vehicleRepository.findAllByType_Type(typeVehicle);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with type: " + typeVehicle);
        return VehicleMapper.buildListDTO(vehicles);
    }

    @Override
    public VehicleDTO findById(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Vehicle not found with id: " + id));
        return VehicleMapper.buildDTO(vehicle);
    }

    @Transactional
    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        Vehicle validatedVehicle = validateAndBuildVehicle(vehicleDTO);

        Vehicle vehicle = VehicleMapper.buildVehicleObject(vehicleDTO, validatedVehicle.getMake(),
                validatedVehicle.getModel(), validatedVehicle.getStatus(), validatedVehicle.getType());

        vehicleServiceHelper.createStatusHistory(vehicle);
        return VehicleMapper.buildDTO(vehicleRepository.save(vehicle));
    }

    @Transactional
    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO, Integer id) {
        Vehicle existingVehicle = vehicleRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Vehicle not found with id: " + id));

        StatusVehicle existingStatus = existingVehicle.getStatus().getStatus();

        Vehicle validatedVehicle = validateAndBuildVehicle(vehicleDTO);

        updateVehicleFields(vehicleDTO, existingVehicle, validatedVehicle.getMake(), validatedVehicle.getModel(), validatedVehicle.getStatus(), validatedVehicle.getType());

        if(!(vehicleDTO.getStatus().equals(existingStatus))){
            vehicleServiceHelper.deactivateLastStatus(existingVehicle);
            vehicleServiceHelper.createStatusHistory(existingVehicle);
        }

        return VehicleMapper.buildDTO(vehicleRepository.save(existingVehicle));
    }

    private static void updateVehicleFields(VehicleDTO vehicleDTO, Vehicle existingVehicle, Make make, Model model, VehicleStatus vehicleStatus, com.rd.entity.TypeVehicle typeVehicle) {
        existingVehicle.setSerialNumber(vehicleDTO.getSerialNumber());
        existingVehicle.setMake(make);
        existingVehicle.setModel(model);
        existingVehicle.setType(typeVehicle);
        existingVehicle.setColor(vehicleDTO.getColor());
        existingVehicle.setPrice(vehicleDTO.getPrice());
        existingVehicle.setStatus(vehicleStatus);
        existingVehicle.setAdditionalNotes(vehicleDTO.getAdditionalNotes());
    }

    @Override
    public void deleteVehicle(Integer id){
        Vehicle existingVehicle = vehicleRepository.findById(id).orElseThrow(() -> new IllegalStateException("Vehicle not found with id: " + id));
        vehicleRepository.delete(existingVehicle);
    }

    @Transactional
    public Vehicle validateAndBuildVehicle(VehicleDTO vehicleDTO){
        Make make = makeRepository.findByName(vehicleDTO.getMakeName()).orElseThrow(() -> new IllegalStateException("Make not found"));
        Model model = modelRepository.findByNameAndMake(vehicleDTO.getModelName(), make).orElseThrow(() -> new IllegalStateException("Model not found"));
        VehicleStatus vehicleStatus = vehicleServiceHelper.findOrCreateVehicleStatus(vehicleDTO.getStatus());
        com.rd.entity.TypeVehicle typeVehicle = vehicleServiceHelper.findOrCreateTypeVehicle(vehicleDTO.getType());
        
        return new Vehicle(make, model, vehicleStatus, typeVehicle);
    }
}
