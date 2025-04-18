package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Medicament;
import models.ReceptionCmd;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class VenteController {
    // Attributs
    @FXML
    private TableView<Medicament> listMedicamenttableView;
    @FXML
    private TableColumn<Medicament, String> colDci;
    @FXML
    private TableColumn<Medicament, Integer> colPrixUnit;
    @FXML
    private TableColumn<Medicament, Integer> colFamille;
    @FXML
    private TableColumn<Medicament, Integer> colStockActuel;

    @FXML
    private void initialize() {
        colDci.setCellValueFactory(new PropertyValueFactory<>("DCI"));
        colPrixUnit.setCellValueFactory(new PropertyValueFactory<>("prixUnitVente"));
        colFamille.setCellValueFactory(new PropertyValueFactory<>("famille"));
        colStockActuel.setCellValueFactory(new PropertyValueFactory<>("qteStock"));

        ObservableList<Medicament> data = FXCollections.observableArrayList(
                new Medicament("Doliprane 1000mg - Comprimé", 100, "Antalgique", 300),
                new Medicament("Efferalgan 500mg - Comprimé", 50, "Antalgique", 180),
                new Medicament("Smecta 50mg - Sachet", 35, "Antidiarrhéique", 100),
                new Medicament("Nurofen 400mg - Comprimé", 58.12, "Anti-inflammatoire", 120)

        );

        listMedicamenttableView.setItems(data);
    }

    Utils sceneLoader = new Utils();
    @FXML
    private Button DashboardButton; // button to switch to dashboard
    // Switch to the dashboard scene
    public void DashboardButtonOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        sceneLoader.loadScene("Dashboard.fxml", "Dashboard", DashboardButton);
    }

    // Méthodes
    public void enregistrerVente() {
        // Logique pour enregistrer une vente
    }

    public void annulerVente() {
        // Logique pour annuler une vente
    }
}

