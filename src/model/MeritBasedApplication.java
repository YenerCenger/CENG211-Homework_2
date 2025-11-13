package model;

import java.util.ArrayList;

public class MeritBasedApplication extends Application {

    public MeritBasedApplication(String applicantID, String applicantName, double GPA,
            boolean transcriptValid, ArrayList<Document> documents) {
        super(applicantID, applicantName, GPA, transcriptValid, documents);
    }

    @Override
    public String getScholarshipName() {
        return "Merit";
    }

    @Override
    public void evaluate() {
        // Önce genel kontrolleri yap (ENR, Transcript, GPA >= 2.5)
        if (!generalChecks()) {
            return;
        }

        // GPA'ya göre değerlendirme
        if (GPA >= 3.20) {
            // Full Scholarship
            scholarshipType = "Full";
            status = "Accepted";
            
        } else if (GPA >= 3.00) {
            // Half Scholarship
            scholarshipType = "Half";
            status = "Accepted";
            
        } else {
            // GPA < 3.0 - Rejected
            status = "Rejected";
            reason = "GPA below 3.0";
            return;
        }

        // Duration: REC varsa 2 yıl, yoksa 1 yıl
        if (hasDocument("REC")) {
            duration = 2;
        } else {
            duration = 1;
        }
    }
}
