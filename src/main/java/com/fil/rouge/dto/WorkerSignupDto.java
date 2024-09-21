package com.fil.rouge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerSignupDto extends UserDto {
    private String skill;
    private double balance;
    private int experience;
}
