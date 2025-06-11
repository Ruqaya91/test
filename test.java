import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

 class PasswordUtils {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static String hashPassword(char[] password, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    public static boolean validatePassword(char[] password, String storedHash) throws Exception {
        String[] parts = storedHash.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = Base64.getDecoder().decode(parts[1]);

        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        return java.security.MessageDigest.isEqual(hash, testHash);
    }

    public static byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
public class LoginExample {
    public static void main(String[] args) throws Exception {
        String username = "user1";
        char[] password = "securePass123".toCharArray();

        // Register user
        byte[] salt = PasswordUtils.getSalt();
        String hashedPassword = PasswordUtils.hashPassword(password, salt);
        // Save `username` and `hashedPassword` to DB

        // Login
        char[] loginPassword = "securePass123".toCharArray();
        boolean isValid = PasswordUtils.validatePassword(loginPassword, hashedPassword);

        if (isValid) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}public class test {
    public static void main(String[] args) throws Exception {
        String username = "user1";
        char[] password = "securePass123".toCharArray();

        // Register user
        byte[] salt = PasswordUtils.getSalt();
        String hashedPassword = PasswordUtils.hashPassword(password, salt);
        // Save `username` and `hashedPassword` to DB

        // Login
        char[] loginPassword = "securePass123".toCharArray();
        boolean isValid = PasswordUtils.validatePassword(loginPassword, hashedPassword);

        if (isValid) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}public class LoginExample {
    public static void main(String[] args) throws Exception {
        String username = "user1";
        char[] password = "securePass123".toCharArray();

        // Register user
        byte[] salt = PasswordUtils.getSalt();
        String hashedPassword = PasswordUtils.hashPassword(password, salt);
        // Save `username` and `hashedPassword` to DB

        // Login
        char[] loginPassword = "securePass123".toCharArray();
        boolean isValid = PasswordUtils.validatePassword(loginPassword, hashedPassword);

        if (isValid) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}
