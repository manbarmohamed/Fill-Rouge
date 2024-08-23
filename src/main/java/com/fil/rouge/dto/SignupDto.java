package com.fil.rouge.dto;

import com.fil.rouge.emuns.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {

    private String name;
    private String username;
    private String password;
    private String companyName; // Only for Client
    private Skill skill; // Only for Worker
}

