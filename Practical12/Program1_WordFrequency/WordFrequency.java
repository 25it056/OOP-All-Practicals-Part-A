import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "Java is easy and Java is powerful";
        Map<String, Integer> frequency = new HashMap<>();
        String[] words = paragraph.toLowerCase().split("\\s+");
        for (String word : words) frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequency.entrySet());
        entries.sort((a, b) -> {
            int byCount = b.getValue().compareTo(a.getValue());
            return byCount != 0 ? byCount : a.getKey().compareTo(b.getKey());
        });
        System.out.println("Word Frequency:");
        for (Map.Entry<String, Integer> entry : entries)
            System.out.println(entry.getKey() + " = " + entry.getValue());
    }
}
