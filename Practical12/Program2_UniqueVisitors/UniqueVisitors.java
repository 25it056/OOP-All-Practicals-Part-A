import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class UniqueVisitors {
    public static void main(String[] args) {
        ArrayList<String> visitors = new ArrayList<>();
        visitors.add("Bhavya"); visitors.add("Amit"); visitors.add("Rahul");
        visitors.add("Bhavya"); visitors.add("Priya"); visitors.add("Amit"); visitors.add("Neha");
        HashSet<String> uniqueUsers = new HashSet<>(visitors);
        LinkedHashSet<String> orderedUsers = new LinkedHashSet<>(visitors);
        System.out.println("Unique Visitors: " + uniqueUsers.size());
        System.out.println("First Three Distinct Visitors:");
        int count = 0;
        for (String name : orderedUsers) {
            if (count == 3) break;
            System.out.println(name);
            count++;
        }
    }
}
