import java.util.Arrays;
import java.util.Scanner;

class WordSplitter {

    public static String[] customSplit(String text) {
        int wordCount = 0;
        String tempText = text.trim();
        if (!tempText.isEmpty()) {
            wordCount = 1;
            for (int i = 0; i < tempText.length(); i++) {
                if (tempText.charAt(i) == ' ') {
                    wordCount++;
                }
            }
        }

        String[] words = new String[wordCount];
        if (wordCount == 0) {
            return words;
        }

        String currentWord = "";
        int wordIndex = 0;
        text = text + " ";

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (!currentWord.isEmpty()) {
                    words[wordIndex++] = currentWord;
                    currentWord = "";
                }
            } else {
                currentWord += text.charAt(i);
            }
        }
        return words;
    }

    public static boolean compareStringArrays(String[] arr1, String[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String userInput = scanner.nextLine();

        String[] customResult = customSplit(userInput);
        System.out.println("Custom split result: " + Arrays.toString(customResult));

        String[] builtInResult = userInput.split("\\s+");
        System.out.println("Built-in split() result: " + Arrays.toString(builtInResult));

        boolean areEqual = compareStringArrays(customResult, builtInResult);
        System.out.println("Do the two arrays match? " + areEqual);

        scanner.close();
    }
}
