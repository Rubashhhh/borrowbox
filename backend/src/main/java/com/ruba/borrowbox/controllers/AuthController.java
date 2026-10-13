package com.ruba.borrowbox.controllers;

import com.ruba.borrowbox.dto.RegisterRequest;
import com.ruba.borrowbox.entity.User;
import com.ruba.borrowbox.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.ruba.borrowbox.dto.AuthResponse;
import com.ruba.borrowbox.dto.LoginRequest;
import com.ruba.borrowbox.dto.LoginResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        User user= authService.register(request);
        return new AuthResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }
}