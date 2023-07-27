package com.rd.DTO.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentRequestDTO {
    private String paymentMethod;
}
