import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TextFormatter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text to format:");
        String text = scanner.nextLine();
        System.out.print("Enter the desired line width: ");
        int width = scanner.nextInt();

        System.out.println("\n--- Original Text ---");
        System.out.println(text);

        System.out.println("\n--- Justified Text ---");
        String justifiedText = justifyText(text, width);
        displayFormattedText(justifiedText);

        System.out.println("\n--- Centered Text ---");
        String centeredText = centerAlignText(text, width);
        displayFormattedText(centeredText);
        
        System.out.println("\n--- Performance Comparison ---");
        comparePerformance(text, width);
    }

    public static List<String> splitIntoWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isWhitespace(text.charAt(i))) {
                if (i > start) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < text.length()) {
            words.add(text.substring(start));
        }
        return words;
    }

    public static String justifyText(String text, int width) {
        List<String> words = splitIntoWords(text);
        StringBuilder result = new StringBuilder();
        List<String> currentLine = new ArrayList<>();
        int currentLength = 0;

        for (String word : words) {
            if (currentLength + word.length() + currentLine.size() > width) {
                int spacesNeeded = width - currentLength;
                int gaps = currentLine.size() - 1;
                if (gaps == 0) {
                    result.append(currentLine.get(0));
                    for (int i = 0; i < spacesNeeded; i++) result.append(' ');
                } else {
                    int spacesPerGap = spacesNeeded / gaps;
                    int extraSpaces = spacesNeeded % gaps;
                    for (int i = 0; i < currentLine.size(); i++) {
                        result.append(currentLine.get(i));
                        if (i < gaps) {
                            for (int j = 0; j < spacesPerGap; j++) result.append(' ');
                            if (i < extraSpaces) result.append(' ');
                        }
                    }
                }
                result.append("\n");
                currentLine.clear();
                currentLength = 0;
            }
            currentLine.add(word);
            currentLength += word.length();
        }

        for (int i = 0; i < currentLine.size(); i++) {
            result.append(currentLine.get(i));
            if (i < currentLine.size() - 1) result.append(" ");
        }
        return result.toString();
    }

    public static String centerAlignText(String text, int width) {
        List<String> words = splitIntoWords(text);
        StringBuilder result = new StringBuilder();
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (currentLine.length() + word.length() + 1 > width) {
                int padding = (width - currentLine.length()) / 2;
                for (int i = 0; i < padding; i++) result.append(' ');
                result.append(currentLine).append("\n");
                currentLine.setLength(0);
            }
            if (currentLine.length() > 0) currentLine.append(" ");
            currentLine.append(word);
        }
        
        int padding = (width - currentLine.length()) / 2;
        for (int i = 0; i < padding; i++) result.append(' ');
        result.append(currentLine);
        return result.toString();
    }

    public static void displayFormattedText(String formattedText) {
        String[] lines = formattedText.split("\n");
        for (int i = 0; i < lines.length; i++) {
            System.out.printf("%2d: %s (%d chars)%n", i + 1, lines[i], lines[i].length());
        }
    }
    
    public static void comparePerformance(String text, int width) {
        long startTimeBuilder = System.nanoTime();
        justifyText(text, width);
        long endTimeBuilder = System.nanoTime();
        long builderTime = endTimeBuilder - startTimeBuilder;

        long startTimeString = System.nanoTime();
        justifyTextWithString(text, width);
        long endTimeString = System.nanoTime();
        long stringTime = endTimeString - startTimeString;
        
        System.out.printf("StringBuilder Time: %,d ns%n", builderTime);
        System.out.printf("String Concat Time: %,d ns%n", stringTime);
    }

    public static String justifyTextWithString(String text, int width) {
        List<String> words = splitIntoWords(text);
        String result = "";
        List<String> currentLine = new ArrayList<>();
        int currentLength = 0;
        for (String word : words) {
            if (currentLength + word.length() + currentLine.size() > width) {
                int spacesNeeded = width - currentLength;
                int gaps = currentLine.size() - 1;
                if (gaps > 0) {
                    int spacesPerGap = spacesNeeded / gaps;
                    int extraSpaces = spacesNeeded % gaps;
                    for (int i = 0; i < currentLine.size() - 1; i++) {
                        result += currentLine.get(i);
                        for (int j = 0; j < spacesPerGap + (i < extraSpaces ? 1 : 0); j++) {
                            result += " ";
                        }
                    }
                    result += currentLine.get(gaps);
                } else {
                    result += currentLine.get(0);
                }
                result += "\n";
                currentLine.clear();
                currentLength = 0;
            }
            currentLine.add(word);
            currentLength += word.length();
        }
        result += String.join(" ", currentLine);
        return result;
    }
}
