package com.rd.entity;

import com.rd.enums.ETypeVehicle;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "type_vehicles")
@Data
public class TypeVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ETypeVehicle type;

    @OneToOne(mappedBy = "type")
    private Vehicle vehicle;
}
