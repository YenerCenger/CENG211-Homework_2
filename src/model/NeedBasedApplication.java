package model;

import java.util.ArrayList;

// Developed by Ebu
public class NeedBasedApplication extends Application {
    
    private final double income;
    private final int dependents;

    public NeedBasedApplication(Applicant applicant) {
        this.ApplicantID = String.valueOf(applicant.getApplicantID());
        this.ApplicantName = applicant.getName();
        this.GPA = applicant.getGpa();
        this.transcriptValid = applicant.isTranscriptValid();
        this.documents = new ArrayList<>(applicant.getDocuments());

        FamilyInfo info = applicant.getFamilyInfo();
        if (info != null) {
            this.income = info.getFamilyIncome();
            this.dependents = info.getDependents();
        } else {
            this.income = applicant.getIncome();
            this.dependents = 0;
        }
    }

    @Override
    public void evaluate() {
        if(!generalChecks()) {
            return;
        }

        if(income <= 0) {
            status = "Rejected"; 
            reason = "Missing mandatory document";
            return;
        }

        double fullLimit = 10000.0;
        double halfLimit = 15000.0;

        if (hasDocuments("SAV")) {
            fullLimit *= 1.2;
            halfLimit *= 1.2;
        }

        if(dependents >= 3) {
            fullLimit *= 1.1;
            halfLimit *= 1.1;
        }

        if (income <= fullLimit) {
            status = "Accepted"; 
            type = "Full";
        }
        else if(income <= halfLimit) {
            status = "Accepted"; 
            type = "Half";
        }
        else {
            status = "Rejected"; 
            reason = "Financial status unstable";
            return;
        }

        duration = 12;
        
    }
}
