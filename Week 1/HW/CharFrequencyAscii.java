import java.util.Scanner;

public class CharFrequencyAscii {

    public static String[][] findFrequency(String text) {
        int[] freq = new int[256];
        int uniqueCharCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (freq[character] == 0) {
                uniqueCharCount++;
            }
            freq[character]++;
        }

        String[][] result = new String[uniqueCharCount][2];
        int resultIndex = 0;

        boolean[] visited = new boolean[256];
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (!visited[character]) {
                result[resultIndex][0] = String.valueOf(character);
                result[resultIndex][1] = String.valueOf(freq[character]);
                visited[character] = true;
                resultIndex++;
            }
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
