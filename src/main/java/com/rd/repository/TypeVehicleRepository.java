package com.rd.repository;

import com.rd.entity.TypeVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeVehicleRepository extends JpaRepository<TypeVehicle, Integer> {
}
