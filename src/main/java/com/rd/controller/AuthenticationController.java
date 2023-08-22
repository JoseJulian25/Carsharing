package com.rd.controller;

import com.rd.DTO.request.AuthenticationRequest;
import com.rd.DTO.response.AuthenticationResponse;
import com.rd.DTO.UserDTO;
import com.rd.jwt.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AuthenticationUser Controller", description = "Endpoints to login and register users")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @Operation(summary = "User register")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody UserDTO request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @Operation(summary = "User login")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @Operation(summary = "User confirm email", description = "This endpoint is used by user when they check their emails and confirm the registration")
    @GetMapping("/registration/confirm")
    public String confirmUser(@RequestParam("token") String token){
        return authService.confirmUser(token);
    }
}