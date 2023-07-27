package com.rd.mappers;

import com.rd.DTO.VehicleRatingRequestDTO;
import com.rd.DTO.VehicleRatingResponseDTO;
import com.rd.entity.User;
import com.rd.entity.Vehicle;
import com.rd.entity.VehicleRatings;

import java.util.ArrayList;
import java.util.List;

public class VehicleRatingsMapper {

    public static VehicleRatings buildObject(VehicleRatingRequestDTO vehicleRatingRequestDTO, User user, Vehicle vehicle){
        return VehicleRatings.builder()
                .vehicle(vehicle)
                .user(user)
                .rating(vehicleRatingRequestDTO.getRating())
                .comment(vehicleRatingRequestDTO.getComment())
                .build();
    }

    public static VehicleRatingResponseDTO buildResponseDTO(VehicleRatings vehicleRatings){
        return VehicleRatingResponseDTO.builder()
                .id(vehicleRatings.getId())
                .vehicle(VehicleMapper.buildDTO(vehicleRatings.getVehicle()))
                .user(UserMapper.buildDTO(vehicleRatings.getUser()))
                .rating(vehicleRatings.getRating())
                .comment(vehicleRatings.getComment())
                .build();
    }

    public static List<VehicleRatingResponseDTO> buildListVehicleRatingResponse(List<VehicleRatings> vehicleRatings){
        List<VehicleRatingResponseDTO> vehicleRatingResponseDTOS = new ArrayList<>();
        vehicleRatings.forEach(vehicleDTO -> vehicleRatingResponseDTOS.add(buildResponseDTO(vehicleDTO)));
        return vehicleRatingResponseDTOS;
    }
}
