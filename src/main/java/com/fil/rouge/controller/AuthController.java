package com.fil.rouge.controller;

import com.fil.rouge.dto.JwtResponse;
import com.fil.rouge.dto.LoginDto;
import com.fil.rouge.dto.SignupDto;

import com.fil.rouge.dto.UpdateProfileDto;
import com.fil.rouge.model.User;
import com.fil.rouge.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserAuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto signupDto) {
        authService.signup(signupDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        JwtResponse user = authService.login(loginDto);

        return ResponseEntity.ok(user);
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateProfile(@PathVariable Long userId, @RequestBody UpdateProfileDto updateProfileDto) {
        User updatedUser = authService.updateProfile(userId, updateProfileDto);
        return ResponseEntity.ok(updatedUser);
    }
}
