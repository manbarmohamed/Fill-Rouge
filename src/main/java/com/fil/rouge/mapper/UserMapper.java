package com.fil.rouge.mapper;

import com.fil.rouge.dto.SignupDto;
import com.fil.rouge.dto.UpdateProfileDto;
import com.fil.rouge.model.Client;
import com.fil.rouge.model.User;
import com.fil.rouge.model.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "companyName", source = "companyName")
    Client signupDtoToClient(SignupDto dto);

    @Mapping(target = "skill", source = "skill")
    Worker signupDtoToWorker(SignupDto dto);


    @Mapping(target = "password", ignore = true)  // Nous ne mettons pas à jour le mot de passe via le mapper
    void updateUserFromDto(UpdateProfileDto dto, @MappingTarget User user);

    @Mapping(source = "companyName", target = "client.companyName")
    void updateClientFromDto(UpdateProfileDto dto, @MappingTarget Client client);

    @Mapping(source = "skill", target = "worker.skill")
    void updateWorkerFromDto(UpdateProfileDto dto, @MappingTarget Worker worker);
}
