package com.rd.repository;

import com.rd.entity.VehicleStatus;
import com.rd.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleStatusRepository extends JpaRepository<VehicleStatus, Integer> {
    Optional<VehicleStatus> findByStatus(EStatus eStatus);
}
