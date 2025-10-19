package com.ali.obs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // FXML dosyasını yüklemek için daha güvenli, mutlak bir yol kullanıyoruz.
            // Bu, "classpath'in kökünden başla, com/ali/obs klasörüne git ve OgrenciView.fxml'i bul" demektir.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/ali/obs/OgrenciView.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("Öğrenci Bilgi Sistemi");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();

        } catch (IOException e) {
            // Eğer bir hata olursa, konsola detaylı bir şekilde yazdırılacak.
            System.err.println("FXML dosyası yüklenirken bir hata oluştu!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}