package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utils.Utils;

import java.io.IOException;

public class DashboardController {
    @FXML private Button maintenanceButton;
    public void maintenanceButtonOnAction(ActionEvent event) throws IOException {
        Utils sceneLoader = new Utils();
        // swith to maintenace interface
        sceneLoader.loadScene("Maintenance.fxml", "Maintenance founisseurs", maintenanceButton);
    }

    public void changeButtonOnAction(ActionEvent event) {
    }

    public void leaveButtonOnAction(ActionEvent event) {
    }
}
