package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "models")
@Data
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "model")
    @JsonIgnore
    private List<Vehicle> vehicle;

    @ManyToOne
    @JoinColumn(name = "make_id", referencedColumnName = "id")
    private Make make;
}
