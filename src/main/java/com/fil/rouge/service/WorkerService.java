package com.fil.rouge.service;

import com.fil.rouge.dto.WorkerDto;
import com.fil.rouge.exception.WorkerNotFoundException;
import com.fil.rouge.mapper.WorkerMapper;
import com.fil.rouge.model.Worker;
import com.fil.rouge.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;

    public List<WorkerDto> findAllWorker() {
        List<Worker> workers = workerRepository.findAll();
        return workerMapper.workerToWorkerDto(workers);
    }

    public List<WorkerDto> findWorkerByName(String name) {
        List<Worker> workers = workerRepository.findByNameContainingIgnoreCase(name);
        return workerMapper.workerToWorkerDto(workers);
    }

    public WorkerDto getWorkerLoggedInfo(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Worker worker = workerRepository.findByUsername(loggedInUser.getName());
        return workerMapper.workerToWorkerDto(worker);
    }


}
