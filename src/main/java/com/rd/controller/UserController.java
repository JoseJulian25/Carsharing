package com.rd.controller;

import com.rd.DTO.UserDTO;
import com.rd.entity.enums.Role;
import com.rd.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "User Controller", description = "Endpoints to manage Users")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Find user by iD", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Find all users", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "Find users by role", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @GetMapping("/role")
    public ResponseEntity<List<UserDTO>> findUserByRole(@RequestParam Role role){
        return ResponseEntity.ok(userService.findAllByRole(role));
    }

    @Operation(summary = "Find users by country", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @GetMapping("/country/{country}")
    public ResponseEntity<List<UserDTO>> findAllByCountry(@PathVariable String country){
        return ResponseEntity.ok(userService.findAllByAddressCountry(country));
    }

    @Operation(summary = "Find users by country and City", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @GetMapping("/country/{country}/city/{city}")
    public ResponseEntity<List<UserDTO>> findAllByCountryAndCity(@PathVariable String country, @PathVariable String city){
        return ResponseEntity.ok(userService.findAllByCountryAndCity(country, city));
    }

    @Operation(summary = "Update user", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestParam Integer id,@RequestBody UserDTO user){
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @Operation(summary = "Delete User by ID", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @DeleteMapping()
    public ResponseEntity<String> deleteUser(@RequestParam Integer id){
        userService.deleteUserById(id);
        return new ResponseEntity<>("User eliminated", HttpStatus.OK);
    }
}
