package com.rd.DTO;

import com.rd.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String lastname;
    private Date dateBirth;
    private String email;
    private String password;
    private Address address;
    private String telephone;
}
