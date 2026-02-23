/**
 * Email channel sender.
 * Long message bodies are trimmed to 40 characters — a known email channel constraint
 * that is documented here rather than enforced by the base type.
 */
public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        String body = n.body;
        if (body.length() > 40) body = body.substring(0, 40);
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
        audit.add("email sent");
    }
}
