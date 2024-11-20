import java.sql.*;
import java.util.concurrent.ConcurrentHashMap;

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

    // idea for tracking active users
    protected static ConcurrentHashMap<Long,User> activeUsers = new ConcurrentHashMap<>();

    public static void addUser(User user) {
        activeUsers.put(user.getUserID(), user);
    }

    public static void removeUser(Long userID) {
        activeUsers.remove(userID);
    }

    public boolean isUserActive(Long userID) {
        return activeUsers.containsKey(userID);
    }

    public User getUser(Long userID) {
        return activeUsers.get(userID);
    }

    public static boolean updateUserType(String username, String usertype) {

        String updateUserSQL = "UPDATE Users SET user_type = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             PreparedStatement pstmt = conn.prepareStatement(updateUserSQL)) {

            pstmt.setString(1, usertype);
            pstmt.setString(2, username);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User type updated successfully for username: " + username);
            } else {
                System.out.println("User not found with username: " + username);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error updating user type for username: " + username);
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void createDataBase() {

        System.out.println("freedom");

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
                + "user_type ENUM('super_admin','admin','returning_user','new_user') DEFAULT 'new_user', "
                + "dob DATE)"; // Added date of birth column

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTableSQL);
            System.out.println("Table created successfully...");
        } catch (SQLException e) {
            System.out.println("Table creation failed...");
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

    public static void createPaymentInfoTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS PaymentInfo ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "userID INT NOT NULL, "
                + "nameOnCard VARCHAR(100) NOT NULL, "
                + "cardNumber CHAR(16) NOT NULL, "
                + "expirationDate CHAR(7) NOT NULL, "
                + "cvc CHAR(3) NOT NULL, "
                + "FOREIGN KEY (userID) REFERENCES Users(id) ON DELETE CASCADE"
                + ")";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTableSQL);
            System.out.println("PaymentInfo table created successfully...");
        } catch (SQLException e) {
            System.out.println("PaymentInfo table creation failed...");
            e.printStackTrace();
        }
    }

    public static boolean savePaymentInfo(Long userID, String nameOnCard, String cardNumber, String expirationDate, String cvc) {
        // SQL query to insert or update payment information
        String insertPaymentSQL = "INSERT INTO PaymentInfo (userID, nameOnCard, cardNumber, expirationDate, cvc) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE nameOnCard = ?, cardNumber = ?, expirationDate = ?, cvc = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertPaymentSQL)) {

            // Set parameters for the insert statement
            pstmt.setLong(1, userID);
            pstmt.setString(2, nameOnCard);
            pstmt.setString(3, cardNumber);
            pstmt.setString(4, expirationDate);
            pstmt.setString(5, cvc);

            // Set parameters for the update part of the query
            pstmt.setString(6, nameOnCard);
            pstmt.setString(7, cardNumber);
            pstmt.setString(8, expirationDate);
            pstmt.setString(9, cvc);

            // Execute the query
            pstmt.executeUpdate();

            System.out.println("Payment info saved successfully for user ID: " + userID);
            return true;

        } catch (SQLException e) {
            // Log the error if the query fails
            System.out.println("Error saving payment info for user ID: " + userID);
            e.printStackTrace();
            return false;
        }
    }


    public static boolean insertUser(String firstname, String lastname, String dob, String email, String username, String pwd) {

        // Modify SQL query to include the 'dob' column
        String insertUserSQL = "INSERT INTO Users (firstname, lastname, dob, email, username, password) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertUserSQL)) {

            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            pstmt.setString(3, dob);  // dob is 'YYYY-MM-DD' format
            pstmt.setString(4, email);
            pstmt.setString(5, username);
            pstmt.setString(6, pwd);

            pstmt.executeUpdate();
            System.out.println("User inserted successfully: " + username);
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + username);
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public static boolean userExists(String username, String pwd) {

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

    public static User getUserByUsername(String username) {
        String selectUserSQL = "SELECT id, firstname, lastname, dob, email, username, password, user_type FROM Users WHERE username = ?";
        User user = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             PreparedStatement pstmt = conn.prepareStatement(selectUserSQL)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Map the result set to a User object
                    Long userID = rs.getLong("id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String dob = rs.getString("dob");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String usertype = rs.getString("user_type");

                    // Create a new User object from the database result
                    user = new User(userID, firstname, lastname, dob, email, username, password, usertype);
                }
            }

            if (user != null) {
                System.out.println("User retrieved successfully: " + user.getUsername());
            } else {
                System.out.println("No user found with username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static String getUserType(String username) {
        String selectUserSQL = "SELECT user_type FROM Users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             PreparedStatement pstmt = conn.prepareStatement(selectUserSQL)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // User found with matching credentials
                    String userType = rs.getString("user_type");  // Retrieve the user_type
                    return userType;
                } else {
                    System.out.println("Invalid username.");
                    return null;  // Return null if the user is not found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;  // Return null in case of an error
        }
    }

    public static PaymentInfo getPaymentInfo(Long userID) {
        String selectPaymentSQL = "SELECT nameOnCard, cardNumber, expirationDate, cvc FROM PaymentInfo WHERE userID = ?";
        PaymentInfo paymentInfo = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASWWORD);
             PreparedStatement pstmt = conn.prepareStatement(selectPaymentSQL)) {

            pstmt.setLong(1, userID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nameOnCard = rs.getString("nameOnCard");
                    String cardNumber = rs.getString("cardNumber");
                    String expirationDate = rs.getString("expirationDate");
                    String cvc = rs.getString("cvc");

                    paymentInfo = new PaymentInfo(nameOnCard, cardNumber, expirationDate, cvc);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving payment info for user ID: " + userID);
            e.printStackTrace();
        }

        return paymentInfo;
    }



}
