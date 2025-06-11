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
}
