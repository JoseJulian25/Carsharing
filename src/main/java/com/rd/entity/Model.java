package com.rd.entity;

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

    @OneToOne(mappedBy = "model")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "make_id", referencedColumnName = "id")
    private Make make;
}
