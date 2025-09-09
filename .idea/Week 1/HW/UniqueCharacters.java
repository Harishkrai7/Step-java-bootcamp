import java.util.Arrays;
import java.util.Scanner;

public class UniqueCharacters {

    public static int findLength(String text) {
        int length = 0;
        try {
            while (true) {
                text.charAt(length);
                length++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // The exception signals we've reached the end of the string.
        }
        return length;
    }

    public static char[] findUniqueCharacters(String text) {
        int textLength = findLength(text);
        char[] tempUnique = new char[textLength];
        int uniqueCount = 0;

        for (int i = 0; i < textLength; i++) {
            char currentChar = text.charAt(i);
            boolean isDuplicate = false;

            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == currentChar) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                tempUnique[uniqueCount] = currentChar;
                uniqueCount++;
            }
        }

        char[] uniqueChars = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            uniqueChars[i] = tempUnique[i];
        }

        return uniqueChars;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String inputText = scanner.nextLine();

        char[] uniqueResult = findUniqueCharacters(inputText);

        System.out.println("The unique characters in the string are:");
        System.out.println(Arrays.toString(uniqueResult));

        scanner.close();
    }
}