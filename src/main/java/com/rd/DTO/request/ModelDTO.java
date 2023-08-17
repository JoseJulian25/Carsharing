package com.rd.DTO.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ModelDTO {
    private Integer id;
    private String name;
}
