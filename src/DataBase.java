import java.sql.*;

public class DataBase {

    private String url;
    private String user;
    private String password;

    public DataBase(){
       createDataBase();
       createTable();
    }

    private void createTable() {

        url = "jdbc:mysql://localhost:3306/demo_db";
        user = "root";
        password = "November12024!";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(255) NOT NULL UNIQUE, "
                + "password VARCHAR(255) NOT NULL)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTableSQL);
            System.out.println("Table created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDataBase() {

        url = "jdbc:mysql://localhost:3306/demo_db";
        user = "root";
        password = "November12024!";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // SQL command to create a new database
            String sql = "CREATE DATABASE IF NOT EXISTS demo_db";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertUser(String username, String pwd) {

        url = "jdbc:mysql://localhost:3306/demo_db";
        user = "root";
        password = "November12024!";

        String insertUserSQL = "INSERT INTO Users (username, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(insertUserSQL)) {

            pstmt.setString(1, username);
            pstmt.setString(2, pwd);
            pstmt.executeUpdate();
            System.out.println("User inserted successfully: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
