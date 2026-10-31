import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leaderboard {
    public static void main(String[] args) {
        Map<String, Integer> players = new HashMap<>();
        players.put("Bhavya", 950); players.put("Amit", 800); players.put("Rahul", 900);
        players.put("Priya", 950); players.put("Neha", 700);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(players.entrySet());
        list.sort((a, b) -> {
            int byScore = b.getValue().compareTo(a.getValue());
            return byScore != 0 ? byScore : a.getKey().compareTo(b.getKey());
        });
        int topN = 3;
        System.out.println("Top " + topN + " Players:");
        for (int i = 0; i < Math.min(topN, list.size()); i++)
            System.out.println(list.get(i).getKey() + " - " + list.get(i).getValue());
    }
}
