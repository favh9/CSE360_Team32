import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CreateAccountControl extends Pane {

    public double width;
    public double height;
    private final LoginControl login;
    private final TextField firstnameTextField;
    private final TextField lastnameTextField;
    private final TextField emailTextField;
    private final TextField usernameTextField;
    private final PasswordField passwordPasswordfield;
    private final PasswordField confirmpasswordPasswordfield;
    private final Button confirmButton;
    private final Button backButton;

    public CreateAccountControl(double width, double height) {

        this.width = width;
        this.height = height;

        CreateAccountPane pane = new CreateAccountPane(width,height);

        login = new LoginControl(width,height);

        firstnameTextField = pane.getFirstnameTextField();

        lastnameTextField = pane.getLastnameTextField();

        emailTextField = pane.getEmailTextField();

        usernameTextField = pane.getUsernameTextField();

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
            } else if (a.getSource() == confirmButton) {

                if(emptyFields()) {
                    displayEmptyFields();
                } else if(!(EmailValidator.isValidEmail(emailTextField.getText()))){
                    displayInvalidEmail();
                } else if(!passwordsMatch()){
                    displayPasswordsMatch();
                } else {
                    Alert acctCreatedAlert = new Alert(Alert.AlertType.INFORMATION);
                    acctCreatedAlert.setTitle("");
                    acctCreatedAlert.setHeaderText("Congratulations!");
                    acctCreatedAlert.setContentText("Welcome " + firstnameTextField.getText() + " " + lastnameTextField.getText() +
                            ",\nYour account was successfully created." +
                            "\nYour username is " + usernameTextField.getText() + ".");
                    ImageView confirmImageView = new ImageView(Main.successIcon);

//                    // TESTING DATA BASE
//                    DataBase.insertUser(usernameTextField.getText(),usernameTextField.getText());

                    confirmImageView.setFitHeight(40);
                    confirmImageView.setFitWidth(100);
                    acctCreatedAlert.setGraphic(confirmImageView);
                    // this will ensure upon closing the login page appears
                    acctCreatedAlert.setOnCloseRequest(arg0 -> {
                        // TODO Auto-generated method stub
                        Main.mainWindow.setScene(new Scene(login));
                    });
                    acctCreatedAlert.show();
                }
            }
        }

        public boolean passwordsMatch() {
            return passwordPasswordfield.getText().compareTo(confirmpasswordPasswordfield.getText()) == 0;
        }

        public void displayPasswordsMatch() {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please verify or re-enter your password\n");
            alert.show();
        }

        public boolean emptyFields() {

            if(firstnameTextField.getText().isEmpty())
                return true;
            if(lastnameTextField.getText().isEmpty())
                return true;
            if(emailTextField.getText().isEmpty())
                return true;
            if(usernameTextField.getText().isEmpty())
                return true;
            if(passwordPasswordfield.getText().isEmpty())
                return true;
            if(confirmpasswordPasswordfield.getText().isEmpty())
                return true;

            return false;
        }

        public void displayEmptyFields() {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            String missingData = "Please enter the following fields to create your account:\n";

            if(firstnameTextField.getText().isEmpty())
                missingData += "\tfirst name\n";
            if(lastnameTextField.getText().isEmpty())
                missingData += "\tlast name\n";
            if(emailTextField.getText().isEmpty())
                missingData += "\te-mail\n";
            if(usernameTextField.getText().isEmpty())
                missingData += "\tusername\n";
            if(passwordPasswordfield.getText().isEmpty())
                missingData += "\tpassword\n";
            if(confirmpasswordPasswordfield.getText().isEmpty())
                missingData += "\tconfirm password\n";

            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText(missingData);
            alert.show();

        }

        public void displayInvalidEmail() {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please verify or re-enter your e-mail\n");
            alert.show();
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
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                if (confirmpasswordPasswordfield.getText().isEmpty())
                    return;
                if (passwordPasswordfield.getText().compareTo(confirmpasswordPasswordfield.getText()) != 0) {
                    passwordPasswordfield.setBorder(Border.stroke(Color.RED));
                    confirmpasswordPasswordfield.setBorder(Border.stroke(Color.RED));
                } else {
                    passwordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
                    confirmpasswordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
                }

            } else if(keyEvent.getSource() == confirmpasswordPasswordfield) {

                if(passwordPasswordfield.getText().compareTo(confirmpasswordPasswordfield.getText()) != 0) {
                    passwordPasswordfield.setBorder(Border.stroke(Color.RED));
                    confirmpasswordPasswordfield.setBorder(Border.stroke(Color.RED));
                } else {
                    passwordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
                    confirmpasswordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
                }

            }
        }
    }
}
