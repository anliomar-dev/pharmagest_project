package DAO;

import dataBase.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Famille;
import models.Forme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilleDAO {
    private final Connection conn;
    public FamilleDAO() throws SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    public ObservableList<Famille> getAllFamille() throws SQLException{
        ObservableList<Famille> familleList = FXCollections.observableArrayList();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM famille");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Famille famille = new Famille(
                        rs.getInt("num_famille"),
                        rs.getString("nom_famille")
                );
                familleList.add(famille);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching famille: " + e.getMessage());
        }
        return familleList;
    }

    public int getNumFamille(String nomFamille) throws SQLException {
        String query = "SELECT num_famille FROM famille WHERE nom_famille = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nomFamille);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("num_famille");
                } else {
                    throw new SQLException("No famille found with name: " + nomFamille);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching famille: " + e.getMessage());
            throw e;  // Re-throw after logging
        }
    }

    public boolean addFamille (Famille famille) throws SQLException{
        String query = "INSERT INTO famille (nom_famille) VALUES (?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, famille.getNomFamille());
            return stmt.executeUpdate() > 0;
        }
    }

    public void deleteFamille(String famille) throws SQLException{
        String query = "DELETE FROM famille WHERE nom_famille = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, famille);
            stmt.executeUpdate();
        }
    }
}
