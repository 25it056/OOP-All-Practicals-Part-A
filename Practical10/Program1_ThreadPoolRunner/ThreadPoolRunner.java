import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            final int taskNumber = i;
            executor.execute(() -> {
                System.out.println("Task " + taskNumber + " running in " + Thread.currentThread().getName());
                try { Thread.sleep(200); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
        }
        executor.shutdown();
        if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
            executor.shutdownNow();
            System.out.println("Some tasks did not finish in time.");
        } else System.out.println("Done");
    }
}
