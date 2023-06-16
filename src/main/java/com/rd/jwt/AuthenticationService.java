package com.rd.jwt;

import com.rd.DTO.AuthenticationRequest;
import com.rd.DTO.AuthenticationResponse;
import com.rd.DTO.RegisterRequest;
import com.rd.entity.Rol;
import com.rd.entity.User;
import com.rd.token.TokenRepository;
import com.rd.repository.UserRepository;
import com.rd.token.Token;
import com.rd.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        User user = User.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .passw(passwordEncoder.encode(request.getPassword()))
                .dateBirth(request.getDateBirth())
                .telephone(request.getTelephone())
                .rol(Rol.USER)
                .address(request.getAddress())
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

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Email doesn't exist"));
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
