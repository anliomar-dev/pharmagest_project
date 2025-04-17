package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.MedicamentCommande;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;

public class CommandeController {

    @FXML
    private TableView<MedicamentCommande> commandeTableView;
    @FXML
    private TableColumn<MedicamentCommande, String> colNom;
    @FXML
    private TableColumn<MedicamentCommande, Integer> colStock;
    @FXML
    private TableColumn<MedicamentCommande, Integer> colSeuil;
    @FXML
    private TableColumn<MedicamentCommande, Integer> colMax;
    @FXML
    private TableColumn<MedicamentCommande, Integer> colCommander;
    @FXML
    private TableColumn<MedicamentCommande, Double> colPU;
    @FXML
    private TableColumn<MedicamentCommande, Double> colMontant;
    @FXML
    private TableColumn<MedicamentCommande, String> colFournisseur;

    @FXML
    private void initialize() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colSeuil.setCellValueFactory(new PropertyValueFactory<>("seuil"));
        colMax.setCellValueFactory(new PropertyValueFactory<>("max"));
        colCommander.setCellValueFactory(new PropertyValueFactory<>("aCommander"));
        colPU.setCellValueFactory(new PropertyValueFactory<>("pu"));
        colMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        colFournisseur.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));

        ObservableList<MedicamentCommande> data = FXCollections.observableArrayList(
                new MedicamentCommande("Doliprane 1000mg - Comprimé", 20, 40, 500, 2.5, "Sanofi"),
                new MedicamentCommande("Efferalgan 500mg - Comprimé", 18, 30, 400, 2.1, "UPS"),
                new MedicamentCommande("Smecta 50mg - Sachet", 42, 50, 400, 7.41, "Aspen"),
                new MedicamentCommande("Nurofen 400mg - Comprimé", 12, 20, 100, 28.12, "Unicorn"),
                new MedicamentCommande("Panadol 500mg - Comprimé", 23, 40, 500, 2.43, "Unicorn")
        );

        commandeTableView.setItems(data);
    }
    Utils sceneLoader = new Utils();
    @FXML private Button DashboardButton; // button to switch to dashboard
    // Switch to the dashboard scene
    public void DashboardButtonOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        sceneLoader.loadScene("Dashboard.fxml", "Dashboard", DashboardButton);
    }
}
