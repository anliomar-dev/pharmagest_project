package dataBase;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try {
            // Récupération de la connexion
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("Connexion réussie !");
                // Fermez la connexion après utilisation
                conn.close();
            } else {
                System.out.println("Échec de la connexion !");
            }
        } catch (SQLException e) {
            // Affichage de l'erreur
            System.err.println("Erreur de connexion : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

