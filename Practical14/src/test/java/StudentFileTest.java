import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentFileTest {
    @Test
    void saveAndReloadData() throws Exception {
        File file = File.createTempFile("students", ".dat");
        try {
            Student original = new Student(25, "Bhavya");
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(original);
            }
            Student loaded;
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                loaded = (Student) in.readObject();
            }
            assertEquals(original.getId(), loaded.getId());
            assertEquals(original.getName(), loaded.getName());
        } finally {
            if (!file.delete()) file.deleteOnExit();
        }
    }
}
