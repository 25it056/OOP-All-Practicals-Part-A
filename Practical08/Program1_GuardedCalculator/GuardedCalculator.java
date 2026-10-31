import java.util.InputMismatchException;
import java.util.Scanner;

class DivideByZeroException extends Exception {
    DivideByZeroException(String message) { super(message); }
}

public class GuardedCalculator {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean success = false;
            while (!success) {
                try {
                    System.out.print("Enter first number: ");
                    double a = sc.nextDouble();
                    System.out.print("Enter operator (+, -, *, /): ");
                    char op = sc.next().charAt(0);
                    System.out.print("Enter second number: ");
                    double b = sc.nextDouble();
                    double result = switch (op) {
                        case '+' -> a + b;
                        case '-' -> a - b;
                        case '*' -> a * b;
                        case '/' -> {
                            if (b == 0) throw new DivideByZeroException("Cannot divide by zero.");
                            yield a / b;
                        }
                        default -> throw new IllegalArgumentException("Invalid operator.");
                    };
                    System.out.println("Result: " + result);
                    success = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid number input.");
                    sc.nextLine();
                } catch (DivideByZeroException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println("Attempt logged.");
                }
            }
        }
    }
}
