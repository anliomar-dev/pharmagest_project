package dataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String DATABASENAME = "pharmagestDB";
    private static final String URL = "jdbc:postgresql://localhost:5432/" + DATABASENAME;
    private static final String USER = "postgres";
    private static final String PASSWORD = "@Anliomar285.";

    // Méthode pour obtenir une connexion à PostgreSQL
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
