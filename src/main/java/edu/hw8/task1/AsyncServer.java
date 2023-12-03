package edu.hw8.task1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncServer {
    private static final int PORT = 8080;
    private static final int MAX_CONNECTIONS = 6;
    private static final ExecutorService pool = Executors.newFixedThreadPool(MAX_CONNECTIONS);

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                pool.submit(new ClientHandler(server.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private static final AtomicInteger a = new AtomicInteger(0);

        private final Socket clientSocket;

        ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {
                String keyword = input.readUTF();
                String quote = "кузля";//findQuote(keyword); // Реализуйте этот метод для поиска цитаты
                int i = a.incrementAndGet();
                if(i%2==0) {
                    Thread.sleep(4000L);
                }
                System.out.println(quote+i);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
