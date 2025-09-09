public class StringPerformanceComparison {

    public static void main(String[] args) {
        int iterations = 20000;

        System.out.println("=== PERFORMANCE COMPARISON (" + iterations + " iterations) ===");

        long startTimeString = System.nanoTime();
        concatenateWithString(iterations);
        long endTimeString = System.nanoTime();
        long durationString = (endTimeString - startTimeString) / 1_000_000;
        System.out.println("1. String concatenation time:      " + durationString + " ms");

        long startTimeBuilder = System.nanoTime();
        concatenateWithStringBuilder(iterations);
        long endTimeBuilder = System.nanoTime();
        long durationBuilder = (endTimeBuilder - startTimeBuilder) / 1_000_000;
        System.out.println("2. StringBuilder concatenation time: " + durationBuilder + " ms");

        long startTimeBuffer = System.nanoTime();
        concatenateWithStringBuffer(iterations);
        long endTimeBuffer = System.nanoTime();
        long durationBuffer = (endTimeBuffer - startTimeBuffer) / 1_000_000;
        System.out.println("3. StringBuffer concatenation time:  " + durationBuffer + " ms");

        System.out.println("\n--- Method Demonstrations ---");
        demonstrateStringBuilderMethods();
        compareStringComparisonMethods();
        
        System.out.println("\n--- Thread Safety Demonstration ---");
        System.out.println("NOTE: The thread safety test may produce different results on each run.");
        System.out.println("StringBuilder (not thread-safe) might show a shorter, incorrect length.");
        System.out.println("StringBuffer (thread-safe) should consistently show the correct final length.");
        demonstrateThreadSafety();
    }

    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "x";
        }
        return result;
    }

    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            result.append("x");
        }
        return result.toString();
    }

    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            result.append("x");
        }
        return result.toString();
    }

    public static void demonstrateStringBuilderMethods() {
        System.out.println("\n--- StringBuilder Methods ---");
        StringBuilder sb = new StringBuilder("Hello World");
        System.out.println("Original:           " + sb);
        sb.append("!");
        System.out.println("After append:       " + sb);
        sb.insert(6, "Java ");
        System.out.println("After insert:       " + sb);
        sb.delete(0, 6);
        System.out.println("After delete:       " + sb);
        sb.deleteCharAt(4);
        System.out.println("After deleteCharAt: " + sb);
        sb.reverse();
        System.out.println("After reverse:      " + sb);
        sb.reverse();
        sb.replace(0, 4, "Universe");
        System.out.println("After replace:      " + sb);
    }

    public static void compareStringComparisonMethods() {
        System.out.println("\n--- String Comparison Methods ---");
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        System.out.println("str1 = \"Hello\"; str2 = \"Hello\"; str3 = new String(\"Hello\");");
        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println("str1 == str3: " + (str1 == str3));
        System.out.println("str1.equals(str3): " + str1.equals(str3));
        System.out.println("str1.compareTo(\"Hello\"): " + str1.compareTo("Hello"));
        System.out.println("str1.compareTo(\"World\"): " + str1.compareTo("World"));
    }
    
    public static void demonstrateThreadSafety() {
        StringBuilder unsafeBuilder = new StringBuilder();
        StringBuffer safeBuffer = new StringBuffer();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                unsafeBuilder.append("x");
                safeBuffer.append("x");
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("StringBuilder final length: " + unsafeBuilder.length());
        System.out.println("StringBuffer final length:  " + safeBuffer.length());
    }
}
