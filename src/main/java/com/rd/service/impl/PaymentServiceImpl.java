package com.rd.service.impl;

import com.rd.DTO.request.PaymentRequestDTO;
import com.rd.DTO.response.PaymentResponseDTO;
import com.rd.entity.*;
import com.rd.enums.EStatus;
import com.rd.enums.StatusReservation;
import com.rd.exception.DataNotFoundException;
import com.rd.repository.PaymentRepository;
import com.rd.repository.ReservationRepository;
import com.rd.repository.UserRepository;
import com.rd.repository.VehicleRepository;
import com.rd.service.PaymentService;
import com.rd.service.VehicleServiceHelper;
import com.rd.utils.ListValidation;
import com.rd.mappers.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final VehicleServiceHelper vehicleServiceHelper;
    private final VehicleRepository vehicleRepository;

    @Override
    public PaymentResponseDTO findById(Integer id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Payment not found"));
        return PaymentMapper.buildDTO(payment);
    }

    @Override
    public List<PaymentResponseDTO> findByUserId(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("user not found"));
        return PaymentMapper.buildListDTO(paymentRepository.findByUser(user));
    }

    @Override
    public List<PaymentResponseDTO> findAll() {
        List<Payment> payments = ListValidation.checkNonEmptyList(paymentRepository.findAll(), () -> "List empty");
        return PaymentMapper.buildListDTO(payments);
    }

    @Transactional
    @Override
    public PaymentResponseDTO savePayment(PaymentRequestDTO paymentDTO, Integer userId, Integer reservationId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user not found"));
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalStateException("reservation not found"));
        reservation.setStatusReservation(StatusReservation.ACTIVE);

        Payment payment = createPayment(paymentDTO, user, reservation);

        updateVehicleStatus(reservation.getVehicle());
        reservationRepository.save(reservation);
        return PaymentMapper.buildDTO(paymentRepository.save(payment));
    }

    @Transactional
    @Override
    public String deletePayment(Integer id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Payment not found"));
        paymentRepository.delete(payment);

        Vehicle vehicle = payment.getReservation().getVehicle();
        VehicleStatus vehicleStatus = vehicleServiceHelper.findOrCreateVehicleStatus(EStatus.AVAILABLE);
        vehicle.setStatus(vehicleStatus);
        vehicleRepository.save(vehicle);
        return "Payment deleted successfully";
    }

    @Transactional
    protected void updateVehicleStatus(Vehicle vehicle) {
        VehicleStatus vehicleStatus = vehicleServiceHelper.findOrCreateVehicleStatus(EStatus.RESERVED);
        vehicle.setStatus(vehicleStatus);
        vehicleRepository.save(vehicle);
        vehicleServiceHelper.createStatusHistory(vehicle);
        vehicleServiceHelper.deactivateLastStatus(vehicle);
    }

    private Payment createPayment(PaymentRequestDTO paymentRequestDTO, User user, Reservation reservation){
        return Payment.builder()
                .user(user)
                .reservation(reservation)
                .date(new Date())
                .amount(reservation.getCost() + 50)
                .paymentMethod(paymentRequestDTO.getPaymentMethod())
                .build();
    }

}
