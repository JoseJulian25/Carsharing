package com.rd.utils;

import com.rd.DTO.PaymentDTO;
import com.rd.entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentMapper {

    public static PaymentDTO buildDTO(Payment payment){
        return PaymentDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .date(payment.getDate())
                .reservationDTO(ReservationMapper.buildReservationDTO(payment.getReservation()))
                .build();
    }

    public static List<PaymentDTO> buildListDTO(List<Payment> payments){
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        payments.forEach(payment -> paymentDTOS.add(buildDTO(payment)));
        return paymentDTOS;
    }
}
