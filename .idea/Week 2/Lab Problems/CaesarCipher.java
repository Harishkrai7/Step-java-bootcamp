import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text to encrypt: ");
        String originalText = scanner.nextLine();

        System.out.print("Enter shift value (e.g., 3): ");
        int shift = scanner.nextInt();

        System.out.println("\n--- Original Text ---");
        displayWithAscii(originalText);

        String encryptedText = encrypt(originalText, shift);
        System.out.println("\n--- Encrypted Text ---");
        displayWithAscii(encryptedText);

        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("\n--- Decrypted Text ---");
        System.out.println("Text: " + decryptedText);

        boolean isValid = validateDecryption(originalText, decryptedText);
        System.out.println("\nDecryption validation successful: " + isValid);

        scanner.close();
    }

    public static String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (character >= 'a' && character <= 'z') {
                char shifted = (char) ('a' + (character - 'a' + shift) % 26);
                encrypted.append(shifted);
            } else if (character >= 'A' && character <= 'Z') {
                char shifted = (char) ('A' + (character - 'A' + shift) % 26);
                encrypted.append(shifted);
            } else {
                encrypted.append(character);
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void displayWithAscii(String text) {
        System.out.print("Text: " + text + "\nASCII: ");
        for (int i = 0; i < text.length(); i++) {
            System.out.print((int) text.charAt(i) + " ");
        }
        System.out.println();
    }

    public static boolean validateDecryption(String original, String decrypted) {
        return original.equals(decrypted);
    }
}
