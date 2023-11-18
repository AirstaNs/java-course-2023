package edu.hw6.task6;

public record PortInfo(int port, Protocol protocol, Status status, String service) {

    public enum Status {
        CLOSED, OPEN
    }

    public enum Protocol {
        TCP, UDP
    }
}
