package com.fil.rouge.dto;

import com.fil.rouge.emuns.Skill;
import lombok.Data;

@Data
public class WorkerProfileUpdateDto extends UserProfileUpdateDto {
    private Skill skill;
    private int experience;
}
