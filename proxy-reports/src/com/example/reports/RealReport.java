package com.example.reports;

/**
 * RealReport is the heavy-weight real subject in the Proxy pattern.
 *
 * It owns the expensive I/O logic. Construction is cheap; the actual disk
 * read happens only inside display(), which is what lets the proxy defer
 * (lazy-load) truly expensive work until it is actually needed.
 */
public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;

    // Loaded lazily — null until the first display() call
    private String cachedContent;

    public RealReport(String reportId, String title, String classification) {
        this.reportId       = reportId;
        this.title          = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // Load once; reuse on subsequent calls through the same RealReport instance
        if (cachedContent == null) {
            cachedContent = loadFromDisk();
        }
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + cachedContent);
    }

    public String getClassification() {
        return classification;
    }

    // Simulates a slow disk read
    private String loadFromDisk() {
        System.out.println("[disk] loading report " + reportId + " ...");
        try {
            Thread.sleep(120);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Internal report body for " + title;
    }
}
