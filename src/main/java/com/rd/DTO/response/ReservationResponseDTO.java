package com.rd.DTO.response;

import com.rd.DTO.UserDTO;
import com.rd.DTO.VehicleDTO;
import com.rd.enums.StatusReservation;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Builder
@Data
public class ReservationResponseDTO {
    private Integer id;
    private VehicleDTO vehicle;
    private UserDTO user;
    private LocalDateTime reservationDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double cost;
    private StatusReservation statusReservation;
}
