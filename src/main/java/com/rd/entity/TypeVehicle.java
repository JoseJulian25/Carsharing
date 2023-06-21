package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rd.enums.ETypeVehicle;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "type_vehicles")
@Data
public class TypeVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ETypeVehicle type;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private List<Vehicle> vehicle;
}
