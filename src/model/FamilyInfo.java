package model;

public class FamilyInfo {
    private final double familyIncome;
    private final int dependents;

    public FamilyInfo(double familyIncome, int dependents) {
        // Input validation
        if (familyIncome < 0) {
            throw new IllegalArgumentException("Family income cannot be negative.");
        }
        if (dependents < 0) {
            throw new IllegalArgumentException("Dependents cannot be negative.");
        }

        this.familyIncome = familyIncome;
        this.dependents = dependents;
    }

    // Getters
    public double getFamilyIncome() {
        return familyIncome;
    }

    public int getDependents() {
        return dependents;
    }

    @Override
    public String toString() {
        return String.format("Income: %.2f, Dependents: %d", familyIncome, dependents);
    }
}
