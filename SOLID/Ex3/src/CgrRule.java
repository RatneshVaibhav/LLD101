import java.util.Collections;
import java.util.List;

/** Fails if the student's CGR is below the minimum threshold. */
public class CgrRule implements EligibilityRule {
    private static final double MIN_CGR = 8.0;

    @Override
    public List<String> check(StudentProfile student) {
        if (student.cgr < MIN_CGR) {
            return Collections.singletonList("CGR below 8.0");
        }
        return Collections.emptyList();
    }
}
