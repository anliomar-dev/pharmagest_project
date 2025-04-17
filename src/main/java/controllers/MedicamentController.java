package controllers;

import DAO.FormeDAO;
import DAO.FamilleDAO;
import DAO.MedicamentDAO;
import DAO.UtilisateurDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import models.Famille;
import models.Forme;
import models.Medicament;
import models.Utilisateur;
import utils.Utils;
import utils.ValidationUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class MedicamentController {
    Utils sceneLoader = new Utils();

    @FXML public Button maintenanceButton;
    @FXML public Button addMedicamentPage;
    @FXML public Button medicamentRetourButton;

    public void maintenanceButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Maintenance.fxml", "Maintenance", maintenanceButton);
    }

    public void addMedicamentPageButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("AddMedicament.fxml", "Ajouter un médicament", addMedicamentPage);
    }

    public void medicamentRetourButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Medicament.fxml", "Médicament", medicamentRetourButton);
    }

    public void medicamentAnnulerButtonOnAction(ActionEvent e) throws IOException {
        clearForm();
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
    @FXML private TableColumn<Medicament, Void> actionsColumn;

    //ComboBox
    @FXML private ComboBox<String> searchComboBox;
    @FXML private ComboBox<String> formeComboBox;
    @FXML private ComboBox<String> familleComboBox;

    //ComboBox values
    private final ObservableList<String> columns = FXCollections.observableArrayList("Médicament", "Forme", "Famille", "Qte stocké");

    public void initialize() throws SQLException{
        if (medicamentTable != null && searchComboBox != null) {
            initializeTable();
        }
        if (formeComboBox != null && familleComboBox != null) {
            initializeForm();
        }
    }

    private void initializeTable() throws SQLException{
        //ComboBox
        searchComboBox.setItems(columns);
        //Table columns
        dciColumn.setCellValueFactory(new PropertyValueFactory<>("DCI"));
        formeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getForme().getNomForme()));
        familleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFamille().getNomFamille()));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        puachatColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitAchat"));
        puventeColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitVente"));
        qtestockeColumn.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
        actionsColumn.setCellFactory(column -> new TableCell<>() {
            private final Button modifyBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            private final HBox buttons = new HBox(5, modifyBtn, deleteBtn);{
                buttons.setAlignment(Pos.CENTER);
                modifyBtn.setOnAction(event -> {
                    Medicament medicament = getTableView().getItems().get(getIndex());
                    try {
//                        modifierDisplayUtilisateurForm(utilisateur);
                        sceneLoader.loadScene("FormUtilisateur.fxml", "Modifier utilisateur", modifyBtn);
//                        utilisateurAddButton.setVisible(false);
//                        utilisateurModifierButton.setVisible(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                deleteBtn.setOnAction(event -> {
                    Medicament medicament = getTableView().getItems().get(getIndex());
                    try {
                        deleteMedicament(medicament);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : buttons);
            }
        });
        loadMedicamentData();
    }

    private void initializeForm() throws SQLException {
        FamilleDAO familleDAO = new FamilleDAO();        FormeDAO formeDAO = new FormeDAO();


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
    }

    private ObservableList<Medicament> medicamentList = FXCollections.observableArrayList();

    private void loadMedicamentData() throws SQLException {
        MedicamentDAO medicamentDAO = new MedicamentDAO();
        medicamentList = medicamentDAO.getAllMedicament();
        medicamentTable.setItems(medicamentList);
    }

    @FXML private TextField searchField;

    public void searchButtonOnAction(ActionEvent e) throws SQLException {

        ObservableList<Medicament> filteredList = FXCollections.observableArrayList();
        MedicamentDAO medicamentDAO = new MedicamentDAO();
        medicamentList = medicamentDAO.getAllMedicament();

        String search = searchField.getText().trim();
        String lowerCaseFilter = search.toLowerCase();

        if(search.isEmpty() || searchComboBox.getValue() == null){
            loadMedicamentData();
        } else {
            String selectedColumn = searchComboBox.getValue().trim();
            for (Medicament medicament : medicamentList) {
                boolean matches = false;

                switch (selectedColumn) {
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

            Forme forme = formeDAO.getFormeByName(nomforme); // You'll need to implement this
            int idfamille = familleDAO.getNumFamille(nomFamille);

            Famille numFamille = new Famille(idfamille);

            Medicament newMedicament = new Medicament(dci, dosage, puVente, puAchat, qteStcoke, forme, numFamille);
            if (medicamentDAO.addMedicament(newMedicament)) {
                clearForm();
                try {
                    sceneLoader.loadScene("Medicament.fxml", "Médicament", medicamentRetourButton);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private void deleteMedicament(Medicament medicament) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer médicament");
        alert.setHeaderText("Supprimer le médicament : " + medicament.getDCI());
        alert.setContentText("Vous vouliez supprimer le médicament " + medicament.getDCI());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            MedicamentDAO medicamentDAO = new MedicamentDAO();

            medicamentDAO.deleteMedicament(medicament.getDCI());
            loadMedicamentData();
        }
    }

    private void clearForm(){
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
    }
}

