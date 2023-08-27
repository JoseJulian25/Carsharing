package com.rd.mappers;

import com.rd.DTO.UserDTO;
import com.rd.entity.User;
import java.util.List;

public class UserMapper {

    public static UserDTO buildDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .dateBirth(user.getDateBirth())
                .password(user.getPassword())
                .address(user.getAddress())
                .telephone(user.getTelephone())
                .role(user.getRole())
                .build();
    }

    public static List<UserDTO> buildListDTO(List<User> users){
        return users.stream().map(UserMapper::buildDTO).toList();
    }
}
