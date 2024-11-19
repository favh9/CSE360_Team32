import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Seller_PaymentInfoPane extends BorderPane {

    private Button backButton;
    private TextField cardnumberField;
    private TextField expDateField;
    private TextField cvcField;

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

        // add the card number stuff
        HBox cardnumberHBox = new HBox(cardnumberLabel, cardnumberField);
        cardnumberHBox.setAlignment(Pos.CENTER_RIGHT);
        cardnumberHBox.setSpacing(10);

        Label expDateLabel = new Label("Exp Date:");
        expDateLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        expDateField = new TextField();
        expDateField.setPromptText("Exp Date");
        expDateField.setEditable(true);
        expDateField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

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

        // add the email label and email text field into this hbox
        HBox cvcHBox = new HBox(cvcLabel, cvcField);
        cvcHBox.setAlignment(Pos.CENTER_RIGHT);
        cvcHBox.setSpacing(10);

        // add the grey box elements which includes the instructions label and all HBoxes
        VBox greyVBox = new VBox(instructionLabel, cardnumberHBox, expDateHBox, cvcHBox);
        // readjust the width of the HBoxes inside the greyVBox
        VBox.setMargin(cardnumberHBox, new Insets(0,350,0,0));
        VBox.setMargin(expDateHBox, new Insets(0,350,0,0));
        VBox.setMargin(cvcHBox, new Insets(0,350,0,0));
        greyVBox.setSpacing(20);
        greyVBox.setPrefHeight(height-160);
        greyVBox.setPadding(new Insets(20,20,20,20));
        greyVBox.setStyle(
                "-fx-background-radius: 2em;" +
                        "-fx-background-color: #D9D9D9;"
        );

        // set attributes for the main VBox which excludes the navigation bar
        VBox mainVBox = new VBox(headerHBox);
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

}




