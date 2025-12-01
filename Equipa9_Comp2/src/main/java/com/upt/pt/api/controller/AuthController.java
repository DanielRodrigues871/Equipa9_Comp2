package com.upt.pt.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.pt.api.dto.RegistroDTO;
import com.upt.pt.api.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegistroDTO dto) {
        Object created = authService.register(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
