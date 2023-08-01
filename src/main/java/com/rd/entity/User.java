package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rd.enums.Role;
import com.rd.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@Table(name = "_users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateBirth;

    @Email
    private String email;
    private Boolean enabled;


    @Column(name = "password")
    private String passw;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$")
    private String telephone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private Token token;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<VehicleRatings> vehicleRatings;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Payment> payments;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return passw;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
