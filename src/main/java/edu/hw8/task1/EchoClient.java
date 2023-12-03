package edu.hw8.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EchoClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 3333;
    final String MESSAGE = """
        request: %s
        respone: %s
        ------
        """;

    public static void main(String[] args) {
        List<String> entries = new ArrayList<>(Repository.getQuotes().keySet()
            .stream()
            .toList());
        List<String> entries1 = Repository.getQuotes().keySet()
            .stream()
            .toList();
        List<String> entries2 = Repository.getQuotes().keySet()
            .stream()
            .toList();
        entries.addAll(entries1);
        entries.addAll(entries2);
        new EchoClient().start(entries);
    }

    public void start(List<String> words) {

        var futures = words.stream()
            .map(word -> CompletableFuture.supplyAsync(() -> sendNewConnection(word))
                                          .thenAccept(str -> System.out.printf((MESSAGE), word, str)))
            .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private String sendNewConnection(String quote) {
        try (Socket socket = new Socket(
            SERVER_ADDRESS,
            SERVER_PORT
        ); DataInputStream input = new DataInputStream(socket.getInputStream()); DataOutputStream output = new DataOutputStream(
            socket.getOutputStream())) {
            output.writeUTF(quote);
            this.waitResponse(input);
            return input.readUTF();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void waitResponse(DataInputStream input) throws IOException {
        while (input.available() == 0) {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
