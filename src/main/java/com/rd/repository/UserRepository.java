package com.rd.repository;

import com.rd.entity.Rol;
import com.rd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRol(Rol rol);
    List<User> findAllByAddress_Country(String country);
    Optional<User> findByEmail(String email);
}
