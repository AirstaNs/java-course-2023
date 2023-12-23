package edu.module2.hw2.task3.connection;

import edu.module2.hw2.task3.connection.StableConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class StableConnectionTest {
    @Test
    @DisplayName("StableConnection должен всегда успешно выполнять команду")
    public void alwaysExecutesSuccessfully() {
        // Given
        StableConnection connection = new StableConnection();

        // When & Then
        assertDoesNotThrow(() -> connection.execute("testCommand"));
    }

    @Test
    @DisplayName("StableConnection должен закрываться без ошибок")
    public void closesWithoutErrors() {
        // Given
        StableConnection connection = new StableConnection();

        // When & Then
        assertDoesNotThrow(connection::close);
    }
}
