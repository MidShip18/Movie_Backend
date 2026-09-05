package com.movieapp.movie_backend.service;
import com.movieapp.movie_backend.dto.AuthResponse;
import com.movieapp.movie_backend.dto.LoginRequest;
import com.movieapp.movie_backend.dto.RegisterRequest;
import com.movieapp.movie_backend.exception.EmailAlreadyExistsException;
import com.movieapp.movie_backend.exception.InvalidCredentialsException;
import org.springframework.stereotype.Service;
import com.movieapp.movie_backend.repository.UserRepository;
import com.movieapp.movie_backend.entity.User;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.movieapp.movie_backend.security.JwtService;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    public AuthResponse register(RegisterRequest request){
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse("User registered successfully", token);
    }
    public AuthResponse login(LoginRequest request){
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isEmpty()){
            throw new InvalidCredentialsException("Invalid email or password");
        }
        User user = existingUser.get();
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid email or password");
        }
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse("Login successful", token);
    }

}

