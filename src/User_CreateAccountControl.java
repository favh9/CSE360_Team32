import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class User_CreateAccountControl extends Pane {

    public double width;
    public double height;
    private User_LoginControl login;
    private TextField firstnameTextField;
    private TextField lastnameTextField;
    private ComboBox<String> yearComboBox;
    private ComboBox<String> monthComboBox;
    private ComboBox<String> dayComboBox;
    private TextField emailTextField;
    private TextField usernameTextField;
    private PasswordField passwordPasswordfield;
    private PasswordField confirmpasswordPasswordfield;
    private Button confirmButton;
    private Button backButton;

    public User_CreateAccountControl(double width, double height) {

        this.width = width;
        this.height = height;

        User_CreateAccountPane pane = new User_CreateAccountPane(width,height);

        login = new User_LoginControl(width,height);

        firstnameTextField = pane.getFirstnameTextField();

        lastnameTextField = pane.getLastnameTextField();

        yearComboBox = pane.getYearComboBox();

        monthComboBox = pane.getMonthComboBox();

        dayComboBox = pane.getDayComboBox();

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

            }
            else if (a.getSource() == confirmButton) {

                if(emptyFields()) {
                    displayEmptyFields();
                } else if(!(EmailValidator.isValidEmail(emailTextField.getText()))) {
                    displayInvalidEmail();
                } else if(!passwordsMatch()) {
                    displayPasswordsNotMatch();
                } else if(!(validUser())) {
                    displayInvalidUser();
                } else {
                    displaySuccess();
                }
            }
        }

        public boolean validUser() {
            return DataBase.insertUser(firstnameTextField.getText(),lastnameTextField.getText(),emailTextField.getText(),usernameTextField.getText(),passwordPasswordfield.getText());
        }

        public void displaySuccess() {
            Alert acctCreatedAlert = new Alert(Alert.AlertType.INFORMATION);
            acctCreatedAlert.setTitle("");
            acctCreatedAlert.setHeaderText("Congratulations!");
            acctCreatedAlert.setContentText("Welcome " + firstnameTextField.getText() + " " + lastnameTextField.getText() +
                    ",\nYour account was successfully created." +
                    "\nYour username is " + usernameTextField.getText() + ".");
            ImageView confirmImageView = new ImageView(Main.successIcon);
            confirmImageView.setFitHeight(40);
            confirmImageView.setFitWidth(40);
            acctCreatedAlert.setGraphic(confirmImageView);
            acctCreatedAlert.setOnCloseRequest(arg0 -> {
                // TODO Auto-generated method stub
                Main.mainWindow.setScene(new Scene(login));
            });
            acctCreatedAlert.show();
        }

        public boolean passwordsMatch() {
            return passwordPasswordfield.getText().compareTo(confirmpasswordPasswordfield.getText()) == 0;
        }

        public void displayPasswordsNotMatch() {
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

        public void displayInvalidUser() {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please choose a different email and/or username\n");
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
