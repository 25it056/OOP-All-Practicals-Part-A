import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column { String name(); }

class StudentRow {
    @Column(name = "student_name") private String name;
    @Column(name = "student_age") private int age;
    @Column(name = "student_course") private String course;
    public StudentRow() { }
    @Override public String toString() {
        return "Name: " + name + ", Age: " + age + ", Course: " + course;
    }
}
class RowMapper {
    public static <T> T map(Class<T> type, String[] headers, String[] values)
            throws ReflectiveOperationException {
        if (headers.length != values.length)
            throw new IllegalArgumentException("Header and data lengths must match");
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < headers.length; i++) data.put(headers[i], values[i]);
        T object = type.getDeclaredConstructor().newInstance();
        for (Field field : type.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column == null || !data.containsKey(column.name())) continue;
            String value = data.get(column.name());
            field.setAccessible(true);
            if (field.getType() == String.class) field.set(object, value);
            else if (field.getType() == int.class) field.setInt(object, Integer.parseInt(value));
            else throw new IllegalArgumentException("Unsupported field type: " + field.getType());
        }
        return object;
    }
}

public class ColumnMapperDemo {
    public static void main(String[] args) throws ReflectiveOperationException {
        String[] headers = {"student_name", "student_age", "student_course"};
        String[] values = {"Bhavya", "19", "B.Tech IT"};
        StudentRow student = RowMapper.map(StudentRow.class, headers, values);
        System.out.println(student);
    }
}
