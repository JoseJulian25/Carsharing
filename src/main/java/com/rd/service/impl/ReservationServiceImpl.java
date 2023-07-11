package com.rd.service.impl;

import com.rd.DTO.ReservationDTO;
import com.rd.entity.Reservation;
import com.rd.entity.User;
import com.rd.entity.Vehicle;
import com.rd.entity.VehicleStatus;
import com.rd.enums.EStatus;
import com.rd.enums.StatusReservation;
import com.rd.exception.DataNotFoundException;
import com.rd.repository.ReservationRepository;
import com.rd.repository.UserRepository;
import com.rd.repository.VehicleRepository;
import com.rd.service.ReservationService;
import com.rd.service.VehicleServiceHelper;
import com.rd.utils.ListValidation;
import com.rd.utils.ReservationMapper;
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
    private final VehicleServiceHelper vehicleServiceHelper;

    @Override
    public ReservationDTO findById(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found: " + id));
        return ReservationMapper.buildReservationDTO(reservation);
    }

    @Override
    public List<ReservationDTO> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        ListValidation.checkNonEmptyList(reservations, () -> "Reservations not found");
        return ReservationMapper.buildListReservationDTO(reservations);
    }

    @Transactional
    @Override
    public ReservationDTO saveReservation(Reservation reservation, Integer userId, Integer vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new DataNotFoundException("vehicle not found with id: " + vehicleId));
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("user not found with id: "+ vehicleId));

        validateVehicleAvailability(vehicle);

        long days = calculateDays(reservation.getStartDate(), reservation.getEndDate());
        double cost = calculateCost(days, vehicle.getPrice());

        Reservation savedReservation = createReservation(reservation, vehicle, user, cost);
        updateVehicleStatus(vehicle);

        return ReservationMapper.buildReservationDTO(reservationRepository.save(savedReservation));
    }

    @Override
    public List<ReservationDTO> findActiveReservations() {
        return ReservationMapper.buildListReservationDTO(reservationRepository.findActiveReservations());
    }

    @Transactional
    @Override
    public void deleteReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new DataNotFoundException("not found with id: " + id));
        Vehicle vehicle = reservation.getVehicle();
        vehicle.getStatus().setStatus(EStatus.AVAILABLE);
        vehicleRepository.save(vehicle);
        reservationRepository.delete(reservation);
    }

    public void updateCompletedReservations(Integer reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow( () -> new DataNotFoundException("No"));
        reservation.setStatusReservation(StatusReservation.COMPLETED);
        reservationRepository.save(reservation);
    }

    private Reservation createReservation(Reservation reservation, Vehicle vehicle, User user, double cost){
        return Reservation.builder()
                .vehicle(vehicle)
                .user(user)
                .reservationDate(LocalDateTime.now())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .statusReservation(StatusReservation.ACTIVE)
                .cost(cost)
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
        if(vehicle.getStatus().getStatus() == EStatus.RESERVED){
            throw new RuntimeException("Vehicle is reserved");
        }
    }

    private void updateVehicleStatus(Vehicle vehicle) {
        VehicleStatus vehicleStatus = vehicleServiceHelper.findOrCreateVehicleStatus(EStatus.RESERVED);
        vehicle.setStatus(vehicleStatus);
        vehicleRepository.save(vehicle);
        vehicleServiceHelper.createStatusHistory(vehicle);
        vehicleServiceHelper.deactiveLastStatus(vehicle);
    }
}
