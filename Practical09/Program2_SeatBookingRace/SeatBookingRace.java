public class SeatBookingRace {
    static int seatsLeft = 5;
    static int booked = 0;

    static void book() {
        if (seatsLeft > 0) {
            try { Thread.sleep(10); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }
            seatsLeft--;
            booked++;
            System.out.println(Thread.currentThread().getName() + " booked a seat");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(SeatBookingRace::book, "Thread-" + (i + 1));
            threads[i].start();
        }
        for (Thread thread : threads) thread.join();
        System.out.println("Seats left = " + seatsLeft);
        System.out.println("Successful bookings = " + booked);
    }
}
