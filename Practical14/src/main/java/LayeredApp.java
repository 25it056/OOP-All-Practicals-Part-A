import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int id;
    private final String name;
    Student(int id, String name) {
        if (id <= 0) throw new IllegalArgumentException("Invalid student ID");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Student name is required");
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    @Override public String toString() { return "Student{id=" + id + ", name='" + name + "'}"; }
}

class StudentService {
    private final List<Student> students = new ArrayList<>();
    public void addStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("Student cannot be null");
        students.add(student);
    }
    public int getCount() { return students.size(); }
    public List<Student> getStudents() { return new ArrayList<>(students); }
}

public class LayeredApp {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        service.addStudent(new Student(1, "Bhavya"));
        service.addStudent(new Student(2, "Amit"));
        System.out.println("Total Students: " + service.getCount());
        for (Student student : service.getStudents()) System.out.println(student);
    }
}
