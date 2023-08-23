package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image_vehicle")
public class ImageVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "name can't be empty or null")
    private String name;
    @NotBlank(message = "type can't be empty or null")
    private String type;

    @NotNull(message = "imageData can't be null")
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Vehicle vehicle;
}
