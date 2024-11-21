import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Buyer_ChangePasswordControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button backButton, confirmButton;
    private PasswordField currentPasswordField, newPasswordField, confirmNewPasswordField;
    private Buyer_ChangePasswordPane pane;

    public Buyer_ChangePasswordControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_ChangePasswordPane(user, width, height);

        backButton = pane.getBackButton();
        backButton.setOnAction(new ButtonHandler());

        confirmButton = pane.getConfirmButton();
        confirmButton.setOnAction(new ButtonHandler());

        newPasswordField = pane.getPasswordField();
        newPasswordField.setOnKeyReleased(new PasswordFieldHandler());

        confirmNewPasswordField = pane.getConfirmPasswordField();
        confirmNewPasswordField.setOnKeyReleased(new PasswordFieldHandler());

        this.getChildren().addAll(pane);

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {

            if(a.getSource().equals(backButton)) {

                Seller_SettingsControl settings = new Seller_SettingsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(settings));

            } else {
                // Verify the current password
                if (!DataBase.verifyPassword(user.getUserID(), pane.getCurrentPasswordField().getText())) {
                    pane.displayPasswordResetFailed(); // Alert: Current password is incorrect
                    return;
                }

                // Ensure the new password is not the same as the current password
                if (pane.getCurrentPasswordField().getText().equals(pane.getPasswordField().getText())) {
                    pane.displayPasswordsNotMatch(); // Alert: New password matches the old one
                    return;
                }

                // Update the password in the database
                boolean success = DataBase.updatePassword(user.getUserID(), pane.getPasswordField().getText());
                if (success) {
                    pane.displayPasswordReset(); // Alert: Password reset success
                    pane.clearFields(); // Clear fields
                } else {
                    pane.displayPasswordResetFailed(); // Alert: Password reset failed
                }
            }


        }
    }

    private class PasswordFieldHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent keyEvent) {

            if(keyEvent.getSource() == newPasswordField) {

                // sleep the thread to find a password mismatch
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                pane.passwordFlag();
                pane.passwordFlag();

            } else if(keyEvent.getSource() == confirmNewPasswordField) {

                pane.passwordFlag();

            }
        }
    }
}
