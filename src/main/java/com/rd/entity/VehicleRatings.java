package com.rd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "vehicle_ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRatings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @ManyToOne()
    @JoinColumn(name = "user_id",referencedColumnName = "id" )
    private User user;
    private Long rating;
    private String comment;
}
