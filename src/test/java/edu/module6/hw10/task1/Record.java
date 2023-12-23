package edu.module6.hw10.task1;

import edu.module6.hw10.task1.annotation.Min;
import edu.module6.hw10.task1.annotation.NotNull;

public record Record(@NotNull String stringValue, @Min(10) int intValue) {}
