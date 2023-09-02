package com.rd.service.impl;


import com.rd.DTO.UserDTO;
import com.rd.entity.Address;
import com.rd.entity.enums.Role;
import com.rd.entity.User;
import com.rd.exception.DataNotFoundException;
import com.rd.repository.AddressRepository;
import com.rd.repository.UserRepository;
import com.rd.service.UserService;
import com.rd.entity.Token;
import com.rd.mappers.UserMapper;
import com.rd.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final TokenRepository tokenRepository;

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> findAll() {
        return UserMapper.buildListDTO(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> findAllByRole(Role role) {
        return UserMapper.buildListDTO(userRepository.findByRole(role));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> findAllByAddressCountry(String country) {
        List<User> usersByAddressCountry = userRepository.findAllByAddress_Country(country);

        if(usersByAddressCountry.isEmpty())
            throw new DataNotFoundException("Country not found: " + country);

        return UserMapper.buildListDTO(usersByAddressCountry);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> findAllByCountryAndCity(String country, String city) {
        List<User> UsersByCountryAndCity = userRepository.findAllByAddress_CountryAndAddress_City(country, city);

        if(UsersByCountryAndCity.isEmpty()){
            throw new DataNotFoundException("City and Country not found: " + country + "," + city);
        }
        return UserMapper.buildListDTO(UsersByCountryAndCity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new DataNotFoundException("email doesn't exist: " + email));
        return UserMapper.buildDTO(user);
    }

    @Override
    public void deleteUserById(Integer id) {
            User existingUser = userRepository.findById(id).orElseThrow( () ->
                    new IllegalStateException("Usuario doesn't exist with id:" + id));

        Token userToken = existingUser.getToken();
        if (userToken != null) {
            tokenRepository.delete(userToken);
        }
            userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        existingUser.setName(userDTO.getName());
        existingUser.setLastname(userDTO.getLastname());
        existingUser.setDateBirth(userDTO.getDateBirth());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setTelephone(userDTO.getTelephone());

        Address address = validateAddress(userDTO.getAddress());
        existingUser.setAddress(address);

        return UserMapper.buildDTO(userRepository.save(existingUser));
    }

    @Override
    public UserDTO findById(Integer id) {
       User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
       return UserMapper.buildDTO(user);
    }

    @Transactional
    protected Address validateAddress(Address newAddress) {
        return addressRepository.findByCountryCityStreetAndPostalCode(newAddress.getCountry(),
                newAddress.getCity(),
                newAddress.getStreet(),
                newAddress.getPostalCode()).orElseGet(() -> addressRepository.save(newAddress));
    }
}
