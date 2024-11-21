import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// This class provides the user interface for the 'Change Password' page.
public class Seller_ChangePasswordPane extends BorderPane {

    // Declare UI components (fields and buttons)
    private PasswordField currentPasswordField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button confirmButton;
    private Button backButton;
    private double width;
    private double height;

    /**
     * Constructor to initialize the Change Password pane.
     * @param user The user object containing the user's details.
     * @param width The width of the pane.
     * @param height The height of the pane.
     */
    public Seller_ChangePasswordPane(User user, double width, double height) {

        // Store pane dimensions
        this.width = width;
        this.height = height;

        // --- Back Button Setup ---
        // Create an ImageView for the back button icon
        ImageView backImageView = new ImageView(Main.backIcon);
        backImageView.setFitWidth(40);
        backImageView.setFitHeight(40);

        // Create the back button and set its graphic
        backButton = new Button();
        backButton.setGraphic(backImageView);
        backButton.setBackground(null);

        // --- Header Setup ---
        // Create a label for the 'Change Password' title
        Label changePasswordLabel = new Label("Change Password");
        changePasswordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 36));
        changePasswordLabel.setTextFill(Color.BLACK);
        changePasswordLabel.setPrefHeight(40);

        // Create a container (HBox) for the back button and the label
        HBox backbuttonChangePasswordLabelHBox = new HBox(backButton, changePasswordLabel);
        backbuttonChangePasswordLabelHBox.setAlignment(Pos.CENTER_LEFT);
        backbuttonChangePasswordLabelHBox.setSpacing(20);

        // --- Instructions Label ---
        Label instructionLabel = new Label("Fill in the information below and click on the confirm button to reset your password.");
        instructionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        // --- Form Fields Setup ---
        // Current password field setup
        Label currentPasswordLabel = new Label("Current Password:");
        currentPasswordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        currentPasswordField = new PasswordField();
        currentPasswordField.setPromptText("Current Password");
        currentPasswordField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Layout for current password field and label
        HBox box1 = new HBox(currentPasswordLabel, currentPasswordField);
        box1.setAlignment(Pos.CENTER_RIGHT);
        box1.setSpacing(10);

        // New password field setup
        Label passwordLabel = new Label("New Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        passwordField = new PasswordField();
        passwordField.setPromptText("New Password");
        passwordField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Layout for new password field and label
        HBox box2 = new HBox(passwordLabel, passwordField);
        box2.setAlignment(Pos.CENTER_RIGHT);
        box2.setSpacing(10);

        // Confirm password field setup
        Label confirmpasswordLabel = new Label("Confirm Password:");
        confirmpasswordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Layout for confirm password field and label
        HBox box3 = new HBox(confirmpasswordLabel, confirmPasswordField);
        box3.setAlignment(Pos.CENTER_RIGHT);
        box3.setSpacing(10);

        // --- Confirm Button Setup ---
        confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        confirmButton.setPrefSize(100, 40);

        // --- Grey Box Container Setup ---
        VBox greyVBox = new VBox(instructionLabel, box1, box2, box3, confirmButton);
        // Adjust margins for better alignment
        VBox.setMargin(box1, new Insets(0, 450, 0, 0));
        VBox.setMargin(box2, new Insets(0, 450, 0, 0));
        VBox.setMargin(box3, new Insets(0, 450, 0, 0));
        VBox.setMargin(confirmButton, new Insets(0, 0, 0, 250));
        greyVBox.setSpacing(20);
        greyVBox.setPadding(new Insets(20, 20, 20, 20));
        greyVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #D9D9D9;");

        // --- White Box Container Setup ---
        VBox whiteVBox = new VBox(backbuttonChangePasswordLabelHBox, greyVBox);
        whiteVBox.setSpacing(10);
        whiteVBox.setPadding(new Insets(40, 40, 40, 40));
        whiteVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #ffffff;");

        // Set the content of the BorderPane
        this.setCenter(whiteVBox);
        BorderPane.setMargin(whiteVBox, new Insets(20, 20, 20, 20));

        // Set the pane's size and background color
        this.setPrefSize(width, height);
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // Darker maroon background
    }

    // --- Getter Methods ---
    public Button getBackButton() {
        return backButton;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public PasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public PasswordField getCurrentPasswordField() {
        return currentPasswordField;
    }


    // --- Helper Methods for Validation ---
    // Check if any of the fields are empty
    public boolean emptyFields() {
        return currentPasswordField.getText().isEmpty() || passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty();
    }

    // Check if the new password and confirmation match
    public boolean passwordsMatch() {
        return passwordField.getText().equals(confirmPasswordField.getText());
    }

    // Clear the Fields
    public void clearFields() {
        currentPasswordField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    // --- Border Update for Password Mismatch ---
    public void passwordFlag() {
        if (confirmPasswordField.getText().isEmpty()) return;
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            passwordField.setBorder(Border.stroke(Color.RED));
            confirmPasswordField.setBorder(Border.stroke(Color.RED));
        } else {
            passwordField.setBorder(Border.stroke(Color.TRANSPARENT));
            confirmPasswordField.setBorder(Border.stroke(Color.TRANSPARENT));
        }
    }

    // --- Display Alerts ---
    // Display a warning alert when password reset fails
    public void displayPasswordResetFailed() {
        String title = "Password Reset Failed";
        String msg = "Your new password was not saved, please re-enter current password.";
        showAlert(Alert.AlertType.WARNING, title, null, msg);
    }

    // Display an information alert when the password is successfully reset
    public void displayPasswordReset() {
        String title = "Password Reset Success";
        String msg = "Your new password has been saved.";
        showAlert(Alert.AlertType.INFORMATION, title, null, msg);
    }

    // Display an alert when passwords do not match
    public void displayPasswordsNotMatch() {
        String title = "Warning";
        String msg = "Password mismatch error: please verify entered password.";
        showAlert(Alert.AlertType.WARNING, title, null, msg);
    }

    // Display an alert when there are empty fields
    public void displayEmptyFields() {
        String title = "Warning";
        String msg = "Please enter the following fields to reset your password:\n";
        if (currentPasswordField.getText().isEmpty()) msg += "\tcurrent password\n";
        if (passwordField.getText().isEmpty()) msg += "\tpassword\n";
        if (confirmPasswordField.getText().isEmpty()) msg += "\tconfirm password\n";
        showAlert(Alert.AlertType.WARNING, title, null, msg);
    }

    // Helper method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }
}
