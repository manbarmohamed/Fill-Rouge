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
import org.mapstruct.ReportingPolicy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper {

    @Mapping(target = "profileImage", ignore = true)
    void updateUserFromDto(UserProfileUpdateDto dto, @MappingTarget User user);

    @Mapping(target = "profileImage", ignore = true)
    void updateWorkerFromDto(WorkerProfileUpdateDto dto, @MappingTarget Worker worker);

    @Mapping(target = "profileImage", ignore = true)
    void updateClientFromDto(ClientProfileUpdateDto dto, @MappingTarget Client client);

    void updateCompanyFromDto(CompanyDto dto, @MappingTarget Company company);

    default byte[] mapMultipartFileToByteArray(MultipartFile file) throws IOException {
        return file != null ? file.getBytes() : null;
    }
}