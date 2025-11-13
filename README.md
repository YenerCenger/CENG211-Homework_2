# CENG211 Homework 2 - Scholarship Evaluation System

Üniversite burs başvurularını değerlendiren bir Java uygulaması. Bu sistem, öğrencilerin burs başvurularını üç farklı kategoride (Başarı, İhtiyaç, Araştırma) değerlendirerek kabul/red kararı verir.

## 📋 Genel Bakış

Bu proje, CENG211 (Data Structures) dersi kapsamında geliştirilmiş bir burs değerlendirme sistemidir. CSV formatındaki başvuru verilerini okuyarak, her başvuruyu ilgili kategorisinde değerlendirir ve sonuçları raporlar.

### Özellikler

- ✅ CSV dosyasından başvuru verilerini okuma
- ✅ Üç farklı burs türünde değerlendirme (Merit, Need, Research)
- ✅ Otomatik belge kontrolü (transkript, kayıt belgesi vb.)
- ✅ GPA bazlı değerlendirme
- ✅ Gelir seviyesi analizi
- ✅ Yayın etki faktörü hesaplama
- ✅ Detaylı raporlama ve istatistikler

## 🎓 Burs Türleri

### 1. Merit-Based Scholarship (Başarı Bursu)
Akademik başarıya dayalı burs değerlendirmesi.

**Gereksinimler:**
- Geçerli transkript (transcript valid)
- Kayıt belgesi (ENR - Enrollment Certificate)
- GPA ≥ 2.5 (minimum genel gereksinim)
- GPA ≥ 3.0 (kabul için)

**Değerlendirme Kriterleri:**
- GPA ≥ 3.20 → **Full Scholarship (Tam Burs)**
- 3.00 ≤ GPA < 3.20 → **Half Scholarship (Yarım Burs)**
- GPA < 3.00 → **Rejected (Reddedildi)**

**Süre:**
- Tavsiye mektubu (REC) varsa: **2 yıl**
- Tavsiye mektubu yoksa: **1 yıl**

### 2. Need-Based Scholarship (İhtiyaç Bursu)
Finansal ihtiyaca dayalı burs değerlendirmesi.

**Gereksinimler:**
- Geçerli transkript
- Kayıt belgesi (ENR)
- GPA ≥ 2.5
- Aile gelir bilgisi (FamilyInfo)

**Değerlendirme Kriterleri:**
- Base eşikler:
  - Full Scholarship: Gelir ≤ 10,000 TL
  - Half Scholarship: Gelir ≤ 15,000 TL

**Eşik Artırımları:**
- Tasarruf hesabı belgesi (SAV) varsa: **+%20**
- 3+ bağımlı aile üyesi varsa: **+%10**
- Her iki durumda da artışlar uygulanır (kümülatif)

**Süre:** Sabit **1 yıl**

### 3. Research Grant (Araştırma Bursu)
Araştırma ve yayın faaliyetlerine dayalı burs.

**Gereksinimler:**
- Geçerli transkript
- Kayıt belgesi (ENR)
- GPA ≥ 2.5
- En az bir yayın (Publication) VEYA araştırma teklifi (GRP - Grant Proposal)

**Değerlendirme Kriterleri:**
- Ortalama Impact Factor ≥ 1.50 → **Full Scholarship**
  - Base süre: 1 yıl
- 1.00 ≤ Ortalama Impact Factor < 1.50 → **Half Scholarship**
  - Base süre: 6 ay
- Ortalama Impact Factor < 1.00 → **Rejected**

**Süre Artırımı:**
- Araştırma rezerv belgesi (RSV) varsa: **+1 yıl**

## 🏗️ Proje Yapısı

```
CENG211-Homework_2/
├── src/
│   ├── app/
│   │   └── Main.java                    # Ana uygulama
│   ├── model/
│   │   ├── Applicant.java              # Başvuru sahibi modeli
│   │   ├── Application.java            # Soyut başvuru sınıfı
│   │   ├── MeritBasedApplication.java  # Başarı bursu
│   │   ├── NeedBasedApplication.java   # İhtiyaç bursu
│   │   ├── ResearchGrantApplication.java # Araştırma bursu
│   │   ├── Document.java               # Belge modeli
│   │   ├── FamilyInfo.java             # Aile bilgisi modeli
│   │   └── Publication.java            # Yayın modeli
│   ├── service/
│   │   ├── CSVReader.java              # CSV okuyucu
│   │   └── ApplicationFactory.java     # Başvuru fabrikası
│   └── output/
│       └── ResultPrinter.java          # Sonuç yazdırma
├── Files/
│   └── ScholarshipApplications.csv     # Başvuru verileri
├── README.md                            # Bu dosya
├── README_CSV_MODULE.md                 # CSV modülü dokümantasyonu
└── CENG211_Fall2025_HW2.pdf            # Ödev tanımı
```

## 🚀 Kurulum ve Çalıştırma

### Gereksinimler
- Java JDK 8 veya üstü
- Terminal/Command Prompt

### Derleme

```bash
# Tüm Java dosyalarını derle
javac -d bin src/model/*.java src/service/*.java src/app/*.java src/output/*.java
```

### Çalıştırma

```bash
# Uygulamayı çalıştır
java -cp bin app.Main
```

### Örnek Çıktı

```
===================================
Scholarship Evaluation System
CENG211 HW2 - Fall 2025
===================================

Reading CSV file: Files/ScholarshipApplications.csv
Total applicants read: 39

Evaluating applications...

===== ALL APPLICATIONS =====
Applicant ID: 33022988, Name: Ethan Roberts, Scholarship: Research, Status: Accepted, Type: Full, Duration: 2 years
Applicant ID: 33442497, Name: Charlotte Thompson, Scholarship: Research, Status: Accepted, Type: Half, Duration: 6 months
...

===== ACCEPTED APPLICATIONS =====
...

===== REJECTED APPLICATIONS =====
...

===== STATISTICS =====
Total Applications: 39
Accepted: 3
Rejected: 36
Acceptance Rate: 7.69%
```

## 📊 CSV Dosya Formatı

CSV dosyası 5 farklı satır tipi içerir:

### A - Applicant (Başvuru Sahibi)
```
Format: A,ApplicantID,Name,GPA,Income
Örnek: A,11263216,Owen Parker,4.00,4936
```

### T - Transcript (Transkript)
```
Format: T,ApplicantID,Y/N
Örnek: T,33919214,Y
```

### D - Document (Belge)
```
Format: D,ApplicantID,DocumentType,Duration
Belge Tipleri: ENR (Enrollment), REC (Recommendation), 
               SAV (Savings), RSV (Research), GRP (Grant Proposal)
Örnek: D,33843720,GRP,6
```

### I - Income/FamilyInfo (Aile Bilgisi)
```
Format: I,ApplicantID,FamilyIncome,Dependents
Örnek: I,22661786,21064,3
```

### P - Publication (Yayın)
```
Format: P,ApplicantID,Title,ImpactFactor
Örnek: P,33442497,Vision Transformer Benchmarks,1.2
```

## 🔍 Değerlendirme Süreci

### Ortak Kontroller (Tüm Başvurular İçin)
1. ✅ Transkript geçerli mi?
2. ✅ Kayıt belgesi (ENR) var mı?
3. ✅ GPA ≥ 2.5 mi?

Bu kontrollerden biri başarısız olursa başvuru otomatik olarak reddedilir.

### Başvuru Türüne Özel Değerlendirme
Her başvuru türü kendi özel kriterlerine göre değerlendirilir (yukarıdaki Burs Türleri bölümüne bakın).

## 🛠️ Mimari ve Tasarım

### Design Patterns
- **Factory Pattern**: `ApplicationFactory` sınıfı farklı başvuru türlerini oluşturur
- **Template Method Pattern**: `Application` soyut sınıfı ortak kontrolleri tanımlar, alt sınıflar özel değerlendirmeyi uygular
- **Strategy Pattern**: Her burs türü kendi değerlendirme stratejisini uygular

### Sınıf Hiyerarşisi
```
Application (abstract)
    ├── MeritBasedApplication
    ├── NeedBasedApplication
    └── ResearchGrantApplication
```

## 📝 Kod Örnekleri

### Başvuru Oluşturma ve Değerlendirme

```java
// CSV dosyasını oku
CSVReader csvReader = new CSVReader();
Map<String, Applicant> applicants = csvReader.readCSV("Files/ScholarshipApplications.csv");

// Başvuruları oluştur ve değerlendir
List<Application> applications = ApplicationFactory.createAndEvaluateApplications(applicants);

// Sonuçları yazdır
ResultPrinter.printAllResults(applications);
ResultPrinter.printStatistics(applications);
```

### Belirli Bir Başvuruya Erişim

```java
for (Application app : applications) {
    if (app.getStatus().equals("Accepted")) {
        System.out.println(app.getApplicantName() + " - " + 
                          app.getScholarshipType() + " scholarship for " + 
                          app.getDuration() + " years");
    }
}
```

## 🧪 Test

Projeyi test etmek için örnek CSV dosyası (`Files/ScholarshipApplications.csv`) ile uygulamayı çalıştırın:

```bash
# Derle ve çalıştır
javac -d bin src/model/*.java src/service/*.java src/app/*.java src/output/*.java
java -cp bin app.Main
```

## ⚠️ Yaygın Hatalar ve Çözümler

### Dosya Bulunamadı Hatası
```
Error: CSV file not found at: ...
```
**Çözüm**: CSV dosyasının `Files/ScholarshipApplications.csv` konumunda olduğundan emin olun.

### Derleme Hatası
```
javac: file not found: src/model/*.java
```
**Çözüm**: Komutu proje kök dizininde çalıştırın.

### Encoding Sorunları
Türkçe karakterlerde sorun yaşıyorsanız:
```bash
javac -encoding UTF-8 -d bin src/model/*.java src/service/*.java src/app/*.java src/output/*.java
```

## 📚 Daha Fazla Bilgi

- CSV modülü hakkında detaylı bilgi için: [README_CSV_MODULE.md](README_CSV_MODULE.md)
- Ödev gereksinimleri için: [CENG211_Fall2025_HW2.pdf](CENG211_Fall2025_HW2.pdf)

## 👥 Katkıda Bulunanlar

- **CSV & Veri Yönetimi Modülü**: Ceren
- **Proje Geliştirme**: CENG211 Ekibi

## 📄 Lisans

Bu proje CENG211 dersi kapsamında eğitim amaçlı geliştirilmiştir.

---

**Not**: Bu sistem akademik bir proje olup, gerçek burs değerlendirme süreçlerinde kullanılmak üzere tasarlanmamıştır.
