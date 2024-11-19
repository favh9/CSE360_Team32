import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.text.DecimalFormat;

public class User_ForgotPasswordControl extends Pane {

    private final double width;
    private final double height;
    private User_ForgotPasswordPane pane;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button confirmButton;
    private Button backButton;

    public User_ForgotPasswordControl(double width, double height) {

        this.width = width;
        this.height = height;

        pane = new User_ForgotPasswordPane(width,height);

        passwordField = pane.getPasswordPasswordfield();
        passwordField.setOnKeyReleased(new PasswordFieldHandler());

        confirmPasswordField = pane.getConfirmpasswordPasswordfield();
        confirmPasswordField.setOnKeyReleased(new PasswordFieldHandler());

        confirmButton = pane.getConfirmButton();
        confirmButton.setOnAction(new ButtonHandler());

        backButton = pane.getBackButton();
        backButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        User_LoginControl login;

        public ButtonHandler() {
            login = new User_LoginControl(width,height);
        }

        @Override
        public void handle(ActionEvent a) {

            if(a.getSource() == backButton) {

                Main.mainWindow.setScene(new Scene(login,width,height));

            } else if (a.getSource() == confirmButton) {

                // use this for the birthdate
                DecimalFormat df = new DecimalFormat("00");
                String birthdate = pane.getYear() + "-"
                        + df.format(Integer.parseInt(pane.getMonth()))
                        + "-" + df.format(Integer.parseInt(pane.getDay()));
                User user;
                String email ="1";
                String DOB = "1";
                boolean UserExists = true;
                try {
                    user = DataBase.getUserByUsername(pane.getUsername());
                    email = user.getEmail();
                    DOB = user.getDob();

                } catch (Exception e) {

                    UserExists =false;
                }

                if(pane.emptyFields()) {

                    pane.displayEmptyFields();

                } else if(!UserExists) {

                    pane.displayUserNotFound();

                } else if(!pane.passwordsMatch()) {

                    pane.displayPasswordsNotMatch();

                } else if (  (email.compareToIgnoreCase(pane.getEmail()) != 0) || (DOB.compareTo(birthdate) != 0)) {

                    pane.displayPasswordResetFailed();

                } else {

                    pane.displayPasswordReset();

                }
            }
        }

    }

    private class PasswordFieldHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent keyEvent) {

            if(keyEvent.getSource() == passwordField) {

                // sleep the thread to find a password mismatch
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                pane.passwordFlag();

            } else if(keyEvent.getSource() == confirmPasswordField) {

                pane.confirmPasswordFlag();

            }
        }
    }
}
