package com.rd.utils;

import com.rd.DTO.UserDTO;
import com.rd.entity.User;

import java.util.List;

public class UserMapper {

    public static UserDTO buildUserDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .dateBirth(user.getDateBirth())
                .password(user.getPassword())
                .address(user.getAddress())
                .telephone(user.getTelephone())
                .build();
    }
}
