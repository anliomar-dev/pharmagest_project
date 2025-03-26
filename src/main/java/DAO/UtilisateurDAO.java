package DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dataBase.DatabaseConnection;
import models.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    //Connection to database
    private final Connection conn;
    public UtilisateurDAO() throws SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    //Get all information of utilisateurs
    public ObservableList<Utilisateur> getAllUtilisateurs() throws SQLException{
        ObservableList<Utilisateur> utilisateurList = FXCollections.observableArrayList();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM utilisateur");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getInt("utilisateur_id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("adresse"),
                        rs.getString("identifiant"),
                        rs.getBoolean("est_superadmin")
                );
                utilisateurList.add(utilisateur);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching utilisateurs: " + e.getMessage());
        }
        return utilisateurList;
    }

    //Get all username
    public boolean verifyUsername(String identifiant) {
        String sql = "SELECT COUNT(*) FROM utilisateur WHERE identifiant = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public void addUtilisateur (Utilisateur utilisateur) throws SQLException{
        String query = "INSERT INTO utilisateur (prenom, nom, telephone, email, adresse, identifiant, mot_de_passe) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, utilisateur.getPrenom());
            stmt.setString(2, utilisateur.getNom());
            stmt.setString(4, utilisateur.getTelephone());
            stmt.setString(5, utilisateur.getEmail());
            stmt.setString(6, utilisateur.getAdresse());
            stmt.setString(7, utilisateur.getIdentifiant());
            stmt.setString(8, utilisateur.getMotDePasse());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}