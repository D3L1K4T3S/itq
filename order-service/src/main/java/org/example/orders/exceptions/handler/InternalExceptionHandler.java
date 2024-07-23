package org.example.orders.exceptions.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.example.orders.exceptions.ModelException;
import org.example.orders.models.dto.response.ErrorResponse;
import org.example.orders.exceptions.ExceptionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InternalExceptionHandler {

    private final ExceptionFactory exceptionFactory;

    public InternalExceptionHandler(ExceptionFactory exceptionFactory) {
        this.exceptionFactory = exceptionFactory;
    }

    @ExceptionHandler(ModelException.class)
    public ResponseEntity<ErrorResponse> handleModelException(ModelException exception, HttpServletRequest request) {
        return new ResponseEntity<>(exceptionFactory.createErrorResponse(exception, request.getServletPath()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
