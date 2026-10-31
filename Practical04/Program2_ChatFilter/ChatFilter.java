import java.util.Scanner;

public class ChatFilter {
    public static String filterLogs(String[] logs, String keyword) {
        int matches = 0;
        StringBuilder report = new StringBuilder();
        String search = keyword.toLowerCase();

        for (String line : logs) {
            if (line == null || line.isBlank()) continue;
            String[] parts = line.trim().split("\\s+", 3);
            if (parts.length < 3) continue;
            String time = parts[0], user = parts[1], message = parts[2];
            if (message.toLowerCase().contains(search)) {
                matches++;
                report.append(time).append(' ').append(user).append(": ")
                      .append(message).append(System.lineSeparator());
            }
        }
        return "Matches: " + matches + System.lineSeparator() + report;
    }

    public static void main(String[] args) {
        String[] logs = {
            "10:05 alice Hello there",
            "10:06 bob corrupt line",
            "10:07 charlie HELLO world!",
            "10:08 System"
        };
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter keyword to filter: ");
            String keyword = sc.nextLine();
            System.out.println("--- Filter Results ---");
            System.out.print(filterLogs(logs, keyword));
        }
    }
}
