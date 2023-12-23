package edu.module2.hw2.task3.connection;

import java.util.Random;

import edu.module2.hw2.task3.connection.ConnectionException;
import edu.module2.hw2.task3.connection.FaultyConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FaultyConnectionTest {

    @Test
    @DisplayName("FaultyConnection должен иногда выбрасывать исключение")
    public void sometimesThrowsException() {
        // Given
        Random seededRandom = new Random(1234567890L);
        FaultyConnection connection = new FaultyConnection(seededRandom);

        // When & Then
        assertThrows(ConnectionException.class, () -> connection.execute("testCommand"));
    }

    @Test
    @DisplayName("FaultyConnection должен иногда успешно выполнять команду")
    public void sometimesExecutesSuccessfully() {
        // Given
        Random seededRandom = new Random(555155L);
        FaultyConnection connection = new FaultyConnection(seededRandom);

        // When & Then
        assertDoesNotThrow(() -> connection.execute("testCommand"));
    }

    @Test
    @DisplayName("FaultyConnection должен закрываться без ошибок")
    public void closesWithoutErrors() {
        // Given
        Random seededRandom = new Random(555155L);
        FaultyConnection connection = new FaultyConnection(seededRandom);

        // When & Then
        assertDoesNotThrow(connection::close);
    }
}
