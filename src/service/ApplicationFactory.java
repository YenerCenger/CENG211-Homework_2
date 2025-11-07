package service;

import model.*;
import java.util.HashMap;
import java.util.ArrayList;

// Developed by Ceren
public class ApplicationFactory {
    private HashMap<Integer, Applicant> applicants;
    private CSVReader csvReader;
    
    public ApplicationFactory(String csvFilePath) {
        this.csvReader = new CSVReader(csvFilePath);
        this.applicants = new HashMap<>();
    }
    
    /**
     * CSV dosyasını okur ve tüm verileri yükler
     */
    public void loadData() {
        HashMap<Integer, String[]> allLines = csvReader.readAllLines();
        
        // Önce tüm Applicant'ları oluştur
        for (String[] line : allLines.values()) {
            if (line[0].equals("A")) {
                createApplicant(line);
            }
        }
        
        // Sonra diğer bilgileri ilişkilendir
        for (String[] line : allLines.values()) {
            String type = line[0];
            
            switch (type) {
                case "T":
                    addTranscriptInfo(line);
                    break;
                case "D":
                    addDocument(line);
                    break;
                case "I":
                    addFamilyInfo(line);
                    break;
                case "P":
                    addPublication(line);
                    break;
            }
        }
    }
    
    /**
     * A satırından Applicant oluşturur
     * Format: A,ApplicantID,Name,GPA,Income
     */
    private void createApplicant(String[] data) {
        try {
            int applicantID = Integer.parseInt(data[1]);
            String name = data[2];
            double gpa = Double.parseDouble(data[3]);
            double income = Double.parseDouble(data[4]);
            
            Applicant applicant = new Applicant(applicantID, name, gpa, income);
            applicants.put(applicantID, applicant);
        } catch (Exception e) {
            System.err.println("Applicant oluşturma hatası: " + e.getMessage());
        }
    }
    
    /**
     * T satırından Transcript bilgisi ekler
     * Format: T,ApplicantID,Y/N
     */
    private void addTranscriptInfo(String[] data) {
        try {
            int applicantID = Integer.parseInt(data[1]);
            boolean isValid = data[2].equals("Y");
            
            Applicant applicant = applicants.get(applicantID);
            if (applicant != null) {
                applicant.setTranscriptValid(isValid);
            }
        } catch (Exception e) {
            System.err.println("Transcript bilgisi ekleme hatası: " + e.getMessage());
        }
    }
    
    /**
     * D satırından Document ekler
     * Format: D,ApplicantID,Type,Duration
     */
    private void addDocument(String[] data) {
        try {
            int applicantID = Integer.parseInt(data[1]);
            String documentType = data[2];
            int duration = Integer.parseInt(data[3]);
            
            Applicant applicant = applicants.get(applicantID);
            if (applicant != null) {
                Document document = new Document(documentType, duration);
                applicant.addDocument(document);
            }
        } catch (Exception e) {
            System.err.println("Document ekleme hatası: " + e.getMessage());
        }
    }
    
    /**
     * I satırından FamilyInfo ekler
     * Format: I,ApplicantID,FamilyIncome,Dependents
     */
    private void addFamilyInfo(String[] data) {
        try {
            int applicantID = Integer.parseInt(data[1]);
            double familyIncome = Double.parseDouble(data[2]);
            int dependents = Integer.parseInt(data[3]);
            
            Applicant applicant = applicants.get(applicantID);
            if (applicant != null) {
                FamilyInfo familyInfo = new FamilyInfo(familyIncome, dependents);
                applicant.setFamilyInfo(familyInfo);
            }
        } catch (Exception e) {
            System.err.println("FamilyInfo ekleme hatası: " + e.getMessage());
        }
    }
    
    /**
     * P satırından Publication ekler
     * Format: P,ApplicantID,Title,ImpactFactor
     */
    private void addPublication(String[] data) {
        try {
            int applicantID = Integer.parseInt(data[1]);
            String title = data[2];
            double impactFactor = Double.parseDouble(data[3]);
            
            Applicant applicant = applicants.get(applicantID);
            if (applicant != null) {
                Publication publication = new Publication(impactFactor, title);
                applicant.addPublication(publication);
            }
        } catch (Exception e) {
            System.err.println("Publication ekleme hatası: " + e.getMessage());
        }
    }
    
    /**
     * Yüklenen tüm başvuru sahiplerini döndürür
     */
    public HashMap<Integer, Applicant> getApplicants() {
        return applicants;
    }
    
    /**
     * Belirli bir ID'ye sahip başvuru sahibini döndürür
     */
    public Applicant getApplicantById(int id) {
        return applicants.get(id);
    }
    
    /**
     * Toplam başvuru sahibi sayısını döndürür
     */
    public int getApplicantCount() {
        return applicants.size();
    }
}
