package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;

public class ClassMultipleConstructors {
    private final String stringValue;
    private final int intValue;

    public ClassMultipleConstructors(@Max("10") int intValue) {
        this("", intValue);
    }

    public ClassMultipleConstructors(@NotNull String stringValue, @Min("10") int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getIntValue() {
        return intValue;
    }
}
