package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rd.enums.EStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "vehicle_Status")
public class VehicleStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Vehicle> vehicle;

}
