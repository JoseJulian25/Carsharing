package com.rd.service;

import com.rd.entity.*;
import com.rd.enums.EStatus;
import com.rd.enums.ETypeVehicle;
import com.rd.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VehicleServiceHelper {
    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;
    private final VehicleStatusRepository vehicleStatusRepository;
    private final TypeVehicleRepository typeVehicleRepository;
    private final StatusHistoryRepository statusHistoryRepository;

    public Make findOrCreateMake(String makeName) {
        return makeRepository.findByName(makeName).orElseGet(() -> {
            Make newMake = new Make();
            newMake.setName(makeName);
            return makeRepository.save(newMake);
        });
    }

    public Model findOrCreateModel(String modelName, Make make) {
        return modelRepository.findByNameAndMake(modelName, make).orElseGet(() -> {
            Model newModel = new Model();
            newModel.setName(modelName);
            newModel.setMake(make);
            return modelRepository.save(newModel);
        });
    }

    public VehicleStatus findOrCreateVehicleStatus(EStatus status) {
        return vehicleStatusRepository.findByStatus(status).orElseGet(() -> {
            VehicleStatus newVehicleStatus = new VehicleStatus();
            newVehicleStatus.setStatus(status);
            return vehicleStatusRepository.save(newVehicleStatus);
        });
    }

    public TypeVehicle findOrCreateTypeVehicle(ETypeVehicle type) {
        return typeVehicleRepository.findByType(type).orElseGet(() -> {
            TypeVehicle newTypeVehicle = new TypeVehicle();
            newTypeVehicle.setType(type);
            return typeVehicleRepository.save(newTypeVehicle);
        });
    }

    public void createStatusHistory(Vehicle vehicle){
        statusHistoryRepository.save(StatusHistory.builder()
                        .vehicle(vehicle)
                        .vehicleStatus(vehicle.getStatus())
                        .updateDate(LocalDateTime.now())
                        .active(true)
                .build());
    }

    public void deactivateLastStatus(Vehicle vehicle){
        StatusHistory lastActiveHistory = statusHistoryRepository.findTopByVehicleAndActiveOrderByUpdateDateDesc(vehicle, true);
        if(lastActiveHistory != null){
            lastActiveHistory.setActive(false);
            statusHistoryRepository.save(lastActiveHistory);
        }
    }
}

