package com.rd.DTO;

import com.rd.entity.Address;
import com.rd.entity.enums.Role;
import com.rd.validation.ValidAge;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;


@Data
@Builder
public class UserDTO {
    private Integer id;
    @NotBlank(message = "name can't be empty or null")
    private String name;
    @NotBlank(message = "lastname can't be empty or null")
    private String lastname;
    @ValidAge
    private LocalDate dateBirth;
    @Email(message = "Email invalid")
    private String email;
    @NotBlank(message = "password can't be empty or null")
    private String password;
    @NotNull(message = "Address can't be null")
    private Address address;
    @Pattern(regexp = "^\\+(?:\\d{1,2}\\s)?(?:\\(\\d{3}\\)|\\d{3})[.-]\\d{3}[.-]\\d{4}$", message = "Telephone invalid")
    private String telephone;
    @NotNull(message = "role can't be null")
    private Role role;
}
