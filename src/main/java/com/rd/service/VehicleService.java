package com.rd.service;

import com.rd.DTO.VehicleDTO;
import com.rd.entity.enums.StatusVehicle;
import com.rd.entity.enums.TypeVehicle;
import org.springframework.data.domain.Page;
import java.util.List;

public interface VehicleService {
    Page<VehicleDTO> findAllPage(int pageable, int size);
    List<VehicleDTO> findAll();
    List<VehicleDTO> findByStatus(StatusVehicle status);
    List<VehicleDTO> findByColor(String color);
    List<VehicleDTO> findByColorAndType(String color, TypeVehicle typeVehicle);
    List<VehicleDTO> findByColorTypeAndMake(String color, TypeVehicle typeVehicle, String make);
    List<VehicleDTO> findByMake(String make);
    List<VehicleDTO> findByType(TypeVehicle typeVehicle);
    VehicleDTO saveVehicle(VehicleDTO vehicleDTO);
    VehicleDTO findById(Integer id);
    VehicleDTO updateVehicle(VehicleDTO vehicle, Integer id);
    void deleteVehicle(Integer id);
}
