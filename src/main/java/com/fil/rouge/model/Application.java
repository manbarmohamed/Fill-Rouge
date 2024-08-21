package com.fil.rouge.model;

import com.fil.rouge.emuns.ApplicationStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ApplicationStatus status;

    @ManyToOne
    private Task task;

    @ManyToOne
    private Worker worker;


}
