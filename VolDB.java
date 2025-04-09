package com.example.tp_poo_kennedy;

import java.sql.*;
import java.util.*;

public class VolDB {
    private static final String URL = "jdbc:mysql://localhost:3306/VOL_CHECKER";
    private static final String USER = "root";
    private static final String PASSWORD = "";  // ou ton mot de passe

    public static void ajouterAppareil(Appareil a) {
        String sql = "INSERT INTO Appareil(type, numeroSerie, marque) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.getType());
            stmt.setString(2, a.getNumeroSerie());
            stmt.setString(3, a.getMarque());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur ajout: " + e.getMessage());
        }
    }

    public static boolean estVole(String numeroSerie) {
        String sql = "SELECT COUNT(*) FROM Appareil WHERE numeroSerie = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroSerie);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("Erreur recherche: " + e.getMessage());
            return false;
        }
    }

    public static List<Appareil> tous() {
        List<Appareil> liste = new ArrayList<>();
        String sql = "SELECT numeroSerie, description FROM Appareil";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                liste.add(new Appareil(rs.getString("type"), rs.getString("numeroserie"),
                        rs.getString("marque")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur liste: " + e.getMessage());
        }
        return liste;
    }
}
