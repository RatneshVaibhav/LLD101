package com.example.reports;

/**
 * ReportProxy sits in front of the expensive RealReport and adds two concerns:
 *
 *  1. Access control — checks the user's role before allowing the report to open.
 *  2. Lazy loading + caching — the RealReport is created only on the first
 *     authorised display() call; subsequent calls reuse the same instance.
 *
 * Clients talk to the Report interface, so swapping proxy for real subject
 * (or vice versa) requires no changes on the calling side.
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;

    private final AccessControl accessControl = new AccessControl();

    // Null until an authorised user requests the report for the first time
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId       = reportId;
        this.title          = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // Guard first — no expensive work until we know the user is allowed in
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[proxy] ACCESS DENIED: "
                    + user.getName() + " (" + user.getRole() + ")"
                    + " cannot open " + title + " [" + classification + "]");
            return;
        }

        // Lazy-load: only instantiate RealReport when truly needed
        if (realReport == null) {
            System.out.println("[proxy] first access — loading report " + reportId);
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] reusing cached report " + reportId);
        }

        realReport.display(user);
    }
}
