package com.fil.rouge.dto;

import com.fil.rouge.emuns.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String token;
    private String username;
    private Role role;
}
