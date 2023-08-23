package com.rd.DTO;

import com.rd.entity.Address;
import com.rd.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import java.util.Date;


@Data
@Builder
public class UserDTO {
    private Integer id;
    @NotBlank(message = "name can't be empty or null")
    private String name;
    @NotBlank(message = "lastname can't be empty or null")
    private String lastname;
    @NotNull(message = "dateBirth can't be null")
    private Date dateBirth;
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
