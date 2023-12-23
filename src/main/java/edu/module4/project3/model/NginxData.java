package edu.module4.project3.model;

import java.time.OffsetDateTime;

public record NginxData(
    String ipAddress, OffsetDateTime dateTime,
    HttpMethod method, String requestUri,
    String httpVersion, int statusCode,
    int bytes) {}
