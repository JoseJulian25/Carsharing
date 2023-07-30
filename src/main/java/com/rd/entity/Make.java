package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "makes", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "make", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Model> modelId;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "make", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vehicle> vehicle;
}
