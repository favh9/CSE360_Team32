import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.text.DecimalFormat;

/**
 * Controller for managing the Forgot Password functionality.
 * This class handles user interactions, including form validation,
 * password reset, and transitioning between scenes.
 */
public class User_ForgotPasswordControl extends Pane {

    private final double width;
    private final double height;
    private User_ForgotPasswordPane pane;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button confirmButton;
    private Button backButton;

    /**
     * Constructs the Forgot Password controller.
     *
     * @param width  The width of the pane.
     * @param height The height of the pane.
     */
    public User_ForgotPasswordControl(double width, double height) {
        this.width = width;
        this.height = height;

        // Initialize the Forgot Password Pane
        pane = new User_ForgotPasswordPane(width, height);

        // Initialize Password Fields and add event handlers
        passwordField = pane.getPasswordPasswordfield();
        passwordField.setOnKeyReleased(new PasswordFieldHandler());

        confirmPasswordField = pane.getConfirmpasswordPasswordfield();
        confirmPasswordField.setOnKeyReleased(new PasswordFieldHandler());

        // Initialize Confirm and Back buttons and add event handlers
        confirmButton = pane.getConfirmButton();
        confirmButton.setOnAction(new ButtonHandler());

        backButton = pane.getBackButton();
        backButton.setOnAction(new ButtonHandler());

        // Add the pane to the current pane (this acts as the container)
        this.getChildren().addAll(pane);
    }

    /**
     * Handles actions for the buttons (Confirm and Back).
     * Validates input and handles navigation or error display.
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {

        private User_LoginControl login;

        /**
         * Initializes the ButtonHandler.
         */
        public ButtonHandler() {
            login = new User_LoginControl(width, height);
        }

        /**
         * Handles the event triggered by a button click.
         * Handles back navigation and password reset confirmation logic.
         *
         * @param a The ActionEvent triggered by the button click.
         */
        @Override
        public void handle(ActionEvent a) {

            // Navigate to the login screen when the back button is pressed
            if (a.getSource() == backButton) {
                Main.mainWindow.setScene(new Scene(login, width, height));

            } else if (a.getSource() == confirmButton) {
                // Handle password reset logic when the confirm button is pressed

                // Format the birthdate using the selected year, month, and day
                DecimalFormat df = new DecimalFormat("00");
                String birthdate = pane.getYear() + "-"
                        + df.format(Integer.parseInt(pane.getMonth()))
                        + "-" + df.format(Integer.parseInt(pane.getDay()));

                // Initialize default values for user details
                User user;
                String email = "1";
                String DOB = "1";
                boolean userExists = true;

                try {
                    // Attempt to retrieve the user details from the database
                    user = DataBase.getUserByUsername(pane.getUsername());
                    email = user.getEmail();
                    DOB = user.getDob();
                } catch (Exception e) {
                    // User was not found in the database
                    userExists = false;
                }

                // Validate user inputs and display appropriate messages
                if (pane.emptyFields()) {

                    pane.displayEmptyFields();

                } else if (!userExists) {

                    pane.displayUserNotFound();

                } else if (!pane.passwordsMatch()) {

                    pane.displayPasswordsNotMatch();

                } else if ((email.compareToIgnoreCase(pane.getEmail()) != 0) || (DOB.compareTo(birthdate) != 0)) {

                    pane.displayPasswordResetFailed();

                } else {
                    // If all validations pass, display success message
                    pane.displayPasswordReset();
                }
            }
        }
    }

    /**
     * Handles key release events for the password fields.
     * Validates the password fields and shows visual feedback for password mismatch.
     */
    private class PasswordFieldHandler implements EventHandler<KeyEvent> {

        /**
         * Handles the key release event for password fields.
         * Checks for password mismatches and updates the visual feedback.
         *
         * @param keyEvent The KeyEvent triggered by a key release.
         */
        @Override
        public void handle(KeyEvent keyEvent) {

            // If the source is the password field, check for mismatches
            if (keyEvent.getSource() == passwordField) {
                try {
                    // Sleep the thread for a short duration to detect password mismatch
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Update the visual feedback based on password mismatch
                pane.passwordFlag();

            } else if (keyEvent.getSource() == confirmPasswordField) {
                // Check for password mismatch when the confirm password field is updated
                pane.passwordFlag();
            }
        }
    }
}
