package app;

import service.ApplicationFactory;
import model.Applicant;
import java.util.HashMap;

// Developed by Yener
public class Main {
    public static void main(String[] args) {
        System.out.println("Scholarship Evaluation System - CENG211 HW2");
        System.out.println("==============================================\n");
        
        // CSV dosya yolu
        String csvFilePath = "Files/ScholarshipApplications.csv";
        
        // ApplicationFactory oluştur ve verileri yükle
        ApplicationFactory factory = new ApplicationFactory(csvFilePath);
        System.out.println("CSV dosyası okunuyor...");
        factory.loadData();
        
        // Yüklenen başvuru sahipleri hakkında bilgi
        System.out.println("Toplam başvuru sahibi sayısı: " + factory.getApplicantCount());
        System.out.println("\nİlk 5 başvuru sahibi:\n");
        
        HashMap<Integer, Applicant> applicants = factory.getApplicants();
        int count = 0;
        for (Applicant applicant : applicants.values()) {
            if (count < 5) {
                System.out.println(applicant);
                System.out.println("  - Belgeler: " + applicant.getDocuments().size());
                System.out.println("  - Yayınlar: " + applicant.getPublications().size());
                if (applicant.getFamilyInfo() != null) {
                    System.out.println("  - Aile Bilgisi: " + applicant.getFamilyInfo());
                }
                System.out.println();
                count++;
            }
        }
    }
}
