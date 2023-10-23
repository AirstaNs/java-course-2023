package edu.hw4.validator;

import edu.hw4.Animal;
import java.util.Optional;

public class AgeValidator implements Validator {

    private static final String FIELD_NAME = "age";
    private static final String MUST_BE_POSITIVE = "age must be positive";
    private static final String BOUND_MESSAGE = "age must be less than 200";
    private static final int MAX_AGE = 200;

    @Override
    public Optional<ValidationError> validate(Animal animal) {
        if (animal.age() < 0) {
            return Optional.of(new ValidationError(MUST_BE_POSITIVE, animal.type(), FIELD_NAME));
        } else if (animal.age() > MAX_AGE) {
            return Optional.of(new ValidationError(BOUND_MESSAGE, animal.type(), FIELD_NAME));
        }
        return Optional.empty();
    }
}
