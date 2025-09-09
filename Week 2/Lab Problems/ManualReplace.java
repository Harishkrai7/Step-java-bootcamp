import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ManualReplace {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the main text: ");
        String mainText = scanner.nextLine();

        System.out.print("Enter the substring to find: ");
        String toFind = scanner.nextLine();

        System.out.print("Enter the substring to replace it with: ");
        String toReplace = scanner.nextLine();

        String manualResult = replaceSubstringManually(mainText, toFind, toReplace);
        String builtinResult = mainText.replace(toFind, toReplace);
        boolean isMatch = compareResults(manualResult, builtinResult);

        System.out.println("\n--- Results ---");
        System.out.println("Original Text:          " + mainText);
        System.out.println("Manually Replaced Text: " + manualResult);
        System.out.println("Built-in Replaced Text: " + builtinResult);
        System.out.println("Do the results match?   " + isMatch);

        scanner.close();
    }

    public static int[] findAllOccurrences(String text, String substring) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(substring);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(substring, index + 1);
        }
        int[] result = new int[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            result[i] = positions.get(i);
        }
        return result;
    }

    public static String replaceSubstringManually(String text, String find, String replace) {
        if (find == null || find.isEmpty()) {
            return text;
        }
        StringBuilder newText = new StringBuilder();
        int lastIndex = 0;
        int currentIndex;
        while ((currentIndex = text.indexOf(find, lastIndex)) != -1) {
            newText.append(text, lastIndex, currentIndex);
            newText.append(replace);
            lastIndex = currentIndex + find.length();
        }
        newText.append(text.substring(lastIndex));
        return newText.toString();
    }

    public static boolean compareResults(String result1, String result2) {
        return result1.equals(result2);
    }
}
