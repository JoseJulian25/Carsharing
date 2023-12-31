package com.rd.service;


import com.rd.DTO.UserDTO;
import com.rd.entity.enums.Role;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    List<UserDTO> findAllByRole(Role role);
    List<UserDTO> findAllByAddressCountry(String country);
    List<UserDTO> findAllByCountryAndCity(String country, String city);
    UserDTO findByEmail(String email);
    void deleteUserById(Integer id);
    UserDTO updateUser(UserDTO user, Integer id);
    UserDTO findById(Integer id);
}
