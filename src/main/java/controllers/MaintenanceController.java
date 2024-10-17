package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utils.Utils;

import java.io.IOException;

public class MaintenanceController {
    @FXML public Button founisseurButton;
    @FXML public  Button utilisateurButton;
    public void returnButtonOnAction(ActionEvent event) {
    }

    public void utilisateurButtonOnAction(ActionEvent event) throws IOException {
        Utils sceneLoader = new Utils();
        // swith to maintenace interface
        sceneLoader.loadScene("Utilisateur.fxml", "utilisateurs", utilisateurButton);
    }

    public void founisseurButtonOnAction(ActionEvent event) throws IOException {
        Utils sceneLoader = new Utils();
        // swith to maintenace interface
        sceneLoader.loadScene("Fournisseurs.fxml", "Maintenance founisseurs", founisseurButton);
    }
}
