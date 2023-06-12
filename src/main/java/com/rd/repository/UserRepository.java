package com.rd.repository;

import com.rd.entity.Rol;
import com.rd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRol(Rol rol);
    List<User> findAllByAddress_Country(String country);
    User findByEmail(String email);
}
