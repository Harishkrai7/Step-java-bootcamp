import java.util.Scanner;

public class StringPerformance {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of concatenations (e.g., 50000): ");
        int iterations = scanner.nextInt();

        long[] stringResult = testStringConcatenation(iterations);
        long[] builderResult = testStringBuilder(iterations);
        long[] bufferResult = testStringBuffer(iterations);

        displayPerformanceTable(stringResult, builderResult, bufferResult);

        scanner.close();
    }

    public static long[] testStringConcatenation(int iterations) {
        long startTime = System.currentTimeMillis();
        String text = "";
        for (int i = 0; i < iterations; i++) {
            text += "a";
        }
        long endTime = System.currentTimeMillis();
        return new long[]{endTime - startTime, text.length()};
    }

    public static long[] testStringBuilder(int iterations) {
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");
        }
        String text = sb.toString();
        long endTime = System.currentTimeMillis();
        return new long[]{endTime - startTime, text.length()};
    }

    public static long[] testStringBuffer(int iterations) {
        long startTime = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbf.append("a");
        }
        String text = sbf.toString();
        long endTime = System.currentTimeMillis();
        return new long[]{endTime - startTime, text.length()};
    }

    public static void displayPerformanceTable(long[] stringRes, long[] builderRes, long[] bufferRes) {
        System.out.println("\n--- Performance Comparison ---");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-20s | %-20s | %-20s%n", "Method", "Time Taken (ms)", "Memory Efficiency");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-20s | %-20d | %-20s%n", "String (+)", stringRes[0], "Poor (new object each time)");
        System.out.printf("%-20s | %-20d | %-20s%n", "StringBuilder", builderRes[0], "Excellent (mutable)");
        System.out.printf("%-20s | %-20d | %-20s%n", "StringBuffer", bufferRes[0], "Excellent (mutable, sync overhead)");
        System.out.println("-----------------------------------------------------------------");
    }
}
