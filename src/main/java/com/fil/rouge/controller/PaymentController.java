package com.fil.rouge.controller;

import com.fil.rouge.dto.PaymentCreateDto;
import com.fil.rouge.dto.PaymentDisplayDto;
import com.fil.rouge.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin("*")

public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDisplayDto> createPayment(@RequestBody PaymentCreateDto paymentCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(paymentCreateDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDisplayDto> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDisplayDto> updatePayment(@PathVariable Long id, @RequestBody PaymentCreateDto paymentCreateDto) {
        return ResponseEntity.ok(paymentService.updatePayment(id, paymentCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<PaymentDisplayDto> processPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.processPayment(id));
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<PaymentDisplayDto> refundPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.refundPayment(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDisplayDto>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<PaymentDisplayDto>> getPaymentsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(paymentService.getPaymentsByClient(clientId));
    }

    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<PaymentDisplayDto>> getPaymentsByWorker(@PathVariable Long workerId) {
        return ResponseEntity.ok(paymentService.getPaymentsByWorker(workerId));
    }
}
