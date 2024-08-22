package com.fil.rouge.controller;


import com.fil.rouge.dto.ApplicationDto;
import com.fil.rouge.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationDto> submitApplication(@RequestBody ApplicationDto applicationDto) {
        ApplicationDto submittedApplication = applicationService.submitApplication(applicationDto);
        return new ResponseEntity<>(submittedApplication, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<ApplicationDto> acceptApplication(@PathVariable Long id) {
        ApplicationDto acceptedApplication = applicationService.acceptApplication(id);
        return new ResponseEntity<>(acceptedApplication, HttpStatus.OK);
    }
}
