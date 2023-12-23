package edu.module3.hw4.validator;

import edu.module3.hw4.Animal;

public class ValidationError {
    private final String message;

    private final Animal.Type animalType;

    private final String fieldName;

    public ValidationError(String message, Animal.Type animalType, String fieldName) {
        this.message = message;
        this.animalType = animalType;
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public Animal.Type getAnimalType() {
        return animalType;
    }

    public String getFieldName() {
        return fieldName;
    }
}
