import java.util.ArrayList;
import java.util.Scanner;

class WordAndLengthArray {
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

    public static String[][] getWordsAndLengths(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(words[i].length()); // Using built-in length for brevity here
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String userInput = scanner.nextLine();
        
        String[][] result = getWordsAndLengths(customSplit(userInput));
        
        System.out.printf("%-15s | %s%n", "Word", "Length");
        System.out.println("--------------------");
        for (String[] row : result) {
            System.out.printf("%-15s | %s%n", row[0], row[1]);
        }
        scanner.close();
    }
}
