package org.shivam.codemeetly.exception;

import org.shivam.codemeetly.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>("Error: " + ex.getMessage(), null));
    }
}