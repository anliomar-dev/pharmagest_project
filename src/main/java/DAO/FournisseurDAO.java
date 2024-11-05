package DAO;

import dataBase.DatabaseConnection;
import models.Fournisseur;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDAO {

    public static void main (String[] args) throws SQLException {
        FournisseurDAO fournisseurDAO = new FournisseurDAO();

        Fournisseur fournisseur = new Fournisseur(
                "fournisseur3",
                "moroni",
                "+269 3252388",
                "fournisseur3@gmail.com",
                "Comores");
        fournisseurDAO.addFournisseur(fournisseur);
        List<Fournisseur> fournisseurs = fournisseurDAO.getAllFounisseur();

        System.out.println("Fournisseur added " + fournisseur.getNom());

        System.out.println("list des fournisseurs");

        for (Fournisseur fournisseurA : fournisseurs) {
            System.out.println(fournisseurA);
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

    public void addFournisseur(Fournisseur fournisseur) throws SQLException {
        String query = "INSERT INTO fournisseur (nom, adresse, telephone, email, pays) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fournisseur.getNom());
            stmt.setString(2, fournisseur.getAdresse());
            stmt.setString(3, fournisseur.getTelephone());
            stmt.setString(4, fournisseur.getEmail());
            stmt.setString(5, fournisseur.getPays());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
