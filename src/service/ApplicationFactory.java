package service;

import model.*;
import java.util.*;

public class ApplicationFactory {

    // Status constants (avoid typos)
    private static final String STATUS_ACCEPTED = "Accepted";
    private static final String STATUS_REJECTED = "Rejected";

    // Tüm applicants için Application nesnelerini oluştur ve değerlendir
    public static List<Application> createAndEvaluateApplications(Map<String, Applicant> applicants) {
        List<Application> applications = new ArrayList<>();

        for (Applicant applicant : applicants.values()) {
            try {
                Application application = applicant.createApplication();

                if (application != null) {
                    // Başvuruyu değerlendir
                    application.evaluate();
                    applications.add(application);
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                // Eksik veri veya geçersiz ID - hata mesajı yazdır ve devam et
                // Unicode characters avoided for terminal compatibility
                System.err.println("Warning: " + e.getMessage() + " - Skipping...");
            }
        }

        return applications;
    }

    // Sonuçları ID'ye göre sırala (null-safe)
    public static List<Application> sortByID(List<Application> applications) {
        List<Application> sorted = new ArrayList<>(applications);
        sorted.sort(Comparator.comparing(
                Application::getApplicantID,
                Comparator.nullsLast(String::compareTo)));
        return sorted;
    }

    // Tek adımda hem değerlendir hem sırala (convenience method)
    public static List<Application> generateEvaluatedAndSorted(Map<String, Applicant> applicants) {
        List<Application> applications = createAndEvaluateApplications(applicants);
        return sortByID(applications);
    }

    // Accepted başvuruları filtrele (null-safe)
    public static List<Application> filterAccepted(List<Application> applications) {
        List<Application> accepted = new ArrayList<>();
        for (Application app : applications) {
            if (STATUS_ACCEPTED.equals(app.getStatus())) {
                accepted.add(app);
            }
        }
        return accepted;
    }

    // Rejected başvuruları filtrele (null-safe)
    public static List<Application> filterRejected(List<Application> applications) {
        List<Application> rejected = new ArrayList<>();
        for (Application app : applications) {
            if (STATUS_REJECTED.equals(app.getStatus())) {
                rejected.add(app);
            }
        }
        return rejected;
    }
}
