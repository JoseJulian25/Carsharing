package com.rd.mappers;

import com.rd.DTO.response.PaymentResponseDTO;
import com.rd.entity.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentMapper {

    public static PaymentResponseDTO buildDTO(Payment payment){
        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .date(payment.getDate())
                .reservationResponseDTO(ReservationMapper.buildDTO(payment.getReservation()))
                .build();
    }

    public static List<PaymentResponseDTO> buildListDTO(List<Payment> payments){
        List<PaymentResponseDTO> paymentResponseDTOS = new ArrayList<>();
        payments.forEach(payment -> paymentResponseDTOS.add(buildDTO(payment)));
        return paymentResponseDTOS;
    }
}
