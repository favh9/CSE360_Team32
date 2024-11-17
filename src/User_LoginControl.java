import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class User_LoginControl extends Pane {

    public double width;
    public double height;
    private User user;
    private final TextField usernameTextField;
    private final PasswordField passwordField;
    private final Button signinButton;
    private final Button createAcctButton;
    private final Hyperlink forgotpasswordHyperlink;

    public User_LoginControl(double width, double height) {

        this.width = width;
        this.height = height;

        user = new User();

        User_LoginPane pane = new User_LoginPane(width,height);

        usernameTextField = pane.getUsernameTextField();
        passwordField = pane.getPasswordField();

        signinButton = pane.getSigninButton();
        signinButton.setOnAction(new ButtonHandler());
        // using set styles removes the default effects buttons have
        // so this is what will provide us with those missing effects
        signinButton.setOnMouseEntered(e -> {
            signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffb600;");
        });

        signinButton.setOnMouseExited(e -> {
            signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffc627;");
        });

        signinButton.setOnMouseReleased(e -> {
            signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffc627;");
        });

        createAcctButton = pane.getCreateAcctButton();
        createAcctButton.setOnAction(new ButtonHandler());

        forgotpasswordHyperlink = pane.getForgotpasswordHyperlink();
        forgotpasswordHyperlink.setOnAction(new LinkHandler());

        this.getChildren().addAll(pane);
    }

    // handles action events for the buttons in the login page
    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {

            if (a.getSource() == createAcctButton) {

                User_CreateAccountControl createAccount = new User_CreateAccountControl(width,height);
                Main.mainWindow.setScene(new Scene(createAccount));

            } else if(a.getSource() == signinButton) {
                signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffa500;");

                // find out if the user exists
                if(!(DataBase.userExists(usernameTextField.getText(),passwordField.getText()))) {

                    displayIncorrectPasswordOrUsername();

                } else if(Objects.equals(DataBase.getUserType(usernameTextField.getText()), "super_admin")) {

                    user = DataBase.getUserByUsername(usernameTextField.getText());
                    Admin_UsersControl admin = new Admin_UsersControl(user,width,height);
                    Main.mainWindow.setScene(new Scene(admin));

                } else if(Objects.equals(DataBase.getUserType(usernameTextField.getText()), "new_user")) { // you are a new user
                    user = DataBase.getUserByUsername(usernameTextField.getText());
                    NewUser_WelcomeControl new_user = new NewUser_WelcomeControl(user,width,height);
                    Main.mainWindow.setScene(new Scene(new_user));

                } else { // you are a returning user
                    user = DataBase.getUserByUsername(usernameTextField.getText());
                    User_SelectAccountControl returning_user = new User_SelectAccountControl(user,width,height);
                    Main.mainWindow.setScene(new Scene(returning_user));
                }

            }

        }

        public void displayIncorrectPasswordOrUsername() {

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please verify your username or re-enter your password\n");
            alert.show();

        }

    }

    private class LinkHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {
            if(a.getSource() == forgotpasswordHyperlink) {
                User_ForgotPasswordControl forgotPassword = new User_ForgotPasswordControl(width,height);
                Main.mainWindow.setScene(new Scene(forgotPassword));
            }
        }
    }

}
