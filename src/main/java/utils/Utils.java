package utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    /*
    public static void main(String[] args){
        System.out.println(hashWithSHA256("@Anliomar285."));
    }*/


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
}
