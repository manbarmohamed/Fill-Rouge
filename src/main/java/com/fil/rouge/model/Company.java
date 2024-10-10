package com.fil.rouge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String description;
    @Lob
    @Column(name = "company_image", columnDefinition="LONGBLOB")
    private byte[] companyImage;
    @OneToMany(mappedBy = "company")
    List<Client> clients;
}
