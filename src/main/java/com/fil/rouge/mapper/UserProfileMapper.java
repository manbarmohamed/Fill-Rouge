package com.fil.rouge.mapper;

import com.fil.rouge.dto.ClientProfileUpdateDto;
import com.fil.rouge.dto.CompanyDto;
import com.fil.rouge.dto.UserProfileUpdateDto;
import com.fil.rouge.dto.WorkerProfileUpdateDto;
import com.fil.rouge.model.Client;
import com.fil.rouge.model.Company;
import com.fil.rouge.model.User;
import com.fil.rouge.model.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateUserFromDto(UserProfileUpdateDto dto, @MappingTarget User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "applicationList", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "payments", ignore = true)
    void updateWorkerFromDto(WorkerProfileUpdateDto dto, @MappingTarget Worker worker);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "payments", ignore = true)
    void updateClientFromDto(ClientProfileUpdateDto dto, @MappingTarget Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clients", ignore = true)
    void updateCompanyFromDto(CompanyDto dto, @MappingTarget Company company);
}