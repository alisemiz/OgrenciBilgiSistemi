package com.ali.obs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ogrenci {

    // JavaFX TableView'in verileri dinamik olarak algılayabilmesi için
    // standart 'int', 'String' yerine JavaFX'e özel Property sınıflarını kullanıyoruz.
    // Bu, "Adı değiştirdiğimde tablodaki yazı da anında değişsin" demenin bir yoludur.

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty ad;
    private final SimpleStringProperty soyad;
    private final SimpleStringProperty ogrenciNo;

    // Bu, yeni bir Ogrenci nesnesi oluşturduğumuzda çağrılacak metottur (Constructor).
    public Ogrenci(Integer id, String ad, String soyad, String ogrenciNo) {
        this.id = new SimpleIntegerProperty(id);
        this.ad = new SimpleStringProperty(ad);
        this.soyad = new SimpleStringProperty(soyad);
        this.ogrenciNo = new SimpleStringProperty(ogrenciNo);
    }

    // --- Getter Metotları ---
    // Bu metotlar, nesnenin içindeki bilgilere dışarıdan erişmemizi sağlar.
    // Örneğin: ogrenci.getAd() -> "Ahmet" sonucunu verir.

    public int getId() {
        return id.get();
    }

    public String getAd() {
        return ad.get();
    }

    public String getSoyad() {
        return soyad.get();
    }

    public String getOgrenciNo() {
        return ogrenciNo.get();
    }

    // --- Setter Metotları ---
    // Bu metotlar, nesne oluşturulduktan sonra içindeki bilgileri değiştirmemizi sağlar.
    // Örneğin: ogrenci.setAd("Mehmet");

    public void setAd(String ad) {
        this.ad.set(ad);
    }

    public void setSoyad(String soyad) {
        this.soyad.set(soyad);
    }

    public void setOgrenciNo(String ogrenciNo) {
        this.ogrenciNo.set(ogrenciNo);
    }
}