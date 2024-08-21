package com.fil.rouge.model;

import com.fil.rouge.emuns.Categories;
import com.fil.rouge.emuns.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Categories category;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private Double price;
    private LocalDate deadline;
    @OneToMany(mappedBy = "task")
    private List<Application> applications;
    @ManyToOne
    private Client client;

}
