package com.fil.rouge.dto;

import com.fil.rouge.emuns.Categories;
import com.fil.rouge.emuns.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Categories category;
    private Double price;
    private TaskStatus status;

    private LocalDate deadline;
    private MultipartFile taskImageFile;
    // New fields for client information
    private String clientName;
    private String companyName;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String descriptionCompany;


}
