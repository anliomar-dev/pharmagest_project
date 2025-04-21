package controllers;

import DAO.MedicamentDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Medicament;
import models.Utilisateur;
import utils.Utils;
import utils.ValidationUtils;
import java.io.IOException;
import java.sql.SQLException;

public class ListePrixController {
    Utils sceneLoader = new Utils();

    @FXML public Button maintenanceButton;
    public void maintenanceButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Maintenance.fxml", "Maintenance", maintenanceButton);
    }

    @FXML private TableView<Medicament> listPrixTable;
    @FXML private TableColumn<Utilisateur, String> medicamentColumn;
    @FXML private TableColumn<Utilisateur, Double> prixachatColumn;
    @FXML private TableColumn<Utilisateur, Double> prixventeColumn;

    @FXML private TextField medicamentSearchField;
    @FXML private TextField PUachatField;
    @FXML private TextField PUventeField;

    @FXML
    public void initialize() throws SQLException {
        if (listPrixTable != null) {
            medicamentColumn.setCellValueFactory(new PropertyValueFactory<>("DCI"));
            prixachatColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitAchat"));
            prixventeColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitVente"));

            listPrixTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    medicamentSearchField.setText(newSelection.getDCI());
                    PUachatField.setText(String.valueOf(newSelection.getPrixUnitAchat()));
                    PUventeField.setText(String.valueOf(newSelection.getPrixUnitVente()));
                }
            });

            loadMedicamentData();
        }
    }

    private void loadMedicamentData() throws SQLException {
        MedicamentDAO medicamentDAO = new MedicamentDAO();
        ObservableList<Medicament> medicamentList = medicamentDAO.getPrixMedicament();
        listPrixTable.setItems(medicamentList);
    }

    @FXML private Label medicamentError;
    @FXML private Label prixachatError;
    @FXML private Label prixventeError;

    public void MAJprixButtonOnAction(ActionEvent e) throws SQLException {
        MedicamentDAO medicamentDAO = new MedicamentDAO();
        boolean isInvalid = false;

        if(medicamentSearchField.getText().isEmpty()){
            medicamentError.setText("Chosissez un médicament");
            isInvalid = true;
        }else{
            medicamentError.setText("");
        }

        if (ValidationUtils.validatePrice(PUachatField, prixachatError)) isInvalid = true;
        if (ValidationUtils.validatePrice(PUventeField, prixventeError)) isInvalid = true;

        if(!isInvalid){
            String dci = medicamentSearchField.getText().trim();
            double prixachat = Double.parseDouble(PUachatField.getText().trim());
            double prixvente = Double.parseDouble(PUventeField.getText().trim());

            if(medicamentDAO.MAJprix(dci, prixachat, prixvente)){
                clearMAJ();
                loadMedicamentData();
            }
        }
    }

    public void annulerButtonOnAction(ActionEvent actionEvent) throws SQLException {
        loadMedicamentData();
        clearMAJ();
    }

    private void clearMAJ(){
        medicamentSearchField.clear();
        PUachatField.clear();
        PUventeField.clear();
    }


}
