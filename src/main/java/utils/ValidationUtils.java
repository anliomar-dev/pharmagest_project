package utils;

import javafx.scene.control.TextField;
import javafx.scene.control.*;

import java.sql.SQLException;

public class ValidationUtils {
    //Filter regex errors
    public static boolean containsNumbers(String input) {
        return input.matches(".*\\d.*");
    }
    public static boolean containsOnlyNumbers(String input) {
        return !input.matches("^[0-9]+$");
    }
    public static boolean only8Digits(String input) {
        return !input.matches("^\\d{8}$");
    }
    public static boolean isValidField(String input) {
        return !input.matches("^[\\p{L} .'-]+$");
    }
    public static boolean isValidEmail(String input) {
        return !input.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }
    public static boolean isValidPassword(String input) {
        return !input.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,}$");
    }

    public static boolean isValidPrice(String input) {
        return !input.matches("^\\d+([.,]\\d{1,2})?$");
    }

    //Handling errors
    public static boolean validateName(TextField field, Label errorLabel) throws SQLException {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            errorLabel.setText("Entrez un " + field.getId().replace("Add", "").toLowerCase());
            return true;
        } else if (containsNumbers(text)) {
            errorLabel.setText(field.getId().replace("Add", "") + " invalide !");
            return true;
        }
        errorLabel.setText("");
        return false;
    }

    public static boolean validateEmail(TextField field, Label errorLabel) {
        String email = field.getText().trim();
        if (email.isEmpty()) {
            errorLabel.setText("Entrez un email");
            return true;
        } else if (isValidEmail(email)) {
            errorLabel.setText("Email invalide !");
            return true;
        }
        errorLabel.setText("");
        return false;
    }

    public static boolean validatePhone(TextField field, Label errorLabel){
        String numTel = field.getText().trim();
        if(numTel.isEmpty()){
            errorLabel.setText("Entrez un numéro téléphone");
            return true;
        }else if(containsOnlyNumbers(numTel) && only8Digits(numTel)){
            errorLabel.setText("Numéro téléphone invalide !");
            return true;
        }
        errorLabel.setText("");
        return false;
    }

    public static boolean validatePassword(PasswordField passwordField, PasswordField confirmeField, Label passwordError, Label confirmError){
        String password = passwordField.getText().trim();
        String passwordIdentique = confirmeField.getText().trim();
        if(password.isEmpty()){
            passwordError.setText("Entrez un mot de passe");
            confirmError.setText("");
            return true;
        }else if(isValidPassword(password)){
            passwordError.setText("Mot de passe invalide !");
            confirmError.setText("");
            return true;
        }else{
            passwordError.setText("");
            if(passwordIdentique.isEmpty()){
                confirmError.setText("Réntrer le mot de passe");
                return true;
            }else if(!passwordIdentique.matches(password)){
                confirmError.setText("Mot de passe non identique");
                return true;
            }
            confirmError.setText("");
            return false;
        }
    }

    public static boolean validatePrice(TextField field, Label errorLabel){
        String prix = field.getText().trim();
        if(prix.isEmpty()){
            errorLabel.setText("Entrez un prix");
            return true;
        }else if(isValidPrice(prix)){
            errorLabel.setText("Prix invalide !");
            return true;
        }
        errorLabel.setText("");
        return false;
    }

    public static boolean validateDCI(TextField field, Label errorLabel){
        String dci = field.getText().trim();
        if (dci.isEmpty()) {
            errorLabel.setText("Entrez un médicament");
            return true;
        } else if (containsNumbers(dci)) {
            errorLabel.setText("Médicament invalide !");
            return true;
        }
        errorLabel.setText("");
        return false;
    }

    public static boolean validateQteStock(TextField field, Label errorLabel){
        String qte = field.getText().trim();
        if (qte.isEmpty()) {
            errorLabel.setText("Entrez la quantité en stock");
            return true;
        } else if (containsOnlyNumbers(qte)) {
            errorLabel.setText("Quantité invalide !");
            return true;
        }
        errorLabel.setText("");
        return false;
    }

    public static boolean validateForme(TextField field, Label errorLabel){
        String forme = field.getText().trim();
        if (forme.isEmpty()) {
            errorLabel.setText("Entrez une forme");
            return true;
        } else if (containsNumbers(forme)) {
            errorLabel.setText("Forme invalide !");
            return true;
        }
        errorLabel.setText("");
        return false;
    }

    public static boolean validateFamille(TextField field, Label errorLabel){
        String famille = field.getText().trim();
        if (famille.isEmpty()) {
            errorLabel.setText("Entrez une famille");
            return true;
        } else if (containsNumbers(famille)) {
            errorLabel.setText("Famille invalide !");
            return true;
        }
        errorLabel.setText("");
        return false;
    }
}
