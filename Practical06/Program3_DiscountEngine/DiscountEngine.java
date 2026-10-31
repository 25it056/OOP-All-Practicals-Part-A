import java.util.List;
import java.util.Scanner;

@FunctionalInterface
interface Rule { double apply(double price); }

public class DiscountEngine {
    public static void main(String[] args) {
        List<Double> prices = List.of(100.0, 250.0, 500.0);
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("1. 10% discount");
            System.out.println("2. 20% discount");
            System.out.print("Choose discount: ");
            int choice = sc.nextInt();
            Rule rule;
            if (choice == 1) rule = price -> price * 0.90;
            else if (choice == 2) rule = price -> price * 0.80;
            else { System.out.println("Invalid choice"); return; }
            for (double price : prices)
                System.out.printf("%.2f -> %.2f%n", price, rule.apply(price));
        }
    }
}
