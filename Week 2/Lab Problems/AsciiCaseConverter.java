import java.util.Scanner;

public class AsciiCaseConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String text = scanner.nextLine();

        String manualUpper = toUpperCaseAscii(text);
        String manualLower = toLowerCaseAscii(text);
        String manualTitle = toTitleCaseAscii(text);

        String builtinUpper = text.toUpperCase();
        String builtinLower = text.toLowerCase();

        System.out.println("\n--- Case Conversion Results ---");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-15s | %-25s | %-25s%n", "Case Type", "Manual ASCII Method", "Built-in Method");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-15s | %-25s | %-25s%n", "Original", text, text);
        System.out.printf("%-15s | %-25s | %-25s%n", "UPPERCASE", manualUpper, builtinUpper);
        System.out.printf("%-15s | %-25s | %-25s%n", "lowercase", manualLower, builtinLower);
        System.out.printf("%-15s | %-25s | %-25s%n", "Title Case", manualTitle, "(No direct equivalent)");
        System.out.println("------------------------------------------------------------------");
        
        System.out.println("\nComparison with built-in methods:");
        System.out.println("Uppercase methods match: " + compareResults(manualUpper, builtinUpper));
        System.out.println("Lowercase methods match: " + compareResults(manualLower, builtinLower));

        scanner.close();
    }

    public static char toUpperAscii(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) (c - 32);
        }
        return c;
    }

    public static char toLowerAscii(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c + 32);
        }
        return c;
    }

    public static String toUpperCaseAscii(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = toUpperAscii(chars[i]);
        }
        return new String(chars);
    }

    public static String toLowerCaseAscii(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = toLowerAscii(chars[i]);
        }
        return new String(chars);
    }

    public static String toTitleCaseAscii(String text) {
        char[] chars = text.toCharArray();
        boolean newWord = true;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isWhitespace(chars[i])) {
                newWord = true;
            } else if (newWord) {
                chars[i] = toUpperAscii(chars[i]);
                newWord = false;
            } else {
                chars[i] = toLowerAscii(chars[i]);
            }
        }
        return new String(chars);
    }

    public static boolean compareResults(String manual, String builtin) {
        return manual.equals(builtin);
    }
}
