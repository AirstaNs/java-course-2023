package edu.hw6.task1;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private static final String DELIMITER = ":";
    private static final Logger LOGGER = LogManager.getLogger();
    private final Path path;
    private final Charset charset;
    private final Map<String, String> map;

    public DiskMap(Path path, Charset charset) {
        Objects.requireNonNull(path);
        Objects.requireNonNull(charset);
        this.path = path;
        this.charset = charset;
        this.map = new HashMap<>();
        this.read();
    }

    private void read() {
        try (Stream<String> lines = Files.lines(path, charset)) {
            lines.forEach(line -> {
                String[] split = line.split(DELIMITER);
                if (split.length >= 2) {
                    map.put(split[0], split[1]);
                }
            });
        } catch (IOException e) {
            String message = "Failed to read from disk: ";
            LOGGER.error(message + e.getMessage());
            throw new RuntimeException(message, e);
        }
    }

    public void save() {
        try (var writer = new PrintWriter(Files.newBufferedWriter(path, charset))) {
            map.forEach((key, value) -> writer.println(key + DELIMITER + value));
        } catch (IOException e) {
            String message = "Failed to save to disk: ";
            LOGGER.error(message + e.getMessage());
            throw new RuntimeException(message, e);
        }
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return map.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
