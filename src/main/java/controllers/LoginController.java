package controllers;
import dataBase.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

import javafx.scene.Parent;
import javafx.stage.StageStyle;
import utils.SessionManager;
import utils.Utils;
public class LoginController {
    @FXML public PasswordField passwordPasswordField;
    @FXML private Button loginButton;
    @FXML private Button cancelButton;
    @FXML private TextField usernameTextField;
    @FXML private Label loginMessageLabel;


    /**
     * update the colone dernier_login by the id of the user
     * @param userId the id of the authenticated user
     */
    public void updateLastLogin(int userId){
        String procedureUpdateLastLoginById = "CALL update_last_login_by_id(?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(procedureUpdateLastLoginById)) {

            // define param for stored procedure
            stmt.setInt(1, userId);

            // Execute procedure
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Handles the login button action. Checks if the username and password fields are filled,
     * then hashes the password using SHA-256.
     *
     * @param event the action event triggered by clicking the login button
     * @throws IOException if an input/output error occurs during the login process
     */
    public void loginButtonOnAction(ActionEvent event) throws IOException, SQLException {
        String username = usernameTextField.getText().trim();
        String password = passwordPasswordField.getText().trim();

        if(username.isBlank() || password.isBlank()){
            loginMessageLabel.setText("Les deux champ doivent être rempli");
            return;
        }else{
            Utils hasher = new Utils();
            String hashedPassword = Utils.hashWithSHA256(password);
            int userID = authenticate(username, hashedPassword);
            boolean isAutenticated = userID != -1;
            if(isAutenticated){
                // update last login timestamp
                updateLastLogin(userID);

                //initialize new Utils instance
                Utils sceneLoader = new Utils();
                // swith to dashboard after successful login by calling loadScene define in Utils
                sceneLoader.loadScene("Dashboard.fxml", username, loginButton);
            }else{
                loginMessageLabel.setText("nom d'utilisateur et/ou mot de passe \n incorrect");
            }
        }
    }


    /**
     * Exits the application when the cancel button is clicked.
     *
     * @param event the action event triggered by clicking the quit button
     */
    @FXML public void cancelButtonOnAction(ActionEvent event) {
        System.exit(0);
    }


    /**
     * Authenticates a user based on their username and password.
     *
     * @param username The username to authenticate.
     * @param password The plain-text password, which will be hashed for comparison.
     * @return the id of the user if the user is successfully authenticated, -1 otherwise.
     * @throws RuntimeException If a SQL error occurs during the authentication attempt.
     */
    private static int authenticate(String username, String password){
        int userId = -1;
        try {
            // connection  to database
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM public.\"utilisateur\" WHERE identifiant = ? AND mot_de_passe = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("utilisateur_id");
                String prenom = resultSet.getString("prenom");
                String nom = resultSet.getString("nom");
                Date date_naissance = Date.valueOf(resultSet.getString("date_naissance"));
                String email = resultSet.getString("email");
                String telephone = resultSet.getString("telephone");
                String adresse = resultSet.getString("adresse");
                String identifiant = resultSet.getString("identifiant");
                String status = resultSet.getString("status");
                boolean est_superadmin = resultSet.getBoolean("est_superadmin");
                SessionManager.setUserInfo(
                        prenom, nom,
                        date_naissance,
                        telephone, email,
                        adresse, identifiant,
                        status, est_superadmin);
            }
            // Close the ResultSet to release the resources it holds.
            resultSet.close();
            // Close the Statement to free up the database resources associated with it.
            statement.close();
            // Close the Connection to the database to release all resources associated with it.
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  userId;
    }

}
