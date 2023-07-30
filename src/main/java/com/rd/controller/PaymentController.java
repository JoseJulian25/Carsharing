package com.rd.controller;

import com.rd.DTO.request.PaymentRequestDTO;
import com.rd.DTO.response.PaymentResponseDTO;
import com.rd.service.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/payment")
@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentServiceImpl paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<PaymentResponseDTO>> findAll(){
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PaymentResponseDTO>> findByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.findByUserId(id));
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> savePayment(@RequestBody PaymentRequestDTO payment, @RequestParam Integer userId, @RequestParam Integer reservationId){
        return ResponseEntity.ok(paymentService.savePayment(payment, userId, reservationId));
    }

    @DeleteMapping
    public ResponseEntity<String> deletePayment(@RequestParam Integer id){
        return ResponseEntity.ok(paymentService.deletePayment(id));
    }
}
