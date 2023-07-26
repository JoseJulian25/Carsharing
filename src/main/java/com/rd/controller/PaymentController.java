package com.rd.controller;

import com.rd.DTO.PaymentDTO;
import com.rd.entity.Payment;
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
    public ResponseEntity<PaymentDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<PaymentDTO>> findAll(){
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PaymentDTO>> findByUserId(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.findByUserId(id));
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> savePayment(@RequestBody Payment payment, @RequestParam Integer userId, @RequestParam Integer reservationId){
        return ResponseEntity.ok(paymentService.savePayment(payment, userId, reservationId));
    }

    @DeleteMapping
    public ResponseEntity<String> deletePayment(@RequestParam Integer id){
        return ResponseEntity.ok(paymentService.deletePayment(id));
    }
}
