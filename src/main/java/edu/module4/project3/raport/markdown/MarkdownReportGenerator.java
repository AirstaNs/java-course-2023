package edu.module4.project3.raport.markdown;

import edu.module4.project3.model.Config;
import edu.module4.project3.raport.BaseReportGenerator;
import edu.module4.project3.statistics.LogStatistics;
import java.util.List;

public class MarkdownReportGenerator extends BaseReportGenerator {
    private static final String NEW_LINE = System.lineSeparator();
    private final MarkdownTableFormatter tableFormatter = new MarkdownTableFormatter();

    @Override
    public String generateReport(Config config, LogStatistics statistics) {
        StringBuilder report = new StringBuilder();
        report
            .append(formatSectionHeader("Общая информация"))
            .append(formatGeneralInfo(config, statistics))
            .append(NEW_LINE)
            .append(formatSectionHeader("Запрашиваемые ресурсы"))
            .append(formatRequestedResources(statistics))
            .append(NEW_LINE)
            .append(formatSectionHeader("Коды ответа"))
            .append(formatResponseCodes(statistics))
            .append(NEW_LINE)
            .append(formatSectionHeader("Процент ошибочных ответов 400-600"))
            .append(formatErrorRate(statistics))
            .append(NEW_LINE)
            .append(formatSectionHeader("Наиболее частые источники запросов"))
            .append(formatMostFrequentRequestSources(statistics));
        return report.toString();
    }

    protected String formatSectionHeader(String header) {
        return "#### " + header + NEW_LINE + NEW_LINE;
    }

    @Override
    protected String createTable(List<String> headers, List<List<String>> rows) {
        return tableFormatter.createTable(headers, rows);
    }
}
