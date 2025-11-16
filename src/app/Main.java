package app;

import service.EvaluationService;

public class Main {
    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println("Scholarship Evaluation System");
        System.out.println("CENG211 HW2 - Fall 2025");
        System.out.println("===================================");

        String csvFilePath = "Files/ScholarshipApplications.csv";
        EvaluationService.evaluateScholarships(csvFilePath);
    }
}