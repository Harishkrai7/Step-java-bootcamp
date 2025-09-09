import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> emails = new ArrayList<>();
        System.out.println("Enter email addresses (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine();
            if ("done".equalsIgnoreCase(input)) {
                break;
            }
            emails.add(input);
        }

        displayResultsTable(emails);
        analyzeEmailStatistics(emails);

        scanner.close();
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');
        if (atIndex == -1 || atIndex != lastAtIndex || atIndex == 0) return false;
        
        int dotIndex = email.indexOf('.', atIndex);
        if (dotIndex == -1 || dotIndex == atIndex + 1 || dotIndex == email.length() - 1) return false;
        
        String username = email.substring(0, atIndex);
        if (username.isEmpty()) return false;

        return true;
    }

    public static String[] extractEmailComponents(String email) {
        if (!isValidEmail(email)) {
            return new String[]{"-", "-", "-", "-", "Invalid"};
        }
        int atIndex = email.indexOf('@');
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);
        
        int dotIndex = domain.lastIndexOf('.');
        String domainName = domain.substring(0, dotIndex);
        String extension = domain.substring(dotIndex + 1);
        
        return new String[]{username, domain, domainName, extension, "Valid"};
    }

    public static void analyzeEmailStatistics(List<String> emails) {
        int validCount = 0;
        int totalUsernameLength = 0;
        Map<String, Integer> domainCounts = new HashMap<>();

        for (String email : emails) {
            if (isValidEmail(email)) {
                validCount++;
                String[] components = extractEmailComponents(email);
                totalUsernameLength += components[0].length();
                domainCounts.put(components[1], domainCounts.getOrDefault(components[1], 0) + 1);
            }
        }
        
        String mostCommonDomain = "N/A";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : domainCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommonDomain = entry.getKey();
            }
        }

        double avgUsernameLength = (validCount > 0) ? (double) totalUsernameLength / validCount : 0;

        System.out.println("\n--- Email Statistics ---");
        System.out.println("Total Emails Processed: " + emails.size());
        System.out.println("Valid Emails: " + validCount);
        System.out.println("Invalid Emails: " + (emails.size() - validCount));
        System.out.println("Most Common Domain: " + mostCommonDomain);
        System.out.printf("Average Username Length: %.2f%n", avgUsernameLength);
    }

    public static void displayResultsTable(List<String> emails) {
        System.out.println("\n--- Email Analysis Results ---");
        String format = "%-25s | %-15s | %-15s | %-10s | %-10s%n";
        System.out.printf(format, "Email", "Username", "Domain", "Extension", "Status");
        System.out.println(new String(new char[85]).replace('\0', '-'));
        for (String email : emails) {
            String[] components = extractEmailComponents(email);
            System.out.printf("%-25s | %-15s | %-15s | %-10s | %-10s%n", 
                email, components[0], components[1], components[3], components[4]);
        }
    }
}
