# Öğrenci Bilgi Sistemi

Bu proje, JavaFX ve SQLite kullanılarak geliştirilmiş, modern ve kullanıcı dostu bir masaüstü öğrenci yönetim uygulamasıdır. Kullanıcıların öğrenci kayıtlarını kolayca oluşturmasına, görüntülemesine, güncellemesine ve silmesine (CRUD) olanak tanır.

*A desktop student management application developed with JavaFX and SQLite. It allows users to easily perform CRUD (Create, Read, Update, Delete) operations on student records.*

---


## 🚀 Özellikler

- **Öğrenci Yönetimi:** Tam CRUD (Oluştur, Oku, Güncelle, Sil) işlevselliği.
- **Veri Kalıcılığı:** Tüm öğrenci verileri, program kapatıldığında bile kaybolmaması için lokal bir **SQLite** veritabanında saklanır.
- **Dinamik Arama:** Kullanıcılar, arama kutusuna yazdıkları anda öğrenci listesini isme, soyada veya numaraya göre anında filtreleyebilir.
- **Kullanıcı Dostu Arayüz:** Temiz, modern ve sezgisel bir arayüz.
- **Güvenli İşlemler:** Kullanıcıyı yanlışlıkla veri silmekten korumak için onay pencereleri.
- **Hata Kontrolü:** Eksik veri girişi gibi durumlar için kullanıcıyı bilgilendiren uyarı pencereleri.

---

## 🛠️ Kullanılan Teknolojiler

- **Programlama Dili:** Java (JDK 23)
- **Arayüz (GUI):** JavaFX
- **Veritabanı:** SQLite
- **Proje Yönetimi ve Bağımlılıklar:** Maven
- **Tasarım Desteği:** Scene Builder (FXML için)
- **Paketleme:** jpackage, WiX Toolset

---

## 👨‍💻 Kurulum ve Çalıştırma (Geliştiriciler İçin)

Projenin kaynak kodunu kendi bilgisayarınızda çalıştırmak için aşağıdaki adımları izleyin.

### Gereksinimler
- JDK 17 veya üstü
- Apache Maven
- Git

### Adımlar
1.  **Depoyu Klonlayın:**
    ```bash
    git clone [https://github.com/alisemiz/OgrenciBilgiSistemi.git]
    ```

2.  **Projeyi IDE'de Açın:**
    - Projeyi IntelliJ IDEA veya tercih ettiğiniz başka bir IDE'de açın.
    - IDE, `pom.xml` dosyasını okuyarak gerekli tüm bağımlılıkları (JavaFX, SQLite vb.) otomatik olarak indirecektir.

3.  **Uygulamayı Çalıştırın:**
    - IntelliJ IDEA'nın sağ tarafındaki Maven panelini açın.
    - `Plugins` -> `openjfx` altındaki `javafx:run` komutuna çift tıklayarak uygulamayı başlatın.

---

## 📦 Uygulamayı Paketleme (.exe Oluşturma)

Projenin dağıtılabilir bir Windows kurulum dosyası (`.exe`) oluşturması için aşağıdaki adımlar izlenmelidir.

### Gereksinimler
- WiX Toolset v3.11 veya üstü

### Komut
1.  IntelliJ IDEA'da `Shift`+`Shift` tuşlarına basarak "Run Anything" penceresini açın.
2.  Aşağıdaki komutu yazın ve `Enter`'a basın:
    ```bash
    mvn clean package
    ```

İşlem tamamlandığında, kurulum dosyanız **`target/dist`** klasörünün içinde `OgrenciBilgiSistemi-1.0.0.exe` olarak hazır olacaktır.

---

## 🔮 Gelecek Geliştirmeler

- [ ] Her öğrenci için ders ve not ekleme özelliği.
- [ ] Öğrenciye tıklandığında ders/not detaylarını gösteren yeni bir pencere.
- [ ] Kullanıcı girişi (login) sistemi.

---

