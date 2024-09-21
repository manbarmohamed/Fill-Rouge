package com.fil.rouge.controller;

import com.fil.rouge.dto.JwtResponse;
import com.fil.rouge.dto.LoginDto;
import com.fil.rouge.dto.SignupDto;

import com.fil.rouge.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {


    private final UserAuthService authService;

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
}
