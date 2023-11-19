package edu.project3.raport;

import edu.project3.model.Config;
import edu.project3.statistics.LogStatistics;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public interface ReportGenerator {
    String generateReport(Config config, LogStatistics statistics);

    default String formatDate(LocalDate dateTime) {
        if (dateTime == null) {
            return "--";
        }
        return dateTime.toString();
    }

    default List<String> getFilesName(List<String> paths) {
        return paths.stream().map(path -> {
            int lastIndex = path.lastIndexOf('/');
            return lastIndex != -1 ? path.substring(lastIndex) : path;
        }).collect(Collectors.toList());
    }
}
