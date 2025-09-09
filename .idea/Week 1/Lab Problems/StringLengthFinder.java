import java.util.Scanner;
class StringLengthFinder {

    public static int findStringLength(String text) {
        int count = 0;
        try {
            for (int i = 0; ; i++) {
                text.charAt(i);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String userInput = scanner.next();

        int customLength = findStringLength(userInput);
        System.out.println("Length from custom method: " + customLength);

        int builtInLength = userInput.length();
        System.out.println("Length from built-in length() method: " + builtInLength);

        scanner.close();
    }
}
