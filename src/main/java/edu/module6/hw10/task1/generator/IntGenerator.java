package edu.module6.hw10.task1.generator;

import edu.module6.hw10.task1.annotation.Max;
import edu.module6.hw10.task1.annotation.Min;

import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("checkstyle:InnerAssignment")
public class IntGenerator extends Generator {
    @Override
    public Object generate(Parameter parameter) {
        if (!(parameter.getType().equals(int.class) || parameter.getType().equals(Integer.class))) {
            return generateNext(parameter);
        }

        var minValue = Integer.MIN_VALUE;
        var maxValue = Integer.MAX_VALUE;
        for (var annotation : parameter.getAnnotations()) {
            switch (annotation) {
                case Min m -> minValue = m.value();
                case Max m -> maxValue = m.value();
                default -> {

                }
            }
        }

        return ThreadLocalRandom.current().nextInt(minValue, maxValue);
    }
}
