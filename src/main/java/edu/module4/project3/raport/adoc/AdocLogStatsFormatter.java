package edu.module4.project3.raport.adoc;

import edu.module4.project3.model.Config;
import edu.module4.project3.raport.BaseReportGenerator;
import edu.module4.project3.statistics.LogStatistics;
import java.util.List;

public class AdocLogStatsFormatter extends BaseReportGenerator {
    private static final String NEW_LINE = System.lineSeparator();
    private final AdocTableFormatter tableFormatter = new AdocTableFormatter();

    @Override
    public String generateReport(Config config, LogStatistics statistics) {
        StringBuilder report = new StringBuilder();
        report.append(formatSectionHeader("Общая информация"))
              .append(formatGeneralInfo(config, statistics))
              .append(NEW_LINE)
              .append(formatSectionHeader("Запрашиваемые ресурсы"))
              .append(formatRequestedResources(statistics))
              .append(NEW_LINE)
              .append(formatSectionHeader("Коды ответа"))
              .append(formatResponseCodes(statistics));
        return report.toString();
    }

    protected String formatSectionHeader(String header) {
        return "==== " + header + NEW_LINE;
    }

    @Override
    protected String createTable(List<String> headers, List<List<String>> rows) {
        return  tableFormatter.createTable(headers, rows);
    }
}
