package app;

import model.*;
import service.*;
import output.*;
import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println("Scholarship Evaluation System");
        System.out.println("CENG211 HW2 - Fall 2025");
        System.out.println("===================================");

        try {
            // UTF-8 encoding guarantee for all platforms
            System.setProperty("file.encoding", "UTF-8");

            // CSV dosyasını oku
            String csvFilePath = "Files/ScholarshipApplications.csv";

            // Dosya varlık kontrolü
            File file = new File(csvFilePath);
            if (!file.exists()) {
                System.err.println("Error: CSV file not found at: " + file.getAbsolutePath());
                return;
            }

            CSVReader csvReader = new CSVReader();
            System.out.println("\nReading CSV file: " + csvFilePath);
            Map<String, Applicant> applicants = csvReader.readCSV(csvFilePath);
            System.out.println("Total applicants read: " + applicants.size());

            // Application nesnelerini oluştur ve değerlendir
            System.out.println("\nEvaluating applications...");
            List<Application> applications = ApplicationFactory.createAndEvaluateApplications(applicants);

            // Null/empty kontrolü
            if (applications == null || applications.isEmpty()) {
                System.out.println("No applications to evaluate.");
                return;
            }

            // Sonuçları ID'ye göre sırala
            applications = ApplicationFactory.sortByID(applications);

            // Sonuçları yazdır
            ResultPrinter.printAllResults(applications);
            ResultPrinter.printAcceptedApplications(applications);
            ResultPrinter.printRejectedApplications(applications);
            ResultPrinter.printStatistics(applications);

            System.out.println("\n===================================");
            System.out.println("Evaluation completed successfully!");
            System.out.println("===================================");

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}