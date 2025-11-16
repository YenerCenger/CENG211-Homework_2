package app;

import service.EvaluationService;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "Files/ScholarshipApplications.csv";
        EvaluationService.evaluateScholarships(csvFilePath);
    }
}