package model;

import java.util.ArrayList;

public class ResearchGrantApplication extends Application {
    private final ArrayList<Publication> publications;

    public ResearchGrantApplication(String applicantID, String applicantName, double GPA,
            boolean transcriptValid, ArrayList<Document> documents,
            ArrayList<Publication> publications) {
        super(applicantID, applicantName, GPA, transcriptValid, documents);
        this.publications = new ArrayList<>(publications);
    }

    @Override
    public String getScholarshipName() {
        return "Research";
    }

    @Override
    public void evaluate() {
        // Genel kontroller (ENR, transcript, GPA≥2.5)
        if (!generalChecks()) {
            return;
        }

        boolean hasPublication = (publications != null && !publications.isEmpty());
        boolean hasProposal = hasDocument("GRP");

        // 1) En az biri olmalı: Publication VEYA GRP (eligibility check)
        if (!hasPublication && !hasProposal) {
            status = "Rejected";
            reason = "Missing publication or grant proposal";
            return;
        }

        // 2) Publication VARSA → Impact değerlendirmesi ZORUNLU
        if (hasPublication) {
            double totalImpact = 0.0;
            for (Publication pub : publications) {
                totalImpact += pub.getImpactFactor();
            }
            double avgImpact = totalImpact / publications.size();

            if (avgImpact < 1.00) {
                // Impact < 1.0 → REJECT (GRP olsa bile kurtarmaz!)
                status = "Rejected";
                reason = "Publication impact too low";
                return;
            } else if (avgImpact < 1.50) {
                // 1.00 ≤ avg < 1.50 → Half
                scholarshipType = "Half";
                durationInMonths = 6;
                status = "Accepted";
            } else {
                // avg ≥ 1.50 → Full
                scholarshipType = "Full";
                durationInMonths = 12;
                status = "Accepted";
            }
        }
        // 3) Publication YOK ama GRP VAR → Impact hesaplanmaz, Half ver
        else {
            scholarshipType = "Half";
            durationInMonths = 6;
            status = "Accepted";
        }

        // 4) RSV varsa +1 yıl (12 ay) ekle
        if (hasDocument("RSV")) {
            durationInMonths += 12;
        }
    }
}
