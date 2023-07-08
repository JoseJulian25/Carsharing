package com.rd.service.impl;

import com.rd.DTO.VehicleDTO;
import com.rd.entity.*;
import com.rd.enums.EStatus;
import com.rd.enums.ETypeVehicle;
import com.rd.exception.DataNotFoundException;
import com.rd.repository.*;
import com.rd.service.VehicleService;
import com.rd.service.VehicleServiceHelper;
import com.rd.utils.ListValidation;
import com.rd.utils.VehicleMapper;
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

    @Override
    public Page<VehicleDTO> findAllPage(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Vehicle> vehicles = vehicleRepository.findAll(paging);
        return VehicleMapper.buildPageVehicleDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        ListValidation.checkNonEmptyList(vehicles, () -> "Not Found");
        return VehicleMapper.buildListVehicleDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByStatus(EStatus status) {
        List<Vehicle> vehicles = vehicleRepository.findByStatus_Status(status);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with status: " + status);
        return VehicleMapper.buildListVehicleDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByColor(String color) {
        List<Vehicle> vehicles = vehicleRepository.findByColor(color);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with color: " + color);
        return VehicleMapper.buildListVehicleDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByColorAndType(String color, ETypeVehicle typeVehicle) {
        List<Vehicle> vehicles = vehicleRepository.findByColorAndType_Type(color, typeVehicle);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with color and type: " + color + ", " + typeVehicle );
        return VehicleMapper.buildListVehicleDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByColorTypeAndMake(String color, ETypeVehicle typeVehicle,String make) {
        List<Vehicle> vehicles = vehicleRepository.findAllByMake_NameAndColorAndType_Type(make, color, typeVehicle);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found wih make: " + make + ", color: " + color + " and type: "+ typeVehicle);
        return VehicleMapper.buildListVehicleDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByMake(String make) {
        List<Vehicle> vehicles = vehicleRepository.findAllByMake_Name(make);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with make: " + make);
        return VehicleMapper.buildListVehicleDTO(vehicles);
    }

    @Override
    public List<VehicleDTO> findByType(ETypeVehicle typeVehicle) {
        List<Vehicle> vehicles = vehicleRepository.findAllByType_Type(typeVehicle);
        ListValidation.checkNonEmptyList(vehicles, () -> "Vehicle not found with type: " + typeVehicle);
        return VehicleMapper.buildListVehicleDTO(vehicles);
    }

    @Override
    public VehicleDTO findById(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Vehicle not found with id: " + id));
        return VehicleMapper.buildVehicleDTO(vehicle);
    }

    @Transactional
    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        Make make = vehicleServiceHelper.findOrCreateMake(vehicleDTO.getMakeName());
        Model model = vehicleServiceHelper.findOrCreateModel(vehicleDTO.getModelName(), make);
        VehicleStatus vehicleStatus = vehicleServiceHelper.findOrCreateVehicleStatus(vehicleDTO.getStatus());
        TypeVehicle typeVehicle = vehicleServiceHelper.findOrCreateTypeVehicle(vehicleDTO.getType());

        Vehicle vehicle = VehicleMapper.buildVehicleObject(vehicleDTO, make, model, vehicleStatus, typeVehicle);
        vehicleServiceHelper.createStatusHistory(vehicle);
        vehicleRepository.save(vehicle);

        return VehicleMapper.buildVehicleDTO(vehicle);
    }

    @Transactional
    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO, Integer id) {
        Vehicle existingVehicle = vehicleRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Vehicle not found with id: " + id));

        EStatus existingStatus = existingVehicle.getStatus().getStatus();

        Make make = vehicleServiceHelper.findOrCreateMake(vehicleDTO.getMakeName());
        Model model = vehicleServiceHelper.findOrCreateModel(vehicleDTO.getModelName(), make);
        VehicleStatus vehicleStatus = vehicleServiceHelper.findOrCreateVehicleStatus(vehicleDTO.getStatus());
        TypeVehicle typeVehicle = vehicleServiceHelper.findOrCreateTypeVehicle(vehicleDTO.getType());
        updateVehicleFields(vehicleDTO, existingVehicle, make, model, vehicleStatus, typeVehicle);

        if(!(vehicleDTO.getStatus().equals(existingStatus))){
            vehicleServiceHelper.deactiveLastStatus(existingVehicle);
            vehicleServiceHelper.createStatusHistory(existingVehicle);
        }

        return VehicleMapper.buildVehicleDTO(vehicleRepository.save(existingVehicle));
    }

    private static void updateVehicleFields(VehicleDTO vehicleDTO, Vehicle existingVehicle, Make make, Model model, VehicleStatus vehicleStatus, TypeVehicle typeVehicle) {
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
        Vehicle existingVehicle = vehicleRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Vehicle not found with id: " + id));
        vehicleRepository.delete(existingVehicle);
    }
}
