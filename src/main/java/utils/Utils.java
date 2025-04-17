package utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    /*
    public static void main(String[] args){
        System.out.println(hashWithSHA256("@Haidar1!"));
    }
    */

    /**
     * Hashes a given text using the SHA-256 algorithm.
     *
     * @param text the text to be hashed
     * @return the hashed text in hexadecimal format
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    public static String hashWithSHA256(String text) {
        try {
            MessageDigest digest  = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(text.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // Ajouter un zéro devant si nécessaire
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads a new scene from the specified FXML file.
     *
     * @param fxmlFile the path to the FXML file to load
     * @param title the title to display in the window's title bar
     * @param button the button used to obtain the current scene
     * @throws IOException if the FXML file cannot be loaded
     */

    public void loadScene(String fxmlFile, String title, Button button) throws IOException {
        // path where fxml file is located
        String pathToFxmlFile = "/com/phamagest/pharmagest/";

        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathToFxmlFile + fxmlFile));

            // Créer une nouvelle scène avec le fichier FXML chargé
            Scene newScene = new Scene(loader.load());

            // Récupérer la fenêtre actuelle (Stage)
            Stage stage = (Stage) button.getScene().getWindow();

            // Définir la nouvelle scène et le titre
            stage.setScene(newScene);
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param str the string we want to verify
     * @param regEx the regex we want to verify
     * @return true if string  match regex else false
     */
    public static boolean validateField(String str, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
