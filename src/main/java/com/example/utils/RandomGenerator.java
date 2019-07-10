package com.example.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class RandomGenerator {

    public static String getRandomString() {
        return UUID.randomUUID().toString();
    }

}
