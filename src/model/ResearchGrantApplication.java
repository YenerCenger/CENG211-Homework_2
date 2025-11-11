package model;

import java.util.ArrayList;

// Developed by Ebu
public class ResearchGrantApplication extends Application {

    private final ArrayList<Publication> publications;
    private final double averageImpact;

    public ResearchGrantApplication(Applicant applicant) {
        this.ApplicantID = String.valueOf(applicant.getApplicantID());
        this.ApplicantName = applicant.getName();
        this.GPA = applicant.getGpa();
        this.transcriptValid = applicant.isTranscriptValid();
        this.documents = new ArrayList<>(applicant.getDocuments());
        this.publications = new ArrayList<>(applicant.getPublications());
        this.averageImpact = calculateAverageImpact();
    }

    @Override
    public void evaluate() {
        if (!generalChecks()) {
            return;
        }

        if (!hasResearchMaterial()) {
            status = "Rejected";
            reason = "Missing publication or proposal";
            return;
        }

        if (publications.isEmpty()) {
            status = "Rejected";
            reason = "Publication impact too low";
            return;
        }

        if (averageImpact >= 1.50) {
            status = "Accepted";
            type = "Full";
        } else if (averageImpact >= 1.00) {
            status = "Accepted";
            type = "Half";
        } else {
            status = "Rejected";
            reason = "Publication impact too low";
            return;
        }

        setBaseDuration();

        if (hasDocuments("RSV")) {
            duration += 12;
        }
    }

    private double calculateAverageImpact() {
        if (publications.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (Publication publication : publications) {
            total += publication.getImpactFactor();
        }
        return total / publications.size();
    }

    private boolean hasResearchMaterial() {
        return !publications.isEmpty() || hasDocuments("GRP");
    }

    private void setBaseDuration() {
        if ("Full".equals(type)) {
            duration = 12;
        } else if ("Half".equals(type)) {
            duration = 6;
        }
    }
}
