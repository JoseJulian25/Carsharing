package com.rd.DTO;

import com.rd.enums.StatusReservation;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Builder
@Data
public class ReservationDTO {
    private Integer id;
    private VehicleDTO vehicle;
    private UserDTO user;
    private LocalDateTime reservationDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double cost;
    private StatusReservation statusReservation;
}
