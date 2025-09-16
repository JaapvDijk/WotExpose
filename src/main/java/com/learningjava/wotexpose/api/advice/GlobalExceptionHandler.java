package com.learningjava.wotexpose.api.advice;

import com.learningjava.wotexpose.shared.exception.PlayerNotFoundException;
import com.learningjava.wotexpose.shared.exception.VehicleNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", "");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return error(HttpStatus.BAD_REQUEST, "Bad request", ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(IllegalArgumentException ex) {
        return error(HttpStatus.BAD_REQUEST, "Bad request", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // From @Valid
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        return error(HttpStatus.BAD_REQUEST, "Validation failed", ex.getMessage());
    }

    @ExceptionHandler(BindException.class) // From @Valid
    public ResponseEntity<Map<String, String>> handleBindErrors(BindException ex) {
        return error(HttpStatus.BAD_REQUEST, "Validation failed", ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(EntityNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, "Not found", ex.getMessage());
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePlayerNotFound(PlayerNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, "Player not found", ex.getMessage());
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleVehicleNotFound(VehicleNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, "Vehicle not found", ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentials(BadCredentialsException ex) {
        return error(HttpStatus.UNAUTHORIZED, "Invalid username or password", ex.getMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String, String>> handleJwtExpired(ExpiredJwtException ex) {
        return error(HttpStatus.UNAUTHORIZED, "The JWT token has expired", ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
        return error(HttpStatus.UNAUTHORIZED, "You are not authorized to access this resource", ex.getMessage());
    }

    private ResponseEntity<Map<String, String>> error(HttpStatus status, String error, String message) {
        return ResponseEntity
                .status(status)
                .body(Map.of("error", error, "message", message));
    }
}
