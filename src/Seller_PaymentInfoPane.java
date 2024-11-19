import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Seller_PaymentInfoPane extends BorderPane {

    private Button backButton;
    private TextField cardnumberField;
    private TextField expDateField;
    private TextField cvcField;
    private Button confirmButton;

    public Seller_PaymentInfoPane(User user, double width, double height) {

        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);

        // set attributes for the image to be used for the back button
        ImageView backImageView = new ImageView(Main.backIcon);
        backImageView.setFitWidth(40);
        backImageView.setFitHeight(40);

        // set attributes for the back button and add the back icon
        backButton = new Button();
        backButton.setGraphic(backImageView);
        backButton.setBackground(null);

        // set attributes for the Title Label
        Label titleLabel = new Label("Payment Info");
        titleLabel.setFont(titleFont);

        // set attributes for the header of the main page
        HBox headerHBox = new HBox(backButton,titleLabel);
        headerHBox.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the instructions label
        Label instructionLabel = new Label("Your payment information.");
        instructionLabel.setFont(Font.font("Arial", FontWeight.NORMAL,18));

        // set attributes for the first name label
        Label cardnumberLabel = new Label("Card Number:");
        cardnumberLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the first name text field
        cardnumberField = new TextField();
        cardnumberField.setPromptText("Card Number");
        cardnumberField.setEditable(true);
        cardnumberField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // Set maximum character limit to 5
        TextFormatter<String> cardFormatter = new TextFormatter<>(change ->
                change.getControlNewText().length() <= 19 ? change : null
        );

        // Apply the formatter to the text field
        cardnumberField.setTextFormatter(cardFormatter);

        // add the card number stuff
        HBox cardnumberHBox = new HBox(cardnumberLabel, cardnumberField);
        cardnumberHBox.setAlignment(Pos.CENTER_RIGHT);
        cardnumberHBox.setSpacing(10);

        Label expDateLabel = new Label("Exp Date:");
        expDateLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        expDateField = new TextField();
        expDateField.setPromptText("MM/YYYY");
        expDateField.setEditable(true);
        expDateField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // Set maximum character limit to 5
        TextFormatter<String> expdateFormatter = new TextFormatter<>(change ->
                change.getControlNewText().length() <= 7 ? change : null
        );

        // Apply the formatter to the text field
        expDateField.setTextFormatter(expdateFormatter);

        HBox expDateHBox = new HBox(expDateLabel, expDateField);
        expDateHBox.setAlignment(Pos.CENTER_RIGHT);
        expDateHBox.setSpacing(10);

        Label cvcLabel = new Label("CVC:");
        cvcLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the email text field
        cvcField = new TextField();
        cvcField.setPromptText("CVC");
        cvcField.setEditable(true);
        cvcField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // Set maximum character limit to 3
        TextFormatter<String> cvcFormatter = new TextFormatter<>(change ->
                change.getControlNewText().length() <= 3 ? change : null
        );

        // Apply the formatter to the text field
        cvcField.setTextFormatter(cvcFormatter);

        // add the email label and email text field into this hbox
        HBox cvcHBox = new HBox(cvcLabel, cvcField);
        cvcHBox.setAlignment(Pos.CENTER_RIGHT);
        cvcHBox.setSpacing(10);

        confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font(16));
        confirmButton.setPrefHeight(35);
        confirmButton.setPrefWidth(150);

        // add the grey box elements which includes the instructions label and all HBoxes
        VBox greyVBox = new VBox(instructionLabel, cardnumberHBox, expDateHBox, cvcHBox, confirmButton);
        // readjust the width of the HBoxes inside the greyVBox
        VBox.setMargin(cardnumberHBox, new Insets(0,450,0,0));
        VBox.setMargin(expDateHBox, new Insets(0,450,0,0));
        VBox.setMargin(cvcHBox, new Insets(0,450,0,0));
        VBox.setMargin(confirmButton, new Insets(0,0,0,200));
        greyVBox.setSpacing(20);
        greyVBox.setPrefHeight(height-160);
        greyVBox.setPadding(new Insets(20,20,20,20));
        greyVBox.setStyle(
                "-fx-background-radius: 2em;" +
                        "-fx-background-color: #D9D9D9;"
        );

        // set attributes for the main VBox which excludes the navigation bar
        VBox mainVBox = new VBox(headerHBox,greyVBox);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setSpacing(10);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        this.setCenter(mainVBox);
        BorderPane.setMargin(mainVBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon color
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public boolean emptyFields() {

        if(cardnumberField.getText().isEmpty()) {
            return true;
        }
        if(expDateField.getText().isEmpty()) {
            return true;
        }
        return cvcField.getText().isEmpty();
    }

    // Method to validate the card details
    public boolean validateCardDetails(TextField cardNumberField, TextField expirationDateField, TextField cvcField) {
        String cardNumber = cardNumberField.getText().trim();
        String expirationDate = expirationDateField.getText().trim();
        String cvc = cvcField.getText().trim();

        // Validate Card Number
        if (!validateCardNumber(cardNumber)) {

            return false;
        }

        // Validate Expiration Date
        if (!validateExpirationDate(expirationDate)) {

            return false;
        }

        // Validate CVC
        if (!validateCVC(cvc)) {

            return false;
        }

        // If all validations pass
        return true;
    }

    // Validate Card Number (13 to 19 digits)
    public boolean validateCardNumber(String cardNumber) {
        Pattern pattern = Pattern.compile("^[0-9]{13,19}$");  // Must be between 13 to 19 digits
        Matcher matcher = pattern.matcher(cardNumber);
        return matcher.matches();
    }

    // Validate Expiration Date (MM/YY or MM/YYYY format)
    public boolean validateExpirationDate(String expirationDate) {
        // Check if the expiration date is in MM/YY or MM/YYYY format
        Pattern pattern = Pattern.compile("^(0[1-9]|1[0-2])/(\\d{2}|\\d{4})$");
        Matcher matcher = pattern.matcher(expirationDate);

        if (!matcher.matches()) {
            return false; // Invalid format
        }

        // Extract month and year
        String[] dateParts = expirationDate.split("/");
        int month = Integer.parseInt(dateParts[0]);
        int year = Integer.parseInt(dateParts[1]);

        // If the year is 2 digits, convert to full year (e.g., 23 -> 2023)
        if (year < 100) {
            year += 2000;
        }

        // Check if the expiration date is in the future
        LocalDate currentDate = LocalDate.now();
        LocalDate expiration = LocalDate.of(year, month, 1).withDayOfMonth(1).plusMonths(1).minusDays(1); // End of the month

        return !expiration.isBefore(currentDate);
    }

    // Validate CVC (3 digits for most cards, 4 digits for American Express)
    public boolean validateCVC(String cvc) {
        Pattern pattern = Pattern.compile("^[0-9]{3,4}$");  // CVC is 3 or 4 digits
        Matcher matcher = pattern.matcher(cvc);
        return matcher.matches();
    }

    public boolean isCardValid() {

        return validateCardNumber(cardnumberField.getText()) &&
                validateExpirationDate(expDateField.getText()) &&
                validateCVC(cvcField.getText());

    }

    public void displayInValidCard() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        String msg = "Your payment method was not accepted.";

        alert.setTitle("Payment Failed");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();

    }

    public void displayEmptyFields() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        String missingData = "Please enter the following fields to create your account:\n";

        if(cardnumberField.getText().isEmpty())
            missingData += "\tCard number\n";
        if(expDateField.getText().isEmpty())
            missingData += "\tExp date\n";
        if(cvcField.getText().isEmpty())
            missingData += "\tCVC\n";

        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(missingData);
        alert.show();
    }

    public void displayPaymentUpdated() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String msg = "Your payment method has been saved.";

        alert.setTitle("Payment Saved Successfully");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }

}




