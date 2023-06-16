package com.rd.service;


import com.rd.entity.Rol;
import com.rd.entity.User;
import com.rd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {

    private final UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> findAllByRol(Rol rol) {
        return repository.findByRol(rol);
    }

    @Override
    public List<User> findAllByfindAllByAddressCountry(String country) {
      return repository.findAllByAddress_Country(country);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email doesn't exist: " + email));
    }


    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
         repository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        User userExist = repository.findById(user.getId()).orElseThrow(() ->
                new RuntimeException("Usuario doesn't exist with id: " + user.getId()));
        userExist.setName(user.getName());
        userExist.setLastname(user.getLastname());
        userExist.setDateBirth(user.getDateBirth());
        userExist.setEmail(user.getEmail());
        userExist.setPassw(user.getPassword());
        userExist.setAddress(user.getAddress());
        userExist.setTelephone(user.getTelephone());
        userExist.setRol(user.getRol());
        return repository.save(userExist);
    }
}
