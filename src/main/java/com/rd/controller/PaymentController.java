package com.rd.controller;

import com.rd.DTO.request.PaymentRequestDTO;
import com.rd.DTO.response.PaymentResponseDTO;
import com.rd.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/payment")
@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @Secured({"USER", "ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @Secured("ADMIN")
    @GetMapping()
    public ResponseEntity<List<PaymentResponseDTO>> findAll(){
        return ResponseEntity.ok(paymentService.findAll());
    }

    @Secured({"USER", "ADMIN"})
    @GetMapping("/user/{id}")
    public ResponseEntity<List<PaymentResponseDTO>> findByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.findByUserId(id));
    }

    @Secured({"USER", "ADMIN"})
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> savePayment(@RequestBody PaymentRequestDTO payment, @RequestParam Integer userId, @RequestParam Integer reservationId){
        return ResponseEntity.ok(paymentService.savePayment(payment, userId, reservationId));
    }

    @Secured("ADMIN")
    @DeleteMapping
    public ResponseEntity<String> deletePayment(@RequestParam Integer id){
        return ResponseEntity.ok(paymentService.deletePayment(id));
    }
}
