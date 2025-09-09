import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileOrganizer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> fileNames = new ArrayList<>();
        System.out.println("Enter file names (e.g., report.docx). Type 'done' to finish:");
        while (true) {
            String input = scanner.nextLine();
            if ("done".equalsIgnoreCase(input)) break;
            fileNames.add(input);
        }

        displayFileOrganizationReport(fileNames);
        generateBatchRenameCommands(fileNames);
        scanner.close();
    }

    public static String[] extractFileComponents(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == 0) {
            return new String[]{fileName, "", "Unknown"};
        }
        String name = fileName.substring(0, dotIndex);
        String ext = fileName.substring(dotIndex + 1).toLowerCase();
        return new String[]{name, ext, categorizeFile(ext)};
    }

    public static String categorizeFile(String extension) {
        switch (extension) {
            case "txt": case "doc": case "docx": case "pdf":
                return "Documents";
            case "jpg": case "jpeg": case "png": case "gif":
                return "Images";
            case "mp3": case "wav": case "aac":
                return "Audio";
            case "mp4": case "mov": case "avi":
                return "Videos";
            default:
                return "Unknown";
        }
    }

    public static String generateNewFileName(String originalName, String category) {
        StringBuilder newName = new StringBuilder();
        String[] components = extractFileComponents(originalName);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        newName.append(category).append("_").append(date).append("_").append(components[0]);
        if (!components[1].isEmpty()) {
            newName.append(".").append(components[1]);
        }
        return newName.toString().replaceAll("\\s+", "_");
    }

    public static void displayFileOrganizationReport(List<String> fileNames) {
        System.out.println("\n--- File Organization Report ---");
        String format = "%-30s | %-15s | %-40s%n";
        System.out.printf(format, "Original Filename", "Category", "New Suggested Name");
        System.out.println(new String(new char[90]).replace('\0', '-'));

        Map<String, Integer> categoryCounts = new HashMap<>();
        for (String fileName : fileNames) {
            String[] components = extractFileComponents(fileName);
            String category = components[2];
            String newName = generateNewFileName(fileName, category);
            System.out.printf(format, fileName, category, newName);
            categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
        }

        System.out.println("\n--- Category Counts ---");
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void generateBatchRenameCommands(List<String> fileNames) {
        System.out.println("\n--- Batch Rename Commands (for Windows CMD) ---");
        for (String fileName : fileNames) {
            String category = extractFileComponents(fileName)[2];
            String newName = generateNewFileName(fileName, category);
            System.out.printf("REN \"%s\" \"%s\"%n", fileName, newName);
        }
    }
}
