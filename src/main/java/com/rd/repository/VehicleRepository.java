package com.rd.repository;

import com.rd.entity.Vehicle;
import com.rd.enums.ETypeVehicle;
import com.rd.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Page<Vehicle> findAll(Pageable pageable);
    List<Vehicle> findByStatus_Status(EStatus status);
    List<Vehicle> findByColor(String color);
    List<Vehicle> findByColorAndType_Type(String color, ETypeVehicle typeVehicle);
    List<Vehicle> findAllByMake_NameAndColorAndType_Type(String nameMake, String color, ETypeVehicle typeVehicle);
    List<Vehicle> findAllByMake_Name(String name);
    Optional<Vehicle> findById(Integer id);
    List<Vehicle> findAllByType_Type(ETypeVehicle typeVehicle);
}
