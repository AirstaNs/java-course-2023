package edu.hw7.task1;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class MultiCoreProcessorTest {

    @RepeatedTest(5)
    void testOperationsCompletionRepeated() {
        int totalOperations = 7776541;
        MultiCoreProcessor processor = new MultiCoreProcessor(totalOperations);
        processor.executeOperations();

        assertEquals(totalOperations, processor.getCompletedOperations());
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000, 5000, 10000, 10000000})
    void testOperationsCompletionWithDifferentCounts(int totalOperations) {
        // Given
        MultiCoreProcessor processor = new MultiCoreProcessor(totalOperations);

        // When
        processor.executeOperations();

        // Then
        assertEquals(totalOperations, processor.getCompletedOperations());
    }
}
