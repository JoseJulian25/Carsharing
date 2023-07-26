package com.rd.service;

import com.rd.DTO.PaymentDTO;
import com.rd.entity.Payment;

import java.util.List;

public interface PaymentService {

    PaymentDTO findById(Integer id);
    List<PaymentDTO> findByUserId(Integer id);
    List<PaymentDTO> findAll();
    PaymentDTO savePayment(Payment payment, Integer userId, Integer reservationId);
    String deletePayment(Integer id);
}
