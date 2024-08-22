package com.fil.rouge.model;

import com.fil.rouge.emuns.Skills;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@DiscriminatorValue("WORKER")
public class Worker extends User {


    @ElementCollection(targetClass = Skills.class)
    @CollectionTable(name = "worker_skills", joinColumns = @JoinColumn(name = "worker_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "skill")
    private List<Skills> skills;


    @OneToMany(mappedBy = "worker")
    private List<Application> applicationList;
    @OneToMany(mappedBy = "worker")
    private List<Review> reviews;

    @OneToMany(mappedBy = "worker")
    private List<Payment> payments;
}
