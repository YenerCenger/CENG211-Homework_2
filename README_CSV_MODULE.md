# CSV & Veri Yönetimi Modülü

Bu modül, CENG211 Homework 2 projesi için CSV dosya okuma ve veri yükleme işlemlerini gerçekleştirir.

## Geliştirici: Ceren

## İçindekiler
1. `CSVReader.java` - CSV dosyasını okuma
2. `ApplicationFactory.java` - Veri yükleme ve nesne oluşturma

## Özellikler

### CSVReader.java
CSV dosyasını satır satır okur ve parse eder.

**Metodlar:**
- `readAllLines()`: Tüm satırları HashMap<Integer, String[]> olarak döndürür
- `readLinesByType(String type)`: Belirli bir türdeki (A, T, D, I, P) satırları filtreler

### ApplicationFactory.java
CSV verilerini okuyarak Applicant nesneleri oluşturur ve ilişkilendirir.

**Metodlar:**
- `loadData()`: CSV dosyasını okur ve tüm verileri yükler
- `getApplicants()`: Tüm başvuru sahiplerini HashMap olarak döndürür
- `getApplicantById(int id)`: Belirli bir ID'ye sahip başvuru sahibini döndürür
- `getApplicantCount()`: Toplam başvuru sahibi sayısını döndürür

## CSV Format

CSV dosyası 5 farklı satır tipi içerir:

### A - Applicant (Başvuru Sahibi)
Format: `A,ApplicantID,Name,GPA,Income`
Örnek: `A,11263216,Owen Parker,4.00,4936`

### T - Transcript (Transkript Bilgisi)
Format: `T,ApplicantID,Y/N`
Örnek: `T,33919214,Y`

### D - Document (Belge)
Format: `D,ApplicantID,DocumentType,Duration`
Belge Tipleri: ENR, REC, SAV, RSV, GRP
Örnek: `D,33843720,GRP,6`

### I - Income/FamilyInfo (Aile Bilgisi)
Format: `I,ApplicantID,FamilyIncome,Dependents`
Örnek: `I,22661786,21064,3`

### P - Publication (Yayın)
Format: `P,ApplicantID,Title,ImpactFactor`
Örnek: `P,33442497,Vision Transformer Benchmarks,1.2`

## Veri İlişkilendirme

ApplicationFactory, Applicant ID'ye göre tüm verileri ilişkilendirir:
1. Önce tüm A satırlarından Applicant nesneleri oluşturulur
2. Sonra T, D, I, P satırları ilgili Applicant'lara eklenir
3. HashMap<Integer, Applicant> yapısında saklanır

## Kullanım

```java
// ApplicationFactory oluştur
ApplicationFactory factory = new ApplicationFactory("Files/ScholarshipApplications.csv");

// Verileri yükle
factory.loadData();

// Başvuru sahiplerini al
HashMap<Integer, Applicant> applicants = factory.getApplicants();

// Belirli bir başvuru sahibini al
Applicant applicant = factory.getApplicantById(11263216);

// Başvuru sahiplerinin bilgilerine eriş
for (Applicant app : applicants.values()) {
    System.out.println(app.getName());
    System.out.println("GPA: " + app.getGpa());
    System.out.println("Belgeler: " + app.getDocuments().size());
    System.out.println("Yayınlar: " + app.getPublications().size());
}
```

## Test

Main.java dosyası ile test edebilirsiniz:

```bash
# Derleme
javac -d bin src/model/*.java src/service/*.java src/app/*.java

# Çalıştırma
java -cp bin app.Main
```

## Hata Yönetimi

- Dosya okuma hataları IOException ile yakalanır
- Parse hataları Exception ile yakalanır ve konsola yazdırılır
- Null kontrolü yapılarak NullPointerException önlenir
- Boş satırlar atlanır

## Model Sınıfları ile Entegrasyon

ApplicationFactory şu model sınıflarını kullanır:
- `Applicant`: Başvuru sahibi bilgileri
- `Document`: Belge bilgileri
- `FamilyInfo`: Aile bilgileri
- `Publication`: Yayın bilgileri

Tüm ilişkilendirme işlemleri otomatik olarak yapılır.
