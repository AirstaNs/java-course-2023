package edu.module3.hw4.validator;

import edu.module3.hw4.Animal;

import java.util.Optional;

public class SizeValidator implements Validator {

    private static final String WEIGHT = "weight";
    private static final String HEIGHT = "height";
    private static final String MESSAGE = "must be positive";

    @Override
    public Optional<ValidationError> validate(Animal animal) {
        if (animal.weight() <= 0 && animal.height() <= 0) {
            final String type = "%s & %s".formatted(WEIGHT, HEIGHT);
            return Optional.of(new ValidationError(MESSAGE, animal.type(), type));
        }
        if (animal.weight() <= 0) {
            return Optional.of(new ValidationError(MESSAGE, animal.type(), WEIGHT));
        }
        if (animal.height() <= 0) {
            return Optional.of(new ValidationError(MESSAGE, animal.type(), HEIGHT));
        }
        return Optional.empty();
    }
}
