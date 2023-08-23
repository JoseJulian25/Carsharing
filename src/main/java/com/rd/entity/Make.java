package com.rd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "makes", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "make", cascade = CascadeType.ALL)
    private Set<Model> modelId;
    private String name;

    @OneToMany(mappedBy = "make", cascade = CascadeType.ALL)
    private List<Vehicle> vehicle;
}
