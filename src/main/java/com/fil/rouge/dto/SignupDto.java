package com.fil.rouge.dto;

import com.fil.rouge.emuns.Role;
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
    private Role role;

}

