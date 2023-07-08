package com.rd.utils;

import com.rd.DTO.ReservationDTO;
import com.rd.entity.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {

    public static ReservationDTO buildReservationDTO(Reservation reservation){
        return ReservationDTO.builder()
                .id(reservation.getId())
                .vehicle(VehicleMapper.buildVehicleDTO(reservation.getVehicle()))
                .user(UserMapper.buildUserDTO(reservation.getUser()))
                .reservationDate(reservation.getReservationDate())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .cost(reservation.getCost())
                .statusReservation(reservation.getStatusReservation())
                .build();
    }

    public static List<ReservationDTO> buildListReservationDTO(List<Reservation> reservations){
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        reservations.forEach(reservation -> reservationDTOS.add(buildReservationDTO(reservation)));
        return reservationDTOS;
    }
}
