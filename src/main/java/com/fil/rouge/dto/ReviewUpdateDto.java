package com.fil.rouge.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpdateDto {

    private Integer rating;
    private String comment;
}