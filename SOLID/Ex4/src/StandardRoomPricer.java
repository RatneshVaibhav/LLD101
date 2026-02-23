/** Pricing for each supported room type. */
public class StandardRoomPricer implements RoomPricer {
    private final int type;
    private final double base;

    public StandardRoomPricer(int type, double base) {
        this.type = type;
        this.base = base;
    }

    @Override
    public boolean supports(int roomType) { return this.type == roomType; }

    @Override
    public double monthlyBase() { return base; }
}
