package model;

public class FamilyInfo {
    private double familyIncome;
    private int dependents;

    public FamilyInfo(double familyIncome, int dependents) {
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

    // Setters
    public void setFamilyIncome(double familyIncome) {
        this.familyIncome = familyIncome;
    }

    public void setDependents(int dependents) {
        this.dependents = dependents;
    }

    // String representation for easy debugging
    @Override
    public String toString() {
        return "Family Income: " + familyIncome +
                ", Dependents: " + dependents;
    }
}
