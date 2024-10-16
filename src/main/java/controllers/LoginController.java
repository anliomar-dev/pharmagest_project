package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
            String hashedPassword = hasher.hashWithSHA256(password);
            loginMessageLabel.setText("");
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



}
