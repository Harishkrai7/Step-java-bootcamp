import java.util.Scanner;

public class CharFrequencyUnique {

    public static char[] findUniqueCharacters(String text) {
        String uniqueCharsString = "";

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (uniqueCharsString.indexOf(currentChar) == -1) {
                uniqueCharsString += currentChar;
            }
        }
        return uniqueCharsString.toCharArray();
    }

    public static String[][] findFrequency(String text) {
        int[] freq = new int[256];

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            freq[character]++;
        }

        char[] uniqueChars = findUniqueCharacters(text);

        String[][] result = new String[uniqueChars.length][2];

        for (int i = 0; i < uniqueChars.length; i++) {
            char character = uniqueChars[i];
            result[i][0] = String.valueOf(character);
            result[i][1] = String.valueOf(freq[character]);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String inputText = scanner.nextLine();

        String[][] frequencies = findFrequency(inputText);

        System.out.println("\nCharacter frequencies:");
        System.out.println("--------------------");
        System.out.println("Character | Frequency");
        System.out.println("--------------------");

        for (String[] entry : frequencies) {
            System.out.printf("%-9s | %-9s%n", entry[0], entry[1]);
        }
        System.out.println("--------------------");

        scanner.close();
    }
}
