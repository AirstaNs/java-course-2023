package edu.module2.hw2.task3.manager;

import edu.module2.hw2.task3.connection.Connection;
import edu.module2.hw2.task3.connection.FaultyConnection;

import java.util.Random;

public class FaultyConnectionManager implements ConnectionManager {

    private final Random random;

    public FaultyConnectionManager(Random random) {
        this.random = random;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(random);
    }
}
