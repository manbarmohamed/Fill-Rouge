package com.fil.rouge.dto;

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
    private String status; // Payment status
}
