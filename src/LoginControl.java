import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;

public class LoginControl extends Pane {

    public double width;
    public double height;
    private final Button signinButton;
    private final Button createAcctButton;
    private final Hyperlink forgotpasswordHyperlink;

    public LoginControl(double width,double height) {

        this.width = width;
        this.height = height;

        LoginPane pane = new LoginPane(width,height);

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

                // set the scene of the main window to Create Account
                CreateAccountControl createAccount = new CreateAccountControl(width,height);
                Main.mainWindow.setScene(new Scene(createAccount));

            } else if(a.getSource() == signinButton) {

//              // TESTING DATABASE
//              DataBase.getUser(usernameTextField.getText(),usernameTextField.getText());
                signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffa500;");
                UsersPane users = new UsersPane(width, height);
                Main.mainWindow.setScene(new Scene(users));

            }

        }

    }

    private class LinkHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {
            if(a.getSource() == forgotpasswordHyperlink) {
                ForgotPasswordPane forgotPasswordDisplayPane = new ForgotPasswordPane(width,height);
                Main.mainWindow.setScene(new Scene(forgotPasswordDisplayPane));
            }
        }
    }

}
