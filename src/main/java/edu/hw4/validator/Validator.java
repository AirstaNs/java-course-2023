package edu.hw4.validator;

import edu.hw4.Animal;
import java.util.Optional;

public interface Validator {
    Optional<ValidationError> validate(Animal animal);
}
