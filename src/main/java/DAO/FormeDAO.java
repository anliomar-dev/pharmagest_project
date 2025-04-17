package DAO;

import dataBase.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Forme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormeDAO {

    private final Connection conn;
    public FormeDAO() throws SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    public ObservableList<Forme> getAllForme() throws SQLException{
        ObservableList<Forme> formList = FXCollections.observableArrayList();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM forme");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Forme forme = new Forme(
                        rs.getString("nom_forme")
                );
                formList.add(forme);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching forme: " + e.getMessage());
        }
        return formList;
    }

    public Forme getFormeByName(String nomForme) throws SQLException {
        String query = "SELECT * FROM forme WHERE nom_forme = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nomForme);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Forme(
                            rs.getString("nom_forme")
                    );
                }
            }
        }
        throw new SQLException("Forme not found: " + nomForme);
    }

    public boolean addForme (Forme forme) throws SQLException{
        String query = "INSERT INTO forme (nom_forme) VALUES (?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, forme.getNomForme());
            return stmt.executeUpdate() > 0;
        }
    }

//    public boolean modifierForme(String originalForme, Forme forme) throws SQLException{
//    }

    public void deleteForme(String forme) throws SQLException{
        String query = "DELETE FROM forme WHERE nom_forme = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, forme);
            stmt.executeUpdate();
        }
    }
}
