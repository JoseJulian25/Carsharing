package com.rd.DTO;

import com.rd.DTO.response.ReservationResponseDTO;
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
    private ReservationResponseDTO reservationResponseDTO;
}
