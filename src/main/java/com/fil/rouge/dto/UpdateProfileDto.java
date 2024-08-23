package com.fil.rouge.dto;

import com.fil.rouge.emuns.Skill;
import lombok.*;



import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileDto {
    private String name;
    private String username;
    private String oldPassword;  // Pour vérifier le mot de passe actuel
    private String newPassword;  // Nouveau mot de passe
    private String companyName;  // Pour les clients
    private Skill skill; // Pour les travailleurs
}