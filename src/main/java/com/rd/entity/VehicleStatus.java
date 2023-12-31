package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rd.entity.enums.StatusVehicle;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "vehicle_Status")
public class VehicleStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private StatusVehicle status;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Vehicle> vehicle;

    @OneToMany(mappedBy = "vehicleStatus")
    @JsonIgnore
    private List<StatusHistory> statusHistories;

}
