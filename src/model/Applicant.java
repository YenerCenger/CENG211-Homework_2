package model;

import java.util.ArrayList;

public class Applicant {
    private final String applicantID;
    private final String applicantName;
    private final double GPA;
    private boolean transcriptValid;
    private final ArrayList<Document> documents;
    private final ArrayList<Publication> publications;
    private FamilyInfo familyInfo;

    public Applicant(String applicantID, String applicantName, double GPA) {
        this.applicantID = applicantID;
        this.applicantName = applicantName;
        this.GPA = GPA;
        this.transcriptValid = false;
        this.documents = new ArrayList<>();
        this.publications = new ArrayList<>();
        this.familyInfo = null;
    }

    // Getters
    public String getApplicantID() {
        return applicantID;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public double getGPA() {
        return GPA;
    }

    public boolean isTranscriptValid() {
        return transcriptValid;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public ArrayList<Publication> getPublications() {
        return publications;
    }

    public FamilyInfo getFamilyInfo() {
        return familyInfo;
    }

    // Setters (sadece mutable alanlar için)
    public void setTranscriptValid(boolean transcriptValid) {
        this.transcriptValid = transcriptValid;
    }

    public void setFamilyInfo(FamilyInfo familyInfo) {
        this.familyInfo = familyInfo;
    }

    // Document ekleme
    public void addDocument(Document document) {
        this.documents.add(document);
    }

    // Publication ekleme
    public void addPublication(Publication publication) {
        this.publications.add(publication);
    }

    // ID'ye göre scholarship kategorisini belirle (Merit/Need/Research)
    public String getScholarshipCategory() {
        String prefix = applicantID.substring(0, 2);
        switch (prefix) {
            case "11":
                return "Merit";
            case "22":
                return "Need";
            case "33":
                return "Research";
            default:
                return "Unknown";
        }
    }

    // Application nesnesi oluştur (Factory Pattern)
    public Application createApplication() {
        String category = getScholarshipCategory();

        switch (category) {
            case "Merit":
                return new MeritBasedApplication(applicantID, applicantName, GPA,
                        transcriptValid, documents);
            case "Need":
                // FamilyInfo yoksa yine de Application oluştur, evaluate() içinde reject edilecek
                // PDF: "Missing mandatory information leads to immediate rejection"
                // Exception atmak yerine rejection reason döndürmeliyiz
                return new NeedBasedApplication(applicantID, applicantName, GPA,
                        transcriptValid, documents, familyInfo);
            case "Research":
                return new ResearchGrantApplication(applicantID, applicantName, GPA,
                        transcriptValid, documents, publications);
            default:
                throw new IllegalArgumentException(
                        "Invalid applicant ID prefix for ID: " + applicantID);
        }
    }

    @Override
    public String toString() {
        return String.format("Applicant ID: %s, Name: %s, GPA: %.2f, Category: %s",
                applicantID, applicantName, GPA, getScholarshipCategory());
    }
}