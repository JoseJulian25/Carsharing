package com.rd.service;

import com.rd.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment findById(Integer id);
    List<Payment> findByUserId(Integer id);
    List<Payment> findAll();
    Payment savePayment(Payment payment, Integer userId, Integer reservationId);
    String deletePayment(Integer id);
    Payment updatePayment(Payment payment);
}
