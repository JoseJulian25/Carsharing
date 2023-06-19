package com.rd.service;


import com.rd.DTO.UserRegisterDTO;
import com.rd.entity.Address;
import com.rd.enums.Role;
import com.rd.entity.User;
import com.rd.exception.DataNotFoundException;
import com.rd.repository.AddressRepository;
import com.rd.repository.TokenRepository;
import com.rd.repository.UserRepository;
import com.rd.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final TokenRepository tokenRepository;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllByAddressCountry(String country) {
        List<User> usersByAddressCountry = userRepository.findAllByAddress_Country(country);

        if(usersByAddressCountry.isEmpty())
            throw new DataNotFoundException("Country not found: " + country);

        return usersByAddressCountry;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllByCountryAndCity(String country, String city) {
        List<User> UsersByCountryAndCity = userRepository.findAllByAddress_CountryAndAddress_City(country, city);

        if(UsersByCountryAndCity.isEmpty()){
            throw new DataNotFoundException("City and Country not found: " + country + "," + city);
        }
        return UsersByCountryAndCity;
    }

    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new DataNotFoundException("email doesn't exist: " + email));
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
    public User updateUser(UserRegisterDTO userRegisterDTO, Integer userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        existingUser.setName(userRegisterDTO.getName());
        existingUser.setLastname(userRegisterDTO.getLastname());
        existingUser.setDateBirth(userRegisterDTO.getDateBirth());
        existingUser.setEmail(userRegisterDTO.getEmail());
        existingUser.setTelephone(userRegisterDTO.getTelephone());

        Address existingAddress = existingUser.getAddress();
        Address updatedAddress = updateOrCreateAddress(userRegisterDTO.getAddress(), existingAddress);
        existingUser.setAddress(updatedAddress);

        return userRepository.save(existingUser);
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
