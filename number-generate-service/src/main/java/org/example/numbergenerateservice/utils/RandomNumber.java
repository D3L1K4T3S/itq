package org.example.numbergenerateservice.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumber {

    private static final Integer maxValues = 90000;
    private static final Integer minValues = 10000;

    public String generateRandomNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(maxValues) + minValues);
    }
}
