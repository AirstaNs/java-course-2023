package edu.module2.hw2.task3.connection;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "Failed to execute command in FaultyConnection";
    private final Random random;

    public FaultyConnection(Random random) {
        this.random = random;
    }

    @Override
    public void execute(String command) throws ConnectionException {
        if (random.nextBoolean()) {
            throw new ConnectionException(ERROR_MESSAGE, new RuntimeException());
        }
        LOGGER.info(String.format("Executing command: %s%n", command));
    }

    @Override
    public void close() {
        LOGGER.info(("Faulty connection closed"));
    }
}
