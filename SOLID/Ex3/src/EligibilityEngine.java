import java.util.*;

/**
 * Evaluates placement eligibility by running each registered rule in order.
 * Adding a new rule means adding an EligibilityRule implementation and
 * registering it here — no changes to evaluate() needed.
 */
public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) {
        this.store = store;
        // Rules are checked in order; first failing rule wins for a short-circuit check.
        // Using a list makes the pipeline open for extension.
        this.rules = Arrays.asList(
            new DisciplinaryRule(),
            new CgrRule(),
            new AttendanceRule(),
            new CreditsRule()
        );
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            List<String> failures = rule.check(s);
            if (!failures.isEmpty()) {
                // Mirrors original short-circuit: first failing rule stops the check.
                return new EligibilityEngineResult("NOT_ELIGIBLE", failures);
            }
        }

        return new EligibilityEngineResult("ELIGIBLE", reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
