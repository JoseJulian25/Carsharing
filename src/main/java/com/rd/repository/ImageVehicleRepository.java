package com.rd.repository;

import com.rd.entity.ImageVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageVehicleRepository extends JpaRepository<ImageVehicle, Integer> {
    List<ImageVehicle> findByVehicle_Id(Integer id);
}
