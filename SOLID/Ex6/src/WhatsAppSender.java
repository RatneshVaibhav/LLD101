/**
 * WhatsApp channel sender.
 * Requires E.164 phone numbers (starting with '+').
 * Invalid numbers are logged and surfaced via console — no exception is thrown,
 * so callers can rely on the base NotificationSender contract.
 */
public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        if (n.phone == null || !n.phone.startsWith("+")) {
            System.out.println("WA ERROR: phone must start with + and country code");
            audit.add("WA failed");
            return;
        }
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
