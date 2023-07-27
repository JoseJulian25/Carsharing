package com.rd.DTO.response;

import com.rd.DTO.UserDTO;
import com.rd.DTO.VehicleDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VehicleRatingResponseDTO {
    private Integer id;
    private VehicleDTO vehicle;
    private UserDTO user;
    private Long rating;
    private String comment;
}
