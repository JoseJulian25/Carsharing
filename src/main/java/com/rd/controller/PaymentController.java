package com.rd.controller;

import com.rd.DTO.request.PaymentRequestDTO;
import com.rd.DTO.response.PaymentResponseDTO;
import com.rd.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Payment Controller", description = "Endpoints to managing payments of reservations")
@RequestMapping("api/payment")
@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "Find payment by id", description = "Requires rol: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @Operation(summary = "Find all payments", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @GetMapping()
    public ResponseEntity<List<PaymentResponseDTO>> findAll(){
        return ResponseEntity.ok(paymentService.findAll());
    }

    @Operation(summary = "Find payments by UserID", description = "Requires rol: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @GetMapping("/user/{id}")
    public ResponseEntity<List<PaymentResponseDTO>> findByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.findByUserId(id));
    }

    @Operation(summary = "Save payment", description = "Requires roles: USER, ADMIN")
    @Secured({"USER", "ADMIN"})
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> savePayment(@RequestBody PaymentRequestDTO payment, @RequestParam Integer userId, @RequestParam Integer reservationId){
        return ResponseEntity.ok(paymentService.savePayment(payment, userId, reservationId));
    }

    @Operation(summary = "Delete payment by ID", description = "Requires rol: ADMIN")
    @Secured("ADMIN")
    @DeleteMapping
    public ResponseEntity<String> deletePayment(@RequestParam Integer id){
        return ResponseEntity.ok(paymentService.deletePayment(id));
    }
}
