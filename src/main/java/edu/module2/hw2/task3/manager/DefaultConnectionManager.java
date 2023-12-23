package edu.module2.hw2.task3.manager;

import edu.module2.hw2.task3.connection.Connection;
import edu.module2.hw2.task3.connection.FaultyConnection;
import edu.module2.hw2.task3.connection.StableConnection;

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
