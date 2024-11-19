import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.Objects;

/**
 * User_LoginControl handles the logic for the login page, including user authentication,
 * switching between different scenes, and handling user actions like signing in or creating an account.
 */
public class User_LoginControl extends Pane {

    private double width;
    private double height;
    private User user;
    private User_LoginPane pane;
    private final TextField usernameTextField;
    private final PasswordField passwordField;
    private final Button signinButton;
    private final Button createAcctButton;
    private final Hyperlink forgotpasswordHyperlink;

    /**
     * Constructor to initialize the User_LoginControl.
     * Sets up the UI components and event handlers for buttons and hyperlinks.
     *
     * @param width  The width of the login pane.
     * @param height The height of the login pane.
     */
    public User_LoginControl(double width, double height) {
        this.width = width;
        this.height = height;

        user = new User(); // Initialize a user object

        pane = new User_LoginPane(width, height); // Create the login pane UI

        // Initialize text fields for username and password
        usernameTextField = pane.getUsernameTextField();
        passwordField = pane.getPasswordField();

        // Initialize and set up the Sign In button
        signinButton = pane.getSigninButton();
        signinButton.setOnAction(new ButtonHandler()); // Attach action handler for Sign In button

        // Apply mouse hover effects on the Sign In button
        signinButton.setOnMouseEntered(e -> {
            signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffb600;");
        });

        signinButton.setOnMouseExited(e -> {
            signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffc627;");
        });

        signinButton.setOnMouseReleased(e -> {
            signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffc627;");
        });

        // Initialize and set up the Create Account button
        createAcctButton = pane.getCreateAcctButton();
        createAcctButton.setOnAction(new ButtonHandler()); // Attach action handler for Create Account button

        // Initialize the Forgot Password hyperlink and attach its handler
        forgotpasswordHyperlink = pane.getForgotpasswordHyperlink();
        forgotpasswordHyperlink.setOnAction(new LinkHandler());

        // Add the pane to this pane (User_LoginControl)
        this.getChildren().addAll(pane);
    }

    /**
     * ButtonHandler handles the action events for the Sign In and Create Account buttons.
     * It validates the login credentials and navigates to the appropriate scene based on the user type.
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {

        /**
         * Handles the action event for the Sign In and Create Account buttons.
         * Depending on the button clicked, it either creates a new account or processes the sign-in.
         *
         * @param a The action event triggered by a button click.
         */
        @Override
        public void handle(ActionEvent a) {
            // Handle the Create Account button click
            if (a.getSource() == createAcctButton) {
                User_CreateAccountControl createAccount = new User_CreateAccountControl(width, height);
                Main.mainWindow.setScene(new Scene(createAccount));

                // Handle the Sign In button click
            } else if (a.getSource() == signinButton) {
                signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffa500;");

                // Check if the user exists with the entered username and password
                if (!(DataBase.userExists(usernameTextField.getText(), passwordField.getText()))) {
                    // If the user does not exist, display an error message
                    pane.displayIncorrectPasswordOrUsername();

                } else if (Objects.equals(DataBase.getUserType(usernameTextField.getText()), "super_admin")) {
                    // If the user is a super admin, load the Admin Users Control scene
                    user = DataBase.getUserByUsername(usernameTextField.getText());
                    Admin_UsersControl admin = new Admin_UsersControl(user, width, height);
                    Main.mainWindow.setScene(new Scene(admin));

                } else if (Objects.equals(DataBase.getUserType(usernameTextField.getText()), "new_user")) {
                    // If the user is a new user, load the New User Welcome scene
                    user = DataBase.getUserByUsername(usernameTextField.getText());
                    NewUser_WelcomeControl newUser = new NewUser_WelcomeControl(user, width, height);
                    Main.mainWindow.setScene(new Scene(newUser));

                } else {
                    // If the user is a returning user, load the User Select Account scene
                    user = DataBase.getUserByUsername(usernameTextField.getText());
                    User_SelectAccountControl returningUser = new User_SelectAccountControl(user, width, height);
                    Main.mainWindow.setScene(new Scene(returningUser));
                }
            }
        }
    }

    /**
     * LinkHandler handles the action event for the "Forgot password?" hyperlink.
     * It navigates the user to the password recovery scene.
     */
    private class LinkHandler implements EventHandler<ActionEvent> {

        /**
         * Handles the action event triggered by the "Forgot password?" hyperlink.
         * It navigates to the password recovery page.
         *
         * @param a The action event triggered by the hyperlink click.
         */
        @Override
        public void handle(ActionEvent a) {
            if (a.getSource() == forgotpasswordHyperlink) {
                User_ForgotPasswordControl forgotPassword = new User_ForgotPasswordControl(width, height);
                Main.mainWindow.setScene(new Scene(forgotPassword));
            }
        }
    }
}
