import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter your full name: ");
        String n = s.nextLine();

        System.out.print("Enter a sentence: ");
        String t = s.nextLine();

        // Split full name into parts
        String[] p = n.trim().split("\\s+");
        String f = p[0];   // first name
        String l = p.length > 1 ? p[p.length - 1] : "";  // last name (if exists)

        // Count non-space characters
        long c = t.chars().filter(ch -> ch != ' ').count();

        // Output summary
        System.out.println("\nSummary:");
        System.out.println("Name: " + f + " " + l);
        System.out.println("Sentence: " + t);
        System.out.println("Chars: " + c);
        System.out.println("Upper: " + f.toUpperCase());

        s.close();
    }
}
