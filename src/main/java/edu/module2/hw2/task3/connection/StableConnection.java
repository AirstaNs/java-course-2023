package edu.module2.hw2.task3.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info(String.format("Executing command in StableConnection: %s%n", command));
    }

    @Override
    public void close() {
        LOGGER.info("Stable connection closed");
    }
}
