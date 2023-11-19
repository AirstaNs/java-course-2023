package edu.project3.statistics;

import edu.project3.model.Config;
import edu.project3.model.DataLog;
import edu.project3.model.NginxData;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("magicnumber")
public class LogStatistics {
    private final List<NginxData> aggregatedData;

    public LogStatistics(List<DataLog> dataLogs, Config config) {
        this.aggregatedData = dataLogs.stream()
            .flatMap(dataLog -> dataLog.getNginxData().stream())
            .filter(data -> isInDateRange(data.dateTime(), config.getFromDate(), config.getToDate()))
            .collect(Collectors.toList());
    }


    private boolean isInDateRange(OffsetDateTime dataDate, LocalDate fromDate, LocalDate toDate) {
        if (fromDate != null && dataDate.isBefore(fromDate.atStartOfDay(ZoneOffset.UTC).toOffsetDateTime())) {
            return false;
        }
        if (toDate != null && dataDate.isAfter(toDate.atTime(23, 59, 59).atOffset(ZoneOffset.UTC))) {
            return false;
        }
        return true;
    }

    public int getTotalRequestCount() {
        return aggregatedData.size();
    }

    public Map<String, Long> getMostRequestedResources() {
        return sort(aggregatedData.stream()
            .collect(Collectors.groupingBy(NginxData::requestUri, Collectors.counting())));
    }

    public Map<Integer, Long> getResponseCodeFrequency() {
        return sort(aggregatedData.stream()
            .collect(Collectors.groupingBy(NginxData::statusCode, Collectors.counting())));
    }

    public int getAverageResponseSize() {
        return (int) aggregatedData.stream()
            .mapToInt(NginxData::bytes)
            .average()
            .orElse(0.0);
    }

    public double getErrorRate() {
        long errorCount = aggregatedData.stream()
            .filter(data -> data.statusCode() >= 400 && data.statusCode() < 600)
            .count();
        return (double) errorCount / getTotalRequestCount() * 100;
    }

    public Map<String, Long> getMostFrequentRequestSources() {
        return sort(aggregatedData.stream()
            .collect(Collectors.groupingBy(NginxData::ipAddress, Collectors.counting())));
    }


    private <T> Map<T, Long> sort(Map<T, Long> map) {
        final int limit = 10;
        return map
            .entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(limit)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new));
    }
}
