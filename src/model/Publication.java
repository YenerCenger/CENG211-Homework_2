package Publication.java;

public class Publication {
    private String title;
    private double impactFactor;

    public Publication(double impactFactor, String title) {
        this.impactFactor = impactFactor;
        this.title = title;
    }

    // Getters
    public double getImpactFactor() {
        return impactFactor;
    }

    public String getTitle() {
        return title;
    }

    // Setters
    public void setImpactFactor(double impactFactor) {
        this.impactFactor = impactFactor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // String representation for easy debugging
    @Override
    public String toString() {
        return "Publication: " + title +
                ", Impact Factor: " + impactFactor;
    }
}
