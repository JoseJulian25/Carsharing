package com.rd.DTO.response;

import com.rd.DTO.request.MakeDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ModelResponseDTO {
    private Integer id;
    private String name;
    private MakeDTO make;
}
