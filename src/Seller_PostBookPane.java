import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Seller_PostBookPane extends BorderPane {

    private Seller_NavigationControl navigationControl;
    private TextField booknameField;
    private TextField authorField;
    private ComboBox<String> yearComboBox;
    private ChoiceBox<String> categoryChoiceBox;
    private ChoiceBox<String> conditionChoiceBox;
    private TextField priceField;
    private TextField generatedpriceField;
    private Button listmybookButton;
    private User user;
    private double width;
    private double height;

    public Seller_PostBookPane(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);
        Font instructionFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16);

        navigationControl = new Seller_NavigationControl(user,width,height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Post a Book for Sale");
        titleLabel.setFont(titleFont);

        // set attributes for instruction label
        Label instructionLabel = new Label("Please fill in all the information and click on the \"List My Book\" button " +
                "\nto post your book for sale.");
        instructionLabel.setFont(instructionFont);

        // set attributes for the text field
        booknameField = new TextField();
        booknameField.setPromptText("Book Name");
        booknameField.setPrefWidth(400);
        HBox booknameHBox = new HBox(booknameField);

        // set attributes for the text field
        authorField = new TextField();
        authorField.setPromptText("Author");
        authorField.setPrefWidth(400);
        HBox authorHBox = new HBox(authorField);

        // set attributes for the box
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
        yearComboBox.setValue("Published Year");

        // set attributes for choice box
        categoryChoiceBox = new ChoiceBox<>();
        categoryChoiceBox.setValue("Select Category");
        categoryChoiceBox.getItems().add("Math");
        categoryChoiceBox.getItems().add("Computer Science");
        categoryChoiceBox.getItems().add("English");
        categoryChoiceBox.getItems().add("Other");

        // set attributes for choice box
        conditionChoiceBox = new ChoiceBox<>();
        conditionChoiceBox.setValue("Select Condition");
        conditionChoiceBox.getItems().add("Like New");
        conditionChoiceBox.getItems().add("Moderately Used");
        conditionChoiceBox.getItems().add("Heavily Used");

        // set attributes for the text field
        priceField = new TextField();
        priceField.setPromptText("Price");
        priceField.setPrefWidth(400);
        HBox priceHBox = new HBox(priceField);

        // set attributes for the text field
        generatedpriceField = new TextField();
        generatedpriceField.setText("Generated Price");
        generatedpriceField.setStyle("-fx-text-fill: #B0B0B0;");
        generatedpriceField.setPrefWidth(400);
        generatedpriceField.setEditable(false);
        HBox generatedpriceHBox = new HBox(generatedpriceField);

        // set attributes for the image
        ImageView image1 = new ImageView(Main.infoIcon); // the 'i' icon
        image1.setFitHeight(15);
        image1.setFitWidth(15);

        // set attributes for the text
        Text infoText = new Text("Note: The price will be determined by the original price and the book condition");

        // set attributes for the HBox
        HBox infoHBox = new HBox(image1,infoText);
        infoHBox.setSpacing(5);

        // set attributes for the VBox
        VBox priceVBox = new VBox(generatedpriceHBox,infoHBox);
        priceVBox.setSpacing(5);

        // set attributes for the button
        listmybookButton = new Button("List My Book");

        // insert user information boxes into this VBox
        VBox infoVBox = new VBox(instructionLabel, booknameHBox, authorHBox, yearComboBox, categoryChoiceBox, conditionChoiceBox, priceHBox, priceVBox, listmybookButton);
        infoVBox.setSpacing(15);
        infoVBox.setPadding(new Insets(20,20,20,20));
        infoVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #FFC627;"
        );

        // insert all elements into main VBox
        VBox mainVBox = new VBox(titleLabel,infoVBox);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setSpacing(20);
        mainVBox.setPrefWidth(width - navigationControl.getWidth() - 40);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #FFFFFF;"
        );

        // insert an HBox with the Navigation pane and main page
        HBox mainHBox = new HBox(navigationControl,mainVBox);
        mainHBox.setSpacing(20);

        // set center of BorderPane
        this.setCenter(mainHBox);

        BorderPane.setMargin(mainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon color

    }

    public Button getListmybookButton() {
        return listmybookButton;
    }

    public boolean emptyFields() {

        if(booknameField.getText().isEmpty()) {
            return true;
        } else if (authorField.getText().isEmpty()) {
            return true;
        } else if(yearComboBox.getValue().equals("Published Year")) {
            return true;
        } else if (categoryChoiceBox.getValue().equals("Select Category")) {
            return true;
        } else if (conditionChoiceBox.getValue().equals("Select Condition")) {
            return true;
        } else return priceField.getText().isEmpty();
    }

    public void displayEmptyFields() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String missingData = "Please check the following fields:\n";

        if(booknameField.getText().isEmpty())
            missingData += "\tBook Name\n";
        if(authorField.getText().isEmpty())
            missingData += "\tAuthor\n";
        if(yearComboBox.getValue().equals("Published Year"))
            missingData += "\tPublished Year\n";
        if(categoryChoiceBox.getValue().equals("Select Category"))
            missingData += "\tCategory\n";
        if(conditionChoiceBox.getValue().equals("Select Condition"))
            missingData += "\tCondition\n";
        if(priceField.getText().isEmpty())
            missingData += "\tPrice\n";

        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(missingData);
        alert.show();
    }

    public boolean isPriceValid() {

        double price;

        try {

            price = Double.parseDouble(priceField.getText());

            if(price <= 0) {
                return false;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid price");
            return false;
        }

        return true;
    }

    public void displayInvalidPrice() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        String msg = "You have entered an invalid price, please input an amount greater than 0.";
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();

    }

    public void displayBookPosted() {

        Alert acctCreatedAlert = new Alert(Alert.AlertType.INFORMATION);
        acctCreatedAlert.setTitle("");
        acctCreatedAlert.setHeaderText("Congratulations!");
        acctCreatedAlert.setContentText("You have posted your book successfully!");

        ImageView confirmImageView = new ImageView(Main.successIcon);
        confirmImageView.setFitHeight(40);
        confirmImageView.setFitWidth(40);

        acctCreatedAlert.setGraphic(confirmImageView);

        // closing pop up behavior
        acctCreatedAlert.setOnCloseRequest(arg0 -> {
            // TODO Auto-generated method stub
            Seller_PostBookControl seller = new Seller_PostBookControl(user, width, height);
            Main.mainWindow.setScene(new Scene(seller));
        });

        acctCreatedAlert.show();
    }






    public double computeGeneratedPrice() {
        int type = 0;
        double generatedPrice;

        if(getCondition().compareTo("Like New") == 0) { type = 1; }
        else if(getCondition().compareTo("Moderately Used") == 0) { type = 2; }
        else if(getCondition().compareTo("Heavily Used") == 0) { type = 3; }

        if(isPriceValid()) {

            generatedPrice = Double.parseDouble(priceField.getText()) - (Double.parseDouble(priceField.getText()) * DataBase.getMarkdown(type));
            generatedpriceField.setText("$"+ generatedPrice);
            return generatedPrice;
        }
        return 0;
    }

    DecimalFormat df = new DecimalFormat("#.##");

    public boolean confirmPost() {

        boolean confirmed = false;
        double fee = 0.20;

        // Create the alert dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        String msg = "You are about to post this book for sale." +
                "\nYou will be charged $" + df.format(computeGeneratedPrice() * fee) +
                "\nYour total profit is $" + df.format(computeGeneratedPrice() - (computeGeneratedPrice() * fee)) +
                "\nPlease confirm whether you would like to continue.";

        alert.setTitle("Confirm Post");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        // Show the alert and wait for the user response
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        // Update the confirmed variable based on the user's choice
        if (result == ButtonType.OK) {
            confirmed = true; // User clicked "Confirm"
        } else if (result == ButtonType.CANCEL) {
            confirmed = false; // User clicked "Cancel"
        }

        return confirmed;
    }


    public String getBookTitle() {
        return booknameField.getText();
    }

    public String getAuthor() {
        return authorField.getText();
    }

    public String getPublishedYear() {
        return yearComboBox.getValue();
    }

    public String getCategory() {
        return categoryChoiceBox.getValue();
    }

    public String getCondition() {
        return conditionChoiceBox.getValue();
    }

    public double getPrice() {
        return Double.parseDouble(priceField.getText());
    }

    public double computeProfit() {
        double fee = 0.20;
        return (computeGeneratedPrice() - (computeGeneratedPrice() * fee));
    }
}