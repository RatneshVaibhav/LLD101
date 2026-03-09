package com.example.reports;

/**
 * CampusVault demo entry point.
 *
 * Clients now receive Report references (which happen to be proxies) instead
 * of concrete ReportFile objects. The proxy enforces access rules and defers
 * the expensive disk read until the first authorised display() call.
 */
public class App {

    public static void main(String[] args) {
        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin   = new User("Kshitij", "ADMIN");

        // Proxies hold only lightweight metadata at this point — no disk I/O yet
        Report publicReport  = new ReportProxy("R-101", "Orientation Plan", "PUBLIC");
        Report facultyReport = new ReportProxy("R-202", "Midterm Review",   "FACULTY");
        Report adminReport   = new ReportProxy("R-303", "Budget Audit",     "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===");

        // student can read a public report
        viewer.open(publicReport, student);
        System.out.println();

        // student tries to open a faculty-only report → should be denied
        viewer.open(facultyReport, student);
        System.out.println();

        // faculty opens the same report → first real load happens here
        viewer.open(facultyReport, faculty);
        System.out.println();

        // admin opens admin-only report for the first time
        viewer.open(adminReport, admin);
        System.out.println();

        // admin opens the same report again → proxy reuses cached RealReport
        viewer.open(adminReport, admin);
    }
}
