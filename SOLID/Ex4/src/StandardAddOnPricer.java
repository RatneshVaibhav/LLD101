/** Price table for a single AddOn type. */
public class StandardAddOnPricer implements AddOnPricer {
    private final AddOn addOn;
    private final double price;

    public StandardAddOnPricer(AddOn addOn, double price) {
        this.addOn = addOn;
        this.price = price;
    }

    @Override
    public boolean supports(AddOn target) { return this.addOn == target; }

    @Override
    public double price() { return price; }
}
