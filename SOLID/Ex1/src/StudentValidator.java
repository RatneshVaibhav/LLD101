import java.util.*;

/** Single responsibility: validate student fields and collect error messages. */
public class StudentValidator {

    private static final Set<String> ALLOWED_PROGRAMS =
            new LinkedHashSet<>(Arrays.asList("CSE", "AI", "SWE"));

    public List<String> validate(String name, String email, String phone, String program) {
        List<String> errors = new ArrayList<>();
        if (name == null || name.isBlank()) {
            errors.add("name is required");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            errors.add("email is invalid");
        }
        if (phone == null || phone.isBlank() || !phone.chars().allMatch(Character::isDigit)) {
            errors.add("phone is invalid");
        }
        if (!ALLOWED_PROGRAMS.contains(program)) {
            errors.add("program is invalid");
        }
        return errors;
    }
}
