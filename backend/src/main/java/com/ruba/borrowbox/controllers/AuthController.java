package com.ruba.borrowbox.controllers;

import com.ruba.borrowbox.dto.RegisterRequest;
import com.ruba.borrowbox.entity.User;
import com.ruba.borrowbox.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.ruba.borrowbox.dto.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterRequest request) {
        User user= authService.register(request);
        return new AuthResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}