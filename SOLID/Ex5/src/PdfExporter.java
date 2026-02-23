import java.nio.charset.StandardCharsets;

/**
 * PDF exporter with a size limit. Exceeding the limit returns an error result
 * rather than throwing — the base contract is honoured.
 */
public class PdfExporter extends Exporter {
    private static final int PDF_MAX_CHARS = 20;

    @Override
    public ExportResult export(ExportRequest req) {
        if (req.body != null && req.body.length() > PDF_MAX_CHARS) {
            return ExportResult.error("PDF cannot handle content > 20 chars");
        }
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return ExportResult.ok("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
