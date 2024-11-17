
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Admin_UsersControl extends Pane {

    private final Admin_UsersPane pane;
    private final Button refreshButton;

    public Admin_UsersControl(User user, double width, double height) {

        pane = new Admin_UsersPane(user, width, height);
        displayUsers();

        refreshButton = pane.getRefreshButton();
        refreshButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent a) {

            if(a.getSource() == refreshButton) {

                pane.clearUsers();
                displayUsers();
            } 
        }
    }

    // displays the users in table
    public void displayUsers() {

        // Corrected query to retrieve both 'username' and 'user_type'
        String query = "SELECT username, user_type FROM Users";  // Select both username and user_type columns

        // JDBC connection
        try (Connection connection = DriverManager.getConnection(DataBase.URL, DataBase.USER, DataBase.PASWWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Iterate through the ResultSet and process the data
            while (resultSet.next()) {
                // Get the data from the result set
                String username = resultSet.getString("username");
                String usertype = resultSet.getString("user_type");

                // Use the data (assuming you have a method addUser to handle this)
                pane.addUser(username, usertype);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
