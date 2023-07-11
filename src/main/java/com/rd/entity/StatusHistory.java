package com.rd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "status_history")
@Data
public class StatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "vehicle_status_id", referencedColumnName = "id")
    private VehicleStatus vehicleStatus;
    private LocalDateTime updateDate;
    private boolean active;
}
