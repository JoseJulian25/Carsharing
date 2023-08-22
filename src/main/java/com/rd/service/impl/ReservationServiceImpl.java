package com.rd.service.impl;

import com.rd.DTO.request.ReservationRequestDTO;
import com.rd.DTO.response.ReservationResponseDTO;
import com.rd.entity.Reservation;
import com.rd.entity.User;
import com.rd.entity.Vehicle;
import com.rd.entity.enums.StatusVehicle;
import com.rd.entity.enums.StatusReservation;
import com.rd.exception.DataNotFoundException;
import com.rd.repository.ReservationRepository;
import com.rd.repository.UserRepository;
import com.rd.repository.VehicleRepository;
import com.rd.service.ReservationService;
import com.rd.utils.ListValidation;
import com.rd.mappers.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Override
    public ReservationResponseDTO findById(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found: " + id));
        return ReservationMapper.buildDTO(reservation);
    }

    @Override
    public List<ReservationResponseDTO> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        ListValidation.checkNonEmptyList(reservations, () -> "Reservations not found");
        return ReservationMapper.buildListDTO(reservations);
    }

    @Transactional
    @Override
    public ReservationResponseDTO saveReservation(ReservationRequestDTO reservation, Integer userId, Integer vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new DataNotFoundException("vehicle not found with id: " + vehicleId));
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("user not found with id: "+ vehicleId));

        validateVehicleAvailability(vehicle);

        long days = calculateDays(reservation.getStartDate(), reservation.getEndDate());
        double cost = calculateCost(days, vehicle.getPrice());

        Reservation savedReservation = createReservation(reservation, vehicle, user, cost);
        vehicleRepository.save(vehicle);

        return ReservationMapper.buildDTO(reservationRepository.save(savedReservation));
    }

    @Override
    public List<ReservationResponseDTO> findActiveReservations() {
        return ReservationMapper.buildListDTO(reservationRepository.findActiveReservations());
    }

    @Override
    public List<ReservationResponseDTO> findByUserId(Integer id) {
       userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
       return ReservationMapper.buildListDTO(reservationRepository.findByUser_Id(id));
    }

    @Transactional
    @Override
    public void deleteReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new DataNotFoundException("not found with id: " + id));
        reservationRepository.delete(reservation);
    }

    @Override
    public void updateCompletedReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow( () -> new DataNotFoundException("Reservation not found"));
        reservation.setStatusReservation(StatusReservation.COMPLETED);
        reservationRepository.save(reservation);
    }

    @Override
    public void setReservationEmailSent(Integer id) {
        try{
            reservationRepository.setReservationEmailSent(id);
        }catch (Exception ex){
            throw new IllegalArgumentException("Reservation not found");
        }
    }

    private Reservation createReservation(ReservationRequestDTO reservation, Vehicle vehicle, User user, double cost){
        return Reservation.builder()
                .vehicle(vehicle)
                .user(user)
                .reservationDate(LocalDateTime.now())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .statusReservation(StatusReservation.PENDING)
                .cost(cost)
                .emailSent(false)
                .build();
    }

    private long calculateDays(LocalDateTime startDay, LocalDateTime endDay){
        return Duration.between(startDay, endDay).toDays();
    }

    private double calculateCost(long days, double priceVehicle){
        double cost = priceVehicle;
        while(days > 0){
            cost += 1000;
            days--;
        }
        return cost;
    }

    private void validateVehicleAvailability(Vehicle vehicle){
        if(vehicle.getStatus().getStatus() == StatusVehicle.RESERVED){
            throw new IllegalStateException("Vehicle is reserved");
        }
    }
}
