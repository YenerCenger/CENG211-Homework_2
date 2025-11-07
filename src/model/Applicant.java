package model;

import java.util.ArrayList;

public class Applicant {
    private int applicantID;
    private String name;
    private double gpa;
    private double income;
    private boolean transcriptValid;
    private ArrayList<Document> documents;
    private FamilyInfo familyInfo;
    private ArrayList<Publication> publications;

    public Applicant(int applicantID, String name, double gpa, double income) {
        this.applicantID = applicantID;
        this.name = name;
        this.gpa = gpa;
        this.income = income;
        this.transcriptValid = false; // Varsayılan olarak false
        this.documents = new ArrayList<>();
        this.publications = new ArrayList<>();
        this.familyInfo = null;
    }

    // Getters
    public int getApplicantID() {
        return applicantID;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public double getIncome() {
        return income;
    }

    public boolean isTranscriptValid() {
        return transcriptValid;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public FamilyInfo getFamilyInfo() {
        return familyInfo;
    }

    public ArrayList<Publication> getPublications() {
        return publications;
    }

    // Setters
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setTranscriptValid(boolean transcriptValid) {
        this.transcriptValid = transcriptValid;
    }

    public void setFamilyInfo(FamilyInfo familyInfo) {
        this.familyInfo = familyInfo;
    }

    // Document eklemek için metod
    public void addDocument(Document document) {
        if (document != null) {
            this.documents.add(document);
        }
    }

    // Publication eklemek için metod
    public void addPublication(Publication publication) {
        if (publication != null) {
            this.publications.add(publication);
        }
    }

    // String representation for easy debugging
    @Override
    public String toString() {
        return "Applicant ID: " + applicantID +
                ", Name: " + name +
                ", GPA: " + gpa +
                ", Income: " + income +
                ", Transcript Valid: " + transcriptValid +
                ", Documents: " + documents.size() +
                ", Publications: " + publications.size();
    }
}