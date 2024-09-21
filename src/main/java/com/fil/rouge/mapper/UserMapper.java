package com.fil.rouge.mapper;

import com.fil.rouge.dto.*;
import com.fil.rouge.model.Admin;
import com.fil.rouge.model.Client;
import com.fil.rouge.model.Worker;
import org.mapstruct.*;

//@Mapper(componentModel = "spring")
//public interface UserMapper {
//
//    @Mapping(target = "company.name", source = "companyName")
//    @Mapping(target = "company.address", source = "companyAddress")
//    @Mapping(target = "company.phone", source = "companyPhone")
//    @Mapping(target = "company.email", source = "companyEmail")
//    @Mapping(target = "company.website", source = "companyWebsite")
//    @Mapping(target = "company.description", source = "companyDescription")
//    Client clientSignupDtoToClient(ClientSignupDto dto);
//
//    @Mapping(target = "skill", source = "skill")
//    Worker workerSignupDtoToWorker(WorkerSignupDto dto);
//
//    Admin adminSignupDtoToAdmin(AdminSignupDto dto);
//}
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    Client clientSignupDtoToClient(SignupDto dto);
    Worker workerSignupDtoToWorker(SignupDto dto);
    Admin adminSignupDtoToAdmin(SignupDto dto);
}












//@Mapper(componentModel = "spring")
//public interface UserMapper {
//
//    @Mapping(target = "companyName", source = "companyName")
//    Client signupDtoToClient(SignupDto dto);
//
//    @Mapping(target = "skill", source = "skill")
//    Worker signupDtoToWorker(SignupDto dto);
//
//
//    Admin signupDtoToAdmin(SignupDto dto);
//
//
//    @Mapping(target = "password", ignore = true)  // Nous ne mettons pas à jour le mot de passe via le mapper
//    void updateUserFromDto(UpdateProfileDto dto, @MappingTarget User user);
//
//    @Mapping(source = "companyName", target = "client.companyName")
//    void updateClientFromDto(UpdateProfileDto dto, @MappingTarget Client client);
//
//    @Mapping(source = "skill", target = "worker.skill")
//    void updateWorkerFromDto(UpdateProfileDto dto, @MappingTarget Worker worker);
//}

