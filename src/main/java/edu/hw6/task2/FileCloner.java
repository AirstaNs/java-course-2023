package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FileCloner {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final char DELIMITER = '.';

    private FileCloner() {}

    public static void cloneFile(Path path) {
        try {
            Path uniqueName = getUniquePath(path);
            Files.copy(path, path.getParent().resolve(uniqueName), StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static String getNameWithoutExtension(String name) {
        int dotIndex = name.lastIndexOf(DELIMITER);
        if (dotIndex != -1) {
            return name.substring(0, dotIndex);
        }
        return name;
    }

    private static String getExtension(String str) {
        String name = str.strip();
        int dotIndex = name.lastIndexOf(DELIMITER);
        if (dotIndex != -1) {
            return name.substring(dotIndex);
        }
        return "";
    }

    private static Path getUniquePath(Path path) {
        var originalName = path.getFileName().toString();
        Path targetPath = path.getParent().resolve(originalName);

        int id = 0;
        while (Files.exists(targetPath)) {
            String name = getNameWithoutExtension(originalName);
            String extension = getExtension(originalName);

            String newName;
            if (id == 0 || id == 1) {
                newName = String.format("%s — копия%s", name, extension);
            } else {
                newName = String.format("%s — копия (%d)%s", name, id, extension);
            }
            targetPath = path.getParent().resolve(newName);
            id++;
        }
        return targetPath;
    }
}
