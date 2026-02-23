import java.util.Collections;
import java.util.List;

/** Fails if the student carries any active disciplinary flag. */
public class DisciplinaryRule implements EligibilityRule {
    @Override
    public List<String> check(StudentProfile student) {
        if (student.disciplinaryFlag != LegacyFlags.NONE) {
            return Collections.singletonList("disciplinary flag present");
        }
        return Collections.emptyList();
    }
}
