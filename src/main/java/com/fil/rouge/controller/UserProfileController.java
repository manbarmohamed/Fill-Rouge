package com.fil.rouge.controller;

import com.fil.rouge.dto.ClientProfileUpdateDto;
import com.fil.rouge.dto.WorkerProfileUpdateDto;
import com.fil.rouge.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PutMapping("/worker/{id}")
   // @PreAuthorize("hasRole('WORKER') and #id == authentication.principal.id")
    public ResponseEntity<Void> updateWorkerProfile(@PathVariable("id") Long id, @RequestBody WorkerProfileUpdateDto updateDto) {
        userProfileService.updateProfileWorker(id, updateDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/client/{id}")
    //@PreAuthorize("hasRole('CLIENT') and #id == authentication.principal.id")
    public ResponseEntity<Void> updateClientProfile(@PathVariable("id") Long id, @RequestBody ClientProfileUpdateDto updateDto) {
        userProfileService.updateProfileClient(id, updateDto);
        return ResponseEntity.ok().build();
    }
}