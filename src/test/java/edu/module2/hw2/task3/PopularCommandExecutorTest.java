package edu.module2.hw2.task3;

import edu.module2.hw2.task3.PopularCommandExecutor;
import edu.module2.hw2.task3.connection.ConnectionException;
import edu.module2.hw2.task3.connection.StableConnection;
import edu.module2.hw2.task3.manager.ConnectionManager;
import edu.module2.hw2.task3.manager.DefaultConnectionManager;
import edu.module2.hw2.task3.manager.FaultyConnectionManager;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PopularCommandExecutorTest {

    @Test
    @DisplayName("Команда должна успешно выполняться с помощью StableConnection")
    public void successfulExecutionWithStableConnection() {
        // Given
        ConnectionManager stableManager = StableConnection::new;
        PopularCommandExecutor executor = new PopularCommandExecutor(stableManager, 3);

        // When & Then
        assertDoesNotThrow(executor::updatePackages);
    }

    @Test
    @DisplayName("Команда может выбросить исключение с FaultyConnection, но выполниться в пределах maxAttempts")
    public void mayThrowWithFaultyConnectionButSucceed() {
        // Given
        Random seededRandom = new Random(1234567890L);
        ConnectionManager defaultManager = new DefaultConnectionManager(seededRandom);
        PopularCommandExecutor executor = new PopularCommandExecutor(defaultManager, 3);

        // When & Then
        assertDoesNotThrow(executor::updatePackages);
    }

    @Test
    @DisplayName("Команда выбрасывает ConnectionException после превышения maxAttempts с FaultyConnection")
    public void throwsAfterExceedingMaxAttempts() {
        // Given
        Random seededRandom = new Random(4L);
        ConnectionManager faultyManager = new FaultyConnectionManager(seededRandom);
        PopularCommandExecutor executor = new PopularCommandExecutor(faultyManager, 3);

        // When
        Exception exception = assertThrows(ConnectionException.class, executor::updatePackages);

        // Then
        assertTrue(exception instanceof ConnectionException);
    }
}
