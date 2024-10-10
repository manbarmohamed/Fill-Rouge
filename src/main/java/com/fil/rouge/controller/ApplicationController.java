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

    //Dashboard Methods

    @GetMapping("/worker/me")
    public ResponseEntity<List<ApplicationDto>> getApplicationsForLoggedInWorker() {
        List<ApplicationDto> applications = applicationService.getApplicationsByWorkerId();
        return ResponseEntity.ok(applications);
    }

    // Get total number of applications for the logged-in worker
    @GetMapping("/worker/me/count")
    public ResponseEntity<Long> countApplicationsForLoggedInWorker() {
        Long totalApplications = applicationService.countApplicationsByWorkerId();
        return ResponseEntity.ok(totalApplications);
    }

    // Get total number of applications
    @GetMapping("/count")
    public ResponseEntity<Long> countAllApplications() {
        Long totalApplications = applicationService.countAllApplications();
        return ResponseEntity.ok(totalApplications);
    }

    // Get total number of applications by status
    @GetMapping("/status/accepted/count")
    public ResponseEntity<Long> countAcceptedApplications() {
        Long totalApplications = applicationService.countAcceptedApplications();
        return ResponseEntity.ok(totalApplications);
    }

    @GetMapping("/status/pending/count")
    public ResponseEntity<Long> countPendingApplications() {
        Long totalApplications = applicationService.countPendingApplications();
        return ResponseEntity.ok(totalApplications);
    }

    @GetMapping("/status/rejected/count")
    public ResponseEntity<Long> countRejectedApplications() {
        Long totalApplications = applicationService.countRejectedApplications();
        return ResponseEntity.ok(totalApplications);
    }

    // Get all applications for a specific task
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByTaskId(@PathVariable Long taskId) {
        List<ApplicationDto> applications = applicationService.getApplicationsByTaskId(taskId);
        return ResponseEntity.ok(applications);
    }
}
