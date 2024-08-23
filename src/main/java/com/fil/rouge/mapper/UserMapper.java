package com.fil.rouge.mapper;

import com.fil.rouge.dto.SignupDto;
import com.fil.rouge.model.Client;
import com.fil.rouge.model.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "companyName", source = "companyName")
    Client signupDtoToClient(SignupDto dto);

    @Mapping(target = "skill", source = "skill")
    Worker signupDtoToWorker(SignupDto dto);
}
