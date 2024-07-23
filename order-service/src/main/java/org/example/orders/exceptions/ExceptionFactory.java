package org.example.orders.exceptions;

import org.springframework.stereotype.Component;
import org.example.orders.models.dto.response.ErrorResponse;

@Component
public class ExceptionFactory {

    public ErrorResponse createErrorResponse(ModelException exception, String apiPath) {
        return ErrorResponse
                .builder()
                .message(exception.getMessage())
                .path(apiPath)
                .build();
    }
}
