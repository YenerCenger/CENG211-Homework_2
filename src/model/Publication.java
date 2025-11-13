package model;

import java.util.ArrayList;

public class Publication {
    private final String title;
    private final double impactFactor;

    public Publication(String title, double impactFactor) {
        // Input validation
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (impactFactor < 0) {
            throw new IllegalArgumentException("Impact factor cannot be negative");
        }

        this.title = title.trim();
        this.impactFactor = impactFactor;
    }

    // Getters
    public double getImpactFactor() {
        return impactFactor;
    }

    public String getTitle() {
        return title;
    }

    // Average impact factor hesaplama (static utility method)
    public static double calculateAverageImpact(ArrayList<Publication> publications) {
        if (publications == null || publications.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (Publication pub : publications) {
            total += pub.getImpactFactor();
        }
        return total / publications.size();
    }

    @Override
    public String toString() {
        return String.format("Publication: %s, Impact Factor: %.2f", title, impactFactor);
    }
}
