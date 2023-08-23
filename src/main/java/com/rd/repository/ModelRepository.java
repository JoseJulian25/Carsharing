package com.rd.repository;

import com.rd.entity.Make;
import com.rd.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    Optional<Model> findByNameAndMake(String name, Make make);
    List<Model> findByMake_Id(Integer id);
}
