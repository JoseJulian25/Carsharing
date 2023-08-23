package com.rd.DTO.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VehicleRatingRequestDTO {
    @NotNull(message = "Rating can't be null")
    @Max(value = 5, message = "Rating must be less than or equals to 5")
    private Long rating;
    private String comment;
}
