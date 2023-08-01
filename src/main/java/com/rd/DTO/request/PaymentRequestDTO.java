package com.rd.DTO.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PaymentRequestDTO {
    private String paymentMethod;

    @JsonCreator
    public PaymentRequestDTO(@JsonProperty("paymentMethod") String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
}
