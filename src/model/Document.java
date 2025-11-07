package model;

public class Document {
    private String documentType; // ENR, REC, SAV, RSV, GRP
    private int durationInMonths; // PDF’teki "durationInMonths" kolonu

    // Constructor
    public Document(String documentType, int durationInMonths) {
        this.documentType = documentType;
        this.durationInMonths = durationInMonths;
    }

    // Getters
    public String getType() {
        return documentType;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    // Setters
    public void setType(String documentType) {
        this.documentType = documentType;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    // String representation for easy debugging
    @Override
    public String toString() {
        return "Document Type: " + documentType +
                ", Duration: " + durationInMonths + " months";
    }
}