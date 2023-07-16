package com.rd.service;

import com.rd.DTO.VehicleRatingRequestDTO;
import com.rd.DTO.VehicleRatingResponseDTO;

import java.util.List;

public interface VehicleRatingsService {
    VehicleRatingResponseDTO saveRating(VehicleRatingRequestDTO vehicleRatings, Integer userId, Integer VehicleId);
    List<VehicleRatingResponseDTO> findByUser(Integer userId);
    List<VehicleRatingResponseDTO> findByVehicle(Integer vehicleId);
    String delete(Integer id);
}
