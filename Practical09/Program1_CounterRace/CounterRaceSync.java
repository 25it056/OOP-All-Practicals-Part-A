public class CounterRaceSync {
    static int counter = 0;
    static synchronized void increment() { counter++; }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_000; j++) increment();
            });
            threads[i].start();
        }
        for (Thread thread : threads) thread.join();
        System.out.println("Counter = " + counter);
        System.out.println("Expected = 1000000");
    }
}
