package com.ali.obs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    private static final String DATABASE_URL = "jdbc:sqlite:ogrenciler.db";

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ogrenciler ("
                + " id integer PRIMARY KEY AUTOINCREMENT,"
                + " ad text NOT NULL,"
                + " soyad text NOT NULL,"
                + " ogrenci_no text NOT NULL UNIQUE"
                + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addOgrenci(Ogrenci ogrenci) {
        String sql = "INSERT INTO ogrenciler(ad, soyad, ogrenci_no) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ogrenci.getAd());
            pstmt.setString(2, ogrenci.getSoyad());
            pstmt.setString(3, ogrenci.getOgrenciNo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Ogrenci> getAllOgrenciler() {
        String sql = "SELECT id, ad, soyad, ogrenci_no FROM ogrenciler";
        List<Ogrenci> ogrenciler = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Ogrenci ogrenci = new Ogrenci(
                        rs.getInt("id"),
                        rs.getString("ad"),
                        rs.getString("soyad"),
                        rs.getString("ogrenci_no"));
                ogrenciler.add(ogrenci);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ogrenciler;
    }

    public void updateOgrenci(Ogrenci ogrenci) {
        String sql = "UPDATE ogrenciler SET ad = ? , "
                + "soyad = ? , "
                + "ogrenci_no = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ogrenci.getAd());
            pstmt.setString(2, ogrenci.getSoyad());
            pstmt.setString(3, ogrenci.getOgrenciNo());
            pstmt.setInt(4, ogrenci.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteOgrenci(Ogrenci ogrenci) {
        String sql = "DELETE FROM ogrenciler WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ogrenci.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}