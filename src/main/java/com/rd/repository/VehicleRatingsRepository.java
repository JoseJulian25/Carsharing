package com.rd.repository;

import com.rd.entity.VehicleRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleRatingsRepository extends JpaRepository<VehicleRatings,Integer > {
    List<VehicleRatings> findAllByVehicle_Id(Integer id);
    List<VehicleRatings> findAllByUser_Id(Integer id);
}
