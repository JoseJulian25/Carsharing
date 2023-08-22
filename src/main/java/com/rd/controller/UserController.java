package com.rd.controller;

import com.rd.DTO.UserDTO;
import com.rd.enums.Role;
import com.rd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Secured({"USER", "ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @Secured("ADMIN")
    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @Secured("ADMIN")
    @GetMapping("/role")
    public ResponseEntity<List<UserDTO>> findUserByRole(@RequestParam Role role){
        return ResponseEntity.ok(userService.findAllByRole(role));
    }

    @Secured("ADMIN")
    @GetMapping("/country/{country}")
    public ResponseEntity<List<UserDTO>> findAllByCountry(@PathVariable String country){
        return new ResponseEntity<>(userService.findAllByAddressCountry(country), HttpStatus.FOUND);
    }

    @Secured("ADMIN")
    @GetMapping("/country/{country}/city/{city}")
    public ResponseEntity<List<UserDTO>> findAllByCountryAndCity(@PathVariable String country, @PathVariable String city){
        return new ResponseEntity<>(userService.findAllByCountryAndCity(country, city), HttpStatus.FOUND);
    }

    @Secured({"USER", "ADMIN"})
    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestParam Integer id,@RequestBody UserDTO user){
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @Secured("ADMIN")
    @DeleteMapping()
    public ResponseEntity<String> deleteUSer(@RequestParam Integer id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("User eliminated", HttpStatus.OK);
    }
}
