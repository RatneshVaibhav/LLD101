import java.util.Collections;
import java.util.List;

/** Fails if the student's attendance percentage is below the minimum. */
public class AttendanceRule implements EligibilityRule {
    private static final int MIN_ATTENDANCE = 75;

    @Override
    public List<String> check(StudentProfile student) {
        if (student.attendancePct < MIN_ATTENDANCE) {
            return Collections.singletonList("attendance below 75");
        }
        return Collections.emptyList();
    }
}
