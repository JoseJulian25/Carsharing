package com.rd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name = "makes", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "make")
    private Set<Model> modelId;

    @NotNull
    private String name;

    @OneToOne(mappedBy = "make")
    private Vehicle vehicle;
}
