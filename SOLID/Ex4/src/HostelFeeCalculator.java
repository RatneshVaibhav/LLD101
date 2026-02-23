import java.util.*;

/**
 * Calculates hostel fees by iterating pricing components.
 * New room types or add-ons can be supported by adding a pricer — no changes here.
 */
public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final List<RoomPricer> roomPricers;
    private final List<AddOnPricer> addOnPricers;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
        this.roomPricers = Arrays.asList(
            new StandardRoomPricer(LegacyRoomTypes.SINGLE, 14000.0),
            new StandardRoomPricer(LegacyRoomTypes.DOUBLE, 15000.0),
            new StandardRoomPricer(LegacyRoomTypes.TRIPLE, 12000.0),
            new StandardRoomPricer(LegacyRoomTypes.DELUXE, 16000.0)
        );
        this.addOnPricers = Arrays.asList(
            new StandardAddOnPricer(AddOn.MESS,    1000.0),
            new StandardAddOnPricer(AddOn.LAUNDRY,  500.0),
            new StandardAddOnPricer(AddOn.GYM,      300.0)
        );
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        double base = roomPricers.stream()
            .filter(p -> p.supports(req.roomType))
            .findFirst()
            .map(RoomPricer::monthlyBase)
            .orElse(16000.0);

        double addOnsTotal = req.addOns.stream()
            .flatMap(a -> addOnPricers.stream().filter(p -> p.supports(a)))
            .mapToDouble(AddOnPricer::price)
            .sum();

        return new Money(base + addOnsTotal);
    }
}
