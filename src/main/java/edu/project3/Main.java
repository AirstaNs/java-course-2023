package edu.project3;

import edu.project3.model.Config;
import edu.project3.model.DataLog;
import edu.project3.parser.CommandLineParser;
import edu.project3.raport.BaseReportGenerator;
import edu.project3.reader.LogReader;
import edu.project3.statistics.LogStatistics;
import java.util.List;

@SuppressWarnings("regexpsinglelinejava")
public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Config config = CommandLineParser.parse(args);
        List<DataLog> dataLogs = LogReader.readAllLogs(config.getPaths());
        dataLogs.forEach(DataLog::parseLinesLog);
        LogStatistics logStatistics = new LogStatistics(dataLogs, config);
        System.out.println(BaseReportGenerator.selectFormat(config.getFormat()).generateReport(config, logStatistics));
    }
}
