package com.rd.mappers;

import com.rd.DTO.UserDTO;
import com.rd.entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserDTO buildDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .dateBirth(user.getDateBirth())
                .password(user.getPassword())
                .address(user.getAddress())
                .telephone(user.getTelephone())
                .build();
    }

    public static List<UserDTO> buildListDTO(List<User> users){
        List<UserDTO> reservationDTOS = new ArrayList<>();
        users.forEach(reservation -> reservationDTOS.add(buildDTO(reservation)));
        return reservationDTOS;
    }
}
