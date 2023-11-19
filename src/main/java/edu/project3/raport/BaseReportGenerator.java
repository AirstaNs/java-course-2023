package edu.project3.raport;

import edu.project3.model.Config;
import edu.project3.model.Format;
import edu.project3.model.HttpStatusCode;
import edu.project3.raport.adoc.AdocLogStatsFormatter;
import edu.project3.raport.markdown.MarkdownReportGenerator;
import edu.project3.statistics.LogStatistics;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseReportGenerator implements ReportGenerator {
    protected abstract String formatSectionHeader(String header);

    protected String formatGeneralInfo(Config config, LogStatistics statistics) {
        List<String> headers = Arrays.asList("Метрика", "Значение");
        List<List<String>> rows = Arrays.asList(
            Arrays.asList("Файл(-ы)", String.join(", ", getFilesName(config.getPaths()))),
            Arrays.asList("Начальная дата", formatDate(config.getFromDate())),
            Arrays.asList("Конечная дата", formatDate(config.getToDate())),
            Arrays.asList("Количество запросов", String.valueOf(statistics.getTotalRequestCount())),
            Arrays.asList("Средний размер ответа", statistics.getAverageResponseSize() + "b")
        );
        return createTable(headers, rows);
    }

    protected String formatRequestedResources(LogStatistics statistics) {
        List<String> headers = Arrays.asList("Ресурс", "Кол-во");
        List<List<String>> rows = statistics.getMostRequestedResources().entrySet().stream()
            .map(entry -> Arrays.asList(entry.getKey(), String.valueOf(entry.getValue())))
            .collect(Collectors.toList());
        return createTable(headers, rows);
    }

    protected String formatResponseCodes(LogStatistics statistics) {
        List<String> headers = Arrays.asList("Код", "Имя", "Количество");
        List<List<String>> rows = statistics.getResponseCodeFrequency().entrySet().stream()
            .map(entry -> Arrays.asList(String.valueOf(entry.getKey()),
                HttpStatusCode.parse(entry.getKey()).toString(), String.valueOf(entry.getValue())))
            .collect(Collectors.toList());
        return createTable(headers, rows);
    }

    protected String formatErrorRate(LogStatistics statistics) {
        double errorRate = statistics.getErrorRate();
        String header = "Процент ошибочных ответов";
        List<List<String>> rows = Collections.singletonList(
            Arrays.asList(String.format("%.2f%%", errorRate))
        );
        return createTable(Collections.singletonList(header), rows);
    }

    protected String formatMostFrequentRequestSources(LogStatistics statistics) {
        List<String> headers = Arrays.asList("IP-адрес", "Кол-во запросов");
        List<List<String>> rows = statistics.getMostFrequentRequestSources().entrySet().stream()
            .map(entry -> Arrays.asList(entry.getKey(), String.valueOf(entry.getValue())))
            .collect(Collectors.toList());
        return createTable(headers, rows);
    }

    protected abstract String createTable(List<String> headers, List<List<String>> rows);

    public static BaseReportGenerator selectFormat(Format format) {
        if (format == Format.MARKDOWN) {
            return new MarkdownReportGenerator();
        } else {
            return new AdocLogStatsFormatter();
        }
    }
}
