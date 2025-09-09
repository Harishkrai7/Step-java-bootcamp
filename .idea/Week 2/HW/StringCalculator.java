import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a mathematical expression (e.g., 15 + 23 * 2): ");
        String expression = scanner.nextLine();

        if (isValidExpression(expression)) {
            evaluateAndShowSteps(expression);
        } else {
            System.out.println("Invalid expression format.");
        }
        scanner.close();
    }

    public static boolean isValidExpression(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!(Character.isDigit(c) || c == '+' || c == '-' || c == '*' || c == '/' || Character.isWhitespace(c))) {
                return false;
            }
        }
        return true;
    }

    public static void evaluateAndShowSteps(String expression) {
        System.out.println("\n--- Calculation Steps ---");
        System.out.println("Original: " + expression);

        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        
        StringBuilder currentNumber = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                currentNumber.append(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (currentNumber.length() > 0) {
                    numbers.add(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                }
                operators.add(c);
            }
        }
        if (currentNumber.length() > 0) {
            numbers.add(Double.parseDouble(currentNumber.toString()));
        }

        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '*' || operators.get(i) == '/') {
                double left = numbers.get(i);
                double right = numbers.get(i + 1);
                double result = (operators.get(i) == '*') ? left * right : left / right;
                System.out.println("Step: " + left + " " + operators.get(i) + " " + right + " = " + result);
                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }

        while (!operators.isEmpty()) {
            double left = numbers.get(0);
            double right = numbers.get(1);
            char op = operators.get(0);
            double result = (op == '+') ? left + right : left - right;
            System.out.println("Step: " + left + " " + op + " " + right + " = " + result);
            numbers.set(0, result);
            numbers.remove(1);
            operators.remove(0);
        }

        System.out.println("\nFinal Result: " + numbers.get(0));
    }
}
