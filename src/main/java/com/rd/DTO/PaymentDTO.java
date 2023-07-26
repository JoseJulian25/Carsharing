package com.rd.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class PaymentDTO {
    private Integer id;
    private double amount;
    private Date date;
    private String paymentMethod;
    private ReservationDTO reservationDTO;
}
