import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("sample-logs");
        Files.createDirectories(folder);
        Files.write(folder.resolve("app1.txt"), List.of("INFO started", "ERROR failed", "INFO stopped"));
        Files.write(folder.resolve("app2.txt"), List.of("WARNING low memory", "ERROR timeout"));

        String keyword = "ERROR";
        long totalLines = 0, totalMatches = 0;
        try (DirectoryStream<Path> files = Files.newDirectoryStream(folder, "*.txt")) {
            for (Path file : files) {
                long lines = 0, matches = 0;
                try (var reader = Files.newBufferedReader(file)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines++;
                        if (line.contains(keyword)) matches++;
                    }
                }
                BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
                totalLines += lines;
                totalMatches += matches;
                System.out.println("File: " + file.getFileName());
                System.out.println("Size: " + attrs.size() + " bytes");
                System.out.println("Lines: " + lines);
                System.out.println("Keyword matches: " + matches);
                System.out.println();
            }
        }
        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing " + keyword + ": " + totalMatches);
    }
}
