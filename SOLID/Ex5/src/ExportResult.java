/**
 * Outcome of an export operation.
 * Either a successful payload or a named error — never a thrown exception.
 */
public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean isError;
    public final String errorMessage;

    private ExportResult(String contentType, byte[] bytes, boolean isError, String errorMessage) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.isError = isError;
        this.errorMessage = errorMessage;
    }

    public static ExportResult ok(String contentType, byte[] bytes) {
        return new ExportResult(contentType, bytes, false, null);
    }

    public static ExportResult error(String message) {
        return new ExportResult(null, new byte[0], true, message);
    }
}
