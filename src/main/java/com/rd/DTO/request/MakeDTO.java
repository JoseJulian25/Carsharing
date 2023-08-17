package com.rd.DTO.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MakeDTO {
    private Integer id;
    private String name;
}
