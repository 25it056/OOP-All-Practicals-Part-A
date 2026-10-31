public class ProducerConsumer {
    private static int item;
    private static boolean available = false;

    static synchronized void produce(int value) throws InterruptedException {
        while (available) ProducerConsumer.class.wait();
        item = value;
        available = true;
        System.out.println("Produced: " + value);
        ProducerConsumer.class.notifyAll();
    }

    static synchronized int consume() throws InterruptedException {
        while (!available) ProducerConsumer.class.wait();
        int value = item;
        available = false;
        System.out.println("Consumed: " + value);
        ProducerConsumer.class.notifyAll();
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            try { for (int i = 1; i <= 10; i++) produce(i); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, "Producer");
        Thread consumer = new Thread(() -> {
            try { for (int i = 1; i <= 10; i++) consume(); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, "Consumer");
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Finished");
    }
}
