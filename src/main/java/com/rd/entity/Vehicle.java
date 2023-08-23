package com.rd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@Table(name = "vehicles",  uniqueConstraints = {@UniqueConstraint(columnNames = "serialNumber")})
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
    @JoinColumn(name = "make_id", referencedColumnName = "id", nullable = false)
    private Make make;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id", nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "type_vehicle_id", referencedColumnName = "id", nullable = false)
    private TypeVehicle type;

    private String color;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "vehicle_status_id", referencedColumnName = "id", nullable = false)
    private VehicleStatus status;
    private String additionalNotes;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<StatusHistory> statusHistory;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "vehicle")
    private List<VehicleRatings> vehicleRatings;

    @OneToMany(mappedBy = "vehicle")
    private List<ImageVehicle> imageVehicles;

    public Vehicle(Make make, Model model, VehicleStatus vehicleStatus, TypeVehicle typeVehicle) {
        this.make = make;
        this.model = model;
        this.status = vehicleStatus;
        this.type = typeVehicle;
    }
}
