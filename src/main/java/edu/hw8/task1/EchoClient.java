package edu.hw8.task1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("regexpsinglelinejava")
public class EchoClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 3333;
    private static final String MESSAGE = """
        request: %s
        respone: %s
        ------
        """;

    public void start(List<String> words) {
        var futures = words.stream()
            .map(word -> CompletableFuture.supplyAsync(() -> sendNewConnection(word))
                                          .thenAccept(str -> System.out.printf((MESSAGE), word, str)))
            .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private String sendNewConnection(String quote) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()))) {
            output.writeUTF(quote);
            return input.readUTF();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
