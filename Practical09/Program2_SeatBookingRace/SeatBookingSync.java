public class SeatBookingSync {
    static int seatsLeft = 5;
    static int booked = 0;

    static synchronized void book() {
        if (seatsLeft > 0) {
            seatsLeft--;
            booked++;
            System.out.println(Thread.currentThread().getName() + " booked a seat");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(SeatBookingSync::book, "Thread-" + (i + 1));
            threads[i].start();
        }
        for (Thread thread : threads) thread.join();
        System.out.println("Seats left = " + seatsLeft);
        System.out.println("Successful bookings = " + booked);
    }
}
