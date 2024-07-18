package org.example.numbergenerateservice.exceptions.handlers;

import org.example.numbergenerateservice.models.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InternalHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
