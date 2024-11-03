import java.sql.*;

public class DataBase {

    private static String URL = "jdbc:mysql://127.0.0.1:3306/demo";
    private static String USER = "root";
    private static String PASWWORD = "November12024!";

    public static void createTable() {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS UsersPane ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(255) NOT NULL UNIQUE, "
                + "password VARCHAR(255) NOT NULL)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTableSQL);
            System.out.println("Table created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDataBase() {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             Statement stmt = conn.createStatement()) {

            // SQL command to create a new database
            String sql = "CREATE DATABASE IF NOT EXISTS demo";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertUser(String username, String pwd) {


        String insertUserSQL = "INSERT INTO UsersPane (username, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertUserSQL)) {

            pstmt.setString(1, username);
            pstmt.setString(2, pwd);
            pstmt.executeUpdate();
            System.out.println("User inserted successfully: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getUser(String username, String pwd) {

        String selectUserSQL = "SELECT * FROM UsersPane WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             PreparedStatement pstmt = conn.prepareStatement(selectUserSQL)) {

            pstmt.setString(1, username);
            pstmt.setString(2, pwd);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // User found with matching credentials
                    int id = rs.getInt("id");
                    String dbUsername = rs.getString("username");

                    System.out.println("User found: ID = " + id + ", Username = " + dbUsername);
                } else {
                    System.out.println("Invalid username or password.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
