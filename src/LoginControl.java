import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class LoginControl extends Pane {

    public double width;
    public double height;
    private User user;
    private final TextField usernameTextField;
    private final PasswordField passwordField;
    private final Button signinButton;
    private final Button createAcctButton;
    private final Hyperlink forgotpasswordHyperlink;

    public LoginControl(double width, double height) {

        this.width = width;
        this.height = height;

        user = new User();

        LoginPane pane = new LoginPane(width,height);

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

                CreateAccountControl createAccount = new CreateAccountControl(width,height);
                Main.mainWindow.setScene(new Scene(createAccount));

            } else if(a.getSource() == signinButton) {
                signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffa500;");

                if(!(DataBase.userExists(usernameTextField.getText(),passwordField.getText()))) {

                    displayIncorrectPasswordOrUsername();

                } else if(DataBase.getUserType(usernameTextField.getText()) == null) {

                    user = DataBase.getUserByUsername(usernameTextField.getText());
                    NewUserControl newUser = new NewUserControl(user,width,height);
                    Main.mainWindow.setScene(new Scene(newUser));

                } else if(Objects.equals(DataBase.getUserType(usernameTextField.getText()), "seller")) {

                    user = DataBase.getUserByUsername(usernameTextField.getText());
                    SellerControl seller = new SellerControl(user,width,height);
                    Main.mainWindow.setScene(new Scene(seller));

                } else if(Objects.equals(DataBase.getUserType(usernameTextField.getText()), "buyer")) {

                    user = DataBase.getUserByUsername(usernameTextField.getText());
                    BuyerControl buyer = new BuyerControl(user,width,height);
                    Main.mainWindow.setScene(new Scene(buyer));

                } else if(Objects.equals(DataBase.getUserType(usernameTextField.getText()), "admin")) {

                    user = DataBase.getUserByUsername(usernameTextField.getText());
                    AdminControl admin = new AdminControl(user,width,height);
                    Main.mainWindow.setScene(new Scene(admin));

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
                ForgotPasswordControl forgotPassword = new ForgotPasswordControl(width,height);
                Main.mainWindow.setScene(new Scene(forgotPassword));
            }
        }
    }

}
