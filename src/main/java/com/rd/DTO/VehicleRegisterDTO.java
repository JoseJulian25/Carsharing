package com.rd.DTO;

import com.rd.enums.EStatus;
import com.rd.enums.ETypeVehicle;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VehicleRegisterDTO {
    private Integer id;
    private String serialNumber;
    private String makeName;
    private String modelName;
    private ETypeVehicle type;
    private double price;
    private String color;
    private EStatus status;
    private String additionalNotes;
}
