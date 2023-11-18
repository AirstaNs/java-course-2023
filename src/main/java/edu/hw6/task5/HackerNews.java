package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HackerNews {

    private static final URI TOP_NEWS = URI.create("https://hacker-news.firebaseio.com/v0/topstories.json");
    private static final String NEWS = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    private static final Pattern TITLE_PATTERN = Pattern.compile(".*\"title\":\"([^\"]*)\".*");
    private static final Logger LOGGER = LogManager.getLogger();
    private final HttpClient client;

    public HackerNews() {
        this.client = HttpClient.newHttpClient();
    }

    public String getNewsTitle(long newsId) {
        if (newsId <= 0) {
            throw new IllegalArgumentException("Неверный идентификатор");
        }
        URI uri = URI.create(NEWS.formatted(newsId));
        var request = HttpRequest.newBuilder(uri).timeout(TIMEOUT).GET().build();
        var response = this.send(request);
        return response
                .map(this::jsonToTitle)
                .orElse("");
    }

    public long[] hackerNewsTopStories() {
        var request = HttpRequest.newBuilder(TOP_NEWS).timeout(TIMEOUT).GET().build();
        Optional<String> response = this.send(request);
        return response
                .map(this::jsonToArray)
                .orElse(new long[0]);
    }

    private Optional<String> send(HttpRequest request) {
        final var handler = HttpResponse.BodyHandlers.ofString();
        try {
            var response = client.send(request, handler);
            return Optional.ofNullable(response.body());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    private long[] jsonToArray(String json) {
        final String delimiter = ",";
        int end = json.length() - 1;
        String[] stories = json.substring(1, end).split(delimiter);
        return Arrays.stream(stories).mapToLong(Long::parseLong).toArray();
    }

    private String jsonToTitle(String json) {
        Matcher m = TITLE_PATTERN.matcher(json);
        return m.find() ? m.group(1) : "";
    }
}
