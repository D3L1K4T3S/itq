package org.example.orders.exceptions.handler;

import org.example.orders.models.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TestException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.OK);
    }

}
