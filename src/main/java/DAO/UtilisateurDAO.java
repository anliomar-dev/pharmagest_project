package DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dataBase.DatabaseConnection;
import models.Utilisateur;

import java.sql.*;

public class UtilisateurDAO {

    //Connection to database
    private final Connection conn;
    public UtilisateurDAO() throws SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    //Get all information of utilisateurs
    public ObservableList<Utilisateur> getAllUtilisateurs() throws SQLException{
        ObservableList<Utilisateur> utilisateurList = FXCollections.observableArrayList();
        String allUtilisateur = "SELECT * FROM utilisateur ORDER BY nom";
        try (PreparedStatement stmt = conn.prepareStatement(allUtilisateur);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getInt("utilisateur_id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("identifiant"),
                        rs.getString("status"),
                        rs.getBoolean("est_superadmin")
                );
                utilisateurList.add(utilisateur);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching utilisateurs: " + e.getMessage());
        }
        return utilisateurList;
    }

    //Verify existing username
    public boolean verifyUsername(String identifiant) {
        String verifyIdentifiant = "SELECT COUNT(*) FROM utilisateur WHERE TRIM(LOWER(identifiant)) = TRIM(LOWER(?))";
        try (PreparedStatement stmt = conn.prepareStatement(verifyIdentifiant)) {
            stmt.setString(1, identifiant);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking username: " + e.getMessage());
        }
        return false;
    }

    //Get utilisateur id
    public int getUtilisateurId(String username) throws SQLException{
        String getId = "SELECT utilisateur_id FROM utilisateur WHERE identifiant = ?";
        try(PreparedStatement stmt = conn.prepareStatement(getId)){
            stmt.setString(1, username);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("utilisateur_id");
                }
            }
        }
        return -1;
    }

    //Ajouter utilisateur
    public boolean addUtilisateur (Utilisateur utilisateur) throws SQLException{
        String ajouterUtilisateur = "INSERT INTO utilisateur (prenom, nom, telephone, email, identifiant, mot_de_passe, status, est_superadmin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(ajouterUtilisateur)) {
            stmt.setString(1, utilisateur.getPrenom());
            stmt.setString(2, utilisateur.getNom());
            stmt.setString(3, utilisateur.getTelephone());
            stmt.setString(4, utilisateur.getEmail());
            stmt.setString(5, utilisateur.getIdentifiant());
            stmt.setString(6, utilisateur.getMotDePasse());
            stmt.setString(7, utilisateur.getStatus());
            stmt.setBoolean(8, utilisateur.isEstSuperAdmin());
            return stmt.executeUpdate() > 0;
        }
    }

    //Modify utilisateur
    public boolean modifierUtilisateur(int userID, Utilisateur utilisateur) throws SQLException{
        String modifyUtilisateurPassword = "UPDATE utilisateur SET prenom = ?, nom = ?, telephone = ?, email = ?, identifiant = ?, mot_de_passe = ?, status = ?, est_superadmin = ? WHERE utilisateur_id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(modifyUtilisateurPassword)) {
            stmt.setString(1, utilisateur.getPrenom());
            stmt.setString(2, utilisateur.getNom());
            stmt.setString(3, utilisateur.getTelephone());
            stmt.setString(4, utilisateur.getEmail());
            stmt.setString(5, utilisateur.getIdentifiant());
            stmt.setString(6, utilisateur.getMotDePasse());
            stmt.setString(7, utilisateur.getStatus());
            stmt.setBoolean(8, utilisateur.isEstSuperAdmin());
            stmt.setInt(9, userID);
            return stmt.executeUpdate() > 0;
        }
    }

    //Supprimer utilisateur
    public boolean deleteUtilisateur(int userID) throws SQLException{
        String supprimerUtilisateur = "DELETE FROM utilisateur WHERE utilisateur_id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(supprimerUtilisateur)) {
            stmt.setInt(1, userID);
            return stmt.executeUpdate() > 0;
        }
    }
}