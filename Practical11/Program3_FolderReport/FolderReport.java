import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FolderReport {
    public static void main(String[] args) throws IOException {
        Path root = Paths.get(args.length > 0 ? args[0] : ".").toAbsolutePath().normalize();
        Path report = Paths.get("folder-report.txt").toAbsolutePath().normalize();
        if (!Files.isDirectory(root)) throw new IllegalArgumentException("Folder does not exist: " + root);

        try (var writer = Files.newBufferedWriter(report)) {
            Files.walkFileTree(root, new SimpleFileVisitor<>() {
                @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (!file.toAbsolutePath().normalize().equals(report)) {
                        writer.write(file.getFileName() + " | Size: " + attrs.size()
                                + " bytes | Modified: " + attrs.lastModifiedTime());
                        writer.newLine();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        System.out.println("Report created successfully.");
        System.out.println("File: " + report);
    }
}
