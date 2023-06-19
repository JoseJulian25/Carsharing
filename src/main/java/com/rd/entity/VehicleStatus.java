package com.rd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "vehicle_Status")
public class VehicleStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    private Date updateDate;

    @OneToOne(mappedBy = "status")
    private Vehicle vehicle;
}
