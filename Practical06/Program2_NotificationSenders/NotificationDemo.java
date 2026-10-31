@FunctionalInterface
interface Notifier { void send(String message); }

public class NotificationDemo {
    public static void main(String[] args) {
        Notifier email = message -> System.out.println("Email: " + message);
        Notifier sms = message -> System.out.println("SMS: " + message);
        Notifier[] senders = {email, sms};
        boolean[] urgent = {true, false};
        String message = "Exam reminder";
        for (int i = 0; i < senders.length; i++) {
            senders[i].send(message);
            if (urgent[i]) senders[i].send("URGENT: " + message);
        }
    }
}
