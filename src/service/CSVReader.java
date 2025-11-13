package service;

import model.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVReader {
    private final Map<String, Applicant> applicants;

    public CSVReader() {
        this.applicants = new HashMap<>();
    }

    public Map<String, Applicant> readCSV(String filePath) throws IOException {
        // Try-with-resources ile otomatik kaynak yönetimi + UTF-8 encoding
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 0) {
                    continue;
                }

                String recordType = parts[0].trim();

                switch (recordType) {
                    case "A": // Applicant
                        processApplicant(parts);
                        break;
                    case "D": // Document
                        processDocument(parts);
                        break;
                    case "T": // Transcript
                        processTranscript(parts);
                        break;
                    case "I": // Family Info
                        processFamilyInfo(parts);
                        break;
                    case "P": // Publication
                        processPublication(parts);
                        break;
                    default:
                        System.err.println("⚠ Unknown record type: " + recordType);
                }
            }
        }

        return applicants;
    }

    private void processApplicant(String[] parts) {
        // A,ID,Name,GPA,Income (PDF format)
        if (parts.length < 4) {
            System.err.println("⚠ Invalid Applicant record: insufficient columns");
            return;
        }

        try {
            String id = parts[1].trim();
            String name = parts[2].trim();
            double gpa = Double.parseDouble(parts[3].trim());

            Applicant applicant = new Applicant(id, name, gpa);

            // Income alanı varsa (parts[4]), default FamilyInfo oluştur
            if (parts.length > 4) {
                double income = Double.parseDouble(parts[4].trim());
                if (income > 0) {
                    // Default dependents = 0 (I satırı varsa override edilecek)
                    applicant.setFamilyInfo(new FamilyInfo(income, 0));
                }
            }

            applicants.put(id, applicant);
        } catch (NumberFormatException e) {
            System.err.println("⚠ Invalid number format in Applicant record");
        }
    }

    private void processDocument(String[] parts) {
        // D,ID,Type,Duration
        if (parts.length < 4) {
            System.err.println("⚠ Invalid Document record: insufficient columns");
            return;
        }

        try {
            String id = parts[1].trim();
            String type = parts[2].trim();
            int duration = Integer.parseInt(parts[3].trim());

            Applicant applicant = applicants.get(id);
            if (applicant != null) {
                Document document = new Document(type, duration);
                applicant.addDocument(document);
            }
        } catch (NumberFormatException e) {
            System.err.println("⚠ Invalid number format in Document record");
        }
    }

    private void processTranscript(String[] parts) {
        // T,ID,Y/N
        if (parts.length < 3) {
            System.err.println("⚠ Invalid Transcript record: insufficient columns");
            return;
        }

        String id = parts[1].trim();
        String valid = parts[2].trim();

        Applicant applicant = applicants.get(id);
        if (applicant != null) {
            applicant.setTranscriptValid(valid.equalsIgnoreCase("Y"));
        }
    }

    private void processFamilyInfo(String[] parts) {
        // I,ID,Income,Dependents
        if (parts.length < 4) {
            System.err.println("⚠ Invalid FamilyInfo record: insufficient columns");
            return;
        }

        try {
            String id = parts[1].trim();
            double income = Double.parseDouble(parts[2].trim());
            int dependents = Integer.parseInt(parts[3].trim());

            Applicant applicant = applicants.get(id);
            if (applicant != null) {
                FamilyInfo familyInfo = new FamilyInfo(income, dependents);
                applicant.setFamilyInfo(familyInfo);
            }
        } catch (NumberFormatException e) {
            System.err.println("⚠ Invalid number format in FamilyInfo record");
        }
    }

    private void processPublication(String[] parts) {
        // P,ID,Title,ImpactFactor
        if (parts.length < 4) {
            System.err.println("⚠ Invalid Publication record: insufficient columns");
            return;
        }

        try {
            String id = parts[1].trim();
            String title = parts[2].trim();
            double impactFactor = Double.parseDouble(parts[3].trim());

            Applicant applicant = applicants.get(id);
            if (applicant != null) {
                Publication publication = new Publication(title, impactFactor);
                applicant.addPublication(publication);
            }
        } catch (NumberFormatException e) {
            System.err.println("⚠ Invalid number format in Publication record");
        }
    }

    public Map<String, Applicant> getApplicants() {
        return applicants;
    }
}
