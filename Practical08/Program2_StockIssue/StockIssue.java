import java.util.HashMap;
import java.util.Map;

class OutOfStockException extends Exception {
    private final int shortfall;
    OutOfStockException(String message, int shortfall) { super(message); this.shortfall = shortfall; }
    public int getShortfall() { return shortfall; }
}
class InvalidQuantityException extends Exception {
    InvalidQuantityException(String message) { super(message); }
}
class Warehouse {
    private final Map<String, Integer> stock = new HashMap<>();
    Warehouse() { stock.put("Pen", 10); stock.put("Book", 5); stock.put("Bag", 2); }
    void issue(String item, int quantity) throws OutOfStockException, InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException("Quantity must be greater than zero.");
        if (!stock.containsKey(item)) throw new OutOfStockException("Unknown item: " + item, quantity);
        int available = stock.get(item);
        if (quantity > available)
            throw new OutOfStockException("Not enough stock for " + item + ". Shortfall: " + (quantity - available), quantity - available);
        stock.put(item, available - quantity);
        System.out.println("Issued " + quantity + " " + item + ". Remaining: " + stock.get(item));
    }
}

public class StockIssue {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        String[][] requests = {{"Pen", "4"}, {"Book", "8"}, {"Bag", "0"}, {"Bag", "1"}};
        for (String[] request : requests) {
            try {
                warehouse.issue(request[0], Integer.parseInt(request[1]));
            } catch (OutOfStockException e) {
                System.out.println("Out of stock: " + e.getMessage());
                System.out.println("Shortfall: " + e.getShortfall());
            } catch (InvalidQuantityException | NumberFormatException e) {
                System.out.println("Invalid quantity: " + e.getMessage());
            }
        }
    }
}
