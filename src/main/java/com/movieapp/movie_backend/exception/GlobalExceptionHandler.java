package com.movieapp.movie_backend.exception;
import com.movieapp.movie_backend.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.movieapp.movie_backend.exception.EmailAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<AuthResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        AuthResponse response = new AuthResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<AuthResponse> handleInvalidCredentialsException(
            InvalidCredentialsException exception) {

        AuthResponse response = new AuthResponse(exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }
}
