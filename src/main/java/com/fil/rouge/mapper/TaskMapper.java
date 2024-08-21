package com.fil.rouge.mapper;

import com.fil.rouge.dto.TaskDto;
import com.fil.rouge.model.Task;


//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    Task toEntity(TaskDto taskDto);
}
