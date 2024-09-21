package com.fil.rouge.mapper;

import com.fil.rouge.dto.TaskDto;
import com.fil.rouge.model.Task;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    // ربط اسم العميل واسم الشركة بالـ TaskDto
    @Mapping(source = "client.name", target = "clientName")
    @Mapping(source = "client.company.name", target = "companyName")  // استخراج اسم الشركة من كيان Company المرتبط

    @Mapping(source = "client.company.address", target = "address")  // استخراج اسم الشركة من كيان Company المرتبط
    @Mapping(source = "client.company.phone", target = "phone")  // استخراج اسم الشركة من كيان Company المرتبط
    @Mapping(source = "client.company.email", target = "email")  // استخراج اسم الشركة من كيان Company المرتبط
    @Mapping(source = "client.company.website", target = "website")  // استخراج اسم الشركة من كيان Company المرتبط
    @Mapping(source = "client.company.description", target = "descriptionCompany")  // استخراج اسم الشركة من كيان Company المرتبط
    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);

    List<Task> toEntity(List<TaskDto> taskDtoList);
    List<TaskDto> toDto(List<Task> taskList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task partialUpdate(TaskDto taskDto, @MappingTarget Task task);
}
