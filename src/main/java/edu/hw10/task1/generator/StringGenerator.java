package edu.hw10.task1.generator;

import edu.hw10.task1.annotation.NotNull;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator extends Generator {
    private static final int LENGTH = 10;
    private static final char[] CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    @Override
    public Object generate(Parameter parameter) {
        if (!parameter.getType().equals(String.class)) {
            return generateNext(parameter);
        }

        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(CHARS.length);
            randomString.append(CHARS[randomIndex]);
        }

        for (var annotation : parameter.getAnnotations()) {
            if (annotation instanceof NotNull) {
                return randomString.toString();
            }
        }

        return ThreadLocalRandom.current().nextBoolean() ? randomString.toString() : null;
    }
}
