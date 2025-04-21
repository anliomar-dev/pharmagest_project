package DAO;

import dataBase.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Famille;
import models.Forme;
import models.Medicament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicamentDAO {

    //Connection to database
    private final Connection conn;
    public MedicamentDAO() throws SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    public ObservableList<Medicament> getAllMedicament() throws SQLException {
        ObservableList<Medicament> allMedicamentList = FXCollections.observableArrayList();
        String allMedicament = "SELECT m.*, f.nom_forme, fam.nom_famille FROM medicament m JOIN forme f ON m.forme_nom_forme = f.nom_forme JOIN famille fam ON m.famille_num_famille = fam.num_famille ORDER BY m.dci";
        try (PreparedStatement stmt = conn.prepareStatement(allMedicament);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Forme forme = new Forme(rs.getString("nom_forme"));
                Famille famille = new Famille(rs.getString("nom_famille"));
                Medicament medicament = new Medicament(
                        rs.getString("dci"),
                        rs.getString("dosage"),
                        rs.getDouble("prixunit_vente"),
                        rs.getDouble("prixunit_achat"),
                        rs.getInt("qte_stock"),
                        forme,
                        famille
                );
                allMedicamentList.add(medicament);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching medicament: " + e.getMessage());
        }
        return allMedicamentList;
    }

    public ObservableList<Medicament> getPrixMedicament() throws SQLException {
        ObservableList<Medicament> medicamentList = FXCollections.observableArrayList();
        String prixMedicament = "SELECT dci, prixunit_vente, prixunit_achat FROM medicament ORDER BY dci";
        try (PreparedStatement stmt = conn.prepareStatement(prixMedicament);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Medicament medicament = new Medicament(
                        rs.getString("dci"),
                        rs.getDouble("prixunit_vente"),
                        rs.getDouble("prixunit_achat")
                );
                medicamentList.add(medicament);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching medicament: " + e.getMessage());
        }
        return medicamentList;
    }

    public boolean MAJprix(String DCI, double prixVente, double prixAchat) throws SQLException{
        String majPrix = "UPDATE medicament SET prixunit_achat = ?, prixunit_vente = ? WHERE dci = ?";
        try(PreparedStatement stmt = conn.prepareStatement(majPrix)) {
            stmt.setDouble(2, prixAchat);
            stmt.setDouble(1, prixVente);
            stmt.setString(3, DCI);

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean addMedicament(Medicament medicament) throws SQLException{
        String ajouterMedicament = "INSERT INTO medicament (dci, dosage, prixunit_vente, prixunit_achat, qte_stock, forme_nom_forme, famille_num_famille) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(ajouterMedicament)) {
            stmt.setString(1, medicament.getDCI());
            stmt.setString(2, medicament.getDosage());
            stmt.setDouble(3, medicament.getPrixUnitVente());
            stmt.setDouble(4, medicament.getPrixUnitAchat());
            stmt.setInt(5, medicament.getQteStock());
            stmt.setString(6, medicament.getForme().getNomForme());
            stmt.setInt(7, medicament.getFamille().getNumFamille());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean modifierMedicament(String originalDCI, Medicament medicament) throws SQLException{
        String modifyMedicament = "UPDATE medicament SET dci = ?, dosage = ?, prixunit_vente = ?, prixunit_achat = ?, qte_stock = ?, forme_nom_forme = ?, famille_num_famille = ? WHERE dci = ?";
        try(PreparedStatement stmt = conn.prepareStatement(modifyMedicament)) {
            stmt.setString(1, medicament.getDCI());
            stmt.setString(2, medicament.getDosage());
            stmt.setDouble(3, medicament.getPrixUnitVente());
            stmt.setDouble(4, medicament.getPrixUnitAchat());
            stmt.setInt(5, medicament.getQteStock());
            stmt.setString(6, medicament.getForme().getNomForme());
            stmt.setInt(7, medicament.getFamille().getNumFamille());
            stmt.setString(8, originalDCI);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteMedicament(String dci) throws SQLException{
        String supprimerMedicament = "DELETE FROM medicament WHERE dci = ?";
        try(PreparedStatement stmt = conn.prepareStatement(supprimerMedicament)) {
            stmt.setString(1, dci);
            return stmt.executeUpdate() > 0;
        }
    }
}
