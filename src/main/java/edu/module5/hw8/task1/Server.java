package edu.module5.hw8.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@SuppressWarnings("regexpsinglelinejava") public class Server {
    private static final int PORT = 3333;
    private static final int MAX_CONNECTIONS = 4;
    private static final ExecutorService POOL = Executors.newFixedThreadPool(MAX_CONNECTIONS);

    private final Semaphore semaphore = new Semaphore(MAX_CONNECTIONS, true);

    public void start() throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started. Listening on port: " + PORT);
            while (!Thread.currentThread().isInterrupted()) {
                this.process(server);
            }
        }
    }

    private void process(ServerSocket server) throws IOException {
        try {
            semaphore.acquire();
            POOL.execute(new ClientHandler(server.accept()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

    private static class ClientHandler implements Runnable {
        private static final int PAYLOAD = 400;
        private final Socket clientSocket;

        ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override public void run() {
            try (DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                 DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())
            ) {
                String keyword = input.readUTF();
                String quote = Repository.findQuote(keyword);
                Thread.sleep(PAYLOAD);
                output.writeUTF(quote);
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
