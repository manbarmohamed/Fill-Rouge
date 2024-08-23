package com.fil.rouge.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreateDto {

    private Long id;
    private Double amount;
    private Long clientId;
    private Long workerId;
}
