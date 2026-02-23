import java.util.Collections;
import java.util.List;

/** Fails if the student has not earned the minimum number of credits. */
public class CreditsRule implements EligibilityRule {
    private static final int MIN_CREDITS = 20;

    @Override
    public List<String> check(StudentProfile student) {
        if (student.earnedCredits < MIN_CREDITS) {
            return Collections.singletonList("credits below 20");
        }
        return Collections.emptyList();
    }
}
