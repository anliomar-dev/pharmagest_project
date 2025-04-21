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
        String allForme = "SELECT * FROM forme ORDER BY nom_forme";
        try (PreparedStatement stmt = conn.prepareStatement(allForme);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Forme forme = new Forme(
                        rs.getString("nom_forme")
                );
                formList.add(forme);
            }
        }
        return formList;
    }

    public Forme getFormeByName(String nomForme) throws SQLException {
        String nameForme = "SELECT * FROM forme WHERE nom_forme = ?";
        try (PreparedStatement stmt = conn.prepareStatement(nameForme)) {
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

    public boolean verifyMedicamentForme(String nameForme) throws SQLException{
        String verifyForme = "SELECT 1 FROM medicament WHERE forme_nom_forme = ?";
        try(PreparedStatement stmt = conn.prepareStatement(verifyForme)){
            stmt.setString(1, nameForme);
            return stmt.execute();
        }
    }

    public boolean addForme (Forme forme) throws SQLException{
        String ajouterForme = "INSERT INTO forme (nom_forme) VALUES (?)";
        try(PreparedStatement stmt = conn.prepareStatement(ajouterForme)) {
            stmt.setString(1, forme.getNomForme());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteForme(String forme) throws SQLException{
        String supprimerForme = "DELETE FROM forme WHERE nom_forme = ?";
        try(PreparedStatement stmt = conn.prepareStatement(supprimerForme)) {
            stmt.setString(1, forme);
            return stmt.executeUpdate() > 0;
        }
    }
}
