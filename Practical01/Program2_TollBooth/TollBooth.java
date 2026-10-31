import java.util.Scanner;

public class TollBooth {
    record Vehicle(String number, String type) { }

    public static void main(String[] args) {
        int totalToll = 0;
        int bikeCount = 0, carCount = 0, truckCount = 0;

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter vehicle number (or done): ");
                String number = sc.nextLine().trim();
                if (number.equalsIgnoreCase("done")) break;

                System.out.print("Enter vehicle type (bike/car/truck): ");
                String type = sc.nextLine().trim().toLowerCase();
                Vehicle vehicle = new Vehicle(number, type);

                int toll = switch (vehicle.type()) {
                    case "bike" -> 20;
                    case "car" -> 50;
                    case "truck" -> 150;
                    default -> -1;
                };

                if (toll == -1) {
                    System.out.println("Invalid vehicle type. Vehicle not counted.");
                    continue;
                }

                totalToll += toll;
                switch (vehicle.type()) {
                    case "bike" -> bikeCount++;
                    case "car" -> carCount++;
                    case "truck" -> truckCount++;
                    default -> { /* already validated */ }
                }
            }
        }

        System.out.println("Total toll: " + totalToll);
        int highest = Math.max(bikeCount, Math.max(carCount, truckCount));
        if (highest == 0) {
            System.out.println("Most frequent: none");
        } else {
            StringBuilder frequent = new StringBuilder();
            if (bikeCount == highest) frequent.append("bike ");
            if (carCount == highest) frequent.append("car ");
            if (truckCount == highest) frequent.append("truck ");
            String result = frequent.toString().trim();
            System.out.println("Most frequent: " + (result.contains(" ") ? "tie (" + result + ")" : result));
        }
    }
}
