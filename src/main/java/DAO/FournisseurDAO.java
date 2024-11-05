package DAO;

import dataBase.DatabaseConnection;
import models.Client;
import models.Fournisseur;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDAO {
    private final Connection connection;
    // constructor for initializing database connection
    public FournisseurDAO(DatabaseConnection dbConnection) throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Fournisseur> getAllFounisseur() throws SQLException{
        List<Fournisseur> Fournisseurs = new ArrayList<>();
        String query = "select * from fournisseur";
        try(Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query)) {

            while(resultSet.next()) {
                int id = resultSet.getInt("fournisseur_id");
                String nom = resultSet.getString("nom");
                String pays = resultSet.getString("pays");
                String adresse = resultSet.getString("adresse");
                String email = resultSet.getString("email");
                String telephone = resultSet.getString("telephone");
            }
        }catch (SQLException e) {
            e.printStackTrace();  // Gestion de l'exception
        }
         return Fournisseurs;
    }
}
