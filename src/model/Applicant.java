package Applicant.java;

public class Applicant {
    private String ApplicantID;
    private String ApplicantName;
    private double GPA;
    private double Income;

    public Applicant(String ApplicantID, String ApplicantName, double GPA, double Income) {
        this.ApplicantID = ApplicantID;
        this.ApplicantName = ApplicantName;
        this.GPA = GPA;
        this.Income = Income;
    }

    // Getters
    public String getApplicantId() {
        return applicantId;
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

    // Setters
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    // String representation for easy debugging
    @Override
    public String toString() {
        return "Applicant ID: " + applicantId +
                ", Name: " + name +
                ", GPA: " + gpa +
                ", Income: " + income;
    }
}