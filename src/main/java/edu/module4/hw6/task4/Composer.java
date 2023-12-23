package edu.module4.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Composer {

    private Composer() {
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static void write(Path path) {
        final String message = "Programming is learned by writing programs. ― Brian Kernighan";
        final Checksum checksum = new Adler32();
        final Charset charset = StandardCharsets.UTF_8;

        try (var os = Files.newOutputStream(path);
             var checkedos = new CheckedOutputStream(os, checksum);
             var buffos = new BufferedOutputStream(checkedos);
             var writer = new OutputStreamWriter(buffos, charset);
             PrintWriter printWriter = new PrintWriter(writer)) {

            printWriter.println(message);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
