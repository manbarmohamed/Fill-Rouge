package com.fil.rouge.service;

import com.fil.rouge.dto.ApplicationDto;
import com.fil.rouge.emuns.ApplicationStatus;
import com.fil.rouge.exception.ApplicationNotFoundException;
import com.fil.rouge.exception.TaskNotFoundException;
import com.fil.rouge.exception.WorkerNotFoundException;
import com.fil.rouge.mapper.ApplicationMapper;
import com.fil.rouge.model.Application;
import com.fil.rouge.model.Task;
import com.fil.rouge.model.Worker;
import com.fil.rouge.repository.ApplicationRepository;
import com.fil.rouge.repository.TaskRepository;
import com.fil.rouge.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final TaskRepository taskRepository;
    private final WorkerRepository workerRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationDto submitApplication(ApplicationDto applicationDto) {

        Task task = taskRepository.findById(applicationDto.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        Worker worker = workerRepository.findById(applicationDto.getWorkerId().getId())
                .orElseThrow(() -> new WorkerNotFoundException("Worker not found"));

        Application application = applicationMapper.toEntity(applicationDto);
        application.setStatus(ApplicationStatus.PENDING);
        application.setTask(task);
        application.setWorker(worker);

        application = applicationRepository.save(application);

        return applicationMapper.toDto(application);
    }

    public ApplicationDto acceptApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));
        application.setStatus(ApplicationStatus.ACCEPTED);
        Application updatedApplication = applicationRepository.save(application);
        return applicationMapper.toDto(updatedApplication);
    }

    public ApplicationDto rejectApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));
        application.setStatus(ApplicationStatus.REJECTED);
        Application updatedApplication = applicationRepository.save(application);
        return applicationMapper.toDto(updatedApplication);
    }
}
