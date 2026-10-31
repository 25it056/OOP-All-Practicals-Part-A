import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private transient String temporaryNote;
    Student(int id, String name, String temporaryNote) {
        this.id = id; this.name = name; this.temporaryNote = temporaryNote;
    }
    @Override public String toString() {
        return "ID: " + id + ", Name: " + name + ", Temporary Note: " + temporaryNote;
    }
}

public class SaveLoadDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student[] students = {
            new Student(1, "Asha", "Temporary data"),
            new Student(2, "Ravi", "Not saved")
        };
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            out.writeObject(students);
        }
        Student[] loaded;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.dat"))) {
            loaded = (Student[]) in.readObject();
        }
        System.out.println("Loaded Students:");
        for (Student student : loaded) System.out.println(student);
        System.out.println("The transient temporaryNote values are null after loading.");
    }
}
