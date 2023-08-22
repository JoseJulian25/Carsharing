package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private com.rd.entity.enums.TypeVehicle type;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private List<Vehicle> vehicle;
}
