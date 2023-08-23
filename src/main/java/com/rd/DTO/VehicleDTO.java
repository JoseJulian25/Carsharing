package com.rd.DTO;

import com.rd.entity.enums.StatusVehicle;
import com.rd.entity.enums.ETypeVehicle;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VehicleDTO {
    private Integer id;
    @NotBlank(message = "serialNumber can't be empty or null")
    private String serialNumber;
    @NotBlank(message = "makeName can't be empty or null")
    private String makeName;
    @NotBlank(message = "modelName can't be empty or null")
    private String modelName;
    @NotNull(message = "type can't be null")
    private ETypeVehicle type;
    @DecimalMin(value = "50.0", message = "Price must be greater than 50")
    private double price;
    @NotBlank(message = "color can't be empty or null")
    private String color;
    @NotNull(message = "modelName can't be empty or null")
    private StatusVehicle status;
    private String additionalNotes;
}
