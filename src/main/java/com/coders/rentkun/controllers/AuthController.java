package com.coders.rentkun.controllers;

import com.coders.rentkun.dtos.users.requests.UserLoginRequestDto;
import com.coders.rentkun.dtos.users.requests.UserRegisterRequestDto;
import com.coders.rentkun.dtos.users.responses.CurrentUserResponseDto;
import com.coders.rentkun.services.users.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<CurrentUserResponseDto> register(@RequestBody UserRegisterRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}

