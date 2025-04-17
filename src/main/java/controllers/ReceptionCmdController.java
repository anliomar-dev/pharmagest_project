package controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.MedicamentCommande;
import models.ReceptionCmd;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;

public class ReceptionCmdController {

    @FXML
    private TableView<ReceptionCmd> tableView;
    @FXML
    private TableColumn<ReceptionCmd, String> colNom;
    @FXML
    private TableColumn<ReceptionCmd, Integer> colStockActuel;
    @FXML
    private TableColumn<ReceptionCmd, Integer> colQteCommandee;
    @FXML
    private TableColumn<ReceptionCmd, Integer> colQteLivree;
    @FXML
    private TableColumn<ReceptionCmd, Integer> colPuAchat;
    @FXML
    private TableColumn<ReceptionCmd, Double> colLigneTotal;
    @FXML
    private TableColumn<ReceptionCmd, Double> colStockApres;

    @FXML
    private void initialize() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("medicament"));
        colStockActuel.setCellValueFactory(new PropertyValueFactory<>("stockActuel"));
        colQteCommandee.setCellValueFactory(new PropertyValueFactory<>("qteCmdee"));
        colQteLivree.setCellValueFactory(new PropertyValueFactory<>("qteLivree"));
        colPuAchat.setCellValueFactory(new PropertyValueFactory<>("prixUnitAchat"));
        colLigneTotal.setCellValueFactory(new PropertyValueFactory<>("ligneTotal"));
        colStockApres.setCellValueFactory(new PropertyValueFactory<>("stockApres"));

        ObservableList<ReceptionCmd> data = FXCollections.observableArrayList(
                new ReceptionCmd("Doliprane 1000mg - Comprimé", 40, 260, 260, 100, 26000, 300),
                new ReceptionCmd("Efferalgan 500mg - Comprimé", 20, 180, 160, 50, 9000, 180),
                new ReceptionCmd("Smecta 50mg - Sachet", 35, 65, 65, 60.50, 4255.5, 100),
                new ReceptionCmd("Nurofen 400mg - Comprimé", 20, 100, 100, 58.12, 5812, 120)
        );

        tableView.setItems(data);
    }

    Utils sceneLoader = new Utils();
    @FXML
    private Button DashboardButton; // button to switch to dashboard
    // Switch to the dashboard scene
    public void DashboardButtonOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        sceneLoader.loadScene("Dashboard.fxml", "Dashboard", DashboardButton);
    }
}
