package model;

import java.util.ArrayList;

public abstract class Application {
    protected final String applicantID;
    protected final String applicantName;
    protected final double GPA;
    protected final boolean transcriptValid;
    protected final ArrayList<Document> documents;
    protected String status;
    protected String reason;
    protected String scholarshipType;
    protected int duration;

    // Constructor
    public Application(String applicantID, String applicantName, double GPA,
            boolean transcriptValid, ArrayList<Document> documents) {
        this.applicantID = applicantID;
        this.applicantName = applicantName;
        this.GPA = GPA;
        this.transcriptValid = transcriptValid;
        this.documents = new ArrayList<>(documents);
        this.status = "Pending";
        this.reason = "";
        this.scholarshipType = "";
        this.duration = 0;
    }

    // Genel kontroller - tüm başvurular için ortak
    protected boolean generalChecks() {
        if (!transcriptValid) {
            status = "Rejected";
            reason = "Transcript is not valid";
            return false;
        }
        if (!hasDocument("ENR")) {
            status = "Rejected";
            reason = "Missing Enrollment Certificate";
            return false;
        }
        if (GPA < 2.5) {
            status = "Rejected";
            reason = "GPA is below 2.5";
            return false;
        }
        return true;
    }

    protected boolean hasDocument(String code) {
        for (Document document : documents) {
            if (document.getType().equals(code))
                return true;
        }
        return false;
    }

    protected Document getDocument(String code) {
        for (Document document : documents) {
            if (document.getType().equals(code))
                return document;
        }
        return null;
    }

    // Abstract method - alt sınıflar kendi değerlendirme mantığını implement eder
    public abstract void evaluate();

    // Getters
    public String getApplicantID() {
        return applicantID;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getStatus() {
        return status;
    }

    public String getScholarshipType() {
        return scholarshipType;
    }

    public int getDuration() {
        return duration;
    }

    // toString için scholarship type adını döndür
    public abstract String getScholarshipName();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Applicant ID: ").append(applicantID);
        sb.append(", Name: ").append(applicantName);
        sb.append(", Scholarship: ").append(getScholarshipName());
        sb.append(", Status: ").append(status);

        if ("Accepted".equalsIgnoreCase(status)) {
            sb.append(", Type: ").append(scholarshipType);
            sb.append(", Duration: ");

            if (duration == 0) {
                sb.append("6 months");
            } else if (duration == 1) {
                sb.append("1 year");
            } else {
                sb.append(duration).append(" years");
            }
        } else if ("Rejected".equalsIgnoreCase(status)) {
            sb.append(", Reason: ").append(reason);
        }

        return sb.toString();
    }
}
