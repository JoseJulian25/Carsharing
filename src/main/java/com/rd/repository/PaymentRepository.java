package com.rd.repository;

import com.rd.entity.Payment;
import com.rd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Override
    Optional<Payment> findById(Integer integer);
    List<Payment> findByUser(User user);
}
