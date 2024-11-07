import java.sql.*;

// the database is yet to do the following
// hash passwords,
// store transactions,
// store books,
// prevent things like sql injection
public class DataBase {

    // add url, user and password
    protected static String URL = "jdbc:mysql://70.176.17.178:3306/team32";
    protected static String USER = "admin";
    protected static String PASWWORD = "admin";

    public static void createDataBase() {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             Statement stmt = conn.createStatement()) {

            // SQL command to create a new database
            String sql = "CREATE DATABASE IF NOT EXISTS team32";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            System.out.println("Database boom!.. failure!...");
        }

    }

    public static void createUsersTable() {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "firstname VARCHAR(255) NOT NULL, "
                + "lastname VARCHAR(255) NOT NULL, "
                + "email VARCHAR(255) NOT NULL UNIQUE, "
                + "username VARCHAR(255) NOT NULL UNIQUE, "
                + "password VARCHAR(255) NOT NULL, "
                + "usertype ENUM('buyer', 'seller') DEFAULT NULL)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTableSQL);
            System.out.println("Table created successfully...");
        } catch (SQLException e) {
            System.out.println("Table created.. SIKE!...");
            e.printStackTrace();
        }
    }

    public static void createTransactionsTable() {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS Transactions ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(255) NOT NULL, "
                + "timestmap VARCHAR(255) NOT NULL, "
                + "amount VARCHAR(255) NOT NULL UNIQUE, ";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTableSQL);
            System.out.println("Table created successfully...");
        } catch (SQLException e) {
            System.out.println("Table created.. SIKE!...");
            e.printStackTrace();
        }
    }

    public static boolean insertUser(String firstname, String lastname, String email, String username, String pwd) {

        String insertUserSQL = "INSERT INTO Users (firstname, lastname, email, username, password, usertype) " +
                               "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertUserSQL)) {

            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            pstmt.setString(3, email);
            pstmt.setString(4, username);
            pstmt.setString(5, pwd);
            pstmt.setString(6, "");

            pstmt.executeUpdate();
            System.out.println("User inserted successfully: " + username);
        } catch (SQLException e) {
            System.out.println("Invalid username or password: " + username);
            return false;
        }

        return true;
    }

    public static boolean getUser(String username, String pwd) {

        String selectUserSQL = "SELECT * FROM Users WHERE username = ? AND password = ?";

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
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

}