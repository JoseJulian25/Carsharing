package com.rd.service;


import com.rd.entity.Rol;
import com.rd.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    List<User> findAllByRol(Rol rol);
    List<User> findAllByfindAllByAddressCountry(String country);
    User findByEmail(String email);
    User saveUser(User user);
    void deleteUserById(Integer id);
    User updateUser(User user);

}
