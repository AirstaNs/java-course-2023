package edu.module3.hw4.validator;

import edu.module3.hw4.Animal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompositeAnimalValidator {

    private final List<Validator> validators;

    public CompositeAnimalValidator(List<Validator> validators) {
        this.validators = validators;
    }

    public Set<ValidationError> validate(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        for (Validator validator : validators) {
            validator.validate(animal).ifPresent(errors::add);
        }
        return errors;
    }
}
