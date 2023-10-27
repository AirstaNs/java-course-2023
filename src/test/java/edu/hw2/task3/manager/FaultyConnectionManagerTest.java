package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FaultyConnectionManagerTest {

    @Test
    @DisplayName("FaultyConnectionManager должен всегда возвращать FaultyConnection")
    public void alwaysReturnsFaultyConnection() {
        // Given
        Random anyRandom = new Random();
        FaultyConnectionManager manager = new FaultyConnectionManager(anyRandom);

        // When
        Connection connection = manager.getConnection();

        // Then
        assertTrue(connection instanceof FaultyConnection);
    }
}
