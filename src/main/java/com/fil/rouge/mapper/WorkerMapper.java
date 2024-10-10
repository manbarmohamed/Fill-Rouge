package com.fil.rouge.mapper;


import com.fil.rouge.dto.WorkerDto;
import com.fil.rouge.model.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper
public interface WorkerMapper {


    @Mapping(source = "profileImage", target = "profileImage", ignore = true) // You will need custom logic to handle the conversion of the byte array to File
    WorkerDto workerToWorkerDto(Worker worker);

    @Mapping(source = "profileImage", target = "profileImage", ignore = true) // Custom logic required for File to byte[]
    Worker workerDtoToWorker(WorkerDto workerDto);

    @Mapping(source = "profileImage", target = "profileImage", ignore = true) // You will need custom logic to handle the conversion of the byte array to File
    List<WorkerDto> workerToWorkerDto(List<Worker> worker);

    @Mapping(source = "profileImage", target = "profileImage", ignore = true) // Custom logic required for File to byte[]
    List<Worker> workerDtoToWorker(List<WorkerDto> workerDto);

}
