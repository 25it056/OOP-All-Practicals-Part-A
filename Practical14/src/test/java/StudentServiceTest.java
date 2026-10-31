import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @Test
    void addingStudentIncreasesCount() {
        StudentService service = new StudentService();
        service.addStudent(new Student(1, "Bhavya"));
        assertEquals(1, service.getCount());
    }

    @Test
    void addingTwoStudentsIncreasesCountByTwo() {
        StudentService service = new StudentService();
        service.addStudent(new Student(1, "Bhavya"));
        service.addStudent(new Student(2, "Amit"));
        assertEquals(2, service.getCount());
    }

    @Test
    void invalidStudentIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Student(0, "Test"));
    }
}
