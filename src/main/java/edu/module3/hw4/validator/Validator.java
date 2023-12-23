package edu.module3.hw4.validator;

import edu.module3.hw4.Animal;
import java.util.Optional;

public interface Validator {
    Optional<ValidationError> validate(Animal animal);
}
