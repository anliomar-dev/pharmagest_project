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
public class LoginController {
    @FXML public PasswordField passwordPasswordFiled;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label loginMessageLabel;

    public void loginButtonOnAction(ActionEvent event) throws IOException {
        String username = usernameTextField.getText().trim();
        String password = passwordPasswordFiled.getText().trim();
        if(username.isBlank() || password.isBlank()){
            loginMessageLabel.setText("Les deux champ doivent être rempli");
            return;
        }else{
            loginMessageLabel.setText("");
        }
    }

    @FXML
    public void cancelButtonOnAction(ActionEvent event) {
        System.exit(0);
    }
}
