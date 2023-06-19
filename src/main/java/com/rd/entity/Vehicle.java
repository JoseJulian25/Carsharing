package com.rd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "vehicles")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serialNumber;

    @OneToOne
    @JoinColumn(name = "make_id", referencedColumnName = "id")
    private Make make;

    @OneToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;

    @OneToOne
    @JoinColumn(name = "type_vehicle_id", referencedColumnName = "id")
    private TypeVehicle type;

    private String color;
    private Double price;

    @OneToOne
    @JoinColumn(name = "vehicle_status_id", referencedColumnName = "id")
    private VehicleStatus status;

    private String additionalNotes;
}
