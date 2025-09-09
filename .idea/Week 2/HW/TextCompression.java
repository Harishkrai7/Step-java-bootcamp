import java.util.Scanner;

public class TextCompression {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to compress:");
        String text = scanner.nextLine();

        Object[] freqData = countCharFrequencyWithoutMap(text);
        char[] uniqueChars = (char[]) freqData[0];
        int[] frequencies = (int[]) freqData[1];

        String[][] mapping = createCompressionCodes(uniqueChars, frequencies);
        String compressedText = compressText(text, mapping);
        String decompressedText = decompressText(compressedText, mapping);

        displayCompressionAnalysis(text, compressedText, decompressedText, uniqueChars, frequencies, mapping);
        scanner.close();
    }

    public static Object[] countCharFrequencyWithoutMap(String text) {
        char[] tempChars = new char[256];
        int[] tempFreqs = new int[256];
        int uniqueCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = -1;
            for (int j = 0; j < uniqueCount; j++) {
                if (tempChars[j] == c) {
                    index = j;
                    break;
                }
            }
            if (index != -1) {
                tempFreqs[index]++;
            } else {
                tempChars[uniqueCount] = c;
                tempFreqs[uniqueCount] = 1;
                uniqueCount++;
            }
        }
        char[] finalChars = new char[uniqueCount];
        int[] finalFreqs = new int[uniqueCount];
        System.arraycopy(tempChars, 0, finalChars, 0, uniqueCount);
        System.arraycopy(tempFreqs, 0, finalFreqs, 0, uniqueCount);
        return new Object[]{finalChars, finalFreqs};
    }

    public static String[][] createCompressionCodes(char[] chars, int[] freqs) {
        String[][] mapping = new String[chars.length][2];
        for (int i = 0; i < chars.length; i++) {
            mapping[i][0] = String.valueOf(chars[i]);
            mapping[i][1] = Integer.toString(i); 
        }
        return mapping;
    }

    public static String compressText(String text, String[][] mapping) {
        StringBuilder compressed = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String charStr = String.valueOf(text.charAt(i));
            for (String[] map : mapping) {
                if (map[0].equals(charStr)) {
                    compressed.append(map[1]).append(" ");
                    break;
                }
            }
        }
        return compressed.toString().trim();
    }

    public static String decompressText(String compressedText, String[][] mapping) {
        StringBuilder decompressed = new StringBuilder();
        String[] codes = compressedText.split(" ");
        for (String code : codes) {
            for (String[] map : mapping) {
                if (map[1].equals(code)) {
                    decompressed.append(map[0]);
                    break;
                }
            }
        }
        return decompressed.toString();
    }

    public static void displayCompressionAnalysis(String orig, String comp, String decomp, char[] chars, int[] freqs, String[][] mapping) {
        System.out.println("\n--- Character Frequencies ---");
        for (int i = 0; i < chars.length; i++) {
            System.out.println("'" + chars[i] + "': " + freqs[i]);
        }
        System.out.println("\n--- Compression Mapping ---");
        for (String[] map : mapping) {
            System.out.println("'" + map[0] + "' -> " + map[1]);
        }
        System.out.println("\n--- Compression Results ---");
        System.out.println("Original Text: " + orig);
        System.out.println("Compressed Text: " + comp);
        System.out.println("Decompressed Text: " + decomp);
        System.out.println("Validation (Decompressed equals Original): " + orig.equals(decomp));
        double ratio = 100.0 * (orig.length() - comp.length()) / orig.length();
        System.out.printf("Compression Efficiency: %.2f%%%n", ratio);
    }
}
