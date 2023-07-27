package com.rd.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class PaymentResponseDTO {
    private Integer id;
    private double amount;
    private Date date;
    private String paymentMethod;
    private ReservationResponseDTO reservationResponseDTO;
}
