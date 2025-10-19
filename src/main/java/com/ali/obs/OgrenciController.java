package com.ali.obs;

// Gerekli tüm import'lar burada listelenmiştir.
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class OgrenciController {

    // FXML Değişkenleri
    @FXML private TableView<Ogrenci> ogrenciTableView;
    @FXML private TableColumn<Ogrenci, String> adColumn;
    @FXML private TableColumn<Ogrenci, String> soyadColumn;
    @FXML private TableColumn<Ogrenci, String> ogrenciNoColumn;
    @FXML private TextField adTextField;
    @FXML private TextField soyadTextField;
    @FXML private TextField ogrenciNoTextField;
    @FXML private Button ekleButton;
    @FXML private Button silButton;
    @FXML private Button guncelleButton;
    @FXML private TextField searchTextField; // Arama kutusu

    // Sınıf Değişkenleri
    private DatabaseUtil dbUtil = new DatabaseUtil();
    private ObservableList<Ogrenci> ogrenciListesi = FXCollections.observableArrayList();
    private Ogrenci seciliOgrenci;

    @FXML
    public void initialize() {
        dbUtil.createTable();
        // DÜZELTME: Buradaki yazım hatası düzeltildi.
        adColumn.setCellValueFactory(new PropertyValueFactory<>("ad"));
        soyadColumn.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        ogrenciNoColumn.setCellValueFactory(new PropertyValueFactory<>("ogrenciNo"));

        // Veritabanından tüm öğrencileri ana listemize yüklüyoruz.
        ogrenciListesi.addAll(dbUtil.getAllOgrenciler());

        // --- FİLTRELEME MANTIĞI ---
        FilteredList<Ogrenci> filteredData = new FilteredList<>(ogrenciListesi, p -> true);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ogrenci -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (ogrenci.getAd().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (ogrenci.getSoyad().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (ogrenci.getOgrenciNo().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Ogrenci> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ogrenciTableView.comparatorProperty());
        ogrenciTableView.setItems(sortedData);
        // --- FİLTRELEME MANTIĞI BİTTİ ---

        // Seçim dinleyicisi
        ogrenciTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    seciliOgrenci = newValue;
                    if (newValue != null) {
                        adTextField.setText(seciliOgrenci.getAd());
                        soyadTextField.setText(seciliOgrenci.getSoyad());
                        ogrenciNoTextField.setText(seciliOgrenci.getOgrenciNo());
                    }
                }
        );
    }

    @FXML
    private void ekleButtonAction() {
        String ad = adTextField.getText();
        String soyad = soyadTextField.getText();
        String no = ogrenciNoTextField.getText();

        if (ad.isEmpty() || soyad.isEmpty() || no.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Uyarı", "Eksik Bilgi", "Lütfen tüm alanları doldurun.");
            return;
        }

        Ogrenci yeniOgrenci = new Ogrenci(0, ad, soyad, no);
        dbUtil.addOgrenci(yeniOgrenci);

        // Ana listeyi güncelle, filtre otomatik olarak çalışacaktır.
        ogrenciListesi.clear();
        ogrenciListesi.addAll(dbUtil.getAllOgrenciler());

        temizleForm();
    }

    @FXML
    private void silButtonAction() {
        if (seciliOgrenci == null) {
            showAlert(Alert.AlertType.WARNING, "Uyarı", "Öğrenci Seçilmedi", "Lütfen silmek için bir öğrenci seçin.");
            return;
        }

        Optional<ButtonType> result = showConfirmation("Öğrenci Silme", "Bu öğrenciyi silmek istediğinizden emin misiniz?", seciliOgrenci.getAd() + " " + seciliOgrenci.getSoyad() + " kalıcı olarak silinecektir.");

        if (result.isPresent() && result.get() == ButtonType.OK) {
            dbUtil.deleteOgrenci(seciliOgrenci);
            ogrenciListesi.remove(seciliOgrenci); // Ana listeden de sil
            temizleForm();
        }
    }

    @FXML
    private void guncelleButtonAction() {
        if (seciliOgrenci == null) {
            showAlert(Alert.AlertType.WARNING, "Uyarı", "Öğrenci Seçilmedi", "Lütfen güncellemek için bir öğrenci seçin.");
            return;
        }

        String yeniAd = adTextField.getText();
        String yeniSoyad = soyadTextField.getText();
        String yeniNo = ogrenciNoTextField.getText();

        seciliOgrenci.setAd(yeniAd);
        seciliOgrenci.setSoyad(yeniSoyad);
        seciliOgrenci.setOgrenciNo(yeniNo);

        dbUtil.updateOgrenci(seciliOgrenci);
        ogrenciTableView.refresh();
        temizleForm();
    }

    private void temizleForm() {
        ogrenciTableView.getSelectionModel().clearSelection();
        adTextField.clear();
        soyadTextField.clear();
        ogrenciNoTextField.clear();
        seciliOgrenci = null;
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Optional<ButtonType> showConfirmation(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}