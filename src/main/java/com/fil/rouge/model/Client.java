package com.fil.rouge.model;

import com.fil.rouge.emuns.Role;
import com.fil.rouge.model.Payment;
import com.fil.rouge.model.Review;
import com.fil.rouge.model.Task;
import com.fil.rouge.model.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("CLIENT")
public class Client extends User {


    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "client")
    private List<Task> tasks;

    @OneToMany(mappedBy = "client")
    private List<Review> reviews;

    @OneToMany(mappedBy = "client")
    private List<Payment> payments;

    public Client() {
        this.setRole(Role.CLIENT);
    }
}