package com.rd.repository;

import com.rd.entity.TypeVehicle;
import com.rd.enums.ETypeVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeVehicleRepository extends JpaRepository<TypeVehicle, Integer> {
    Optional<TypeVehicle> findByType(ETypeVehicle eTypeVehicle);
}
