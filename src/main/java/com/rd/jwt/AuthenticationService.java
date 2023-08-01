package com.rd.jwt;

import com.rd.DTO.request.AuthenticationRequest;
import com.rd.DTO.response.AuthenticationResponse;
import com.rd.DTO.UserDTO;
import com.rd.email.ConfirmationUserEmailService;
import com.rd.entity.Address;
import com.rd.entity.User;
import com.rd.repository.AddressRepository;
import com.rd.repository.UserRepository;
import com.rd.token.Token;
import com.rd.token.TokenRepository;
import com.rd.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationUserEmailService emailService;

    @Transactional
    public AuthenticationResponse register(UserDTO userDTO){
        Address address = addressRepository.findByCountryCityStreetAndPostalCode(
                userDTO.getAddress().getCountry(),
                userDTO.getAddress().getCity(),
                userDTO.getAddress().getStreet(),
                userDTO.getAddress().getPostalCode()
        ).orElseGet(() -> addressRepository.save(userDTO.getAddress()));

        User user = createUser(userDTO, address);

        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        emailService.sendEmailConfirmationUSer(savedUser, jwtToken);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()
                ));

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Email doesn't exist: " + request.getEmail()));
        String jwtToken =jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Transactional
    protected void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoke(false)
                .expired(false)
                .build();
        tokenRepository.save(token);

    }

    @Transactional
    protected void revokeAllUserTokens(User user) {
        Token validUserToken = tokenRepository.findByUser_Id(user.getId()).orElseThrow(() -> new IllegalStateException("token not found"));
        tokenRepository.delete(validUserToken);
    }

    private User createUser(UserDTO userDTO, Address address) {
        return User.builder()
                .name(userDTO.getName())
                .lastname(userDTO.getLastname())
                .email(userDTO.getEmail())
                .passw(passwordEncoder.encode(userDTO.getPassword()))
                .dateBirth(userDTO.getDateBirth())
                .telephone(userDTO.getTelephone())
                .role(userDTO.getRole())
                .enabled(false)
                .address(address)
                .build();
    }

    public String confirmUser(String token) {
        Token tokenSaved = tokenRepository.findByToken(token).orElseThrow(() -> new IllegalStateException("token not found"));
        User user = userRepository.findByToken(tokenSaved);

        if(user.isEnabled()){
           throw new IllegalStateException("Email already confirmed");
        }

        userRepository.enableUser(user.getEmail());
        return "confirmed";
    }
}
