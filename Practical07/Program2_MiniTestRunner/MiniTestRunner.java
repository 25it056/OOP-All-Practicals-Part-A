import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run { }

class SampleTests {
    @Run public void testAddition() { System.out.println("testAddition passed"); }
    @Run public void testText() { System.out.println("testText passed"); }
    public void helperMethod() { System.out.println("This method should not run"); }
}

public class MiniTestRunner {
    public static void main(String[] args) throws ReflectiveOperationException {
        SampleTests tests = new SampleTests();
        int count = 0;
        Method[] methods = tests.getClass().getDeclaredMethods();
        Arrays.sort(methods, (a, b) -> a.getName().compareTo(b.getName()));
        for (Method method : methods) {
            if (method.isAnnotationPresent(Run.class) && method.getParameterCount() == 0) {
                method.invoke(tests);
                count++;
            }
        }
        System.out.println("Total methods executed: " + count);
    }
}
