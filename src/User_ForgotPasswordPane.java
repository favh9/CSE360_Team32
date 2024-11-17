

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Calendar;

// this class will provide the user interface
// for the create account page
public class User_ForgotPasswordPane extends BorderPane {

    private TextField emailTextField;
    private TextField usernameTextField;
    private ComboBox<String> yearComboBox;
    private ComboBox<String> monthComboBox;

    public ComboBox<String> getYearComboBox() {
        return yearComboBox;
    }

    public ComboBox<String> getMonthComboBox() {
        return monthComboBox;
    }

    public ComboBox<String> getDayComboBox() {
        return dayComboBox;
    }

    private ComboBox<String> dayComboBox;
    private PasswordField passwordPasswordfield;
    private PasswordField confirmpasswordPasswordfield;
    private Button confirmButton;
    private Button backButton;

    // parameterized constructor
    public User_ForgotPasswordPane(double width, double height) {

        // set attributes for the image to be used for the back button
        ImageView backImageView = new ImageView(Main.backIcon);
        backImageView.setFitWidth(40);
        backImageView.setFitHeight(40);

        // set attributes for the back button and add the back icon
        backButton = new Button();
        backButton.setGraphic(backImageView);
        backButton.setBackground(null);

        // set attributes for the create account label
        Label forgotPasswordLabel = new Label("Forgot Password");
        forgotPasswordLabel.setFont(Font.font("Arial",FontWeight.NORMAL,36));
        forgotPasswordLabel.setTextFill(Color.BLACK);
        forgotPasswordLabel.setPrefHeight(40);

        // add the back button and create account label to this HBox
        HBox backbuttonForgotpasswordLabelHBox = new HBox(backButton, forgotPasswordLabel);
        backbuttonForgotpasswordLabelHBox.setAlignment(Pos.CENTER_LEFT);
        backbuttonForgotpasswordLabelHBox.setSpacing(20);

        // set attributes for the instructions label
        Label instructionLabel = new Label("Fill in the information below and click on the confirm button to reset your password.");
        instructionLabel.setFont(Font.font("Arial",FontWeight.NORMAL,18));

        // set attributes for the email label
        Label emailLabel = new Label("E-mail:");
        emailLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the email text field
        emailTextField = new TextField();
        emailTextField.setPromptText("E-mail");
        emailTextField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add the email label and email text field into this hbox
        HBox emailTextFieldHBox = new HBox(emailLabel, emailTextField);
        emailTextFieldHBox.setAlignment(Pos.CENTER_RIGHT);
        emailTextFieldHBox.setSpacing(10);

        // set attributes for the username label
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the username text field
        usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        usernameTextField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add the username label and username text field into this hbox
        HBox usernameTextfieldHBox = new HBox(usernameLabel, usernameTextField);
        usernameTextfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        usernameTextfieldHBox.setSpacing(10);

        // add a label for security question - birth date
        Label birthdateLabel = new Label("Date of Birth:");
        birthdateLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add a birth date field
        // set attributes for the year box
        yearComboBox = new ComboBox<>();
        yearComboBox.setVisibleRowCount(10);

        // get the current year
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        // fill the choice box with yeears from current to 1800?
        for (int year = currentYear; year >= 1800; year--) {
            yearComboBox.getItems().add(Integer.toString(year));
        }

        // set the default choice to current year
        yearComboBox.setValue("YYYY");

        // set attributes for the day box
        dayComboBox = new ComboBox<>();
        dayComboBox.setVisibleRowCount(10);

        // fill the choice box with days
        int maxDays = 31;
        for (int day = 1; day <= maxDays; day++) {
            dayComboBox.getItems().add(Integer.toString(day));
        }

        // set the default choice for days
        dayComboBox.setValue("DD");

        // set attributes for the day box
        monthComboBox = new ComboBox<>();
        monthComboBox.setVisibleRowCount(10);

        // fill the choice box with days
        int maxMonths = 12;
        for (int month = 1; month <= maxMonths; month++) {
            monthComboBox.getItems().add(Integer.toString(month));
        }

        // set the default choice for days
        monthComboBox.setValue("MM");

        // create a HBox to store the label and text field
        HBox birthdateHBox = new HBox(birthdateLabel,yearComboBox,monthComboBox,dayComboBox);
        birthdateHBox.setAlignment(Pos.CENTER_RIGHT);
        birthdateHBox.setSpacing(10);

        // set attributes for the password label
        Label passwordLabel = new Label("New Password:");
        passwordLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the password password field
        passwordPasswordfield = new PasswordField();
        passwordPasswordfield.setPromptText("New Password");
        passwordPasswordfield.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add the password label and password password field into this hbox
        HBox passwordPasswordfieldHBox = new HBox(passwordLabel, passwordPasswordfield);
        passwordPasswordfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        passwordPasswordfieldHBox.setSpacing(10);

        // set attributes for the confirm password label
        Label confirmpasswordLabel = new Label("Confirm Password:");
        confirmpasswordLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the password password field
        confirmpasswordPasswordfield = new PasswordField();
        confirmpasswordPasswordfield.setPromptText("Confirm Password");
        confirmpasswordPasswordfield.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add the password label and password password field into this hbox
        HBox confirmpasswordPasswordfieldHBox = new HBox(confirmpasswordLabel, confirmpasswordPasswordfield);
        confirmpasswordPasswordfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        confirmpasswordPasswordfieldHBox.setSpacing(10);

        // set the attributes for the confirm button
        confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font("Arial",FontWeight.NORMAL,16));
        confirmButton.setPrefSize(100,40);

        // add the grey box elements which includes the instructions label and all HBoxes
        VBox greyVBox = new VBox(instructionLabel, emailTextFieldHBox, usernameTextfieldHBox, birthdateHBox, passwordPasswordfieldHBox, confirmpasswordPasswordfieldHBox, confirmButton);
        // readjust the width of the HBoxes inside the greyVBox
        VBox.setMargin(emailTextFieldHBox, new Insets(0,450,0,0));
        VBox.setMargin(usernameTextfieldHBox, new Insets(0,450,0,0));
        VBox.setMargin(birthdateHBox, new Insets(0,415,0,0));
        VBox.setMargin(passwordPasswordfieldHBox, new Insets(0,450,0,0));
        VBox.setMargin(confirmpasswordPasswordfieldHBox, new Insets(0,450,0,0));
        VBox.setMargin(confirmButton, new Insets(0,0,0,400));
        greyVBox.setSpacing(20);
        greyVBox.setPadding(new Insets(20,20,20,20));
        greyVBox.setStyle(
                "-fx-background-radius: 2em;" +
                        "-fx-background-color: #D9D9D9;"
        );

        // the white VBox is our main box
        VBox whiteVBox = new VBox(backbuttonForgotpasswordLabelHBox, greyVBox);
        whiteVBox.setSpacing(10);
        whiteVBox.setPadding(new Insets(40,40,40,40));
        whiteVBox.setStyle(
                "-fx-background-radius: 2em;" +
                        "-fx-background-color: #ffffff;"
        );

        // set attributes for the Border Pane
        this.setCenter(whiteVBox);
        BorderPane.setMargin(whiteVBox, new Insets(20,20,20,20));

        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon colors

    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public PasswordField getPasswordPasswordfield() {
        return passwordPasswordfield;
    }

    public PasswordField getConfirmpasswordPasswordfield() {
        return confirmpasswordPasswordfield;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }


}
