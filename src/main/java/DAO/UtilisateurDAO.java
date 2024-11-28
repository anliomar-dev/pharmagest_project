package DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dataBase.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Fournisseur;
import models.Utilisateur;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilisateurDAO {


    private final Connection connection;
    // constructor for initializing database connection
    public UtilisateurDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

//    public List<Utilisateur> getallUtilisateurs() {
//        List<Utilisateur> utilisateurs = new ArrayList<>();
//        String query = "SELECT * FROM utilisateur";
//
//        try(Statement stmt = connection.createStatement();
//            ResultSet resultSet = stmt.executeQuery(query)){
//
//            while (resultSet.next()) {
//                resultSet.getString("prenom");
//                resultSet.getString("nom");
////                resultSet.getString("date_naissance");
//                resultSet.getString("telephone");
//                resultSet.getString("email");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return utilisateurs;
//    }

    public void addUtilisateur (Utilisateur utilisateur) throws SQLException{
        String query = "INSERT INTO utilisateur (prenom, nom, telephone, email, adresse, identifiant, mot_de_passe) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, utilisateur.getPrenom());
            stmt.setString(2, utilisateur.getNom());
            stmt.setString(3, utilisateur.getTelephone());
            stmt.setString(4, utilisateur.getEmail());
            stmt.setString(5, utilisateur.getAdresse());
            stmt.setString(6, utilisateur.getIdentifiant());
            stmt.setString(7, utilisateur.getMotDePasse());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}