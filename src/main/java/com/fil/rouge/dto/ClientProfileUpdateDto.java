package com.fil.rouge.dto;

import lombok.Data;

@Data
public class ClientProfileUpdateDto extends UserProfileUpdateDto {
    private CompanyDto company;
}