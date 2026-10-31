public class ParkingLot {
    private int twoWheelers;
    private int fourWheelers;
    private final int twoCap;
    private final int fourCap;
    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        if (twoCap < 0 || fourCap < 0) throw new IllegalArgumentException("Capacity cannot be negative");
        this.twoCap = twoCap;
        this.fourCap = fourCap;
    }

    public void park(String type) {
        if ("two".equalsIgnoreCase(type)) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Two-wheeler parked");
            } else System.out.println("Full");
        } else if ("four".equalsIgnoreCase(type)) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Four-wheeler parked");
            } else System.out.println("Full");
        } else System.out.println("Invalid vehicle type");
    }

    public void leave(String type) {
        if ("two".equalsIgnoreCase(type)) {
            if (twoWheelers > 0) { twoWheelers--; System.out.println("Two-wheeler left"); }
        } else if ("four".equalsIgnoreCase(type)) {
            if (fourWheelers > 0) { fourWheelers--; System.out.println("Four-wheeler left"); }
        }
    }

    public int getTwoWheelers() { return twoWheelers; }
    public int getFourWheelers() { return fourWheelers; }
    public static long getRevenue() { return revenue; }

    public static void main(String[] args) {
        ParkingLot p = new ParkingLot(2, 2);
        p.park("two"); p.park("two"); p.park("two");
        p.park("four"); p.park("four"); p.park("four");
        p.leave("two"); p.leave("four");
        p.park("two"); p.park("four");
        System.out.println("Two-Wheelers: " + p.getTwoWheelers());
        System.out.println("Four-Wheelers: " + p.getFourWheelers());
        System.out.println("Revenue: " + getRevenue());
    }
}
