import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;

// the database is yet to do the following
// hash passwords,
// store transactions,
// store books,
// prevent things like sql injection
public class DataBase {

    // add url, user and password
    protected static String URL = "jdbc:mysql://70.176.17.178:3306/team32";
    protected static String USER = "admin";
    protected static String PASSWORD = "admin";

    public static boolean updateUserType(String username, String usertype) {

        String updateUserSQL = "UPDATE Users SET user_type = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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

    public static boolean updatePassword(int userID, String newPassword) {
        String hashedPassword = hashPassword(newPassword);  // Hash the new password
        String updateQuery = "UPDATE Users SET password = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            // Set the parameters for the update query
            pstmt.setString(1, hashedPassword);
            pstmt.setInt(2, userID);

            // Execute the update and return whether it succeeded
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateUserProfile(int userID, String firstName, String lastName, String email, String username) {
        String query = "UPDATE Users SET firstname = ?, lastname = ?, email = ?, username = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, username);
            stmt.setInt(5, userID);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error updating user profile: " + e.getMessage());
            return false;
        }
    }


    public static boolean verifyPassword(int userID, String currentPassword) {
        String verifySQL = "SELECT password FROM Users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(verifySQL)) {
            pstmt.setInt(1, userID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    return storedPassword.equals(hashPassword(currentPassword)); // Compare hashed password
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void createPaymentInfoTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS PaymentInfo ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "userID INT NOT NULL UNIQUE, "
                + "nameOnCard VARCHAR(100) NOT NULL, "
                + "cardNumber CHAR(16) NOT NULL, "
                + "expirationDate CHAR(7) NOT NULL, "
                + "cvc CHAR(3) NOT NULL, "
                + "FOREIGN KEY (userID) REFERENCES Users(id) ON DELETE CASCADE"
                + ")";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTableSQL);
            System.out.println("PaymentInfo table created successfully...");
        } catch (SQLException e) {
            System.out.println("PaymentInfo table creation failed...");
            e.printStackTrace();
        }
    }

    //make sure to prevent duplicates and connect them to the right users.

    public static boolean savePaymentInfo(int userID, String nameOnCard, String cardNumber, String expirationDate, String cvc) {
        String upsertSQL = "INSERT INTO PaymentInfo (userID, nameOnCard, cardNumber, expirationDate, cvc) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE nameOnCard = VALUES(nameOnCard), " +
                "cardNumber = VALUES(cardNumber), expirationDate = VALUES(expirationDate), cvc = VALUES(cvc)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(upsertSQL)) {

            // Debugging: Print the query
            System.out.println("Executing SQL: " + upsertSQL);

            pstmt.setInt(1, userID);
            pstmt.setString(2, nameOnCard);
            pstmt.setString(3, cardNumber);
            pstmt.setString(4, expirationDate);
            pstmt.setString(5, cvc);

            pstmt.executeUpdate();
            System.out.println("Payment info replaced successfully for user ID: " + userID);
            return true;

        } catch (SQLException e) {
            System.out.println("Error replacing payment info for user ID: " + userID);
            e.printStackTrace(); // Print detailed error
            return false;
        }
    }



    public static boolean insertUser(String firstname, String lastname, String dob, String email, String username, String pwd) {

        // Modify SQL query to include the 'dob' column
        String insertUserSQL = "INSERT INTO Users (firstname, lastname, dob, email, username, password) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertUserSQL)) {

            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            pstmt.setString(3, dob);  // dob is 'YYYY-MM-DD' format
            pstmt.setString(4, email);
            pstmt.setString(5, username);
            pstmt.setString(6, hashPassword(pwd));

            pstmt.executeUpdate();
            System.out.println("User inserted successfully: " + username);
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + username);
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void updateEmail(int userID, String newEmail) {
        String query = "UPDATE Users SET email = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newEmail);
            stmt.setInt(2, userID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Email updated successfully.");
            } else {
                System.out.println("User ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating email: " + e.getMessage());
        }
    }

    public static void updateUsername(int userID, String newUsername) {
        String query = "UPDATE Users SET username = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newUsername);
            stmt.setInt(2, userID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Username updated successfully.");
            } else {
                System.out.println("User ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating username: " + e.getMessage());
        }
    }

    public static void updateFirstName(int userID, String newFirstName) {
        String query = "UPDATE Users SET firstname = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newFirstName);
            stmt.setInt(2, userID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("First name updated successfully.");
            } else {
                System.out.println("User ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating first name: " + e.getMessage());
        }
    }

    public static void updateLastName(int userID, String newLastName) {
        String query = "UPDATE Users SET lastname = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newLastName);
            stmt.setInt(2, userID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Last name updated successfully.");
            } else {
                System.out.println("User ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating last name: " + e.getMessage());
        }
    }

    public static void updateDob(int userID, Date newDob) {
        String query = "UPDATE Users SET dob = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, newDob);
            stmt.setInt(2, userID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Date of birth updated successfully.");
            } else {
                System.out.println("User ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating date of birth: " + e.getMessage());
        }
    }

    public static User getUserFromDB(int userID) {
        String query = "SELECT id, firstname, lastname, dob, email, username, password, userType FROM Users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String dob = rs.getString("dob");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String userType = rs.getString("userType");

                return new User(id, firstName, lastName, dob, email, username, password, userType);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving user from database: " + e.getMessage());
        }

        return null;
    }

    public static List<User> searchUsers(String searchInput) {
        List<User> users = new ArrayList<>();
        String searchQuery = "SELECT id, firstname, lastname, dob, email, username, password, userType FROM Users WHERE " +
                "username LIKE ? OR firstname LIKE ? OR lastname LIKE ? OR id LIKE ? OR email LIKE ?";

        String searchPattern = "%" + searchInput + "%";  // To match part of the string

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(searchQuery)) {

            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            stmt.setString(4, searchPattern);
            stmt.setString(5, searchPattern);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String dob = rs.getString("dob");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String userType = rs.getString("userType");

                users.add(new User(id, firstName, lastName, dob, email, username, password, userType));
            }

        } catch (SQLException e) {
            System.out.println("Error searching users: " + e.getMessage());
        }

        return users;
    }

    public static boolean userExists(String username, String pwd) {

        String selectUserSQL = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(selectUserSQL)) {

            pstmt.setString(1, username);
            System.out.println("password: " + pwd);
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

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(selectUserSQL)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Map the result set to a User object
                    int userID = rs.getInt("id");
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

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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

    public static boolean listBook(int UserID, String bookName, int publishYear, String authorName, String category, String conditionn, double price) {
        String insertQuery = "INSERT INTO Listings (Bookname, UserID, PublishYear, AuthorName, Category, Conditionn, Price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER,PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            // Set parameters for the prepared statement
            stmt.setString(1, bookName);
            stmt.setInt(2, UserID);
            stmt.setInt(3, publishYear);
            stmt.setString(4, authorName);
            stmt.setString(5, category);
            stmt.setString(6, conditionn);
            stmt.setDouble(7, price);
            //stmt.setString(7, "N");

            // Execute the insert operation
            int rowsInserted = stmt.executeUpdate();

            // Return true if the insertion is successful (1 or more rows affected)
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting book listing: " + e.getMessage());
            return false;
        }
    }

    public static void deleteListing(int listingID) {
        String sql = "DELETE FROM Listings WHERE ListingID = ?";  // SQL query to delete the listing

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set the ListingID parameter in the SQL query
            statement.setInt(1, listingID);

            // Execute the delete statement
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Listing deleted successfully.");
            } else {
                System.out.println("No listing found with ListingID: " + listingID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting listing: " + e.getMessage());
        }
    }

    public static String hashPassword(String password) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Hash the password bytes
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Convert hashed bytes to hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Return the resulting hash
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: SHA-256 algorithm not found.", e);
        }
    }

    public static PaymentInfo getPaymentInfo(int userID) {
        String selectPaymentSQL = "SELECT nameOnCard, cardNumber, expirationDate, cvc FROM PaymentInfo WHERE userID = ?";
        PaymentInfo paymentInfo = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(selectPaymentSQL)) {

            pstmt.setInt(1, userID);

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

    public static boolean addToCart(int userID, int bookID) {
        String insertQuery = "INSERT INTO cart (userID, bookID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            // Set parameters for the prepared statement
            stmt.setInt(1, userID);
            stmt.setInt(2, bookID);

            // Execute the insert operation
            int rowsInserted = stmt.executeUpdate();

            // Return true if the insertion is successful (1 or more rows affected)
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error adding book to cart: " + e.getMessage());
            return false;
        }
    }

    public static List<Book> getCart(int userID) {
        List<Book> booksInCart = new ArrayList<>();
        String query = "SELECT bookID FROM cart WHERE userID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the userID parameter for the query
            stmt.setInt(1, userID);

            ResultSet rs = stmt.executeQuery(); // Execute the query

            while (rs.next()) {
                int bookID = rs.getInt("bookID");
                Book book = getBookFromListing(bookID); // Use the helper function to create the Book object
                if (book != null) {
                    booksInCart.add(book); // Add the Book object to the list
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving books in cart: " + e.getMessage());
        }

        return booksInCart; // Return the list of books in the cart
    }

    public static List<Integer> getCartBookIDs(int userID) {
        List<Integer> bookIDsInCart = new ArrayList<>();
        String query = "SELECT bookID FROM cart WHERE userID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the userID parameter for the query
            stmt.setInt(1, userID);

            ResultSet rs = stmt.executeQuery(); // Execute the query

            while (rs.next()) {
                int bookID = rs.getInt("bookID");
                bookIDsInCart.add(bookID); // Add the bookID directly to the list
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving book IDs in cart: " + e.getMessage());
        }

        return bookIDsInCart; // Return the list of book IDs in the cart
    }


    public static boolean removeFromCart(int userID, int bookID) {
        String deleteQuery = "DELETE FROM cart WHERE userID = ? AND bookID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, bookID);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("Error removing book from cart: " + e.getMessage());
            return false;
        }
    }

    public static int getCartCount(int userID) {
        int cartCount = 0;
        String query = "SELECT COUNT(*) AS itemCount FROM cart WHERE UserID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the UserID parameter
            stmt.setInt(1, userID);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Retrieve the count of items in the cart
            if (rs.next()) {
                cartCount = rs.getInt("itemCount");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving cart count: " + e.getMessage());
        }

        return cartCount;
    }

    public static Book getBookFromListing(int listingID) {
        String query = "SELECT Bookname, AuthorName, Category, Conditionn, PublishYear, Price, ListingID FROM Listings WHERE ListingID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, listingID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("Bookname");
                String author = rs.getString("AuthorName");
                String category = rs.getString("Category");
                String condition = rs.getString("Conditionn");
                int year = rs.getInt("PublishYear");
                double price = rs.getDouble("Price");
                int id = rs.getInt("ListingID");

                return new Book(title, author, category, condition, price, year, id);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving book from listing: " + e.getMessage());
        }

        return null;
    }

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT ListingID FROM Listings WHERE Sold = 'N'"; // Query to get all ListingIDs from Listings table

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery(); // Execute query to get all ListingIDs

            while (rs.next()) {
                int listingID = rs.getInt("ListingID");
                Book book = getBookFromListing(listingID); // Convert ListingID to Book object
                if (book != null) {
                    books.add(book); // Add the Book object to the list
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving all books: " + e.getMessage());
        }

        return books; // Return the list of books
    }

    public static List<Book> myListedBooks(int userID) {
        List<Book> books = new ArrayList<>();
        // Query to get all ListingIDs where Sold = 'N' and the userID matches the seller
        String query = "SELECT ListingID FROM Listings WHERE Sold = 'N' AND userID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);  // Set the userID in the query

            ResultSet rs = stmt.executeQuery(); // Execute query to get all ListingIDs for unsold books by the user

            while (rs.next()) {
                int listingID = rs.getInt("ListingID");
                Book book = getBookFromListing(listingID); // Convert ListingID to Book object
                if (book != null) {
                    books.add(book); // Add the Book object to the list
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving books for userID " + userID + ": " + e.getMessage());
        }

        return books; // Return the list of books
    }

    public static List<Book> searchBooksByFilter(String searchInput, String[] conditions, String[] categories, int order) {
        // Initialize a list to hold the resulting Book objects
        List<Book> books = new ArrayList<>();

        // Initialize the query builder with the Sold condition
        StringBuilder query = new StringBuilder("SELECT Bookname, AuthorName, Category, Conditionn, PublishYear, Price, ListingID FROM Listings WHERE Sold = 'N'");

        // Initialize a list to hold the filters
        List<String> filters = new ArrayList<>();

        // Add the search filter if searchInput is provided (search in Bookname or AuthorName)
        if (searchInput != null && !searchInput.trim().isEmpty()) {
            filters.add("(Bookname LIKE ? OR AuthorName LIKE ?)");
        }

        // Add the condition filter if conditions are provided
        if (conditions != null && conditions.length > 0) {
            String conditionQuery = "Conditionn IN (" + String.join(",", Collections.nCopies(conditions.length, "?")) + ")";
            filters.add(conditionQuery);
        }

        // Add any additional categories filter here as needed
        if (categories != null && categories.length > 0) {
            String categoryQuery = "Category IN (" + String.join(",", Collections.nCopies(categories.length, "?")) + ")";
            filters.add(categoryQuery);
        }

        // Combine all filters with AND and add to the query
        if (!filters.isEmpty()) {
            query.append(" AND ").append(String.join(" AND ", filters));
        }

        // Add the ORDER BY clause based on the 'order' parameter
        if (order == 1) {
            query.append(" ORDER BY Price ASC");  // Ascending order
        } else if (order == 2) {
            query.append(" ORDER BY Price DESC"); // Descending order
        }

        // Now execute the query to fetch the listings that match the filters
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            // Set the parameters for the query
            int paramIndex = 1;
            if (searchInput != null && !searchInput.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + searchInput + "%");
                stmt.setString(paramIndex++, "%" + searchInput + "%");
            }
            if (conditions != null) {
                for (String condition : conditions) {
                    stmt.setString(paramIndex++, condition);
                }
            }
            if (categories != null) {
                for (String category : categories) {
                    stmt.setString(paramIndex++, category);
                }
            }

            // Execute the query and process the results
            ResultSet rs = stmt.executeQuery();

            System.out.println("About to create bookList");
            // For each row in the result set, create a Book object
            while (rs.next()) {
                String title = rs.getString("Bookname");
                String author = rs.getString("AuthorName");
                String category = rs.getString("Category");
                String condition = rs.getString("Conditionn");
                int year = rs.getInt("PublishYear");
                double price = rs.getDouble("Price");
                int id = rs.getInt("ListingID");

                // Create a Book object and add it to the list
                Book book = new Book(title, author, category, condition, price, year, id);
                books.add(book);
            }
            System.out.println("Created bookList");

        } catch (SQLException e) {
            System.out.println("Error retrieving books in searchBooksByFilter: " + e.getMessage());
        }

        // Return the list of books that match the filters
        return books;
    }


    public static List<Book> getMyBooksByFilter(int userID, String searchInput, String[] conditions, String[] categories, int order) {
        // Initialize a list to hold the resulting Book objects
        List<Book> books = new ArrayList<>();

        // Initialize the query builder with the Sold condition and the userID condition (to get books from that specific user)
        StringBuilder query = new StringBuilder("SELECT ListingID FROM Listings WHERE Sold = 'N' AND userID = ?");

        // Initialize a list to hold the filters
        List<String> filters = new ArrayList<>();

        // Add the search filter if searchInput is provided (search in Bookname or AuthorName)
        if (searchInput != null && !searchInput.trim().isEmpty()) {
            filters.add("(Bookname LIKE ? OR AuthorName LIKE ?)");
        }

        // Add the condition filter if conditions are provided
        if (conditions != null && conditions.length > 0) {
            String conditionQuery = "Conditionn IN (" + String.join(",", Collections.nCopies(conditions.length, "?")) + ")";
            filters.add(conditionQuery);
        }

        // Add any additional categories filter here as needed
        if (categories != null && categories.length > 0) {
            String categoryQuery = "Category IN (" + String.join(",", Collections.nCopies(categories.length, "?")) + ")";
            filters.add(categoryQuery);
        }

        // Combine all filters with AND and add to the query
        if (!filters.isEmpty()) {
            query.append(" AND ").append(String.join(" AND ", filters));
        }

        // Add the ORDER BY clause based on the 'order' parameter
        if (order == 1) {
            query.append(" ORDER BY Price ASC");  // Ascending order
        } else if (order == 2) {
            query.append(" ORDER BY Price DESC"); // Descending order
        }

        // Now execute the query to fetch the ListingIDs that match the filters
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            // Set the parameters for the query
            int paramIndex = 1;
            stmt.setInt(paramIndex++, userID); // Set the userID to filter by the seller (or owner)

            if (searchInput != null && !searchInput.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + searchInput + "%");
                stmt.setString(paramIndex++, "%" + searchInput + "%");
            }
            if (conditions != null) {
                for (String condition : conditions) {
                    stmt.setString(paramIndex++, condition);
                }
            }
            if (categories != null) {
                for (String category : categories) {
                    stmt.setString(paramIndex++, category);
                }
            }

            // Execute the query and process the results
            ResultSet rs = stmt.executeQuery();

            // For each ListingID in the result set, get the corresponding Book object
            while (rs.next()) {
                int listingID = rs.getInt("ListingID");

                // Get the Book object using the listingID
                Book book = getBookFromListing(listingID);
                //System.out.println(listingID + " " + book.getTitle()); // For debugging

                // If the book is not null, add it to the list
                if (book != null) {
                    books.add(book);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving books in searchMyBooks: " + e.getMessage());
        }

        // Return the list of books that match the filters and belong to the specified user
        return books;
    }

    public static void updateBookPrice(int bookID, int newPrice) {
        String updateQuery = "UPDATE Listings SET Price = ? WHERE ListingID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            // Set parameters for the prepared statement
            stmt.setInt(1, newPrice);
            stmt.setInt(2, bookID);

            // Execute the update operation
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Book price updated successfully.");
            } else {
                System.out.println("No book found with the specified ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating book price: " + e.getMessage());
        }
    }

    public static void updateBookName(int bookID, String newName) {
        String updateQuery = "UPDATE Listings SET Bookname = ? WHERE ListingID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            // Set parameters for the prepared statement
            stmt.setString(1, newName);
            stmt.setInt(2, bookID);

            // Execute the update operation
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Book name updated successfully.");
            } else {
                System.out.println("No book found with the specified ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating book name: " + e.getMessage());
        }
    }

    public static void updateBookAuthor(int bookID, String newAuthor) {
        String updateQuery = "UPDATE Listings SET AuthorName = ? WHERE ListingID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            // Set parameters for the prepared statement
            stmt.setString(1, newAuthor);
            stmt.setInt(2, bookID);

            // Execute the update operation
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Book author updated successfully.");
            } else {
                System.out.println("No book found with the specified ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating book author: " + e.getMessage());
        }
    }

    public static void updateBookCategory(int bookID, String newCategory) {
        String updateQuery = "UPDATE Listings SET Category = ? WHERE ListingID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            // Set parameters for the prepared statement
            stmt.setString(1, newCategory);
            stmt.setInt(2, bookID);

            // Execute the update operation
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Book category updated successfully.");
            } else {
                System.out.println("No book found with the specified ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating book category: " + e.getMessage());
        }
    }

    public static void updateBookCondition(int bookID, String newCondition) {
        String updateQuery = "UPDATE Listings SET Conditionn = ? WHERE ListingID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            // Set parameters for the prepared statement
            stmt.setString(1, newCondition);
            stmt.setInt(2, bookID);

            // Execute the update operation
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Book condition updated successfully.");
            } else {
                System.out.println("No book found with the specified ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating book condition: " + e.getMessage());
        }
    }

    public static Transaction getTransactionFromResultSet(ResultSet rs) throws SQLException {
        // Retrieve the data from the ResultSet
        int transactionID = rs.getInt("transactionID");
        int buyerID = rs.getInt("buyerID");
        int sellerID = rs.getInt("sellerID");
        int bookID = rs.getInt("bookID");
        double amount = rs.getDouble("amount");

        // Get the Timestamp from the ResultSet and convert to java.util.Date
        Timestamp timestamp = rs.getTimestamp("date");
        Date date = timestamp != null ? new Date(timestamp.getTime()) : null;  // Convert Timestamp to Date

        // Create a new Transaction object
        Transaction transaction = new Transaction();
        transaction.transactionID = transactionID;
        transaction.buyerID = buyerID;
        transaction.sellerID = sellerID;
        transaction.book = getBookFromListing(bookID);  // Assume this method retrieves a Book from Listings table based on bookID
        transaction.amount = amount;
        transaction.date = date;

        return transaction;
    }

    public static void insertTransaction(int buyerID, int sellerID, int bookID, double amount) {
        // Insert query for the Transactions table
        String insertQuery = "INSERT INTO Transactions (buyerID, sellerID, bookID, amount) VALUES (?, ?, ?, ?)";

        // Update query to mark the book as sold in the Listings table
        String updateQuery = "UPDATE Listings SET Sold = 'Y' WHERE ListingID = ?";

        // Start a transaction to ensure both queries are executed together
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            // Disable auto-commit for transaction
            conn.setAutoCommit(false);

            // Insert the transaction details
            insertStmt.setInt(1, buyerID);
            insertStmt.setInt(2, sellerID);
            insertStmt.setInt(3, bookID);
            insertStmt.setDouble(4, amount);

            // Execute the insert query
            insertStmt.executeUpdate();

            // Mark the book as sold in the Listings table
            updateStmt.setInt(1, bookID);

            // Execute the update query
            updateStmt.executeUpdate();

            // Commit the transaction
            conn.commit();

        } catch (SQLException e) {

            System.out.println("Error inserting transaction: " + e.getMessage());
            try {Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                // If an error occurs, roll back the transaction
                conn.rollback();
            } catch (SQLException rollbackException) {
                System.out.println("Error during rollback: " + rollbackException.getMessage());
            }
        } finally {
            try {Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                // Reset auto-commit to true for subsequent operations
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    public static List<Transaction> returnTransactions(int userID) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM Transactions";

//        if (userID != null) {
//            query += " WHERE buyerID = ? OR sellerID = ?";
//        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

//            // Set the parameters if userID is provided
            stmt.setInt(1, userID);
            stmt.setInt(2, userID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create a Transaction object from the result set
                Transaction transaction = getTransactionFromResultSet(rs);
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving transactions: " + e.getMessage());
        }

        return transactions;
    }

    public static double getMarkdown(int condition) {
        // Initialize a variable to hold the discount value
        double markdown = 0.0;

        // Query to fetch the markdown based on condition
        String query = "SELECT Like_New, Moderately_Used, Heavily_Used FROM ConditionDiscount"; // Assume the condition_id is used to identify a single row
      
        // Validate condition input
        if (condition < 1 || condition > 3) {
            System.out.println("Invalid condition value. Please provide 1, 2, or 3.");
            return markdown;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery(); // Execute query to fetch the markdown values

            if (rs.next()) {
                // Based on condition, return the corresponding markdown value
                switch (condition) {
                    case 1:
                        markdown = rs.getDouble("Like_New");
                        break;
                    case 2:
                        markdown = rs.getDouble("Moderately_Used");
                        break;
                    case 3:
                        markdown = rs.getDouble("Heavily_Used");
                        break;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving markdown: " + e.getMessage());
        }

        return markdown; // Return the markdown value based on condition
    }

    public static void setMarkdown(int condition, double conditionDiscount) {
        // Query to update the markdown value based on condition
        String query = "UPDATE ConditionDiscount SET ";

        // Determine which column to update based on the condition value
        switch (condition) {
            case 1:
                query += "Like_New = ?";
                break;
            case 2:
                query += "Moderately_Used = ?";
                break;
            case 3:
                query += "Heavily_Used = ?";
                break;
            default:
                System.out.println("Invalid condition value. Please provide 1, 2, or 3.");
                return;
        }

        // Execute the update query to set the new markdown value
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the new discount value
            stmt.setDouble(1, conditionDiscount);

            // Execute the update query
            int rowsAffected = stmt.executeUpdate();

            // If no rows were affected, the condition was invalid or not found
            if (rowsAffected > 0) {
                System.out.println("Successfully updated the markdown value.");
            } else {
                System.out.println("No rows were updated. The condition may not exist.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating markdown: " + e.getMessage());
        }
    }

    public static int getNumSold(String category) {
        String query = "SELECT COUNT(*) AS numSold FROM Listings WHERE Sold = 'Y' AND Category = ?";
        int numSold = 0;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the category parameter
            stmt.setString(1, category);

            // Execute the query and retrieve the result
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                numSold = rs.getInt("numSold"); // Get the count from the result set
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving number of sold items: " + e.getMessage());
        }

        return numSold; // Return the count of sold listings in the specified category
    }

    public static void addReview(int userID, double newRating) {
        String query = "SELECT rating, numReviews FROM Users WHERE id = ?";
        String updateQuery = "UPDATE Users SET rating = ?, numReviews = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement selectStmt = conn.prepareStatement(query);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            // Get the current rating and numReviews for the user
            selectStmt.setInt(1, userID);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                double currentRating = rs.getDouble("rating");
                int currentNumReviews = rs.getInt("numReviews");

                // Calculate the new rating
                double totalRating = (currentRating * currentNumReviews) + newRating;
                int newNumReviews = currentNumReviews + 1;
                double updatedRating = totalRating / newNumReviews;

                // Update the rating and numReviews in the database
                updateStmt.setDouble(1, updatedRating);
                updateStmt.setInt(2, newNumReviews);
                updateStmt.setInt(3, userID);
                updateStmt.executeUpdate();

                System.out.println("Updated user rating to: " + updatedRating + " with total reviews: " + newNumReviews);
            } else {
                System.out.println("User not found with UserID: " + userID);
            }

        } catch (SQLException e) {
            System.out.println("Error updating user review: " + e.getMessage());
        }
    }

    public static double getReview(int userID) {
        double rating = 0.0;

        String query = "SELECT Rating FROM Users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rating = rs.getDouble("Rating");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving rating for UserID " + userID + ": " + e.getMessage());
        }

        // Format the rating to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(rating));
    }

    public static boolean isSeller(int userID) {
        String query = "SELECT is_seller FROM Users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);  // Set the userID parameter

            ResultSet rs = stmt.executeQuery();  // Execute the query

            if (rs.next()) {
                // Check if the value of is_seller is 'Y'
                return rs.getString("is_seller").equals("Y");
            }

        } catch (SQLException e) {
            System.out.println("Error checking seller status: " + e.getMessage());
        }

        return false;  // Return false if no user was found or if is_seller is not 'Y'
    }

    public static boolean isBuyer(int userID) {
        String query = "SELECT is_buyer FROM Users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);  // Set the userID parameter

            ResultSet rs = stmt.executeQuery();  // Execute the query

            if (rs.next()) {
                // Check if the value of is_buyer is 'Y'
                return rs.getString("is_buyer").equals("Y");
            }

        } catch (SQLException e) {
            System.out.println("Error checking buyer status: " + e.getMessage());
        }

        return false;  // Return false if no user was found or if is_buyer is not 'Y'
    }

    public static void makeSeller(int userID) {
        String query = "UPDATE Users SET is_seller = 'Y' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);  // Set the userID parameter

            // Execute the update query
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User with ID " + userID + " is now a seller.");
            } else {
                System.out.println("User with ID " + userID + " not found or already marked as seller.");
            }

        } catch (SQLException e) {
            System.out.println("Error making user a seller: " + e.getMessage());
        }
    }

    public static void makeBuyer(int userID) {
        String query = "UPDATE Users SET is_buyer = 'Y' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);  // Set the userID parameter

            // Execute the update query
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User with ID " + userID + " is now a buyer.");
            } else {
                System.out.println("User with ID " + userID + " not found or already marked as buyer.");
            }

        } catch (SQLException e) {
            System.out.println("Error making user a buyer: " + e.getMessage());
        }
    }

    public static boolean isInCart(int userID, int bookID) {
        String query = "SELECT 1 FROM cart WHERE userID = ? AND bookID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, bookID);

            try (ResultSet rs = stmt.executeQuery()) {
                // If there is a result, the entry exists
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately in production code
        }


        // Return false if there was an error or no match found
        return false;
    }

}

