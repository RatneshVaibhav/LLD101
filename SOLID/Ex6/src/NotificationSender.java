/**
 * Base contract for notification delivery channels.
 * <p><b>Contract</b>:
 * <ul>
 *   <li>{@code send(n)} <em>must not throw</em> for any non-null notification.</li>
 *   <li>Channel-specific limitations (e.g. phone format) must be handled gracefully
 *       inside the implementation — callers should not need channel-specific workarounds.</li>
 * </ul>
 */
public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }
    public abstract void send(Notification n);
}
