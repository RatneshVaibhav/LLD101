/** Contributes an incremental cost for a single AddOn. */
public interface AddOnPricer {
    boolean supports(AddOn addOn);
    double price();
}
