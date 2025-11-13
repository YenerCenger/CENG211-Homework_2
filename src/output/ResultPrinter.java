package output;

import model.Application;
import java.util.Comparator;
import java.util.List;

public class ResultPrinter {

    // Tüm sonuçları yazdır (ID sıralı garantili)
    public static void printAllResults(List<Application> applications) {
        System.out.println("\n===== ALL APPLICATIONS =====");
        // Extra sorting guarantee for output consistency
        applications.sort(Comparator.comparing(Application::getApplicantID));
        for (Application app : applications) {
            System.out.println(app);
        }
    }

    // Sadece kabul edilenleri yazdır (case-insensitive)
    public static void printAcceptedApplications(List<Application> applications) {
        System.out.println("\n===== ACCEPTED APPLICATIONS =====");
        int count = 0;
        for (Application app : applications) {
            if ("Accepted".equalsIgnoreCase(app.getStatus())) {
                System.out.println(app);
                count++;
            }
        }
        System.out.println("Total Accepted: " + count);
    }

    // Sadece reddedilenleri yazdır (case-insensitive)
    public static void printRejectedApplications(List<Application> applications) {
        System.out.println("\n===== REJECTED APPLICATIONS =====");
        int count = 0;
        for (Application app : applications) {
            if ("Rejected".equalsIgnoreCase(app.getStatus())) {
                System.out.println(app);
                count++;
            }
        }
        System.out.println("Total Rejected: " + count);
    }

    // İstatistikleri yazdır (bonus: acceptance rate)
    public static void printStatistics(List<Application> applications) {
        int totalAccepted = 0;
        int totalRejected = 0;
        int meritAccepted = 0;
        int needAccepted = 0;
        int researchAccepted = 0;

        for (Application app : applications) {
            if ("Accepted".equalsIgnoreCase(app.getStatus())) {
                totalAccepted++;
                String scholarshipType = app.getScholarshipName();
                switch (scholarshipType) {
                    case "Merit":
                        meritAccepted++;
                        break;
                    case "Need":
                        needAccepted++;
                        break;
                    case "Research":
                        researchAccepted++;
                        break;
                }
            } else if ("Rejected".equalsIgnoreCase(app.getStatus())) {
                totalRejected++;
            }
        }

        System.out.println("\n===== STATISTICS =====");
        System.out.println("Total Applications: " + applications.size());
        System.out.println("Total Accepted: " + totalAccepted);
        System.out.println("  - Merit-Based: " + meritAccepted);
        System.out.println("  - Need-Based: " + needAccepted);
        System.out.println("  - Research Grant: " + researchAccepted);
        System.out.println("Total Rejected: " + totalRejected);

        // Bonus: Acceptance rate
        double acceptanceRate = applications.isEmpty() ? 0.0
                : (totalAccepted * 100.0 / applications.size());
        System.out.printf("Acceptance Rate: %.2f%%\n", acceptanceRate);
    }
}
