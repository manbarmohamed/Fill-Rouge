package com.fil.rouge.model;

import com.fil.rouge.emuns.Role;
import com.fil.rouge.emuns.Skill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Worker extends User {


   @Enumerated(EnumType.STRING)
    private Skill skill;
    private double balance;
    private int experience;
    @OneToMany(mappedBy = "worker")
    private List<Application> applicationList;
    @OneToMany(mappedBy = "worker")
    private List<Review> reviews;

    @OneToMany(mappedBy = "worker")
    private List<Payment> payments;

 public Worker() {
  this.setRole(Role.WORKER);
 }

}
