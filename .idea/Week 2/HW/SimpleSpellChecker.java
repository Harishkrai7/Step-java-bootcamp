import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class SimpleSpellChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] dictionary = {"hello", "world", "java", "programming", "is", "fun", "spell", "checker", "correct"};

        System.out.println("Enter a sentence to check:");
        String sentence = scanner.nextLine();

        List<String> words = splitSentenceWithoutSplit(sentence);
        displaySpellCheckResults(words, dictionary);

        scanner.close();
    }

    public static List<String> splitSentenceWithoutSplit(String sentence) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                if (i > start) {
                    words.add(sentence.substring(start, i).toLowerCase());
                }
                start = i + 1;
            }
        }
        if (start < sentence.length()) {
            words.add(sentence.substring(start).toLowerCase());
        }
        return words;
    }

    public static int calculateStringDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1),
                                      Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[a.length()][b.length()];
    }

    public static String findClosestWord(String word, String[] dictionary) {
        int minDistance = Integer.MAX_VALUE;
        String suggestion = "";
        boolean isCorrect = false;

        for (String dictWord : dictionary) {
            if (word.equals(dictWord)) {
                isCorrect = true;
                break;
            }
            int distance = calculateStringDistance(word, dictWord);
            if (distance < minDistance) {
                minDistance = distance;
                suggestion = dictWord;
            }
        }

        if (isCorrect) {
            return "Correct";
        }
        if (minDistance <= 2) {
            return suggestion + " (" + minDistance + ")";
        }
        return "No suggestion";
    }

    public static void displaySpellCheckResults(List<String> words, String[] dictionary) {
        System.out.println("\n--- Spell Check Report ---");
        String format = "%-15s | %-20s | %-10s%n";
        System.out.printf(format, "Original Word", "Suggestion (Distance)", "Status");
        System.out.println(new String(new char[55]).replace('\0', '-'));

        for (String word : words) {
            String result = findClosestWord(word, dictionary);
            String status;
            String suggestion;
            if (result.equals("Correct")) {
                status = "Correct";
                suggestion = "-";
            } else {
                status = "Misspelled";
                suggestion = result;
            }
            System.out.printf(format, word, suggestion, status);
        }
    }
}
