package DAO;

import dataBase.DatabaseConnection;
import models.Client;
import models.Fournisseur;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDAO {

    public static void main (String[] args) throws SQLException {
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        List<Fournisseur> fournisseurs = fournisseurDAO.getAllFounisseur();

        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println(fournisseur);
        }

    }
    private final Connection connection;
    // constructor for initializing database connection
    public FournisseurDAO() throws SQLException {
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
                Fournisseur fournisseur = new Fournisseur(id, nom, pays, adresse, email, telephone);
                Fournisseurs.add(fournisseur);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Fournisseurs;
    }


}
