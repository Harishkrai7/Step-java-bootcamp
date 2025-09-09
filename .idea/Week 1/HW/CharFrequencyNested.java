import java.util.Scanner;

public class CharFrequencyNested {

    public static String[] findFrequency(String text) {
        char[] chars = text.toCharArray();
        int[] freq = new int[chars.length];
        int uniqueCount = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                continue;
            }

            int count = 1;
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    count++;
                    chars[j] = '0';
                }
            }
            freq[i] = count;
            uniqueCount++;
        }

        String[] result = new String[uniqueCount];
        int resultIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0' && chars[i] != ' ') {
                result[resultIndex] = "'" + chars[i] + "' : " + freq[i];
                resultIndex++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String inputText = scanner.nextLine();

        String[] frequencies = findFrequency(inputText);

        System.out.println("\nCharacter frequencies:");
        System.out.println("--------------------");
        for (String entry : frequencies) {
            if (entry != null) {
                System.out.println(entry);
            }
        }
        System.out.println("--------------------");

        scanner.close();
    }
}
