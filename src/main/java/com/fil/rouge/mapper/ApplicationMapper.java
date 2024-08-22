package com.fil.rouge.mapper;

import com.fil.rouge.dto.ApplicationDto;

import com.fil.rouge.model.Application;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationMapper {

    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "workerId", target = "worker.id")
    Application toEntity(ApplicationDto applicationDto);

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "worker.id", target = "workerId")
    ApplicationDto toDto(Application application);

    List<Application> toEntity(List<ApplicationDto> applicationDto);
    List<ApplicationDto> toDto(List<Application> application);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Application partialUpdate(ApplicationDto applicationDto, @MappingTarget Application application);
}
