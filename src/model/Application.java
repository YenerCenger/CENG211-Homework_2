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
