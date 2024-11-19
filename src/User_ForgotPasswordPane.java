

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Calendar;

// This class provides the user interface for the Forgot Password page in the application.
public class User_ForgotPasswordPane extends BorderPane {

    // Declare UI components
    private TextField emailTextField;
    private TextField usernameTextField;
    private ComboBox<String> yearComboBox;
    private ComboBox<String> monthComboBox;
    private ComboBox<String> dayComboBox;
    private PasswordField passwordPasswordfield;
    private PasswordField confirmpasswordPasswordfield;
    private Button confirmButton;
    private Button backButton;
    private double width;
    private double height;

    // Parameterized constructor to initialize the Forgot Password interface
    public User_ForgotPasswordPane(double width, double height) {

        this.width = width;
        this.height = height;

        // --- Back Button Setup ---
        // Set the image for the back button
        ImageView backImageView = new ImageView(Main.backIcon);
        backImageView.setFitWidth(40);
        backImageView.setFitHeight(40);

        // Create and configure the back button
        backButton = new Button();
        backButton.setGraphic(backImageView);
        backButton.setBackground(null); // No background for the button

        // --- Forgot Password Label ---
        // Create the label for the page title ("Forgot Password")
        Label forgotPasswordLabel = new Label("Forgot Password");
        forgotPasswordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 36));
        forgotPasswordLabel.setTextFill(Color.BLACK);
        forgotPasswordLabel.setPrefHeight(40);

        // Place the back button and forgot password label into an HBox
        HBox backbuttonForgotpasswordLabelHBox = new HBox(backButton, forgotPasswordLabel);
        backbuttonForgotpasswordLabelHBox.setAlignment(Pos.CENTER_LEFT);
        backbuttonForgotpasswordLabelHBox.setSpacing(20);

        // --- Instruction Label ---
        // Create an instruction label to guide the user
        Label instructionLabel = new Label("Fill in the information below and click on the confirm button to reset your password.");
        instructionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        // --- Email Field Setup ---
        // Label and text field for email input
        Label emailLabel = new Label("E-mail:");
        emailLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        emailTextField = new TextField();
        emailTextField.setPromptText("E-mail");
        emailTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Combine email label and text field into an HBox for alignment
        HBox emailTextFieldHBox = new HBox(emailLabel, emailTextField);
        emailTextFieldHBox.setAlignment(Pos.CENTER_RIGHT);
        emailTextFieldHBox.setSpacing(10);

        // --- Username Field Setup ---
        // Label and text field for username input
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        usernameTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Combine username label and text field into an HBox for alignment
        HBox usernameTextfieldHBox = new HBox(usernameLabel, usernameTextField);
        usernameTextfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        usernameTextfieldHBox.setSpacing(10);

        // --- Birth Date Field Setup ---
        // Label and combo boxes for selecting birth date (year, month, day)
        Label birthdateLabel = new Label("Date of Birth:");
        birthdateLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Year combo box setup
        yearComboBox = new ComboBox<>();
        yearComboBox.setVisibleRowCount(10);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = currentYear; year >= 1800; year--) {
            yearComboBox.getItems().add(Integer.toString(year));
        }
        yearComboBox.setValue("YYYY"); // Default to "YYYY"

        // Month combo box setup
        monthComboBox = new ComboBox<>();
        monthComboBox.setVisibleRowCount(10);
        for (int month = 1; month <= 12; month++) {
            monthComboBox.getItems().add(Integer.toString(month));
        }
        monthComboBox.setValue("MM"); // Default to "MM"

        // Day combo box setup
        dayComboBox = new ComboBox<>();
        dayComboBox.setVisibleRowCount(10);
        for (int day = 1; day <= 31; day++) {
            dayComboBox.getItems().add(Integer.toString(day));
        }
        dayComboBox.setValue("DD"); // Default to "DD"

        // Combine birthdate label and combo boxes into an HBox for alignment
        HBox birthdateHBox = new HBox(birthdateLabel, yearComboBox, monthComboBox, dayComboBox);
        birthdateHBox.setAlignment(Pos.CENTER_RIGHT);
        birthdateHBox.setSpacing(10);

        // --- Password and Confirm Password Fields ---
        // Label and password field for new password
        Label passwordLabel = new Label("New Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        passwordPasswordfield = new PasswordField();
        passwordPasswordfield.setPromptText("New Password");
        passwordPasswordfield.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Combine password label and field into an HBox
        HBox passwordPasswordfieldHBox = new HBox(passwordLabel, passwordPasswordfield);
        passwordPasswordfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        passwordPasswordfieldHBox.setSpacing(10);

        // Label and password field for confirm password
        Label confirmpasswordLabel = new Label("Confirm Password:");
        confirmpasswordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        confirmpasswordPasswordfield = new PasswordField();
        confirmpasswordPasswordfield.setPromptText("Confirm Password");
        confirmpasswordPasswordfield.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Combine confirm password label and field into an HBox
        HBox confirmpasswordPasswordfieldHBox = new HBox(confirmpasswordLabel, confirmpasswordPasswordfield);
        confirmpasswordPasswordfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        confirmpasswordPasswordfieldHBox.setSpacing(10);

        // --- Confirm Button ---
        // Create the confirm button to submit the form
        confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        confirmButton.setPrefSize(100, 40);

        // --- Main Layout ---
        // Create a grey VBox to hold the form elements
        VBox greyVBox = new VBox(instructionLabel, emailTextFieldHBox, usernameTextfieldHBox, birthdateHBox, passwordPasswordfieldHBox, confirmpasswordPasswordfieldHBox, confirmButton);

        // Adjust margins of each HBox inside the grey VBox for proper alignment
        VBox.setMargin(emailTextFieldHBox, new Insets(0, 450, 0, 0));
        VBox.setMargin(usernameTextfieldHBox, new Insets(0, 450, 0, 0));
        VBox.setMargin(birthdateHBox, new Insets(0, 415, 0, 0));
        VBox.setMargin(passwordPasswordfieldHBox, new Insets(0, 450, 0, 0));
        VBox.setMargin(confirmpasswordPasswordfieldHBox, new Insets(0, 450, 0, 0));
        VBox.setMargin(confirmButton, new Insets(0, 0, 0, 250));

        greyVBox.setSpacing(20);
        greyVBox.setPadding(new Insets(20, 20, 20, 20));
        greyVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #D9D9D9;");

        // Create a white VBox to hold the grey VBox and back button
        VBox whiteVBox = new VBox(backbuttonForgotpasswordLabelHBox, greyVBox);
        whiteVBox.setSpacing(10);
        whiteVBox.setPadding(new Insets(40, 40, 40, 40));
        whiteVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #ffffff;");

        // Set the main layout for the BorderPane
        this.setCenter(whiteVBox);
        BorderPane.setMargin(whiteVBox, new Insets(20, 20, 20, 20));

        // Set the preferred size and background color for the BorderPane
        this.setPrefSize(width, height);
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // Darker maroon background color
    }

    public String getEmail() {
        return emailTextField.getText();
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getYear() {
        return yearComboBox.getValue();
    }

    public String getMonth() {
        return monthComboBox.getValue();
    }

    public String getDay() {
        return dayComboBox.getValue();
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

    public boolean emptyFields() {

        if(emailTextField.getText().isEmpty())
            return true;
        if(usernameTextField.getText().isEmpty())
            return true;
        if(yearComboBox.getValue().equals("YYYY"))
            return true;
        if(monthComboBox.getValue().equals("MM"))
            return true;
        if(dayComboBox.getValue().equals("DD"))
            return true;
        if(passwordPasswordfield.getText().isEmpty())
            return true;
        return confirmpasswordPasswordfield.getText().isEmpty();
    }

    public boolean passwordsMatch() {
        return passwordPasswordfield.getText().compareTo(confirmpasswordPasswordfield.getText()) == 0;
    }

    public void passwordFlag() {

        if (confirmpasswordPasswordfield.getText().isEmpty())
            return;
        if (passwordPasswordfield.getText().compareTo(confirmpasswordPasswordfield.getText()) != 0) {
            passwordPasswordfield.setBorder(Border.stroke(Color.RED));
            confirmpasswordPasswordfield.setBorder(Border.stroke(Color.RED));
        } else {
            passwordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
            confirmpasswordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
        }

    }
    public void confirmPasswordFlag() {

        if(passwordPasswordfield.getText().compareTo(confirmpasswordPasswordfield.getText()) != 0) {
            passwordPasswordfield.setBorder(Border.stroke(Color.RED));
            confirmpasswordPasswordfield.setBorder(Border.stroke(Color.RED));
        } else {
            passwordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
            confirmpasswordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
        }

    }

    // Method to display an alert when password reset fails
    public void displayPasswordResetFailed() {
        String title = "Password Reset Failed";
        String msg = "Your new password was not saved, please re-enter current password.";
        // Use the helper method to display the alert
        showAlert(Alert.AlertType.WARNING, title, null, msg);
    }

    // Method to display an alert when password reset is successful
    public void displayPasswordReset() {
        String title = "Password Reset Success";
        String msg = "Your new password has been saved.";
        // Use the helper method to display the alert
        showAlert(Alert.AlertType.INFORMATION, title, null, msg);
    }

    // Method to display an alert when passwords do not match
    public void displayPasswordsNotMatch() {
        String title = "Warning";
        String msg = "Password mismatch error: verify please entered password\n";
        // Use the helper method to display the alert
        showAlert(Alert.AlertType.WARNING, title, null, msg);
    }

    // Method to display an alert when the user is not found
    public void displayUserNotFound() {
        String title = "Warning";
        String msg = "No such User\n";
        // Use the helper method to display the alert
        showAlert(Alert.AlertType.WARNING, title, null, msg);
    }

    // Method to display an alert when there are empty fields
    public void displayEmptyFields() {
        String title = "Warning";
        String msg = "Please enter the following fields to create your account:\n";

        // Append the missing fields to the message
        if (emailTextField.getText().isEmpty())
            msg += "\temail\n";
        if (usernameTextField.getText().isEmpty())
            msg += "\tusername\n";
        if (yearComboBox.getValue().equals("YYYY"))
            msg += "\tbirth year\n";
        if (monthComboBox.getValue().equals("MM"))
            msg += "\tbirth month\n";
        if (dayComboBox.getValue().equals("DD"))
            msg += "\tbirth day\n";
        if (passwordPasswordfield.getText().isEmpty())
            msg += "\tpassword\n";
        if (confirmpasswordPasswordfield.getText().isEmpty())
            msg += "\tconfirm password\n";

        // Use the helper method to display the alert
        showAlert(Alert.AlertType.WARNING, title, null, msg);
    }

    // Helper method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText); // Can be null
        alert.setContentText(contentText);
        alert.show();
    }

}
