package edu.module4.project3.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Config {
    private final List<String> paths;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final Format format;

    private Config(List<String> paths, LocalDate fromDate, LocalDate toDate, Format format) {
        this.paths = paths;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.format = format;
    }

    public List<String> getPaths() {
        return paths;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Format getFormat() {
        return format;
    }

    public static class ConfigBuilder {
        private List<String> paths;
        private String fromDate;
        private String toDate;
        private Format format;

        public ConfigBuilder setPaths(List<String> paths) {
            this.paths = paths;
            return this;
        }

        public ConfigBuilder setFromDate(String fromDate) throws DateTimeParseException {
            this.fromDate = fromDate;
            return this;
        }

        public ConfigBuilder setToDate(String toDate) throws DateTimeParseException {
            this.toDate = toDate;
            return this;
        }

        public ConfigBuilder setFormat(Format format) {
            this.format = format;
            return this;
        }

        public Config build() {
            this.validate();
            LocalDate fromOffsetDate = null;
            LocalDate toOffsetDate = null;

            if (fromDate != null && !fromDate.isEmpty()) {
                fromOffsetDate = LocalDate.parse(fromDate);
            }
            if (toDate != null && !toDate.isEmpty()) {
                toOffsetDate = LocalDate.parse(toDate);
            }

            // Проверка правильности диапазона дат
            if (fromOffsetDate != null && toOffsetDate != null && fromOffsetDate.isAfter(toOffsetDate)) {
                throw new IllegalStateException("Дата начала не может быть после даты окончания");
            }
            return new Config(paths, fromOffsetDate, toOffsetDate, format);
        }

        private void validate() {
            if (paths == null || paths.isEmpty()) {
                throw new IllegalStateException("Путь к лог-файлу не может быть пустым");
            }

            if (format == null) {
                format = Format.MARKDOWN;
            }
        }
    }
}
