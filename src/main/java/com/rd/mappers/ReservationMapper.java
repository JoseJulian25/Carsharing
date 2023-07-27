package com.rd.mappers;

import com.rd.DTO.ReservationDTO;
import com.rd.entity.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {

    public static ReservationDTO buildDTO(Reservation reservation){
        return ReservationDTO.builder()
                .id(reservation.getId())
                .vehicle(VehicleMapper.buildDTO(reservation.getVehicle()))
                .user(UserMapper.buildDTO(reservation.getUser()))
                .reservationDate(reservation.getReservationDate())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .cost(reservation.getCost())
                .statusReservation(reservation.getStatusReservation())
                .build();
    }

    public static List<ReservationDTO> buildListDTO(List<Reservation> reservations){
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        reservations.forEach(reservation -> reservationDTOS.add(buildDTO(reservation)));
        return reservationDTOS;
    }

}
