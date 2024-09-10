package com.fil.rouge.model;


import com.fil.rouge.emuns.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin extends User {

    public Admin() {
        this.setRole(Role.ADMIN);
    }
}
