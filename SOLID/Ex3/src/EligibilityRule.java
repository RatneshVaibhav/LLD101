import java.util.List;

/**
 * A single eligibility check. Returns an empty list when the student passes,
 * or a list of failure reasons when they do not.
 */
public interface EligibilityRule {
    List<String> check(StudentProfile student);
}
