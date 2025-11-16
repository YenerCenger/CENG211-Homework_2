package app;

import model.*;
import service.*;
import output.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, Applicant> applicants = new CSVReader().readCSV("Files/ScholarshipApplications.csv");
            List<Application> applications = ApplicationFactory.sortByID(
                ApplicationFactory.createAndEvaluateApplications(applicants)
            );
            
            ResultPrinter.printAllResults(applications);
            ResultPrinter.printAcceptedApplications(applications);
            ResultPrinter.printRejectedApplications(applications);
            ResultPrinter.printStatistics(applications);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}