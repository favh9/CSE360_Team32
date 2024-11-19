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

/**
 * User_ForgotPasswordPane provides the user interface for the Forgot Password page.
 * This includes fields for email, username, birthdate, new password, and confirm password,
 * along with buttons for confirming the reset and navigating back.
 */
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

    /**
     * Constructor for the Forgot Password interface.
     * Initializes UI components and arranges them in a structured layout.
     *
     * @param width  The width of the Forgot Password pane.
     * @param height The height of the Forgot Password pane.
     */
    public User_ForgotPasswordPane(double width, double height) {
        this.width = width;
        this.height = height;

        // --- Back Button Setup ---
        // Create the back button with an icon
        ImageView backImageView = new ImageView(Main.backIcon);
        backImageView.setFitWidth(40);
        backImageView.setFitHeight(40);

        backButton = new Button();
        backButton.setGraphic(backImageView);
        backButton.setBackground(null); // No background for the button

        // --- Forgot Password Label ---
        // Create the title label for the page
        Label forgotPasswordLabel = new Label("Forgot Password");
        forgotPasswordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 36));
        forgotPasswordLabel.setTextFill(Color.BLACK);
        forgotPasswordLabel.setPrefHeight(40);

        // Create an HBox to hold the back button and the title label
        HBox backbuttonForgotpasswordLabelHBox = new HBox(backButton, forgotPasswordLabel);
        backbuttonForgotpasswordLabelHBox.setAlignment(Pos.CENTER_LEFT);
        backbuttonForgotpasswordLabelHBox.setSpacing(20);

        // --- Instruction Label ---
        // Label to guide the user
        Label instructionLabel = new Label("Fill in the information below and click on the confirm button to reset your password.");
        instructionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        // --- Email Field Setup ---
        Label emailLabel = new Label("E-mail:");
        emailLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        emailTextField = new TextField();
        emailTextField.setPromptText("E-mail");
        emailTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        HBox emailTextFieldHBox = new HBox(emailLabel, emailTextField);
        emailTextFieldHBox.setAlignment(Pos.CENTER_RIGHT);
        emailTextFieldHBox.setSpacing(10);

        // --- Username Field Setup ---
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        usernameTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        HBox usernameTextfieldHBox = new HBox(usernameLabel, usernameTextField);
        usernameTextfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        usernameTextfieldHBox.setSpacing(10);

        // --- Birth Date Field Setup ---
        Label birthdateLabel = new Label("Date of Birth:");
        birthdateLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Set up year, month, and day combo boxes
        yearComboBox = new ComboBox<>();
        yearComboBox.setVisibleRowCount(10);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = currentYear; year >= 1800; year--) {
            yearComboBox.getItems().add(Integer.toString(year));
        }
        yearComboBox.setValue("YYYY"); // Default value

        monthComboBox = new ComboBox<>();
        monthComboBox.setVisibleRowCount(10);
        for (int month = 1; month <= 12; month++) {
            monthComboBox.getItems().add(Integer.toString(month));
        }
        monthComboBox.setValue("MM"); // Default value

        dayComboBox = new ComboBox<>();
        dayComboBox.setVisibleRowCount(10);
        for (int day = 1; day <= 31; day++) {
            dayComboBox.getItems().add(Integer.toString(day));
        }
        dayComboBox.setValue("DD"); // Default value

        HBox birthdateHBox = new HBox(birthdateLabel, yearComboBox, monthComboBox, dayComboBox);
        birthdateHBox.setAlignment(Pos.CENTER_RIGHT);
        birthdateHBox.setSpacing(10);

        // --- Password and Confirm Password Fields ---
        Label passwordLabel = new Label("New Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        passwordPasswordfield = new PasswordField();
        passwordPasswordfield.setPromptText("New Password");
        passwordPasswordfield.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        HBox passwordPasswordfieldHBox = new HBox(passwordLabel, passwordPasswordfield);
        passwordPasswordfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        passwordPasswordfieldHBox.setSpacing(10);

        Label confirmpasswordLabel = new Label("Confirm Password:");
        confirmpasswordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        confirmpasswordPasswordfield = new PasswordField();
        confirmpasswordPasswordfield.setPromptText("Confirm Password");
        confirmpasswordPasswordfield.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        HBox confirmpasswordPasswordfieldHBox = new HBox(confirmpasswordLabel, confirmpasswordPasswordfield);
        confirmpasswordPasswordfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        confirmpasswordPasswordfieldHBox.setSpacing(10);

        // --- Confirm Button ---
        confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        confirmButton.setPrefSize(100, 40);

        // --- Main Layout ---
        // Create the VBox to hold all form elements
        VBox greyVBox = new VBox(instructionLabel, emailTextFieldHBox, usernameTextfieldHBox, birthdateHBox, passwordPasswordfieldHBox, confirmpasswordPasswordfieldHBox, confirmButton);
        greyVBox.setSpacing(20);
        greyVBox.setPadding(new Insets(20, 20, 20, 20));
        greyVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #D9D9D9;");

        // Create the white VBox to hold the grey VBox and back button
        VBox whiteVBox = new VBox(backbuttonForgotpasswordLabelHBox, greyVBox);
        whiteVBox.setSpacing(10);
        whiteVBox.setPadding(new Insets(40, 40, 40, 40));
        whiteVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #ffffff;");

        // Set the main layout for the BorderPane
        this.setCenter(whiteVBox);
        BorderPane.setMargin(whiteVBox, new Insets(20, 20, 20, 20));

        // Set the preferred size and background color for the BorderPane
        this.setPrefSize(width, height);
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // Darker maroon background
    }

    // Getters for the various input fields and buttons
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

    /**
     * Checks if any of the input fields are empty.
     *
     * @return true if any required field is empty, false otherwise.
     */
    public boolean emptyFields() {
        return emailTextField.getText().isEmpty() ||
                usernameTextField.getText().isEmpty() ||
                yearComboBox.getValue().equals("YYYY") ||
                monthComboBox.getValue().equals("MM") ||
                dayComboBox.getValue().equals("DD") ||
                passwordPasswordfield.getText().isEmpty() ||
                confirmpasswordPasswordfield.getText().isEmpty();
    }

    /**
     * Checks if the new password and confirm password fields match.
     *
     * @return true if passwords match, false otherwise.
     */
    public boolean passwordsMatch() {
        return passwordPasswordfield.getText().equals(confirmpasswordPasswordfield.getText());
    }

    /**
     * Validates if the passwords match and sets the border color to red if they do not match.
     */
    public void passwordFlag() {
        if (passwordPasswordfield.getText().equals(confirmpasswordPasswordfield.getText())) {
            passwordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
            confirmpasswordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
        } else {
            passwordPasswordfield.setBorder(Border.stroke(Color.RED));
            confirmpasswordPasswordfield.setBorder(Border.stroke(Color.RED));
        }
    }

    /**
     * Displays an alert dialog with the specified type, title, and message.
     *
     * @param alertType The type of the alert (e.g., WARNING, INFORMATION).
     * @param title     The title of the alert.
     * @param headerText The header text (optional, can be null).
     * @param contentText The content text to be displayed in the alert.
     */
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText); // Can be null
        alert.setContentText(contentText);
        alert.show();
    }

    // Display error messages in different scenarios

    /**
     * Displays an alert when the password reset fails.
     */
    public void displayPasswordResetFailed() {
        showAlert(Alert.AlertType.WARNING, "Password Reset Failed", null, "Your new password was not saved, please re-enter current password.");
    }

    /**
     * Displays an alert when the password is successfully reset.
     */
    public void displayPasswordReset() {
        showAlert(Alert.AlertType.INFORMATION, "Password Reset Success", null, "Your new password has been saved.");
    }

    /**
     * Displays an alert when the passwords do not match.
     */
    public void displayPasswordsNotMatch() {
        showAlert(Alert.AlertType.WARNING, "Warning", null, "Password mismatch error: verify please entered password");
    }

    /**
     * Displays an alert when the user is not found.
     */
    public void displayUserNotFound() {
        showAlert(Alert.AlertType.WARNING, "Warning", null, "No such User");
    }

    /**
     * Displays an alert when there are empty fields in the form.
     */
    public void displayEmptyFields() {
        StringBuilder msg = new StringBuilder("Please enter the following fields to create your account:\n");

        if (emailTextField.getText().isEmpty()) msg.append("\temail\n");
        if (usernameTextField.getText().isEmpty()) msg.append("\tusername\n");
        if (yearComboBox.getValue().equals("YYYY")) msg.append("\tbirth year\n");
        if (monthComboBox.getValue().equals("MM")) msg.append("\tbirth month\n");
        if (dayComboBox.getValue().equals("DD")) msg.append("\tbirth day\n");
        if (passwordPasswordfield.getText().isEmpty()) msg.append("\tpassword\n");
        if (confirmpasswordPasswordfield.getText().isEmpty()) msg.append("\tconfirm password\n");

        showAlert(Alert.AlertType.WARNING, "Warning", null, msg.toString());
    }
}
