package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.SessionManager;
import utils.Utils;
import models.Utilisateur;

import java.io.IOException;

public class DashboardController {
    Utils sceneLoader = new Utils();


    @FXML private Label usernameLabel;

    public void initialize(){
        usernameLabel.setText("Bienvenue " + SessionManager.setUserInfo(SessionManager.getIdentifiant()));
    }

    @FXML private Button maintenanceButton;
    public void maintenanceButtonOnAction(ActionEvent event) throws IOException {
        sceneLoader.loadScene("Maintenance.fxml", "Maintenance", maintenanceButton);
    }

    @FXML private Button changeButton;
    public void changeButtonOnAction(ActionEvent event) throws IOException {
        sceneLoader.loadScene("login.fxml", "Login", changeButton);
    }

    public void leaveButtonOnAction(ActionEvent event) { System.exit(0); }
}
