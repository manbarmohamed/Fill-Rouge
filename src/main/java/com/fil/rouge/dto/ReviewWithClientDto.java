package com.fil.rouge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewWithClientDto {

    private Long id;
    private Integer rating;
    private String comment;
    private String workerName;
    private String clientName;
}
