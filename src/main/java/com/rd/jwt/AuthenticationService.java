package com.rd.jwt;

import com.rd.DTO.request.AuthenticationRequest;
import com.rd.DTO.response.AuthenticationResponse;
import com.rd.DTO.UserDTO;
import com.rd.entity.Address;
import com.rd.enums.Role;
import com.rd.entity.User;
import com.rd.repository.AddressRepository;
import com.rd.repository.TokenRepository;
import com.rd.repository.UserRepository;
import com.rd.token.Token;
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

    @Transactional
    public AuthenticationResponse register(UserDTO request){
        Address address = addressRepository.findByCountryCityStreetAndPostalCode(
                request.getAddress().getCountry(),
                request.getAddress().getCity(),
                request.getAddress().getStreet(),
                request.getAddress().getPostalCode()
        ).orElseGet(() -> addressRepository.save(request.getAddress()));

        User user = User.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .passw(passwordEncoder.encode(request.getPassword()))
                .dateBirth(request.getDateBirth())
                .telephone(request.getTelephone())
                .role(Role.USER)
                .address(address)
                .build();

        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);

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

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoke(false)
                .expired(false)
                .build();
        tokenRepository.save(token);

    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserToken = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserToken.isEmpty()){return;}
        validUserToken.forEach(token -> {
            token.setExpired(true);
            token.setRevoke(true);
        });
        tokenRepository.saveAll(validUserToken);
    }
}
