package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final Random random;

    public DefaultConnectionManager(Random random) {
        this.random = random;
    }

    @Override
    public Connection getConnection() {
        if (random.nextBoolean()) {
            return new StableConnection();
        } else {
            return new FaultyConnection(random);
        }
    }
}
