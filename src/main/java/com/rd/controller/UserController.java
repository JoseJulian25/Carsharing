package com.rd.controller;

import com.rd.DTO.UserRegisterDTO;
import com.rd.entity.Role;
import com.rd.entity.User;
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
    private final UserServiceImpl userService;

    @GetMapping("/email/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/role")
    public ResponseEntity<List<User>> findUserByRole(@RequestParam Role role){
        return ResponseEntity.ok(userService.findAllByRole(role));
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<User>> findAllByCountry(@PathVariable String country){
        return new ResponseEntity<>(userService.findAllByAddressCountry(country), HttpStatus.FOUND);
    }

    @GetMapping("/country/{country}/city/{city}")
    public ResponseEntity<List<User>> findAllByCountryAndCity(@PathVariable String country, @PathVariable String city){
        return new ResponseEntity<>(userService.findAllByCountryAndCity(country, city), HttpStatus.FOUND);
    }

    @PutMapping("/edit")
    public ResponseEntity<User> updateUser(@RequestParam Integer id,@RequestBody UserRegisterDTO user){
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/edit")
    public ResponseEntity<String> deleteUSer(@RequestParam Integer id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("User eliminated", HttpStatus.OK);
    }
}
