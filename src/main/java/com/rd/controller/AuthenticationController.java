package com.rd.controller;

import com.rd.DTO.AuthenticationRequest;
import com.rd.DTO.AuthenticationResponse;
import com.rd.DTO.RegisterRequest;
import com.rd.entity.User;
import com.rd.jwt.AuthenticationService;
import com.rd.service.UserService;
import com.rd.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authService;
    private final UserServiceImpl userService;

    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<User>> findAllUSers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public String hola(){
        return "Bienvenido de vuelta";
    }
}
