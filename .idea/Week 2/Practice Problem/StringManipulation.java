import java.util.Scanner;
import java.util.StringJoiner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence with mixed formatting (e.g., extra spaces, numbers, punctuation): ");
        String userInput = scanner.nextLine();

        System.out.println("\n--- Processing Steps ---");

        String trimmed = userInput.trim();
        System.out.println("1. Trimmed: \"" + trimmed + "\"");

        String replaced = trimmed.replace(' ', '_');
        System.out.println("2. Spaces replaced with underscores: \"" + replaced + "\"");

        String noDigits = trimmed.replaceAll("\\d", "");
        System.out.println("3. Digits removed: \"" + noDigits + "\"");

        String[] words = trimmed.split("\\s+");
        System.out.println("4. Split into words: " + Arrays.toString(words));

        String joined = String.join(" | ", words);
        System.out.println("5. Rejoined with ' | ': \"" + joined + "\"");

        System.out.println("\n--- Additional Custom Methods ---");

        String noPunctuation = removePunctuation(trimmed);
        System.out.println("Punctuation removed: \"" + noPunctuation + "\"");

        String capitalized = capitalizeWords(noPunctuation);
        System.out.println("Capitalized words: \"" + capitalized + "\"");

        String reversed = reverseWordOrder(noPunctuation);
        System.out.println("Reversed word order: \"" + reversed + "\"");

        System.out.println("Word Frequency Count:");
        countWordFrequency(noPunctuation);

        scanner.close();
    }

    public static String removePunctuation(String text) {
        return text.replaceAll("[\\p{Punct}]", "");
    }

    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder capitalizedText = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String restOfWord = word.substring(1).toLowerCase();
                capitalizedText.append(firstLetter).append(restOfWord).append(" ");
            }
        }
        return capitalizedText.toString().trim();
    }

    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            System.out.println("- '" + entry.getKey() + "': " + entry.getValue());
        }
    }
}
