package com.rd.DTO.request;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Builder
@Data
public class ReservationRequestDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
