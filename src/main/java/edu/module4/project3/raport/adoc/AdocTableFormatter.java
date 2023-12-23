package edu.module4.project3.raport.adoc;

import java.util.List;

public class AdocTableFormatter {
    private static final String CELL_DELIMITER = "|";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String TABLE_START_END = "|===" + NEW_LINE;

    public String createTable(List<String> headers, List<List<String>> rows) {
        return new StringBuilder().append(TABLE_START_END)
                                  .append(formatTableHeaders(headers))
                                  .append(NEW_LINE)
                                  .append(formatTableRows(rows))
                                  .append(TABLE_START_END)
                                  .toString();
    }

    private String formatTableHeaders(List<String> headers) {
        StringBuilder headerLine = new StringBuilder();
        for (String header : headers) {
            headerLine.append(CELL_DELIMITER).append(header);
        }
        headerLine.append(NEW_LINE);
        return headerLine.toString();
    }

    private String formatTableRows(List<List<String>> rows) {
        StringBuilder rowsFormatted = new StringBuilder();
        for (List<String> row : rows) {
            rowsFormatted.append(CELL_DELIMITER);
            for (int i = 0; i < row.size(); i++) {
                if (i > 0) {
                    rowsFormatted.append(CELL_DELIMITER);
                }
                rowsFormatted.append(row.get(i));
            }
            rowsFormatted.append(NEW_LINE);
        }
        return rowsFormatted.toString();
    }
}
