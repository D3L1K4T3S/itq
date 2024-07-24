package org.example.orders.models.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorResponse {
    
    private String message;
    private String path;


    public ErrorResponse() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String message;
        private String path;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            ErrorResponse response = new ErrorResponse();
            response.message = this.message;
            response.path = this.path;
            return response;
        }
    }
}
