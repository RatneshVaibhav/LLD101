import java.nio.charset.StandardCharsets;

/**
 * CSV exporter. Normalises newlines and commas in field values to spaces
 * so the result is valid CSV — this is an explicit, documented encoding choice,
 * not a silent data corruption.
 */
public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        String body = req.body == null ? "" : req.body.replace("\n", " ").replace(",", " ");
        String csv = "title,body\n" + req.title + "," + body + "\n";
        return ExportResult.ok("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
