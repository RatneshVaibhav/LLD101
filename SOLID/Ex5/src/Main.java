public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();

        System.out.println("PDF: " + safe(pdf, req));
        System.out.println("CSV: " + safe(csv, req));
        System.out.println("JSON: " + safe(json, req));
    }

    private static String safe(Exporter e, ExportRequest r) {
        ExportResult out = e.export(r);
        if (out.isError) {
            return "ERROR: " + out.errorMessage;
        }
        return "OK bytes=" + out.bytes.length;
    }
}
