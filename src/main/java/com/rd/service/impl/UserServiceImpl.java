package com.rd.service.impl;


import com.rd.DTO.UserDTO;
import com.rd.entity.Address;
import com.rd.enums.Role;
import com.rd.entity.User;
import com.rd.exception.DataNotFoundException;
import com.rd.repository.AddressRepository;
import com.rd.repository.TokenRepository;
import com.rd.repository.UserRepository;
import com.rd.service.UserService;
import com.rd.token.Token;
import com.rd.utils.UserMapper;
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
        return UserMapper.buildListUserDTO(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> findAllByRole(Role role) {
        return UserMapper.buildListUserDTO(userRepository.findByRole(role));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> findAllByAddressCountry(String country) {
        List<User> usersByAddressCountry = userRepository.findAllByAddress_Country(country);

        if(usersByAddressCountry.isEmpty())
            throw new DataNotFoundException("Country not found: " + country);

        return UserMapper.buildListUserDTO(usersByAddressCountry);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> findAllByCountryAndCity(String country, String city) {
        List<User> UsersByCountryAndCity = userRepository.findAllByAddress_CountryAndAddress_City(country, city);

        if(UsersByCountryAndCity.isEmpty()){
            throw new DataNotFoundException("City and Country not found: " + country + "," + city);
        }
        return UserMapper.buildListUserDTO(UsersByCountryAndCity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new DataNotFoundException("email doesn't exist: " + email));
        return UserMapper.buildUserDTO(user);
    }

    @Override
    public void deleteUserById(Integer id) {
            User existingUser = userRepository.findById(id).orElseThrow( () ->
                    new DataNotFoundException("Usuario doesn't exist with id:" + id));

        List<Token> userTokens = existingUser.getToken();
        if (userTokens != null && !userTokens.isEmpty()) {
            tokenRepository.deleteAll(userTokens);
        }
            userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        existingUser.setName(userDTO.getName());
        existingUser.setLastname(userDTO.getLastname());
        existingUser.setDateBirth(userDTO.getDateBirth());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setTelephone(userDTO.getTelephone());

        Address existingAddress = existingUser.getAddress();
        Address updatedAddress = updateOrCreateAddress(userDTO.getAddress(), existingAddress);
        existingUser.setAddress(updatedAddress);

        return UserMapper.buildUserDTO(userRepository.save(existingUser));
    }
    private Address updateOrCreateAddress(Address newAddress, Address existingAddress) {
        if (existingAddress != null) {
            existingAddress.setCountry(newAddress.getCountry());
            existingAddress.setCity(newAddress.getCity());
            existingAddress.setStreet(newAddress.getStreet());
            existingAddress.setPostalCode(newAddress.getPostalCode());
            return existingAddress;
        } else {
            return addressRepository.save(newAddress);
        }
    }
}
