import java.sql.*;
import java.util.Scanner;

public class LoginApp {

    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/login_system";
        String dbUser = "root";
        String dbPass = ""; // your DB password

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection conn = DriverManager.getConnection(url, dbUser, dbPass);

            // Prepare SQL with MD5 password check
            String sql = "SELECT * FROM users WHERE username = ? AND password = MD5(?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful. Welcome, " + username + "!");
            } else {
                System.out.println("Invalid username or password.");
            }

            conn.close();
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
