import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class User_CreateAccountControl extends Pane {

    public double width;
    public double height;
    private User_LoginControl login;
    private PasswordField passwordPasswordfield;
    private PasswordField confirmpasswordPasswordfield;
    private Button confirmButton;
    private Button backButton;
    private User_CreateAccountPane pane;

    public User_CreateAccountControl(double width, double height) {

        this.width = width;
        this.height = height;

        pane = new User_CreateAccountPane(width,height);

        login = new User_LoginControl(width,height);

        passwordPasswordfield = pane.getPasswordPasswordfield();
        passwordPasswordfield.setOnKeyReleased(new PasswordFieldHandler());

        confirmpasswordPasswordfield = pane.getConfirmpasswordPasswordfield();
        confirmpasswordPasswordfield.setOnKeyReleased(new PasswordFieldHandler());

        confirmButton = pane.getConfirmButton();
        confirmButton.setOnAction(new ButtonHandler());

        backButton = pane.getBackButton();
        backButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {

            if(a.getSource() == backButton) {

                Main.mainWindow.setScene(new Scene(login));

            }
            else if (a.getSource() == confirmButton) {

                if(pane.emptyFields()) {

                    pane.displayEmptyFields();

                } else if(!(EmailValidator.isValidEmail(pane.getEmailTextField().getText()))) {

                    pane.displayInvalidEmail();

                } else if(!(pane.passwordsMatch())) {

                    pane.displayPasswordsNotMatch();

                } else if(!(pane.isValidUser())) {

                    pane.displayInvalidUser();

                } else {

                    pane.displaySuccess();

                }
            }
        }

    }

    private class PasswordFieldHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent keyEvent) {

            if(keyEvent.getSource() == passwordPasswordfield) {

                // sleep the thread to find a password mismatch
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                pane.passwordFlag();

            } else if(keyEvent.getSource() == confirmpasswordPasswordfield) {

                pane.confirmpasswordFlag();

            }
        }
    }
}
