import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Admin_ChangePasswordControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button backButton, confirmButton;
    private PasswordField newPasswordField, confirmNewPasswordField;
    private Admin_ChangePasswordPane pane;

    public Admin_ChangePasswordControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Admin_ChangePasswordPane(user, width, height);

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

                Admin_SettingsControl settings = new Admin_SettingsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(settings));

            } else if(a.getSource().equals(confirmButton)) {

                if(pane.emptyFields()) {
                    pane.displayEmptyFields();
                } else if(!pane.passwordsMatch()) {
                    pane.displayPasswordsNotMatch();
                } else {
                    DataBase.updatePassword(user.getUserID(), pane.getPasswordField().getText());
                    pane.displayPasswordReset();
                    pane.clearFields();
                    // pane.displayPasswordResetFailed();
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

            } else if(keyEvent.getSource() == confirmNewPasswordField) {

                pane.passwordFlag();

            }
        }
    }
}
