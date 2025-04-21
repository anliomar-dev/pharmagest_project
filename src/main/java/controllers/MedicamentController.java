package controllers;

import DAO.FormeDAO;
import DAO.FamilleDAO;
import DAO.MedicamentDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Famille;
import models.Forme;
import models.Medicament;
import utils.Utils;
import utils.ValidationUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class MedicamentController {
    Utils sceneLoader = new Utils();

    @FXML public Button maintenanceButton;

    public void maintenanceButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Maintenance.fxml", "Maintenance", maintenanceButton);
    }

    //Table columns
    @FXML private TableView<Medicament> medicamentTable;
    @FXML private TableColumn<Medicament, String> dciColumn;
    @FXML private TableColumn<Medicament, String> formeColumn;
    @FXML private TableColumn<Medicament, String> familleColumn;
    @FXML private TableColumn<Medicament, String> dosageColumn;
    @FXML private TableColumn<Medicament, Double> puachatColumn;
    @FXML private TableColumn<Medicament, Double> puventeColumn;
    @FXML private TableColumn<Medicament, String> qtestockeColumn;

    //ComboBox
    @FXML private ComboBox<String> searchComboBox;
    @FXML private ComboBox<String> formeComboBox;
    @FXML private ComboBox<String> familleComboBox;

    //ComboBox values
    private final ObservableList<String> columns = FXCollections.observableArrayList("Médicament", "Forme", "Famille", "Qte stocké");
    private String originalDCI;

    public void initialize() throws SQLException{
        if (medicamentTable != null && searchComboBox != null && formeComboBox != null && familleComboBox != null) {

            //Call DAO
            FamilleDAO familleDAO = new FamilleDAO();
            FormeDAO formeDAO = new FormeDAO();

            //Set up ComboBox
            searchComboBox.setItems(columns);

            ObservableList<Forme> allFormes = formeDAO.getAllForme();
            ObservableList<String> formeNames = FXCollections.observableArrayList();
            for (Forme forme : allFormes) {
                formeNames.add(forme.getNomForme());
            }

            ObservableList<Famille> familles = familleDAO.getAllFamille();
            ObservableList<String> familleNames = FXCollections.observableArrayList();
            for (Famille f : familles) {
                familleNames.add(f.getNomFamille());
            }

            formeComboBox.setItems(formeNames);
            familleComboBox.setItems(familleNames);

            //Table columns
            dciColumn.setCellValueFactory(new PropertyValueFactory<>("DCI"));
            formeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getForme().getNomForme()));
            familleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFamille().getNomFamille()));
            dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));
            puachatColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitAchat"));
            puventeColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitVente"));
            qtestockeColumn.setCellValueFactory(new PropertyValueFactory<>("qteStock"));

            //Selection row
            medicamentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    originalDCI = newSelection.getDCI();

                    medicamentField.setText(newSelection.getDCI());
                    formeComboBox.setValue(newSelection.getForme().getNomForme());
                    familleComboBox.setValue(newSelection.getFamille().getNomFamille());
                    dosageField.setText(newSelection.getDosage());
                    puachatField.setText(String.valueOf(newSelection.getPrixUnitAchat()));
                    puventeField.setText(String.valueOf(newSelection.getPrixUnitVente()));
                    qtestockeField.setText(String.valueOf(newSelection.getQteStock()));
                }
            });

            //Load data
            loadMedicamentData();
        }
    }

    public boolean isRowSelected() {
        return medicamentTable.getSelectionModel().getSelectedItem() != null;
    }

    private ObservableList<Medicament> medicamentList = FXCollections.observableArrayList();

    //Load medicament data
    private void loadMedicamentData() throws SQLException {
        MedicamentDAO medicamentDAO = new MedicamentDAO();
        medicamentList = medicamentDAO.getAllMedicament();
        medicamentTable.setItems(medicamentList);
    }

    @FXML private TextField searchField;

    public void searchButtonOnAction(ActionEvent e) throws SQLException {

        //Filter list and call DAO
        ObservableList<Medicament> filteredList = FXCollections.observableArrayList();
        MedicamentDAO medicamentDAO = new MedicamentDAO();
        medicamentList = medicamentDAO.getAllMedicament();

        String search = searchField.getText().trim();
        String lowerCaseFilter = search.toLowerCase();
        String selectedComboBox = searchComboBox.getValue().trim();

        if(search.isEmpty() || searchComboBox.getValue() == null){
            loadMedicamentData();
        } else {
            if(selectedComboBox.isEmpty()){
                deleteError.setText("Choisir une colonne");
            }else{
                for (Medicament medicament : medicamentList) {
                    boolean matches = false;

                    switch (selectedComboBox) {
                        case "Médicament":
                            matches = medicament.getDCI().toLowerCase().contains(lowerCaseFilter);
                            break;
                        case "Forme":
                            matches = medicament.getForme().getNomForme().toLowerCase().contains(lowerCaseFilter);
                            break;
                        case "Famille":
                            matches = medicament.getFamille().getNomFamille().toLowerCase().contains(lowerCaseFilter);
                            break;
                        case "Qte stocké":
                            try {
                                int filterQte = Integer.parseInt(lowerCaseFilter.trim());
                                matches = medicament.getQteStock() <= filterQte;
                            } catch (NumberFormatException event) {
                                matches = false;
                            }
                            break;
                    }
                    if (matches) {
                        filteredList.add(medicament);
                    }
                }
            }
            medicamentTable.setItems(filteredList);
        }
    }

    public void searchButtonAnnulerOnAction(ActionEvent e) throws SQLException {
        searchComboBox.getSelectionModel().clearSelection();
        searchField.clear();
        loadMedicamentData();
    }

    @FXML private TextField medicamentField;
    @FXML private TextField puachatField;
    @FXML private TextField puventeField;
    @FXML private TextField qtestockeField;
    @FXML private TextField dosageField;

    @FXML private Label medicamentFieldError;
    @FXML private Label familleFieldError;
    @FXML private Label formeFieldError;
    @FXML private Label dosageFieldError;
    @FXML private Label puachatFieldError;
    @FXML private Label puventeFieldError;
    @FXML private Label qtestockeFieldError;

    public void medicamentAddButtonOnAction(ActionEvent e) throws SQLException{
        MedicamentDAO medicamentDAO = new MedicamentDAO();

        boolean isInvalid = false;

        if (ValidationUtils.validateDCI(medicamentField, medicamentFieldError)) isInvalid = true;
        if (ValidationUtils.validatePrice(puachatField, puachatFieldError)) isInvalid = true;
        if (ValidationUtils.validatePrice(puventeField, puventeFieldError)) isInvalid = true;
        if (ValidationUtils.validateQteStock(qtestockeField, qtestockeFieldError)) isInvalid = true;

        if (dosageField.getText().isEmpty()) {
            dosageFieldError.setText("Entrez le dosage du médicament en mg ou g");
            isInvalid = true;
        } else {
            dosageFieldError.setText("");
        }

        if (formeComboBox.getValue() == null) {
            formeFieldError.setText("Choisir la forme du médicament");
            isInvalid = true;
        } else {
            formeFieldError.setText("");
        }

        if (familleComboBox.getValue() == null) {
            familleFieldError.setText("Choisir la famille du médicament");
            isInvalid = true;
        } else {
            familleFieldError.setText("");
        }

        if (!isInvalid) {
            FormeDAO formeDAO = new FormeDAO();
            FamilleDAO familleDAO = new FamilleDAO();

            String dci = medicamentField.getText().trim();
            String nomforme = formeComboBox.getValue();
            String nomFamille = familleComboBox.getValue();
            double puAchat = Double.parseDouble(puachatField.getText().trim());
            double puVente = Double.parseDouble(puventeField.getText().trim());
            int qteStcoke = Integer.parseInt(qtestockeField.getText().trim());
            String dosage = dosageField.getText();

            Forme forme = formeDAO.getFormeByName(nomforme);
            int idfamille = familleDAO.getNumFamille(nomFamille);

            Famille numFamille = new Famille(idfamille);

            Medicament newMedicament = new Medicament(dci, dosage, puVente, puAchat, qteStcoke, forme, numFamille);
            if (medicamentDAO.addMedicament(newMedicament)) {
                clearForm();
            }
        }
    }

    public void medicamentModifierButtonOnAction(ActionEvent e) throws SQLException {
        if(isRowSelected()) {

            MedicamentDAO medicamentDAO = new MedicamentDAO();

            boolean isInvalid = false;

            if(ValidationUtils.validateDCI(medicamentField, medicamentFieldError)) isInvalid = true;
            if(ValidationUtils.validatePrice(puachatField, puachatFieldError)) isInvalid = true;
            if(ValidationUtils.validatePrice(puventeField, puventeFieldError)) isInvalid = true;
            if(ValidationUtils.validateQteStock(qtestockeField, qtestockeFieldError)) isInvalid = true;

            if(dosageField.getText().isEmpty()){
                dosageFieldError.setText("Entrez le dosage du médicament en mg ou g");
                isInvalid = true;
            }else{
                dosageFieldError.setText("");
            }

            if (formeComboBox.getValue() == null) {
                formeFieldError.setText("Choisir la forme du médicament");
                isInvalid = true;
            } else {
                formeFieldError.setText("");
            }

            if (familleComboBox.getValue() == null) {
                familleFieldError.setText("Choisir la famille du médicament");
                isInvalid = true;
            } else {
                familleFieldError.setText("");
            }

            if(!isInvalid){
                FormeDAO formeDAO = new FormeDAO();
                FamilleDAO familleDAO = new FamilleDAO();

                String dci = medicamentField.getText().trim();
                String nomforme = formeComboBox.getValue();
                String nomFamille = familleComboBox.getValue();
                double puAchat = Double.parseDouble(puachatField.getText().trim());
                double puVente = Double.parseDouble(puventeField.getText().trim());
                int qteStcoke = Integer.parseInt(qtestockeField.getText().trim());
                String dosage = dosageField.getText();

                Forme forme = formeDAO.getFormeByName(nomforme);
                int idfamille = familleDAO.getNumFamille(nomFamille);

                Famille numFamille = new Famille(idfamille);

                Medicament modifierMedicament = new Medicament(dci, dosage, puVente, puAchat, qteStcoke, forme, numFamille);
                if (medicamentDAO.modifierMedicament(originalDCI, modifierMedicament)) {
                    loadMedicamentData();
                    clearForm();
                }
            }
        }else{
            medicamentFieldError.setText("Choisir un médicament");
        }
    }

    @FXML private Label deleteError;

    public void supprimerMedicamentButtonOnAction(ActionEvent e) throws SQLException {
        String dci = medicamentField.getText().trim();

        if(dci.isEmpty()){
            deleteError.setText("Choisir un médicament");
        }else{
            deleteError.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer médicament");
            alert.setHeaderText("Vous vouliez supprimer le médicament : " + dci);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                MedicamentDAO medicamentDAO = new MedicamentDAO();
                medicamentDAO.deleteMedicament(dci);
                clearForm();
                loadMedicamentData();
            }else{
                loadMedicamentData();
                clearForm();
            }
        }
    }

    public void medicamentAnnulerButtonOnAction(ActionEvent e) {
        clearForm();
    }

    private void clearForm(){
        medicamentTable.getSelectionModel().clearSelection();

        medicamentField.clear();
        formeComboBox.getSelectionModel().clearSelection();
        familleComboBox.getSelectionModel().clearSelection();
        puventeField.clear();
        puachatField.clear();
        dosageField.clear();
        qtestockeField.clear();

        medicamentFieldError.setText("");
        formeFieldError.setText("");
        familleFieldError.setText("");
        puventeFieldError.setText("");
        puachatFieldError.setText("");
        dosageFieldError.setText("");
        qtestockeFieldError.setText("");
        deleteError.setText("");

        originalDCI = null;
    }
}