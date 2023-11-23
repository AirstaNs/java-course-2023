package edu.project3.parser;

import edu.project3.model.HttpMethod;
import edu.project3.model.NginxData;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("magicnumber")
public final class LogParser {

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
    private static final String LOG_PATTERN =
        "^(\\S+) (\\S+) (\\S+) \\[([^\\]]+)\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+) \"(\\S+)\".*";

    private LogParser() {
    }


    public static NginxData parseLine(String line) {
        Pattern pattern = Pattern.compile(LOG_PATTERN);
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Неверный формат строки");
        }

        String ipAddress = matcher.group(1);
        OffsetDateTime dateTime = OffsetDateTime.parse(matcher.group(4), FORMATTER);
        HttpMethod method = HttpMethod.parse(matcher.group(5));
        String request = matcher.group(6);
        String httpVersion = matcher.group(7);
        int statusCode = Integer.parseInt(matcher.group(8));
        int bytes = Integer.parseInt(matcher.group(9));

        return new NginxData(
            ipAddress, dateTime,
            method, request,
            httpVersion, statusCode, bytes);
    }
}
