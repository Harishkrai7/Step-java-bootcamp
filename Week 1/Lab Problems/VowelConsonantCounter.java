import java.util.Scanner;

class VowelConsonantCounter {
    public static int[] countVowelsAndConsonants(String text) {
        int[] counts = new int[]{0, 0}; // 0: Vowels, 1: Consonants
        String vowels = "aeiou";
        for (char ch : text.toLowerCase().toCharArray()) {
            if (vowels.indexOf(ch) != -1) {
                counts[0]++;
            } else if (ch >= 'a' && ch <= 'z') {
                counts[1]++;
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        int[] counts = countVowelsAndConsonants(scanner.nextLine());
        System.out.println("Vowels: " + counts[0]);
        System.out.println("Consonants: " + counts[1]);
        scanner.close();
    }
}
