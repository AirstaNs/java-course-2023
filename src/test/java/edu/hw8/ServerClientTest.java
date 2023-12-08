package edu.hw8;

import edu.hw8.task1.EchoClient;
import edu.hw8.task1.Repository;
import edu.hw8.task1.Server;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServerClientTest {
    @Test
    public void testServerClientConnections() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Thread serverThread = new Thread(this::startServer);
        serverThread.start();

        Thread.sleep(3000);

        List<String> entries = new ArrayList<>(Repository.getQuotes().keySet().stream().toList());
        List<String> entries1 = Repository.getQuotes().keySet().stream().toList();
        List<String> entries2 = Repository.getQuotes().keySet().stream().toList();
        entries.addAll(entries1);
        entries.addAll(entries2);

        try (ExecutorService clientExecutor = Executors.newCachedThreadPool()) {
            EchoClient client = new EchoClient();
            clientExecutor.execute(() -> client.start(entries));

            clientExecutor.shutdown();
            clientExecutor.awaitTermination(2, TimeUnit.MINUTES);
            serverThread.interrupt();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(originalOut);
            Assertions.assertFalse(outContent.toString().isEmpty());
        }
    }

    private void startServer() {
        try {
            new Server().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
