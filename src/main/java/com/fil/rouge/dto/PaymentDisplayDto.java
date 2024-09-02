package com.fil.rouge.dto;

import com.fil.rouge.emuns.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDisplayDto {

    private Long id;
    private Double amount;
    private String clientName;
    private String workerName;
    private PaymentStatus status; // Payment status
}
