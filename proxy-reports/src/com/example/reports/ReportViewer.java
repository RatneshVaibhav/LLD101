package com.example.reports;

/**
 * Viewer that opens a report for a given user.
 * It depends only on the Report interface, so it works with a ReportProxy
 * without knowing anything about the real subject or access rules behind it.
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
