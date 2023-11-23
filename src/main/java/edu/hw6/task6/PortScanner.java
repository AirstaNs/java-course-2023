package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Map;

@SuppressWarnings({"magicnumber", "regexpsinglelinejava"})
public class PortScanner {

    private static final Map<Integer, String> PORTS;

    static {
        PORTS = Map.of(
            80, "HTTP",
            25, "SMTP",
            21, "FTP",
            22, "SSH",
            443, "HTTPS",
            53, "DNS"
        );
    }

    public void scanPorts() {
        System.out.printf("%-10s %-10s %-10s %s%n", "Protocol", "Port", "Status", "Service");
        PORTS.forEach((key, value) -> {
            PortInfo tcpPort = scanPort(key, PortInfo.Protocol.TCP);
            PortInfo udpPort = scanPort(key, PortInfo.Protocol.UDP);
            printPortInfo(tcpPort);
            printPortInfo(udpPort);
        });
    }

    public PortInfo scanPort(int port, PortInfo.Protocol protocol) {
        if (protocol == edu.hw6.task6.PortInfo.Protocol.TCP) {
            return scanTCPPort(port);
        } else {
            return scanUDPPort(port);
        }
    }

    private PortInfo scanTCPPort(int port) {
        var protocol = PortInfo.Protocol.TCP;

        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress("localhost", port));
            return createPortInfo(protocol, port, PortInfo.Status.CLOSED);
        } catch (IOException e) {
            return createPortInfo(protocol, port, PortInfo.Status.OPEN);
        }
    }

    private PortInfo scanUDPPort(int port) {
        PortInfo.Protocol protocol = PortInfo.Protocol.UDP;
        try (DatagramSocket udpSocket = new DatagramSocket(port)) {
            return createPortInfo(protocol, port, PortInfo.Status.OPEN);
        } catch (SocketException e) {
            return createPortInfo(protocol, port, PortInfo.Status.CLOSED);
        }
    }

    private static void printPortInfo(PortInfo port) {
        System.out.printf("%-10s %-10d %-10s %s%n", port.protocol(), port.port(), port.status(), port.service());
    }

    private PortInfo createPortInfo(PortInfo.Protocol protocol, int port, PortInfo.Status status) {
        String service = PORTS.getOrDefault(port, "Unknown Service");
        return new PortInfo(port, protocol, status, PORTS.getOrDefault(port, service));
    }
}
