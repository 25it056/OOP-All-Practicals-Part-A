public class DeadlockFix {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    static void work(String name) {
        // Every thread acquires locks in the same order: A, then B.
        synchronized (lockA) {
            System.out.println(name + " got lock A");
            synchronized (lockB) {
                System.out.println(name + " got lock B");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> work("Thread 1"));
        Thread t2 = new Thread(() -> work("Thread 2"));
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("No deadlock");
    }
}
