package com.rd.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MakeDTO {
    private Integer id;
    @NotBlank(message = "name can't be empty or null")
    private String name;
}
