package model;

import java.util.ArrayList;

public abstract class Application {
    protected String ApplicantID;
    protected String ApplicantName;
    protected double GPA;
    protected boolean transcriptValid;
    protected ArrayList<Document> documents = new ArrayList<>();
    protected String status;
    protected String reason;
    protected String type;
    protected int duration;

    // Getter methods for accessing application details from other packages
    public String getApplicantID() {
        return ApplicantID;
    }

    public String getApplicantName() {
        return ApplicantName;
    }

    public double getGPA() {
        return GPA;
    }

    public boolean isTranscriptValid() {
        return transcriptValid;
    }

    public ArrayList<Document> getDocuments() {
        return new ArrayList<>(documents);
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    // Genel kontroller - tüm başvurular için ortak
    public boolean generalChecks() {
        if (!hasDocuments("ENR")) {
            status = "Rejected";
            reason = "Missing Enrollment Certificate";
            return false;
        }
        if (!transcriptValid) {
            status = "Rejected";
            reason = "Missing Transcript";
            return false;
        }
        if (GPA < 2.5) {
            status = "Rejected";
            reason = "GPA is below 2.5";
            return false;
        }
        return true;
    }

    protected boolean hasDocuments(String code) {
        for (Document document : documents) {
            if (document.getType().equals(code))
                return true;
        }
        return false;
    }

    public abstract void evaluate(); // Alt sınıflar bunu override eder
}
