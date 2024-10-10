package com.fil.rouge.service;

import com.fil.rouge.dto.ApplicationDisplayDto;
import com.fil.rouge.dto.ApplicationDto;
import com.fil.rouge.emuns.ApplicationStatus;
import com.fil.rouge.emuns.TaskStatus;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final TaskRepository taskRepository;
    private final WorkerRepository workerRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationDto submitApplication(ApplicationDto applicationDto) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Worker worker = workerRepository.findByUsername(loggedInUser.getName());
        Task task = taskRepository.findById(applicationDto.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        Application application = applicationMapper.toEntity(applicationDto);
        application.setStatus(ApplicationStatus.PENDING);
        application.setTask(task);
        application.setWorker(worker);

        application = applicationRepository.save(application);

        return applicationMapper.toDto(application);
    }

    public String acceptApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));

        application.setStatus(ApplicationStatus.ACCEPTED);
        application.getTask().setStatus(TaskStatus.IN_PROGRESS);
        applicationRepository.save(application);
        //emailService.sendEmail(updatedApplication);
        return "Application accepted successfully";
    }

    public String rejectApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));
        application.setStatus(ApplicationStatus.REJECTED);
        applicationRepository.save(application);
        //emailService.sendEmail(updatedApplication);
        return "Application rejected successfully";
    }

    public List<ApplicationDto> getApplications() {
        List<Application> applications = applicationRepository.findAll();
        return applicationMapper.toDto(applications);
    }


    //    Dashboard Methods
    public List<ApplicationDto> getApplicationsByWorkerId() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Worker worker = workerRepository.findByUsername(loggedInUser.getName());
        List<Application> applications = applicationRepository.findApplicationsByWorkerId(worker.getId());
        return applicationMapper.toDto(applications);
    }
    public Long countApplicationsByWorkerId() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Worker worker = workerRepository.findByUsername(loggedInUser.getName());
        return applicationRepository.countApplicationsByWorkerId(worker.getId());
    }
    public Long countAllApplications() {
        return applicationRepository.countAllApplications();
    }
    public Long countPendingApplications() {
        return applicationRepository.countApplicationsByStatus(ApplicationStatus.PENDING);
    }
    public Long countAcceptedApplications() {
        return applicationRepository.countApplicationsByStatus(ApplicationStatus.ACCEPTED);
    }
    public Long countRejectedApplications() {
        return applicationRepository.countApplicationsByStatus(ApplicationStatus.REJECTED);
    }

    public List<ApplicationDto> getApplicationsByTaskId(Long taskId) {
        List<Application> applications = applicationRepository.findApplicationsByTaskId(taskId);
        return applicationMapper.toDto(applications);
    }
}
