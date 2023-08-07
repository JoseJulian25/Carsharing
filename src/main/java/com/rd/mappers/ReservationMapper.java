package com.rd.mappers;

import com.rd.DTO.response.ReservationResponseDTO;
import com.rd.entity.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {

    public static ReservationResponseDTO buildDTO(Reservation reservation){
        return ReservationResponseDTO.builder()
                .id(reservation.getId())
                .vehicle(VehicleMapper.buildDTO(reservation.getVehicle()))
                .user(UserMapper.buildDTO(reservation.getUser()))
                .reservationDate(reservation.getReservationDate())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .cost(reservation.getCost())
                .statusReservation(reservation.getStatusReservation())
                .emailSent(reservation.isEmailSent())
                .build();
    }

    public static List<ReservationResponseDTO> buildListDTO(List<Reservation> reservations){
        List<ReservationResponseDTO> reservationResponseDTOS = new ArrayList<>();
        reservations.forEach(reservation -> reservationResponseDTOS.add(buildDTO(reservation)));
        return reservationResponseDTOS;
    }

}
