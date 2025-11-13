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

        // En az biri olmalı: Publication VEYA GRP
        if (!hasPublication && !hasProposal) {
            status = "Rejected";
            reason = "Missing publication or grant proposal";
            return;
        }

        // Ortalama Impact Factor hesaplama (publication varsa)
        double avgImpact = 0.0;
        if (hasPublication) {
            double totalImpact = 0.0;
            for (Publication pub : publications) {
                totalImpact += pub.getImpactFactor();
            }
            avgImpact = totalImpact / publications.size();
        }

        // Scholarship türü ve base duration belirle
        if (avgImpact >= 1.50) {
            // avg ≥ 1.50 → Full Scholarship
            scholarshipType = "Full";
            duration = 1; // Base: 1 yıl
            status = "Accepted";

        } else if (avgImpact >= 1.00) {
            // 1.00 ≤ avg < 1.50 → Half Scholarship
            scholarshipType = "Half";
            duration = 0; // Base: 6 ay (0.5 yıl olarak gösterilebilir)
            status = "Accepted";

        } else {
            // avg < 1.00 → Rejected
            status = "Rejected";
            reason = "Publication impact too low";
            return;
        }

        // RSV varsa +1 yıl ekle
        if (hasDocument("RSV")) {
            duration += 1;
        }
    }
}
