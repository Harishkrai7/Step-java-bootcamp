import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class PasswordAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> passwordsToAnalyze = new ArrayList<>();
        System.out.println("Enter passwords to analyze (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine();
            if ("done".equalsIgnoreCase(input)) break;
            passwordsToAnalyze.add(input);
        }

        displayAnalysisResults(passwordsToAnalyze);

        System.out.print("\nEnter desired length for a new strong password: ");
        int length = scanner.nextInt();
        String newPassword = generateStrongPassword(length);
        System.out.println("Generated Strong Password: " + newPassword);

        scanner.close();
    }

    public static int[] analyzePasswordCharacters(String password) {
        int[] counts = new int[4]; // 0:upper, 1:lower, 2:digit, 3:special
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= 65 && c <= 90) counts[0]++;
            else if (c >= 97 && c <= 122) counts[1]++;
            else if (c >= 48 && c <= 57) counts[2]++;
            else if (c >= 33 && c <= 126) counts[3]++;
        }
        return counts;
    }

    public static int calculatePasswordScore(String password, int[] counts) {
        int score = 0;
        if (password.length() > 8) {
            score += (password.length() - 8) * 2;
        }
        for (int count : counts) {
            if (count > 0) score += 10;
        }
        if (password.contains("123") || password.contains("abc") || password.contains("qwerty")) {
            score -= 15;
        }
        return Math.max(0, score);
    }

    public static String getStrengthLevel(int score) {
        if (score > 50) return "Strong";
        if (score > 20) return "Medium";
        return "Weak";
    }

    public static String generateStrongPassword(int length) {
        if (length < 8) length = 8;
        StringBuilder password = new StringBuilder(length);
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        String allChars = upper + lower + digits + special;
        Random random = new Random();

        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(special.charAt(random.nextInt(special.length())));

        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        List<Character> chars = new ArrayList<>();
        for (char c : password.toString().toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars);
        
        StringBuilder shuffledPassword = new StringBuilder(length);
        for (char c : chars) {
            shuffledPassword.append(c);
        }
        return shuffledPassword.toString();
    }

    public static void displayAnalysisResults(List<String> passwords) {
        System.out.println("\n--- Password Strength Analysis ---");
        String format = "%-20s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-8s%n";
        System.out.printf(format, "Password", "Len", "Upper", "Lower", "Digits", "Special", "Score", "Strength");
        System.out.println(new String(new char[85]).replace('\0', '-'));
        for (String password : passwords) {
            int[] counts = analyzePasswordCharacters(password);
            int score = calculatePasswordScore(password, counts);
            String strength = getStrengthLevel(score);
            System.out.printf(format, password, password.length(), counts[0], counts[1], counts[2], counts[3], score, strength);
        }
    }
}
