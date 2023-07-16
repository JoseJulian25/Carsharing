package com.rd.DTO;

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
