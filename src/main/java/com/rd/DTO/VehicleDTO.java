package com.rd.DTO;

import com.rd.entity.enums.StatusVehicle;
import com.rd.entity.enums.TypeVehicle;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VehicleDTO {
    private Integer id;
    private String serialNumber;
    private String makeName;
    private String modelName;
    private TypeVehicle type;
    private double price;
    private String color;
    private StatusVehicle status;
    private String additionalNotes;
}
