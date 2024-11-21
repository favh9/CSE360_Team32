import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin_AdjustPricePane extends BorderPane {

    // UI Components
    private Button backButton;
    private static TextField usedLikeNewTextField;
    private static TextField moderatelyUsedTextField;
    private static TextField heavilyUsedTextField;
    private Button confirmButton;

    /**
     * Constructor to initialize the payment information pane.
     * This pane allows the user to input payment details such as card number, expiration date, and CVC.
     * It also sets the size and layout of the pane based on the provided parameters.
     *
     * @param user The user whose payment information will be displayed in the pane. This may include their details.
     * @param width The width of the pane. This defines the horizontal size of the pane.
     * @param height The height of the pane. This defines the vertical size of the pane.
     */
    public Admin_AdjustPricePane(User user, double width, double height) {

        // Title Font Setup
        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);

        // Back Button setup with icon
        ImageView backImageView = new ImageView(Main.backIcon);
        backImageView.setFitWidth(40);
        backImageView.setFitHeight(40);
        backButton = new Button();
        backButton.setGraphic(backImageView);
        backButton.setBackground(null);

        // Title Label setup
        Label titleLabel = new Label("Adjust Price Generators");
        titleLabel.setFont(titleFont);

        // Header Box for the title and back button
        HBox headerHBox = new HBox(backButton, titleLabel);
        headerHBox.setAlignment(Pos.BASELINE_LEFT);

        // Instruction Label setup
        Label instructionLabel = new Label("Set a price adjustment for each condition.");
        instructionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        Label usedLikeNewLabel = new Label("Like New:");
        usedLikeNewLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        usedLikeNewTextField = new TextField();
        usedLikeNewTextField.setPromptText("0.00%");
        usedLikeNewTextField.setEditable(true);
        usedLikeNewTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        HBox usedLikeNewHBox = new HBox(usedLikeNewLabel, usedLikeNewTextField);
        usedLikeNewHBox.setAlignment(Pos.CENTER_RIGHT);
        usedLikeNewHBox.setSpacing(10);

        Label moderatelyUsedLabel = new Label("Moderately Used:");
        moderatelyUsedLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        moderatelyUsedTextField = new TextField();
        moderatelyUsedTextField.setPromptText("0.00%");
        moderatelyUsedTextField.setEditable(true);
        moderatelyUsedTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        HBox moderatelyUsedHBox = new HBox(moderatelyUsedLabel, moderatelyUsedTextField);
        moderatelyUsedHBox.setAlignment(Pos.CENTER_RIGHT);
        moderatelyUsedHBox.setSpacing(10);

        Label heavilyUsedLabel = new Label("Heavily Used:");
        heavilyUsedLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        heavilyUsedTextField = new TextField();
        heavilyUsedTextField.setPromptText("0.00%");
        heavilyUsedTextField.setEditable(true);
        heavilyUsedTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Horizontal Box for Expiration Date label and field
        HBox heavilyUsedHBox = new HBox(heavilyUsedLabel, heavilyUsedTextField);
        heavilyUsedHBox.setAlignment(Pos.CENTER_RIGHT);
        heavilyUsedHBox.setSpacing(10);

        // Confirm Button Setup
        confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font(16));
        confirmButton.setPrefHeight(35);
        confirmButton.setPrefWidth(150);

        // Vertical Box to contain all the fields and the confirm button
        VBox greyVBox = new VBox(instructionLabel, usedLikeNewHBox, moderatelyUsedHBox, heavilyUsedHBox, confirmButton);
        VBox.setMargin(usedLikeNewHBox, new Insets(0,450,0,0));
        VBox.setMargin(moderatelyUsedHBox, new Insets(0, 450, 0, 0));  // Adjust spacing
        VBox.setMargin(heavilyUsedHBox, new Insets(0, 450, 0, 0));
        VBox.setMargin(confirmButton, new Insets(0, 0, 0, 200)); // Center the confirm button
        greyVBox.setSpacing(20);
        greyVBox.setPrefHeight(height - 160);  // Adjust height
        greyVBox.setPadding(new Insets(20, 20, 20, 20));
        greyVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #D9D9D9;");

        // Main VBox that holds the header and grey VBox
        VBox mainVBox = new VBox(headerHBox, greyVBox);
        mainVBox.setPadding(new Insets(40, 40, 40, 40));
        mainVBox.setSpacing(10);
        mainVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #ffffff;");

        // Set the center of the BorderPane
        this.setCenter(mainVBox);
        BorderPane.setMargin(mainVBox, new Insets(20, 20, 20, 20));

        // Set preferred size for the pane
        this.setPrefSize(width, height);
        this.setBackground(Background.fill(Color.web("#4A1E2C")));  // Dark maroon background color
    }

    // Getters for the back and confirm buttons
    public Button getBackButton() {
        return backButton;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    // Method to check if any of the fields are empty
    public boolean emptyFields() {
        return usedLikeNewTextField.getText().isEmpty() || moderatelyUsedTextField.getText().isEmpty() || heavilyUsedTextField.getText().isEmpty();
    }

    public boolean notValid() {
        return (Double.parseDouble(usedLikeNewTextField.getText()) > 100) || (Double.parseDouble(moderatelyUsedTextField.getText()) > 100) || (Double.parseDouble(heavilyUsedTextField.getText()) > 100);
    }

    public static double getUsedLikeNewFee() {
        return Double.parseDouble(usedLikeNewTextField.getText());
    }
    public static double getModeratelyUsedFee() {
        return Double.parseDouble(moderatelyUsedTextField.getText());
    }
    public static double getHeavilyUsedFee() {
        return Double.parseDouble(heavilyUsedTextField.getText());
    }

    // Helper method to display alerts
    private void displayAlert(Alert.AlertType alertType, String title, String message) {
        // Create the alert based on the type
        Alert alert = new Alert(alertType);
        alert.setTitle(title); // Set the alert title
        alert.setHeaderText(null); // No header text
        alert.setContentText(message); // Set the alert content text
        alert.show(); // Display the alert
    }

    // Display an alert when some fields are empty
    public void displayEmptyFields() {
        String title = "Warning";
        String msg = "Please enter the following fields to complete your payment information:\n";

        // Check which fields are empty and add them to the message
        if (usedLikeNewTextField.getText().isEmpty())
            msg += "\tName on Card\n";
        if (moderatelyUsedTextField.getText().isEmpty())
            msg += "\tCard number\n";
        if (heavilyUsedTextField.getText().isEmpty())
            msg += "\tExp date\n";

        // Call the helper method to display the warning alert
        displayAlert(Alert.AlertType.WARNING, title, msg);
    }

    public void displayNotValid() {
        String title = "Warning";
        String msg = "Your Fees can't be more than a 100%\n";

        displayAlert(Alert.AlertType.WARNING, title, msg);
    }

    // Display an alert when the payment method has been successfully updated
    public void displayAdjustmentPricesUpdated() {
        String title = "Adjustment Prices Saved Successfully";
        String msg = "The adjustment prices will now affect all new books that are posted."; // Message indicating successful update
        displayAlert(Alert.AlertType.INFORMATION, title, msg); // Call helper method to display success alert
    }

}




