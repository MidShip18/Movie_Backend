package com.movieapp.movie_backend.controller;
import com.movieapp.movie_backend.dto.LoginRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.movieapp.movie_backend.dto.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import com.movieapp.movie_backend.service.AuthService;
import com.movieapp.movie_backend.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
