package com.fil.rouge.controller;

import com.fil.rouge.dto.ClientProfileUpdateDto;
import com.fil.rouge.dto.WorkerProfileUpdateDto;
import com.fil.rouge.exception.ClientNotFoundException;
import com.fil.rouge.exception.CompanyNotFoundException;
import com.fil.rouge.exception.WorkerNotFoundException;
import com.fil.rouge.model.Client;
import com.fil.rouge.model.Company;
import com.fil.rouge.model.Worker;
import com.fil.rouge.repository.ClientRepository;
import com.fil.rouge.repository.CompanyRepository;
import com.fil.rouge.repository.WorkerRepository;
import com.fil.rouge.service.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin("*")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final CompanyRepository companyRepository;
    private final WorkerRepository workerRepository;
    private final ClientRepository clientRepository;

    public UserProfileController(UserProfileService userProfileService, CompanyRepository companyRepository, WorkerRepository workerRepository, ClientRepository clientRepository) {
        this.userProfileService = userProfileService;
        this.companyRepository = companyRepository;
        this.workerRepository = workerRepository;
        this.clientRepository = clientRepository;
    }

//    @PutMapping("/worker/{id}")
//   // @PreAuthorize("hasRole('WORKER') and #id == authentication.principal.id")
//    public ResponseEntity<Void> updateWorkerProfile(@PathVariable("id") Long id, @RequestBody WorkerProfileUpdateDto updateDto) {
//        userProfileService.updateProfileWorker(id, updateDto);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/client/{id}")
//    //@PreAuthorize("hasRole('CLIENT') and #id == authentication.principal.id")
//    public ResponseEntity<Void> updateClientProfile(@PathVariable("id") Long id, @RequestBody ClientProfileUpdateDto updateDto) {
//        userProfileService.updateProfileClient(id, updateDto);
//        return ResponseEntity.ok().build();
//    }


    @PutMapping(value = "/editworker", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateWorkerProfile(
            @RequestPart("updateDto") WorkerProfileUpdateDto updateDto,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
    ) {
        try {
            userProfileService.updateProfileWorker( updateDto, profileImage);
            return ResponseEntity.ok().body("Worker profile updated successfully");
        } catch (WorkerNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error processing image");
        }
    }

    @PutMapping(value = "/editclient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateClientProfile(
            @RequestPart("updateDto") ClientProfileUpdateDto updateDto,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestPart(value = "companyImage", required = false) MultipartFile companyImage
    ) {
        try {
            userProfileService.updateProfileClient( updateDto, profileImage, companyImage);
            return ResponseEntity.ok().body("Client profile updated successfully");
        } catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error processing image");
        }
    }

    @GetMapping("/worker/{id}/image")
    public ResponseEntity<byte[]> getWorkerProfileImage(@PathVariable("id") Long id) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker not found"));
        if (worker.getProfileImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Adjust if you support different image types
                    .body(worker.getProfileImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{id}/image")
    public ResponseEntity<byte[]> getClientProfileImage(@PathVariable("id") Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        if (client.getProfileImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Adjust if you support different image types
                    .body(client.getProfileImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/company/{id}/image")
    public ResponseEntity<byte[]> getCompanyImage(@PathVariable("id") Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        if (company.getCompanyImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Adjust if you support different image types
                    .body(company.getCompanyImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}