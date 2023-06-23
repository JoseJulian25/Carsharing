package com.rd.service;

import com.rd.DTO.VehicleDTO;
import com.rd.enums.EStatus;
import com.rd.enums.ETypeVehicle;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehicleService {
    Page<VehicleDTO> findAllPage(int pageable, int size);
    List<VehicleDTO> findAll();
    List<VehicleDTO> findByStatus(EStatus status);
    List<VehicleDTO> findByColor(String color);
    List<VehicleDTO> findByColorAndType(String color, ETypeVehicle typeVehicle);
    List<VehicleDTO> findByColorTypeAndMake(String color, ETypeVehicle typeVehicle,String make);
    List<VehicleDTO> findByMake(String make);
    List<VehicleDTO> findByType(ETypeVehicle typeVehicle);
    VehicleDTO saveVehicle(VehicleDTO vehicleDTO);
    VehicleDTO findById(Integer id);
    VehicleDTO updateVehicle(VehicleDTO vehicle, Integer id);
}
