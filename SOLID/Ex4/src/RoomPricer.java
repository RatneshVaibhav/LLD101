/** Pricing abstraction for a specific room type. */
public interface RoomPricer {
    boolean supports(int roomType);
    double monthlyBase();
}
