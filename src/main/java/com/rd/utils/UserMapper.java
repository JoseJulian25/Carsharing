package com.rd.utils;

import com.rd.DTO.ReservationDTO;
import com.rd.DTO.UserDTO;
import com.rd.entity.Reservation;
import com.rd.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserDTO buildUserDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .dateBirth(user.getDateBirth())
                .password(user.getPassword())
                .address(user.getAddress())
                .telephone(user.getTelephone())
                .reservations(ReservationMapper.buildListReservationDTOWithoutUser(user.getReservations()))
                .build();
    }

    public static List<UserDTO> buildListUserDTO(List<User> users){
        List<UserDTO> reservationDTOS = new ArrayList<>();
        users.forEach(reservation -> reservationDTOS.add(buildUserDTO(reservation)));
        return reservationDTOS;
    }
}
