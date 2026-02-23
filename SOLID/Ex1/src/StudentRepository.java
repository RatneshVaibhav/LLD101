/** Persistence contract for student records. Decouples OnboardingService from FakeDb. */
public interface StudentRepository {
    void save(StudentRecord record);
    int count();
}
