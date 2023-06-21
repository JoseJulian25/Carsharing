package com.rd.service;

import com.rd.entity.Make;
import com.rd.entity.Model;
import com.rd.entity.TypeVehicle;
import com.rd.entity.VehicleStatus;
import com.rd.enums.EStatus;
import com.rd.enums.ETypeVehicle;
import com.rd.repository.MakeRepository;
import com.rd.repository.ModelRepository;
import com.rd.repository.TypeVehicleRepository;
import com.rd.repository.VehicleStatusRepository;
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
            newVehicleStatus.setUpdateDate(LocalDateTime.now());
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
}

