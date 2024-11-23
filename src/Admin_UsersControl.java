
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javax.xml.crypto.Data;

public class Admin_UsersControl extends Pane {

    private final Admin_UsersPane pane;
    private final Button refreshButton;
    private List<User> users;

    public Admin_UsersControl(User user, double width, double height) {

        pane = new Admin_UsersPane(user, width, height);

        users = DataBase.getUsers();
        displayUsers();
        refreshButton = pane.getRefreshButton();
        refreshButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    public void displayUsers() {
        for(User user : users) {
            String username = user.getUsername();
            if(DataBase.isBuyer(user.getUserID())) {
                pane.addUserToPane(username,"buyer");
            } else if(DataBase.isSeller(user.getUserID())) {
                pane.addUserToPane(username,"seller");
            } else if(user.getUserType().equals("new_user")) {
                pane.addUserToPane(username, "new user");
            } else if(user.getUserType().equals("returning_user")){
                pane.addUserToPane(username,"user");
            } else if(user.getUserType().equals("super_admin")){
                pane.addUserToPane(username, "boss");
            } else if(user.getUserType().equals("admin")) {
                pane.addUserToPane(username, "slave");
            } else {
                // what other user could there be
                pane.addUserToPane(username, user.getUserType());
            }
        }
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent a) {

            if(a.getSource() == refreshButton) {

                pane.clearUsers();

            } 
        }
    }



}
