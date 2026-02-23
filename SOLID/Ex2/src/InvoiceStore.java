/** Persistence contract for invoices; implementations decide how to actually store them. */
public interface InvoiceStore {
    void save(String invoiceId, String text);
    int countLines(String invoiceId);
}
