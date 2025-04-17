package DAO;

import dataBase.DatabaseConnection;
import models.Fournisseur;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDAO {

    /*public static void main (String[] args) throws SQLException {
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
    }*/
    private final Connection connection;
    // constructor for initializing database connection
    public FournisseurDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * get all founisseur
     * @return List<Fournisseur>
     * @throws SQLException
     */
    public List<Fournisseur> getAllFounisseur() throws SQLException{
        List<Fournisseur> Fournisseurs = new ArrayList<>();
        String query = "SELECT * FROM fournisseur";
        try(Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query)) {

            while(resultSet.next()) {
                int idFournisseur = resultSet.getInt("fournisseur_id");
                String nom = resultSet.getString("nom");
                String pays = resultSet.getString("pays");
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                String adresse = resultSet.getString("adresse");

                Fournisseur fournisseur = new Fournisseur(idFournisseur, pays, nom, telephone, email, adresse);
                Fournisseurs.add(fournisseur);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Fournisseurs;
    }

    /**
     * Retrieves a supplier from the database.
     *
     * @param id The ID of the supplier to retrieve.
     * @return The supplier corresponding to the given ID, or null if it does not exist.
     * @throws SQLException If an error occurs while executing the SQL query.
     */
    public Fournisseur getFournisseurById(int id) throws SQLException{
        String query = "select * from fournisseur where fournisseur_id = ?";
        Fournisseur fournisseur = null;
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try(ResultSet resultSet = stmt.executeQuery()) {
                if(resultSet.next()) {
                    int fournisseurId = resultSet.getInt("fournisseur_id");
                    String nom = resultSet.getString("nom");
                    String pays = resultSet.getString("pays");
                    String telephone = resultSet.getString("telephone");
                    String email = resultSet.getString("email");
                    String adresse = resultSet.getString("adresse");

                    fournisseur = new Fournisseur(fournisseurId, pays, nom, telephone, email, adresse);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return fournisseur;
    }

    /**
     * insert new founisseur in the database
     * @param fournisseur instance of model Founisseur
     * @throws SQLException
     */
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

    /**
     * update founisseur informations
     * @param fournisseur instance on model Founisseur
     * @param id_fournisseur the id of founisseur we want update data
     * @throws SQLException
     */
    public void updateFournisseur(Fournisseur fournisseur, int id_fournisseur) throws SQLException {
        String query = "UPDATE fournisseur SET nom = ?, adresse = ?, telephone = ?, email = ?, pays = ? WHERE fournisseur_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fournisseur.getNom());
            stmt.setString(2, fournisseur.getAdresse());
            stmt.setString(3, fournisseur.getTelephone());
            stmt.setString(4, fournisseur.getEmail());
            stmt.setString(5, fournisseur.getPays());
            stmt.setInt(6, id_fournisseur);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * delete a founisseur in the database
     * @param id_fournisseur the id of the founisseur we want to delete
     * @throws SQLException
     */
    public void deleteFournisseur(int id_fournisseur) throws SQLException {
        String query = "DELETE FROM fournisseur WHERE fournisseur_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id_fournisseur);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
