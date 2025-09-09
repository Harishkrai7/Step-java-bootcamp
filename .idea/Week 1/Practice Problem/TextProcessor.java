import java.util.*;
import java.util.stream.Collectors;

public class TextProcessor {

    // Clean and normalize input text
    public static String cleanInput(String input) {
        String cleaned = input.trim().replaceAll("\\s+", " ");
        return cleaned.substring(0, 1).toUpperCase() + cleaned.substring(1).toLowerCase();
    }

    // Analyze text: words, sentences, chars, most common character
    public static void analyzeText(String text) {
        String[] words = text.split("\\s+");
        long sentences = text.split("[.!?]").length;
        long chars = text.chars().filter(ch -> ch != ' ').count();

        // Count frequency of each character (ignoring spaces)
        Map<Integer, Long> freqMap = text.chars()
                .filter(ch -> ch != ' ')
                .boxed()
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));

        int mostCommonCode = Collections.max(freqMap.entrySet(),
                Map.Entry.comparingByValue()).getKey();
        char mostCommon = (char) mostCommonCode;

        System.out.println("Word count: " + words.length +
                "\nSentence count: " + sentences +
                "\nCharacter count: " + chars +
                "\nMost common: " + mostCommon);
    }

    // Extract and sort words alphabetically
    public static String[] getWordsSorted(String text) {
        return Arrays.stream(text.replaceAll("[^a-zA-Z\\s]", "").split("\\s+"))
                .filter(w -> !w.isEmpty())
                .sorted()
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter text: ");
        String t = cleanInput(s.nextLine());

        analyzeText(t);

        String[] w = getWordsSorted(t);
        System.out.println("\nSorted words:");
        Arrays.stream(w).forEach(System.out::println);

        System.out.print("\nSearch word: ");
        String search = s.nextLine();
        long c = Arrays.stream(w)
                .filter(x -> x.equalsIgnoreCase(search))
                .count();

        System.out.println("Occurrences: " + c + "\nYour implementation here");

        s.close();
    }
}
