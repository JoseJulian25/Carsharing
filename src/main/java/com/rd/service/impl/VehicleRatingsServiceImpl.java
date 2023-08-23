package com.rd.service.impl;

import com.rd.DTO.request.VehicleRatingRequestDTO;
import com.rd.DTO.response.VehicleRatingResponseDTO;
import com.rd.entity.User;
import com.rd.entity.Vehicle;
import com.rd.entity.VehicleRatings;
import com.rd.exception.DataNotFoundException;
import com.rd.repository.UserRepository;
import com.rd.repository.VehicleRatingsRepository;
import com.rd.repository.VehicleRepository;
import com.rd.service.VehicleRatingsService;
import com.rd.mappers.VehicleRatingsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleRatingsServiceImpl implements VehicleRatingsService {
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final VehicleRatingsRepository vehicleRatingsRepository;

    @Transactional
    @Override
    public VehicleRatingResponseDTO saveRating(VehicleRatingRequestDTO vehicleRatings, Integer userId, Integer vehicleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new IllegalStateException("vehicle not found"));

        VehicleRatings vehicleRatings1 = VehicleRatingsMapper.buildObject(vehicleRatings, user, vehicle);
        return VehicleRatingsMapper.buildDTO(vehicleRatingsRepository.save(vehicleRatings1));
    }

    @Override
    public List<VehicleRatingResponseDTO> findByUser(Integer userId) {
        userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("user not found"));

        List<VehicleRatings> vehicleRatings = vehicleRatingsRepository.findAllByUser_Id(userId);
        return VehicleRatingsMapper.buildListVehicleRatingResponse(vehicleRatings);
    }

    @Override
    public List<VehicleRatingResponseDTO> findByVehicle(Integer vehicleId) {
        vehicleRepository.findById(vehicleId).orElseThrow(() -> new DataNotFoundException("user not found"));

        List<VehicleRatings> vehicleRatings = vehicleRatingsRepository.findAllByVehicle_Id(vehicleId);
        return VehicleRatingsMapper.buildListVehicleRatingResponse(vehicleRatings);
    }

    @Transactional
    @Override
    public String delete(Integer id) {
       VehicleRatings vehicleRatings = vehicleRatingsRepository.findById(id).orElseThrow(() -> new IllegalStateException("VehicleRatings not found"));
       vehicleRatingsRepository.delete(vehicleRatings);
       return "VehicleRating deleted";
    }
}
