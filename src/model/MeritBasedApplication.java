package model;

import java.util.ArrayList;

// Developed by Ebu
public class MeritBasedApplication extends Application {
    
    public MeritBasedApplication(Applicant applicant) {
        this.ApplicantID = String.valueOf(applicant.getApplicantID());
        this.ApplicantName = applicant.getName();
        this.GPA = applicant.getGpa();
        this.transcriptValid = applicant.isTranscriptValid();
        this.documents = new ArrayList<>(applicant.getDocuments());
    }
    
    @Override
    public void evaluate() {
        if(!generalChecks()) {
            return;
        }
        
        if(GPA>=3.20) {
            status = "Accepted"; 
            type = "Full";
        }
        else if(GPA>=3.00) {
            status = "Accepted"; 
            type = "Half";
        }
        else {
            status = "Rejected"; 
            reason = "GPA is below 3.00";
            return;
        }

        if(hasDocuments("REC")) {
            duration = 24;
        } else {
            duration = 12;
        }
        
    }

}
