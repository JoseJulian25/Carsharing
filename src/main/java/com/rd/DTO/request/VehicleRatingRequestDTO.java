package com.rd.DTO.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VehicleRatingRequestDTO {
    private Long rating;
    private String comment;
}
