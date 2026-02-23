/**
 * Base contract for all exporters.
 * <p><b>Contract</b>:
 * <ul>
 *   <li>{@code export(req)} <em>must not throw</em> for any non-null request.</li>
 *   <li>If a format cannot handle the request, it must return {@link ExportResult#error}.</li>
 *   <li>Callers can rely on substitutability with no format-specific workarounds.</li>
 * </ul>
 */
public abstract class Exporter {
    public abstract ExportResult export(ExportRequest req);
}
