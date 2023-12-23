package edu.hw10.task1;

import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;

public final class ClassFactory {
    private final String stringValue;
    private final int intValue;

    private ClassFactory(
        String stringValue, int intValue
    ) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }

    public static ClassFactory create(
        @NotNull String stringValue, @Min(10) int intValue
    ) {
        return new ClassFactory(stringValue, intValue);
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getIntValue() {
        return intValue;
    }
}
