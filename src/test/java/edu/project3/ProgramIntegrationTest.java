package edu.project3;

import static org.junit.jupiter.api.Assertions.*;
import edu.project3.model.Config;
import edu.project3.model.DataLog;
import edu.project3.parser.CommandLineParser;
import edu.project3.raport.BaseReportGenerator;
import edu.project3.raport.ReportGenerator;
import edu.project3.reader.LogReader;
import edu.project3.statistics.LogStatistics;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ProgramIntegrationTest {

    @Test
    public void testEndToEnd(){
        String[] args = {"--path", "src/main/resources/nginx_logs.txt", "--format", "markdown"};

        // Парсинг конфигурации
        Config config = CommandLineParser.parse(args);

        // Чтение логов
        List<DataLog> dataLogs = LogReader.readAllLogs(config.getPaths());
        dataLogs.forEach(DataLog::parseLinesLog);

        // Анализ статистики логов
        LogStatistics logStatistics = new LogStatistics(dataLogs, config);

        // Генерация отчета
        ReportGenerator reportGenerator = BaseReportGenerator.selectFormat(config.getFormat());
        String report = reportGenerator.generateReport(config, logStatistics);


        assertNotNull(report);
        assertFalse(report.isEmpty());
    }
}
