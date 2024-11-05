
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class UserControl extends Pane {

    private final UsersPane pane;
    private final Button b1;

    public UserControl(double width, double height) {

        pane = new UsersPane(width, height);

        b1 = pane.getButton1();
        b1.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent a) {

            if(a.getSource() == b1) {

                // clear data in case refresh is hit 1+ times
                pane.clearUsers();

                String query = "SELECT username FROM Users";  // quaery to retrieve data from tabl

                // JDBC connection
                try (Connection connection = DriverManager.getConnection(DataBase.URL, DataBase.USER, DataBase.PASWWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query)) {

                    // Iterate through the ResultSet and process the data
                    while (resultSet.next()) {
                        // Get the data from the result set
                        String username = resultSet.getString("username");

                        // use the data
                        pane.addUser(username, "0", "admin");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                
            } 
        }
    }


}
