import java.util.Arrays;

public class ParallelArraySum {
    static long total;
    static void wrongSum(int[] values, int start, int end) {
        for (int i = start; i < end; i++) total += values[i];
    }
    static synchronized void add(long value) { total += value; }

    public static void main(String[] args) throws InterruptedException {
        int n = 1_000_000;
        int[] values = new int[n];
        Arrays.fill(values, 1);
        Thread[] threads = new Thread[4];

        total = 0;
        for (int k = 0; k < 4; k++) {
            int start = k * n / 4, end = (k + 1) * n / 4;
            threads[k] = new Thread(() -> wrongSum(values, start, end));
            threads[k].start();
        }
        for (Thread thread : threads) thread.join();
        System.out.println("Wrong total = " + total);

        total = 0;
        for (int k = 0; k < 4; k++) {
            int start = k * n / 4, end = (k + 1) * n / 4;
            threads[k] = new Thread(() -> {
                long partial = 0;
                for (int i = start; i < end; i++) partial += values[i];
                add(partial);
            });
            threads[k].start();
        }
        for (Thread thread : threads) thread.join();
        System.out.println("Correct total = " + total);
        System.out.println("Expected total = " + n);
    }
}
