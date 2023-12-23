package edu.module4.project3.raport.markdown;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("multiplestringliterals")
public class MarkdownTableFormatter {
    private static final String PIPE = "|";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DASH = "-";
    private static final String COLON = ":";

    public String createTable(List<String> headers, List<List<String>> rows) {
        List<Integer> columnWidths = calculateColumnWidths(headers, rows);
        return formatTableHeaders(headers, columnWidths) + formatTableRows(rows, columnWidths);
    }

    private List<Integer> calculateColumnWidths(List<String> headers, List<List<String>> rows) {
        List<Integer> widths = new ArrayList<>();
        for (int i = 0; i < headers.size(); i++) {
            int maxWidth = headers.get(i).length();
            for (List<String> row : rows) {
                maxWidth = Math.max(maxWidth, row.get(i).length());
            }
            widths.add(maxWidth);
        }
        return widths;
    }

    private String formatTableHeaders(List<String> headers, List<Integer> columnWidths) {
        StringBuilder headerLine = new StringBuilder(PIPE);
        StringBuilder separatorLine = new StringBuilder(PIPE);
        for (int i = 0; i < headers.size(); i++) {
            int width = columnWidths.get(i);
            headerLine.append(String.format(" %-" + width + "s " + PIPE, headers.get(i)));
            separatorLine.append(COLON).append(DASH.repeat(width)).append(COLON).append(PIPE);
        }
        return headerLine.append(NEW_LINE).append(separatorLine).append(NEW_LINE).toString();
    }

    private String formatTableRows(List<List<String>> rows, List<Integer> columnWidths) {
        StringBuilder rowsFormatted = new StringBuilder();
        for (List<String> row : rows) {
            rowsFormatted.append(PIPE);
            for (int i = 0; i < row.size(); i++) {
                int width = columnWidths.get(i);
                rowsFormatted.append(String.format(" %-" + width + "s " + PIPE, row.get(i)));
            }
            rowsFormatted.append(NEW_LINE);
        }
        return rowsFormatted.toString();
    }
}
