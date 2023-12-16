package edu.hw10.task1.generator;

import edu.hw10.task1.annotation.NotNull;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator extends Generator {

    @Override
    public Object generate(Parameter parameter) {
        if (!parameter.getType().equals(String.class)) {
            return generateNext(parameter);
        }

        for (var annotation : parameter.getAnnotations()) {
            if (annotation instanceof NotNull) {
                return "str";
            }
        }

        return ThreadLocalRandom.current().nextBoolean() ? "rnd" : null;
    }
}
