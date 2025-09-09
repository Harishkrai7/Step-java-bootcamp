public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";

        System.out.println("--- Basic String Operations ---");
        System.out.println("Original Text: \"" + sampleText + "\"");
        System.out.println("----------------------------------------");

        int originalLength = sampleText.length();
        System.out.println("1. Original Length: " + originalLength);

        String trimmedText = sampleText.trim();
        System.out.println("2. Trimmed Text: \"" + trimmedText + "\"");
        System.out.println("   New Length: " + trimmedText.length());

        char charAtIndex5 = trimmedText.charAt(5);
        System.out.println("3. Character at index 5: '" + charAtIndex5 + "'");

        String substring = trimmedText.substring(5, 16);
        System.out.println("4. Extracted Substring: \"" + substring + "\"");

        int indexOfFun = trimmedText.indexOf("Fun");
        System.out.println("5. Index of 'Fun': " + indexOfFun);

        boolean containsJava = trimmedText.contains("Java");
        System.out.println("6. Contains 'Java' (case-sensitive): " + containsJava);

        boolean startsWithJava = trimmedText.startsWith("Java");
        System.out.println("7. Starts with 'Java': " + startsWithJava);

        boolean endsWithExclamation = trimmedText.endsWith("!");
        System.out.println("8. Ends with '!': " + endsWithExclamation);

        String upperCaseText = trimmedText.toUpperCase();
        System.out.println("9. Uppercase: " + upperCaseText);

        String lowerCaseText = trimmedText.toLowerCase();
        System.out.println("10. Lowercase: " + lowerCaseText);

        System.out.println("\n--- Custom Method Demonstrations ---");
        
        int vowelCount = countVowels(trimmedText);
        System.out.println("Total Vowels in the text: " + vowelCount);

        System.out.println("Finding all occurrences of the character 'a':");
        findAllOccurrences(trimmedText, 'a');
    }

    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    public static void findAllOccurrences(String text, char target) {
        System.out.print("Character '" + target + "' found at indices: ");
        boolean found = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                if (found) {
                    System.out.print(", ");
                }
                System.out.print(i);
                found = true;
            }
        }
        if (!found) {
            System.out.print("None");
        }
        System.out.println();
    }
}
