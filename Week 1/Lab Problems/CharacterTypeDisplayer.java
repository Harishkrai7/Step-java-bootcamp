import java.util.Scanner;

class CharacterTypeDisplayer {
    public static String getCharType(char ch) {
        char lowerCh = Character.toLowerCase(ch);
        if (lowerCh >= 'a' && lowerCh <= 'z') {
            return "aeiou".indexOf(lowerCh) != -1 ? "Vowel" : "Consonant";
        }
        return "Not a Letter";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String userInput = scanner.nextLine();

        System.out.printf("%-10s | %s%n", "Character", "Type");
        System.out.println("---------------------");
        for (char ch : userInput.toCharArray()) {
            System.out.printf("%-10s | %s%n", ch, getCharType(ch));
        }
        scanner.close();
    }
}
