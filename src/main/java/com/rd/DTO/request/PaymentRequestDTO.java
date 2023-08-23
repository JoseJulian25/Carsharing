package com.rd.DTO.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PaymentRequestDTO {
    @NotBlank(message = "paymentMethod can't be empty or null")
    private String paymentMethod;

    @JsonCreator
    public PaymentRequestDTO(@JsonProperty("paymentMethod") String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
}
