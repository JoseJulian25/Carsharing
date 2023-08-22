package com.rd.repository;

import com.rd.entity.enums.TypeVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeVehicleRepository extends JpaRepository<com.rd.entity.TypeVehicle, Integer> {
    Optional<com.rd.entity.TypeVehicle> findByType(TypeVehicle typeVehicle);
}
