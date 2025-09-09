import java.util.ArrayList;
import java.util.Scanner;

class ShortestAndLongestWordFinder {
    public static String[] customSplit(String text) {
        ArrayList<String> words = new ArrayList<>();
        String currentWord = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (!currentWord.isEmpty()) {
                    words.add(currentWord);
                    currentWord = "";
                }
            } else {
                currentWord += text.charAt(i);
            }
        }
        if (!currentWord.isEmpty()) words.add(currentWord);
        return words.toArray(new String[0]);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String[] words = customSplit(scanner.nextLine());

        if (words.length == 0) {
            System.out.println("No words entered.");
            return;
        }

        String shortest = words[0], longest = words[0];
        for (String word : words) {
            if (word.length() < shortest.length()) shortest = word;
            if (word.length() > longest.length()) longest = word;
        }

        System.out.println("Shortest word: " + shortest);
        System.out.println("Longest word: " + longest);
        scanner.close();
    }
}
