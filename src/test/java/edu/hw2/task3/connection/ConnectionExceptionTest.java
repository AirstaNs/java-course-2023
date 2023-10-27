package edu.hw2.task3.connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectionExceptionTest {

    @Test
    @DisplayName("Исключение должно сохранить предоставленное сообщение")
    public void retainsMessage() {
        // Given
        String message = "Test Message";
        // When
        ConnectionException exception = new ConnectionException(message, null);
        // Then
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Исключение должно сохранить cause")
    public void retainsCause() {
        // Given
        Throwable cause = new RuntimeException("Original exception");
        // When
        ConnectionException exception = new ConnectionException("", cause);
        // Then
        assertEquals(cause, exception.getCause());
    }
}
