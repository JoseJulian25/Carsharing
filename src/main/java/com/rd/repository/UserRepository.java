package com.rd.repository;

import com.rd.entity.Role;
import com.rd.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole( Role role);
    List<User> findAllByAddress_Country(String country);
    List<User> findAllByAddress_CountryAndAddress_City(String country, String city);
    Optional<User> findByEmail(String email);
}
