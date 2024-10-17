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
import utils.Utils;
public class LoginController {
    @FXML public PasswordField passwordPasswordFiled;
    @FXML private Button loginButton;
    @FXML private Button cancelButton;
    @FXML private TextField usernameTextField;
    @FXML private Label loginMessageLabel;

    /**
     * Handles the login button action. Checks if the username and password fields are filled,
     * then hashes the password using SHA-256.
     *
     * @param event the action event triggered by clicking the login button
     * @throws IOException if an input/output error occurs during the login process
     */
    public void loginButtonOnAction(ActionEvent event) throws IOException {
        String username = usernameTextField.getText().trim();
        String password = passwordPasswordFiled.getText().trim();

        if(username.isBlank() || password.isBlank()){
            loginMessageLabel.setText("Les deux champ doivent être rempli");
            return;
        }else{
            Utils hasher = new Utils();
            String hashedPassword = Utils.hashWithSHA256(password);
            boolean isAutenticated = authenticate(username, hashedPassword);
            if(isAutenticated){
                Utils sceneLoader = new Utils();
                // swith to dashboard after successful login
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
     * @return true if the user is successfully authenticated, false otherwise.
     * @throws RuntimeException If a SQL error occurs during the authentication attempt.
     */
    private static boolean authenticate(String username, String password){
        boolean isAuthenticated = false;
        try {
            // connection  to database
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM public.\"utilisateur\" WHERE identifiant = ? AND mot_de_passe = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isAuthenticated = true;
            }
            // Close the ResultSet to release the resources it holds.
            resultSet.close();
            // Close the Statement to free up the database resources associated with it.
            statement.close();
            // Close the Connection to the database to release all resources associated with it.
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  isAuthenticated;
    }

}
