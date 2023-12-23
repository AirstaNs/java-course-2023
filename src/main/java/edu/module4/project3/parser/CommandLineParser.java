package edu.module4.project3.parser;

import edu.module4.project3.model.Format;
import edu.module4.project3.model.Argument;
import edu.module4.project3.model.Config;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("modifiedcontrolvariable")
public final class CommandLineParser {

    private CommandLineParser() {
    }

    public static Config parse(String[] args) {
        try {
            var configBuilder = new Config.ConfigBuilder();
            for (int i = 0; i < args.length; i++) {
                Argument arg = Argument.parse(args[i]);
                switch (arg) {
                    case PATH -> {
                        List<String> paths = new ArrayList<>();
                        while (i + 1 < args.length && !args[i + 1].startsWith(Argument.PREFIX)) {
                            paths.add(args[++i]);
                        }
                        configBuilder.setPaths(paths);
                    }

                    case FROM -> configBuilder.setFromDate(args[++i]);
                    case TO -> configBuilder.setToDate(args[++i]);
                    case FORMAT -> configBuilder.setFormat(Format.parse(args[++i]));
                    default -> {

                    }
                }
            }
            return configBuilder.build();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("каждому флагу нужен аргумент");
        }
    }
}
