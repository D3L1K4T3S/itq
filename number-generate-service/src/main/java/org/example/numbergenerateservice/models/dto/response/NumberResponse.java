package org.example.numbergenerateservice.models.dto.response;

public class NumberResponse {
    private String number;

    public NumberResponse(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    void setNumber(String number) {
        this.number = number;
    }
}
