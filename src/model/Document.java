package model;

public class Document {
    private final String documentType; // ENR, REC, SAV, RSV, GRP
    private final int durationInMonths;

    public Document(String documentType, int durationInMonths) {
        // Input validation
        if (documentType == null || documentType.trim().isEmpty()) {
            throw new IllegalArgumentException("Document type cannot be null or empty");
        }
        if (durationInMonths < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }

        this.documentType = documentType.trim();
        this.durationInMonths = durationInMonths;
    }

    // Getters
    public String getType() {
        return documentType;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    @Override
    public String toString() {
        return String.format("Document Type: %s, Duration: %d months", documentType, durationInMonths);
    }
}