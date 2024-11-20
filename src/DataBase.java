import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTableSQL);
            System.out.println("PaymentInfo table created successfully...");
        } catch (SQLException e) {
            System.out.println("PaymentInfo table creation failed...");
            e.printStackTrace();
        }
    }

    public static boolean savePaymentInfo(int userID, String nameOnCard, String cardNumber, String expirationDate, String cvc) {
        // SQL query to insert or update payment information
        String insertPaymentSQL = "INSERT INTO PaymentInfo (userID, nameOnCard, cardNumber, expirationDate, cvc) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE nameOnCard = ?, cardNumber = ?, expirationDate = ?, cvc = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertPaymentSQL)) {

            // Set parameters for the insert statement
            pstmt.setInt(1, userID);
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


    public static boolean listBook(String bookName, Date publishDate, String authorName, String category, String conditionn,
                                   double price) {
        String insertQuery = "INSERT INTO Listings (Bookname, UserID, PublishDate, AuthorName, Category, Conditionn, Price) " +
                "VALUES (?, 1, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER,PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            // Set parameters for the prepared statement
            stmt.setString(1, bookName);
            stmt.setDate(2, publishDate);
            stmt.setString(3, authorName);
            stmt.setString(4, category);
            stmt.setString(5, conditionn);
            stmt.setDouble(6, price);
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
        String query = "SELECT bookID FROM Cart WHERE userID = ?";

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

    public static Book getBookFromListing(int listingID) {
        String query = "SELECT Bookname, AuthorName, Category, Conditionn, PublishedDate, Price, ListingID FROM Listings WHERE ListingID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, listingID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("Bookname");
                String author = rs.getString("AuthorName");
                String category = rs.getString("Category");
                String condition = rs.getString("Conditionn");
                Date year = rs.getDate("PublishedDate");
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
        String query = "SELECT ListingID FROM Listings"; // Query to get all ListingIDs from Listings table

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

    public static List<Book> searchBooksByFilter(String searchInput, String[] conditions, String[] categories) {
        // Initialize a list to hold the resulting Book objects
        List<Book> books = new ArrayList<>();

        // Initialize the query builder
        StringBuilder query = new StringBuilder("SELECT ListingID FROM Listings WHERE ");

        // Build the filter conditions based on the searchInput
        String[] filters = new String[3]; // We will have three conditions: searchInput, conditions, and categories
        int filterIndex = 0;

        // Check if searchInput is provided (search in Bookname or Authorname)
        if (searchInput != null && !searchInput.trim().isEmpty()) {
            filters[filterIndex++] = "(Bookname LIKE '%" + searchInput + "%' OR AuthorName LIKE '%" + searchInput + "%')";
        }

        // Add the category filter if categories are provided
        if (conditions != null && conditions.length > 0) {
            String conditionQuery = "Conditionn IN ('" + String.join("','", conditions) + "')";
            filters[filterIndex++] = conditionQuery;
        }


        // Combine all filters together using AND
        String finalQuery = String.join(" AND ", Arrays.copyOfRange(filters, 0, filterIndex));

        // Add the constructed conditions to the query string
        query.append(finalQuery);

        // Now execute the query to fetch the ListingIDs that match the filters
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            // Execute the query and process the results
            ResultSet rs = stmt.executeQuery();

            // For each ListingID in the result set, get the corresponding Book object
            while (rs.next()) {
                int listingID = rs.getInt("ListingID");

                // Get the Book object using the listingID
                Book book = getBookFromListing(listingID);

                // If the book is not null, add it to the list
                if (book != null) {
                    books.add(book);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }

        // Return the list of books that match the filters
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


    public static void updateTransaction(int buyerID, int sellerID, int bookID, double amount) {
        String query = "UPDATE Transactions SET buyerID = ?, sellerID = ?, bookID = ?, amount = ? WHERE transactionID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Since transactionID is auto-generated, we don't need to pass it as an argument
            stmt.setInt(1, buyerID);
            stmt.setInt(2, sellerID);
            stmt.setInt(3, bookID);
            stmt.setDouble(4, amount);

            // You'll need to fetch the transactionID from somewhere to update it, e.g., passing it as a parameter or querying it.
            // Let's assume we want to update the transaction with the provided buyerID and sellerID as primary identifiers
            stmt.setInt(5, buyerID);  // Here, you may adjust logic based on how transactionID is to be identified.

            // Execute the update
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating transaction: " + e.getMessage());
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

}
