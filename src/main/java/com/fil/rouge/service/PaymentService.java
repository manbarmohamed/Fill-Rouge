package com.fil.rouge.service;

import com.fil.rouge.dto.PaymentCreateDto;
import com.fil.rouge.dto.PaymentDisplayDto;
import com.fil.rouge.emuns.PaymentStatus;
import com.fil.rouge.exception.PaymentNotFoundException;
import com.fil.rouge.mapper.PaymentMapper;
import com.fil.rouge.model.Payment;
import com.fil.rouge.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    // Create a new payment
    public PaymentDisplayDto createPayment(PaymentCreateDto paymentCreateDto) {
        Payment payment = paymentMapper.toEntity(paymentCreateDto);
        payment.setStatus(PaymentStatus.PENDING); // Default status
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toDisplayDto(savedPayment);
    }

    // Get a payment by ID
    public PaymentDisplayDto getPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
        return paymentMapper.toDisplayDto(payment);
    }

    // Update a payment
    public PaymentDisplayDto updatePayment(Long paymentId, PaymentCreateDto paymentCreateDto) {
        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));

        paymentMapper.partialUpdate(paymentCreateDto, existingPayment);
        Payment updatedPayment = paymentRepository.save(existingPayment);
        return paymentMapper.toDisplayDto(updatedPayment);
    }

    // Delete a payment
    public void deletePayment(Long paymentId) {
        if (!paymentRepository.existsById(paymentId)) {
            throw new PaymentNotFoundException("Payment not found");
        }
        paymentRepository.deleteById(paymentId);
    }

    // Process a payment
    public PaymentDisplayDto processPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
        var amount = payment.getWorker().getBalance()+payment.getAmount();
        payment.getWorker().setBalance(amount);
        payment.setStatus(PaymentStatus.COMPLETED);
        Payment updatedPayment = paymentRepository.save(payment);

        return paymentMapper.toDisplayDto(updatedPayment);
    }

    // Refund a payment
    public PaymentDisplayDto refundPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));

        payment.setStatus(PaymentStatus.REFUNDED);
        Payment updatedPayment = paymentRepository.save(payment);

        return paymentMapper.toDisplayDto(updatedPayment);
    }

    // List all payments
    public List<PaymentDisplayDto> getAllPayments() {
        return paymentMapper.toDisplayDto(paymentRepository.findAll());
    }

    // List payments by client ID
    public List<PaymentDisplayDto> getPaymentsByClient(Long clientId) {
        return paymentRepository.findByClientId(clientId).stream()
                .map(paymentMapper::toDisplayDto)
                .toList();
    }

    // List payments by worker ID
    public List<PaymentDisplayDto> getPaymentsByWorker(Long workerId) {
        return paymentRepository.findByWorkerId(workerId).stream()
                .map(paymentMapper::toDisplayDto)
                .toList();
    }
}
