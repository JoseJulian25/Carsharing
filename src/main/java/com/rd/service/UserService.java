package com.rd.service;


import com.rd.DTO.UserDTO;
import com.rd.enums.Role;
import com.rd.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    List<User> findAllByRole(Role role);
    List<User> findAllByAddressCountry(String country);
    List<User> findAllByCountryAndCity(String country, String city);
    User findByEmail(String email);
    void deleteUserById(Integer id);
    User updateUser(UserDTO user, Integer id);
}
