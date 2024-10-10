package com.fil.rouge.repository;

import com.fil.rouge.emuns.PaymentStatus;
import com.fil.rouge.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByClientId(Long clientId);

    List<Payment> findByWorkerId(Long workerId);
    @Query("SELECT COUNT(p) FROM Payment p")
    Long countAllPayments();
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.status = :status")
    Long countPaymentsByStatus(@Param("status") PaymentStatus status);
    @Query("SELECT p FROM Payment p WHERE p.worker.id = :workerId")
    List<Payment> findPaymentsByWorkerId(@Param("workerId") Long workerId);

}
