package com.rd.DTO;

import com.rd.entity.Address;
import com.rd.enums.Role;
import lombok.Builder;
import lombok.Data;
import java.util.Date;


@Data
@Builder
public class UserDTO {
    private Integer id;
    private String name;
    private String lastname;
    private Date dateBirth;
    private String email;
    private String password;
    private Address address;
    private String telephone;
    private Role role;
}
