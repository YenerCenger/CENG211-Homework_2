package model;

import java.util.ArrayList;

public class NeedBasedApplication extends Application {
    private final FamilyInfo familyInfo;

    public NeedBasedApplication(String applicantID, String applicantName, double GPA,
            boolean transcriptValid, ArrayList<Document> documents,
            FamilyInfo familyInfo) {
        super(applicantID, applicantName, GPA, transcriptValid, documents);
        this.familyInfo = familyInfo;
    }

    @Override
    public String getScholarshipName() {
        return "Need";
    }

    @Override
    public void evaluate() {
        // Genel kontroller (ENR, transcript, GPA≥2.5)
        if (!generalChecks()) {
            return;
        }

        // İki ayrı eşik: Full için 10,000 ve Half için 15,000
        double fullThreshold = 10000.0;
        double halfThreshold = 15000.0;

        // SAV varsa limitleri %20 artır
        if (hasDocument("SAV")) {
            fullThreshold *= 1.2; // 12,000
            halfThreshold *= 1.2; // 18,000
        }

        // 3+ bağımlı varsa limitleri %10 artır
        if (familyInfo.getDependents() >= 3) {
            fullThreshold *= 1.1;
            halfThreshold *= 1.1;
        }

        double income = familyInfo.getFamilyIncome();

        // Gelir seviyesine göre değerlendirme
        if (income <= fullThreshold) {
            // income ≤ 10,000 (adjusted) → Full
            scholarshipType = "Full";
            status = "Accepted";

        } else if (income <= halfThreshold) {
            // 10,000 < income ≤ 15,000 (adjusted) → Half
            scholarshipType = "Half";
            status = "Accepted";

        } else {
            // income > 15,000 (adjusted) → Rejected
            status = "Rejected";
            reason = "Financial status unstable";
            return;
        }

        // Duration: PDF'e göre sabit 1 yıl
        duration = 1;
    }
}
