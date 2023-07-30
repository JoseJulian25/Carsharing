package com.rd.service;

import com.rd.DTO.request.PaymentRequestDTO;
import com.rd.DTO.response.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {

    PaymentResponseDTO findById(Integer id);
    List<PaymentResponseDTO> findByUserId(Integer id);
    List<PaymentResponseDTO> findAll();
    PaymentResponseDTO savePayment(PaymentRequestDTO payment, Integer userId, Integer reservationId);
    String deletePayment(Integer id);
}
