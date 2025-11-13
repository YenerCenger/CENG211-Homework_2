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
    protected int durationInMonths; // Ay cinsinden (6 ay, 12 ay, 24 ay, 18 ay için)

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
        this.durationInMonths = 0;
    }

    // Genel kontroller - tüm başvurular için ortak
    // PDF'teki öncelik sırasına göre: ENR -> Transcript -> GPA
    protected boolean generalChecks() {
        if (!hasDocument("ENR")) {
            status = "Rejected";
            reason = "Missing Enrollment Certificate";
            return false;
        }
        if (!transcriptValid) {
            status = "Rejected";
            reason = "Missing Transcript"; // PDF'deki exact string
            return false;
        }
        if (GPA < 2.5) {
            status = "Rejected";
            reason = "GPA below 2.5"; // PDF'deki exact string
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
        return durationInMonths;
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

            // Duration ay cinsinden tutulduğu için yıl/ay'a çevir
            if (durationInMonths == 6) {
                sb.append("6 months");
            } else if (durationInMonths == 12) {
                sb.append("1 year");
            } else if (durationInMonths == 18) {
                sb.append("1.5 years");
            } else if (durationInMonths == 24) {
                sb.append("2 years");
            } else if (durationInMonths % 12 == 0) {
                sb.append(durationInMonths / 12).append(" years");
            } else {
                // Karışık durumlar için
                int years = durationInMonths / 12;
                int months = durationInMonths % 12;
                if (years > 0) {
                    sb.append(years).append(".").append((months * 10) / 12).append(" years");
                } else {
                    sb.append(months).append(" months");
                }
            }
        } else if ("Rejected".equalsIgnoreCase(status)) {
            sb.append(", Reason: ").append(reason);
        }

        return sb.toString();
    }
}
