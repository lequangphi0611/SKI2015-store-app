package com.store.util;

import java.util.Random;

/**
 * RandomString
 */
public class RandomString {

    public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final Random random = new Random();

    private final int length;

    public RandomString() {
        length = random.nextInt();
    }

    public RandomString(int length) {
        this.length = length;
    }

    public String next() {
        StringBuilder buider = new StringBuilder();
        for(int i = 0; i < length ; i++) {
            buider.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        }
        return buider.toString();
    }
}