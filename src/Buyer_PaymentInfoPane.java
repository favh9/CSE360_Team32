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

public class Buyer_PaymentInfoPane extends BorderPane {

    // UI Components
    private Button backButton;
    private TextField nameOnCardField;
    private TextField cardnumberField;
    private TextField expDateField;
    private TextField cvcField;
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
    public Buyer_PaymentInfoPane(User user, double width, double height) {

        // Title Font Setup
        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);

        // Back Button setup with icon
        ImageView backImageView = new ImageView(Main.backIcon);  // Assuming Main.backIcon is predefined
        backImageView.setFitWidth(40);
        backImageView.setFitHeight(40);
        backButton = new Button();
        backButton.setGraphic(backImageView);
        backButton.setBackground(null);

        // Title Label setup
        Label titleLabel = new Label("Payment Info");
        titleLabel.setFont(titleFont);

        // Header Box for the title and back button
        HBox headerHBox = new HBox(backButton, titleLabel);
        headerHBox.setAlignment(Pos.BASELINE_LEFT);

        // Instruction Label setup
        Label instructionLabel = new Label("Your payment information.");
        instructionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));

        // Personal Info Setup
        Label nameOnCardLabel = new Label("Name on Card:");
        nameOnCardLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        nameOnCardField = new TextField();
        nameOnCardField.setPromptText("Name on Card");
        nameOnCardField.setEditable(true);
        nameOnCardField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // Horizontal Box for Name on Card label and field
        HBox nameOnCardHBox = new HBox(nameOnCardLabel, nameOnCardField);
        nameOnCardHBox.setAlignment(Pos.CENTER_RIGHT);
        nameOnCardHBox.setSpacing(10);

        // Card Number Setup
        Label cardnumberLabel = new Label("Card Number:");
        cardnumberLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        cardnumberField = new TextField();
        cardnumberField.setPromptText("Card Number");
        cardnumberField.setEditable(true);
        cardnumberField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // TextFormatter to restrict character length for card number field
        TextFormatter<String> cardFormatter = new TextFormatter<>(change ->
                change.getControlNewText().length() <= 19 ? change : null);
        cardnumberField.setTextFormatter(cardFormatter);

        // Horizontal Box for Card Number label and field
        HBox cardnumberHBox = new HBox(cardnumberLabel, cardnumberField);
        cardnumberHBox.setAlignment(Pos.CENTER_RIGHT);
        cardnumberHBox.setSpacing(10);

        // Expiration Date Setup
        Label expDateLabel = new Label("Exp Date:");
        expDateLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        expDateField = new TextField();
        expDateField.setPromptText("MM/YYYY");
        expDateField.setEditable(true);
        expDateField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // TextFormatter for expiration date to restrict input to 7 characters
        TextFormatter<String> expdateFormatter = new TextFormatter<>(change ->
                change.getControlNewText().length() <= 7 ? change : null);
        expDateField.setTextFormatter(expdateFormatter);

        // Horizontal Box for Expiration Date label and field
        HBox expDateHBox = new HBox(expDateLabel, expDateField);
        expDateHBox.setAlignment(Pos.CENTER_RIGHT);
        expDateHBox.setSpacing(10);

        // CVC Setup
        Label cvcLabel = new Label("CVC:");
        cvcLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        cvcField = new TextField();
        cvcField.setPromptText("CVC");
        cvcField.setEditable(true);
        cvcField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        // TextFormatter for CVC to restrict input to 3 characters
        TextFormatter<String> cvcFormatter = new TextFormatter<>(change ->
                change.getControlNewText().length() <= 3 ? change : null);
        cvcField.setTextFormatter(cvcFormatter);

        // Horizontal Box for CVC label and field
        HBox cvcHBox = new HBox(cvcLabel, cvcField);
        cvcHBox.setAlignment(Pos.CENTER_RIGHT);
        cvcHBox.setSpacing(10);

        // Confirm Button Setup
        confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font(16));
        confirmButton.setPrefHeight(35);
        confirmButton.setPrefWidth(150);

        // Vertical Box to contain all the fields and the confirm button
        VBox greyVBox = new VBox(instructionLabel, nameOnCardHBox, cardnumberHBox, expDateHBox, cvcHBox, confirmButton);
        VBox.setMargin(nameOnCardHBox, new Insets(0,450,0,0));
        VBox.setMargin(cardnumberHBox, new Insets(0, 450, 0, 0));  // Adjust spacing
        VBox.setMargin(expDateHBox, new Insets(0, 450, 0, 0));
        VBox.setMargin(cvcHBox, new Insets(0, 450, 0, 0));
        VBox.setMargin(confirmButton, new Insets(0, 0, 0, 200)); // Center the confirm button
        greyVBox.setSpacing(20);
        greyVBox.setPrefHeight(height - 160);  // Adjust height
        greyVBox.setPadding(new Insets(20, 20, 20, 20));
        greyVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #D9D9D9;");

        // Main VBox that holds the header and grey VBox
        VBox mainVBox = new VBox(headerHBox, greyVBox);
        mainVBox.setPadding(new Insets(40, 40, 0, 40));
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
        return cardnumberField.getText().isEmpty() || expDateField.getText().isEmpty() || cvcField.getText().isEmpty();
    }

    // Method to validate the card details
    public boolean validateCardDetails(TextField cardNumberField, TextField expirationDateField, TextField cvcField) {
        String cardNumber = cardNumberField.getText().trim();
        String expirationDate = expirationDateField.getText().trim();
        String cvc = cvcField.getText().trim();

        // Validate Card Number, Expiration Date, and CVC
        return validateCardNumber(cardNumber) && validateExpirationDate(expirationDate) && validateCVC(cvc);
    }

    // Validate Card Number (between 13 to 19 digits)
    public boolean validateCardNumber(String cardNumber) {
        Pattern pattern = Pattern.compile("^[0-9]{13,19}$");
        Matcher matcher = pattern.matcher(cardNumber);
        return matcher.matches();
    }

    // Validate Expiration Date (MM/YY or MM/YYYY format)
    public boolean validateExpirationDate(String expirationDate) {
        // Check the format and that the expiration date is not in the past
        Pattern pattern = Pattern.compile("^(0[1-9]|1[0-2])/(\\d{2}|\\d{4})$");
        Matcher matcher = pattern.matcher(expirationDate);

        if (!matcher.matches()) {
            return false;  // Invalid format
        }

        // Extract month and year
        String[] dateParts = expirationDate.split("/");
        int month = Integer.parseInt(dateParts[0]);
        int year = Integer.parseInt(dateParts[1]);

        // Convert 2-digit year to 4-digit year
        if (year < 100) {
            year += 2000;
        }

        // Check if the expiration date is in the future
        LocalDate currentDate = LocalDate.now();
        LocalDate expiration = LocalDate.of(year, month, 1).withDayOfMonth(1).plusMonths(1).minusDays(1);  // End of the month

        return !expiration.isBefore(currentDate);
    }

    // Validate CVC (3 or 4 digits)
    public boolean validateCVC(String cvc) {
        Pattern pattern = Pattern.compile("^[0-9]{3,4}$");  // CVC is 3 or 4 digits
        Matcher matcher = pattern.matcher(cvc);
        return matcher.matches();
    }

    // Method to check if the card details are valid
    public boolean isCardValid() {
        return validateCardNumber(cardnumberField.getText()) &&
                validateExpirationDate(expDateField.getText()) &&
                validateCVC(cvcField.getText());
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

    // Display an alert when the card is invalid
    public void displayInValidCard() {
        String title = "Payment Failed";
        String msg = "Your payment method was not saved."; // Message for invalid payment method
        displayAlert(Alert.AlertType.WARNING, title, msg); // Call helper method to display alert
    }

    // Display an alert when some fields are empty
    public void displayEmptyFields() {
        String title = "Warning";
        String msg = "Please enter the following fields to complete your payment information:\n";

        // Check which fields are empty and add them to the message
        if (nameOnCardField.getText().isEmpty())
            msg += "\tName on Card\n";
        if (cardnumberField.getText().isEmpty())
            msg += "\tCard number\n";
        if (expDateField.getText().isEmpty())
            msg += "\tExp date\n";
        if (cvcField.getText().isEmpty())
            msg += "\tCVC\n";

        // Call the helper method to display the warning alert
        displayAlert(Alert.AlertType.WARNING, title, msg);
    }

    // Display an alert when the payment method has been successfully updated
    public void displayPaymentUpdated() {
        String title = "Payment Saved Successfully";
        String msg = "Your payment method has been saved."; // Message indicating successful update
        displayAlert(Alert.AlertType.INFORMATION, title, msg); // Call helper method to display success alert
    }

    // Getter for nameOnCardField
    public TextField getNameOnCardField() {
        return nameOnCardField;
    }

    // Getter for cardnumberField
    public TextField getCardNumberField() {
        return cardnumberField;
    }

    // Getter for expDateField
    public TextField getExpDateField() {
        return expDateField;
    }

    // Getter for cvcField
    public TextField getCvcField() {
        return cvcField;
    }

    public void setNameOnCardField(String name) {
        nameOnCardField.setText(name);
    }

    public void setCardNumberField(String cardNumber) {
        cardnumberField.setText(cardNumber);
    }

    public void setExpDateField(String expDate) {
        expDateField.setText(expDate);
    }

    public void setCvcField(String cvc) {
        cvcField.setText(cvc);
    }



}




