package com.fil.rouge.mapper;

import com.fil.rouge.dto.TaskDto;
import com.fil.rouge.model.Task;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    // Mapping from Task entity to TaskDto including clientName and companyName
    @Mapping(source = "client.name", target = "clientName")
    @Mapping(source = "client.companyName", target = "companyName")
    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);

    List<Task> toEntity(List<TaskDto> taskDto);
    List<TaskDto> toDto(List<Task> task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task partialUpdate(TaskDto taskDto, @MappingTarget Task task);
}