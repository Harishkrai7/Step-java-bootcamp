import java.util.Scanner;
import java.util.Arrays;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String userInput = scanner.nextLine();

        System.out.println("\n--- Character Analysis ---");
        for (int i = 0; i < userInput.length(); i++) {
            char ch = userInput.charAt(i);
            int asciiValue = (int) ch;

            System.out.println("\nCharacter: '" + ch + "', ASCII Code: " + asciiValue);

            String type = classifyCharacter(ch);
            System.out.println("Type: " + type);

            if (type.equals("Uppercase Letter") || type.equals("Lowercase Letter")) {
                char toggled = toggleCase(ch);
                System.out.println("Toggled Case: '" + toggled + "', ASCII Code: " + (int) toggled);
                System.out.println("ASCII difference between cases: " + Math.abs(asciiValue - (int)toggled));
            }
        }
        
        System.out.println("\n--- ASCII Art ---");
        System.out.println("  /\\_/\\");
        System.out.println(" ( o.o )");
        System.out.println("  > ^ <");


        System.out.println("\n--- Caesar Cipher ---");
        int shift = 3;
        String encryptedText = caesarCipher(userInput, shift);
        System.out.println("Original Text:  \"" + userInput + "\"");
        System.out.println("Encrypted (Shift " + shift + "): \"" + encryptedText + "\"");
        String decryptedText = caesarCipher(encryptedText, -shift);
        System.out.println("Decrypted:      \"" + decryptedText + "\"");

        System.out.println("\n--- ASCII Table (A-Z) ---");
        displayASCIITable('A', 'Z');
        
        System.out.println("\n--- String <-> ASCII Array Conversion ---");
        int[] asciiArray = stringToASCII(userInput);
        System.out.println("String to ASCII Array: " + Arrays.toString(asciiArray));
        String textFromAscii = asciiToString(asciiArray);
        System.out.println("ASCII Array to String: \"" + textFromAscii + "\"");


        scanner.close();
    }

    public static String classifyCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return "Uppercase Letter";
        } else if (ch >= 'a' && ch <= 'z') {
            return "Lowercase Letter";
        } else if (ch >= '0' && ch <= '9') {
            return "Digit";
        } else {
            return "Special Character";
        }
    }

    public static char toggleCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + 32);
        } else if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 32);
        }
        return ch;
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (character >= 'a' && character <= 'z') {
                char shifted = (char) ('a' + (character - 'a' + shift) % 26);
                result.append(shifted);
            } else if (character >= 'A' && character <= 'Z') {
                char shifted = (char) ('A' + (character - 'A' + shift) % 26);
                result.append(shifted);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public static void displayASCIITable(int start, int end) {
        System.out.println("Code | Char");
        System.out.println("-----|------");
        for (int i = start; i <= end; i++) {
            System.out.printf("%-4d | %-4c%n", i, (char) i);
        }
    }
    
    public static int[] stringToASCII(String text) {
        int[] asciiValues = new int[text.length()];
        for(int i = 0; i < text.length(); i++) {
            asciiValues[i] = (int) text.charAt(i);
        }
        return asciiValues;
    }

    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for(int value : asciiValues) {
            sb.append((char) value);
        }
        return sb.toString();
    }
}
