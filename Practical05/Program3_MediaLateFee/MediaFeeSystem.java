abstract class Media {
    protected final String title;
    protected final int lateDays;
    Media(String title, int lateDays) {
        if (lateDays < 0) throw new IllegalArgumentException("Late days cannot be negative");
        this.title = title;
        this.lateDays = lateDays;
    }
    abstract double calculateFee();
}
class Book extends Media {
    Book(String title, int lateDays) { super(title, lateDays); }
    @Override double calculateFee() { return lateDays * 2.0; }
}
class DVD extends Media {
    DVD(String title, int lateDays) { super(title, lateDays); }
    @Override double calculateFee() { return lateDays * 5.0; }
}
class Magazine extends Media {
    Magazine(String title, int lateDays) { super(title, lateDays); }
    @Override double calculateFee() { return lateDays * 1.5; }
}
public class MediaFeeSystem {
    public static void main(String[] args) {
        Media[] items = {
            new Book("Java Programming", 3),
            new DVD("Avengers", 2),
            new Magazine("Technology", 4)
        };
        double total = 0;
        for (Media item : items) {
            double fee = item.calculateFee();
            System.out.println("Media: " + item.title);
            System.out.println("Late Fee: Rs. " + fee);
            total += fee;
        }
        System.out.println("--------------------");
        System.out.println("Total Late Fee: Rs. " + total);
    }
}
