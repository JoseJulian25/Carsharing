package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Builder
@Table(name = "address")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "country can't be null or empty")
    private String country;
    @NotBlank(message = "city can't be null or empty")
    private String city;
    @NotBlank(message = "street can't be null or empty")
    private String street;
    @NotBlank(message = "postalcode can't be null or empty")
    private String postalCode;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<User> users;
}