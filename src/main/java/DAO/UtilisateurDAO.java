package DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dataBase.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Fournisseur;
import models.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    private final Connection connection;
    // constructor for initializing database connection
    public UtilisateurDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }


    public List<Utilisateur> getallUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";

        try(Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query)){

            while (resultSet.next()) {
                resultSet.getString("prenom");
                resultSet.getString("nom");
                resultSet.getString("date_naissance");
                resultSet.getString("telephone");
                resultSet.getString("email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }

}