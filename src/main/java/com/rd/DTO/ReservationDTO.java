package com.rd.DTO;

import com.rd.enums.StatusReservation;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
public class ReservationDTO {
    private Integer id;
    private VehicleDTO vehicle;
    private UserDTO user;
    private LocalDateTime reservationDate;
    private Date startDate;
    private Date endDate;
    private double cost;
    private StatusReservation statusReservation;
}
