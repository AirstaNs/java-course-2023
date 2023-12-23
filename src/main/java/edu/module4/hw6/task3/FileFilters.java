package edu.module4.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FileFilters {

    private FileFilters() {
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static AbstractFilter largerThan(long size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                LOGGER.error(e);
                return false;
            }
        };
    }

    public static AbstractFilter globMatches(String glob) {
        final String pattern = "glob:" + glob;
        return path ->
            path.getParent()
                .getFileSystem()
                .getPathMatcher(pattern)
                .matches(path.getFileName());
    }

    public static AbstractFilter regexContains(String regex) {
        final Pattern pattern = Pattern.compile(regex);
        return path -> pattern.matcher(path.toString()).find();
    }

    public static AbstractFilter magicNumber(int... magicNumbers) {
        return (path) -> {
            try (var is = Files.newInputStream(path)) {
                for (int magicNumber : magicNumbers) {
                    if (is.read() != magicNumber) {
                        return false;
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                return false;
            }
            return true;
        };
    }
}
