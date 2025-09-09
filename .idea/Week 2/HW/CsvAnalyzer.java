import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CsvAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter CSV data (e.g., Name,Age,City). Type 'done' on a new line to finish:");
        StringBuilder csvInput = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if ("done".equalsIgnoreCase(line)) break;
            csvInput.append(line).append('\n');
        }

        List<List<String>> parsedData = parseCsvWithoutSplit(csvInput.toString());
        String formattedTable = formatOutput(parsedData);
        System.out.println("\n--- Formatted Data ---");
        System.out.println(formattedTable);
        generateSummaryReport(parsedData);
        scanner.close();
    }

    public static List<List<String>> parseCsvWithoutSplit(String csvText) {
        List<List<String>> data = new ArrayList<>();
        String[] lines = csvText.split("\n");
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            List<String> row = new ArrayList<>();
            int start = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ',') {
                    row.add(line.substring(start, i).trim());
                    start = i + 1;
                }
            }
            row.add(line.substring(start).trim());
            data.add(row);
        }
        return data;
    }

    public static String formatOutput(List<List<String>> data) {
        if (data.isEmpty()) return "No data to display.";
        StringBuilder table = new StringBuilder();
        int[] colWidths = new int[data.get(0).size()];
        for (List<String> row : data) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).length() > colWidths[i]) {
                    colWidths[i] = row.get(i).length();
                }
            }
        }

        for (List<String> row : data) {
            for (int i = 0; i < row.size(); i++) {
                table.append(String.format("%-" + (colWidths[i] + 2) + "s", row.get(i)));
            }
            table.append("\n");
        }
        return table.toString();
    }

    public static void generateSummaryReport(List<List<String>> data) {
        if (data.size() <= 1) {
            System.out.println("\n--- Summary Report ---");
            System.out.println("Not enough data for analysis.");
            return;
        }
        int totalRecords = data.size() - 1;
        int numCols = data.get(0).size();
        System.out.println("\n--- Summary Report ---");
        System.out.println("Total Records Processed: " + totalRecords);
        System.out.println("Number of Columns: " + numCols);

        for (int i = 0; i < numCols; i++) {
            try {
                double sum = 0, min = Double.MAX_VALUE, max = Double.MIN_VALUE;
                int count = 0;
                for (int j = 1; j < data.size(); j++) {
                    double val = Double.parseDouble(data.get(j).get(i));
                    sum += val;
                    if (val < min) min = val;
                    if (val > max) max = val;
                    count++;
                }
                System.out.println("\nStatistics for Column '" + data.get(0).get(i) + "':");
                System.out.printf("  Average: %.2f%n", sum / count);
                System.out.println("  Min: " + min);
                System.out.println("  Max: " + max);
            } catch (NumberFormatException e) {
                // Not a numeric column, skip stats
            }
        }
    }
}
