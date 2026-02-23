public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new EmailSender(audit);
        NotificationSender sms   = new SmsSender(audit);
        NotificationSender wa    = new WhatsAppSender(audit);

        email.send(n);
        sms.send(n);
        wa.send(n);  // no try-catch needed — senders honour the base contract

        System.out.println("AUDIT entries=" + audit.size());
    }
}
