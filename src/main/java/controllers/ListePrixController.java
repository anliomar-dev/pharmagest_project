package controllers;

import DAO.MedicamentDAO;
import DAO.UtilisateurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
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
import javafx.collections.transformation.FilteredList;
import utils.ValidationUtils;

import java.awt.*;
import java.io.File;
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

    @FXML
    public void initialize() throws SQLException {
        if (listPrixTable != null) {
            medicamentColumn.setCellValueFactory(new PropertyValueFactory<>("DCI"));
            prixachatColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitAchat"));
            prixventeColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitVente"));
            loadMedicamentData();

            listPrixTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    medicamentSearchField.setText(newSelection.getDCI());
                }
            });
        }
    }

    private void loadMedicamentData() throws SQLException {
        MedicamentDAO medicamentDAO = new MedicamentDAO();
        ObservableList<Medicament> medicamentList = medicamentDAO.getPrixMedicament();
        listPrixTable.setItems(medicamentList);
    }

    @FXML
    private void refreshTable() throws SQLException {
        loadMedicamentData();
    }

    public void listPrixExcelButtonOnAction(ActionEvent e){
        try {
            File excelFile = new File("C:/IdeaProjects/Groupe_PharmaGest/Liste-de-prix.xlsx");

            if (!excelFile.exists()) {
                System.err.println("File not found!");
                return;
            }

            // Open with default application
            Desktop.getDesktop().open(excelFile);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML private TextField newPUachatField;
    @FXML private TextField newPUventeField;
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
        if (ValidationUtils.validatePrice(newPUachatField, prixachatError)) isInvalid = true;
        if (ValidationUtils.validatePrice(newPUventeField, prixventeError)) isInvalid = true;

        if(!isInvalid){
            String dci = medicamentSearchField.getText().trim();
            double prixachat = Double.parseDouble(newPUachatField.getText().trim());
            double prixvente = Double.parseDouble(newPUventeField.getText().trim());

            if(medicamentDAO.MAJprix(dci, prixachat, prixvente)){
                clearMAJ();
                loadMedicamentData();
            }
        }
    }

    private void clearMAJ(){
        medicamentSearchField.clear();
        newPUachatField.clear();
        newPUventeField.clear();
    }
}
