package com.fil.rouge.dto;

import com.fil.rouge.emuns.Skills;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {

    private String name;
    private String username;
    private String password;
    private String companyName; // Only for Client
    private Skills skill; // Only for Worker
}

