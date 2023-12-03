package edu.hw8.task1;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        for (int i = 0; i < 13; i++) {
            try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                 DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream())
            ) {

                output.writeUTF("aaaaaaaaaaaaaaaaaaaaaaaaa"); // send a message to the server
//                String receivedMsg = input.readUTF(); // read the reply from the server

//                System.out.println("Received from the server: " + receivedMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
