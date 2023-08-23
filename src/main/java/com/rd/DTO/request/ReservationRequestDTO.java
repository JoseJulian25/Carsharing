package com.rd.DTO.request;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Builder
@Data
public class ReservationRequestDTO {
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}", message = "The Date's format must be 'yyyy-MM-ddTHH:mm:ss'")
    private LocalDateTime startDate;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}", message = "The Date's format must be 'yyyy-MM-ddTHH:mm:ss'")
    private LocalDateTime endDate;
}
