package com.fil.rouge.controller;


import com.fil.rouge.dto.ApplicationDisplayDto;
import com.fil.rouge.dto.ApplicationDto;
import com.fil.rouge.exception.ApplicationNotFoundException;
import com.fil.rouge.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getApplications() {
        List<ApplicationDto> applications = applicationService.getApplications();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApplicationDto> submitApplication(@RequestBody ApplicationDto applicationDto) {
        ApplicationDto submittedApplication = applicationService.submitApplication(applicationDto);
        return new ResponseEntity<>(submittedApplication, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<String> acceptApplication(@PathVariable("id") Long id) {
        String acceptedApplication = applicationService.acceptApplication(id);
        return new ResponseEntity<>(acceptedApplication, HttpStatus.OK);
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<String> rejectApplication(@PathVariable("id") Long id) {
        String rejectedApplication = applicationService.rejectApplication(id);
        return new ResponseEntity<>(rejectedApplication, HttpStatus.OK);
    }

    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<ApplicationDisplayDto>> getApplicationsByWorkerId(@PathVariable Long workerId) {
        List<ApplicationDisplayDto> applications = applicationService.findApplicationsByWorkerId(workerId);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<ApplicationDisplayDto>> getApplicationsByTaskId(@PathVariable Long taskId) {
        List<ApplicationDisplayDto> applications = applicationService.findApplicationsByTaskId(taskId);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDisplayDto> getApplicationById(@PathVariable Long id) {
        try {
            ApplicationDisplayDto application = applicationService.findApplicationsById(id);
            return ResponseEntity.ok(application);
        } catch (ApplicationNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
