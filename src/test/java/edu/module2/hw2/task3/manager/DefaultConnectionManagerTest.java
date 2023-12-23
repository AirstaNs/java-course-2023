package edu.module2.hw2.task3.manager;

import edu.module2.hw2.task3.connection.Connection;
import edu.module2.hw2.task3.connection.FaultyConnection;
import edu.module2.hw2.task3.connection.StableConnection;
import java.util.Random;

import edu.module2.hw2.task3.manager.DefaultConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultConnectionManagerTest {

    @Test
    @DisplayName("DefaultConnectionManager должен возвращает StableConnection")
    public void returnsStableConnection() {
        // Given
        Random seededRandom = new Random(1234567890L);
        DefaultConnectionManager manager = new DefaultConnectionManager(seededRandom);

        // When
        Connection connection = manager.getConnection();

        // Then
        assertTrue(connection instanceof StableConnection);
    }

    @Test
    @DisplayName("DefaultConnectionManager должен возвращает FaultyConnection")
    public void returnsFaultyConnection() {
        // Given
        Random seededRandom = new Random(555155L);
        DefaultConnectionManager manager = new DefaultConnectionManager(seededRandom);

        // When
        Connection connection = manager.getConnection();

        // Then
        assertTrue(connection instanceof FaultyConnection);
    }
}
