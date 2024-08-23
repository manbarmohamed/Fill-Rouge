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


   @Enumerated(EnumType.STRING)
    private Skills skill;


    @OneToMany(mappedBy = "worker")
    private List<Application> applicationList;
    @OneToMany(mappedBy = "worker")
    private List<Review> reviews;

    @OneToMany(mappedBy = "worker")
    private List<Payment> payments;
}
