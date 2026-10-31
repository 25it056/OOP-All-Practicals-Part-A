import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateFiller {
    public static String fill(String template, String[] names, String[] values) {
        if (names.length != values.length)
            throw new IllegalArgumentException("Names and values arrays must have the same length");
        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(template);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = lookupValue(key, names, values);
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    private static String lookupValue(String key, String[] names, String[] values) {
        for (int i = 0; i < names.length; i++)
            if (names[i].equalsIgnoreCase(key)) return values[i];
        return "[?]";
    }

    public static void main(String[] args) {
        String template = "Dear {name}, order {id} ships {date}.";
        String[] names = {"name", "id"};
        String[] values = {"Riya", "A07"};
        System.out.println("Original Template: " + template);
        System.out.println("Filled Result: " + fill(template, names, values));
    }
}
