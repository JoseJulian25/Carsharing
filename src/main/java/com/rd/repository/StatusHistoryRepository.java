package com.rd.repository;

import com.rd.entity.StatusHistory;
import com.rd.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Integer> {
    StatusHistory findTopByVehicleAndActiveOrderByUpdateDateDesc(Vehicle vehicle, boolean active);
}
