package com.rd.repository;

import com.rd.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer > {

    Optional<Token> findByUser_Id(Integer userId);

    Optional<Token> findByToken(String token);
}
