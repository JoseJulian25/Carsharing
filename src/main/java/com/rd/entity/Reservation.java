package com.rd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rd.enums.StatusReservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private LocalDateTime reservationDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private StatusReservation statusReservation;
    private Double cost;
}
