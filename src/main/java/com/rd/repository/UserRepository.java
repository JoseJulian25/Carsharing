package com.rd.repository;

import com.rd.enums.Role;
import com.rd.entity.User;
import com.rd.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole( Role role);
    List<User> findAllByAddress_Country(String country);
    List<User> findAllByAddress_CountryAndAddress_City(String country, String city);
    Optional<User> findByEmail(String email);
    User findByToken(Token token);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);

}
