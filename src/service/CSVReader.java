package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

// Developed by Ceren
public class CSVReader {
    private String filePath;
    
    public CSVReader(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * CSV dosyasını okur ve her satırı döndürür
     * @return Her satır bir String dizisi olarak döner
     */
    public HashMap<Integer, String[]> readAllLines() {
        HashMap<Integer, String[]> lines = new HashMap<>();
        int lineNumber = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Boş satırları atla
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                // Virgülle ayır
                String[] data = line.split(",");
                lines.put(lineNumber++, data);
            }
        } catch (IOException e) {
            System.err.println("Dosya okuma hatası: " + e.getMessage());
            e.printStackTrace();
        }
        
        return lines;
    }
    
    /**
     * Belirli bir türdeki satırları filtreler
     * @param type Satır türü (A, T, D, I, P)
     * @return Filtrelenmiş satırlar
     */
    public HashMap<Integer, String[]> readLinesByType(String type) {
        HashMap<Integer, String[]> allLines = readAllLines();
        HashMap<Integer, String[]> filteredLines = new HashMap<>();
        
        for (Integer key : allLines.keySet()) {
            String[] line = allLines.get(key);
            if (line.length > 0 && line[0].equals(type)) {
                filteredLines.put(key, line);
            }
        }
        
        return filteredLines;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
