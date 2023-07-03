package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
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

    @ManyToOne()
    @JoinColumn(name = "make_id", referencedColumnName = "id")
    private Make make;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "type_vehicle_id", referencedColumnName = "id")
    private TypeVehicle type;

    private String color;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "vehicle_status_id", referencedColumnName = "id")
    private VehicleStatus status;
    private String additionalNotes;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StatusHistory> statusHistory;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Reservation reservation;
}
