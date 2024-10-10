package com.fil.rouge.dto;


import com.fil.rouge.emuns.Skill;
import lombok.Data;

import java.io.File;

@Data
public class WorkerDto {
    private Long id;
    private String name;
    private String username;
    private File profileImage;
    private Skill skill;
    private double balance;
    private int experience;
}
