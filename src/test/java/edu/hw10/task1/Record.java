package edu.hw10.task1;

import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;

public record Record(@NotNull String stringValue, @Min("10") int intValue) {}
