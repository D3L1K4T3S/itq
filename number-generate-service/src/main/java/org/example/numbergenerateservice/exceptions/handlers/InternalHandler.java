package org.example.numbergenerateservice.exceptions.handlers;

import org.example.numbergenerateservice.exceptions.ModelException;
import org.example.numbergenerateservice.models.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InternalHandler {

    @ExceptionHandler(ModelException.class)
    public ResponseEntity<ErrorResponse> handle(ModelException exception) {
        return new ResponseEntity<>(new ErrorResponse("Model: " + exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handle(RuntimeException exception) {
        return new ResponseEntity<>(new ErrorResponse("Error server: " + exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
