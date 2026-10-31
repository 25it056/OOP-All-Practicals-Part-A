import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank { }
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength { int value(); }

class SignupForm {
    @NotBlank private String username;
    @NotBlank @MaxLength(40) private String email;
    @MaxLength(12) private String displayName;
    SignupForm(String username, String email, String displayName) {
        this.username = username; this.email = email; this.displayName = displayName;
    }
}
class FormChecker {
    public static List<String> validate(Object object) {
        List<String> errors = new ArrayList<>();
        if (object == null) { errors.add("Object cannot be null"); return errors; }
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(object);
                if (field.isAnnotationPresent(NotBlank.class)
                        && (value == null || value.toString().trim().isEmpty()))
                    errors.add(field.getName() + " must not be blank");
                MaxLength max = field.getAnnotation(MaxLength.class);
                if (max != null && value != null && value.toString().length() > max.value())
                    errors.add(field.getName() + " length must be <= " + max.value());
            } catch (IllegalAccessException | SecurityException e) {
                errors.add("Cannot access field: " + field.getName());
            }
        }
        return errors;
    }
}

public class FormValidatorDemo {
    public static void main(String[] args) {
        SignupForm form = new SignupForm(" ", "verylongemailaddress@example.com",
                "This display name is too long");
        List<String> errors = FormChecker.validate(form);
        if (errors.isEmpty()) System.out.println("Form is valid");
        else errors.forEach(System.out::println);
    }
}
