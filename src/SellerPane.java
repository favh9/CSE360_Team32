import javafx.geometry.Insets;
import javafx.scene.Node;
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

import java.util.Calendar;

public class SellerPane extends BorderPane {

    private TextField tf1;
    private TextField tf2;
    private ComboBox<String> yearComboBox;
    private ChoiceBox<String> categoryChoiceBox;
    private ChoiceBox<String> conditionChoiceBox;
    private TextField tf3;
    private TextField tf4;
    private Button b1;

    public SellerPane(double width, double height) {

        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);
        Font instructionFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16);

        NavigationUserControl navbarVBox = new NavigationUserControl(width,height);


        // set attributes for the Title Label
        Label titleLabel = new Label("Post a Book for Sale");
        titleLabel.setFont(titleFont);

        // set attributes for instruction label
        Label instructionLabel = new Label("Please fill in all the information and click on the \"List My Book\" button " +
                "\nto post your book for sale.");
        instructionLabel.setFont(instructionFont);

        // set attributes for the text field
        tf1 = new TextField();
        tf1.setPromptText("Book Name");
        tf1.setPrefWidth(400);
        HBox hbox1 = new HBox(tf1);

        // set attributes for the text field
        tf2 = new TextField();
        tf2.setPromptText("Author");
        tf2.setPrefWidth(400);
        HBox hbox2 = new HBox(tf2);

        // set attributes for label
        Label yearLabel = new Label("Year:");

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
        yearComboBox.setValue("Select Year");

        VBox yearVBox = new VBox(yearLabel,yearComboBox);

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
        tf3 = new TextField();
        tf3.setPromptText("Price");
        tf3.setPrefWidth(400);
        HBox hbox3 = new HBox(tf3);

        // set attributes for the label
        Label generatedPriceLabel = new Label("The Generated Price is:");

        // set attributes for the text field
        tf4 = new TextField();
        tf4.setPromptText("");
        tf4.setDisable(true);

        // set attributes for the HBox
        HBox generatedPriceHBox = new HBox(generatedPriceLabel,tf4);
        tf4.setPrefWidth(270);
        generatedPriceHBox.setSpacing(5);

        // set attributes for the image
        ImageView image1 = new ImageView(Main.infoIcon);
        image1.setFitHeight(15);
        image1.setFitWidth(15);

        // set attributes for the text
        Text t1 = new Text("Note: The price will be determined by the original price and the book condition");

        // set attributes for the HBox
        HBox infoHBox = new HBox(image1,t1);
        infoHBox.setSpacing(5);

        // set attributes for the VBox
        VBox priceVBox = new VBox(generatedPriceHBox,infoHBox);
        priceVBox.setSpacing(5);

        // set attributes for the button
        b1 = new Button("List My Book");

        // insert user information boxes into this VBox
        VBox infoVBox = new VBox(instructionLabel, hbox1, hbox2, yearComboBox, categoryChoiceBox, conditionChoiceBox, hbox3, priceVBox, b1);
        infoVBox.setSpacing(15);
        infoVBox.setPadding(new Insets(20,20,20,20));
        infoVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #FFC627;"
        );

        // insert all elements into main VBox
        VBox mainVBox = new VBox(titleLabel,infoVBox);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setSpacing(20);
        mainVBox.setPrefWidth(width - navbarVBox.getWidth() - 40);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #FFFFFF;"
        );

        // insert an HBox with the Navigation pane and main page
        HBox mainHBox = new HBox(navbarVBox,mainVBox);
        mainHBox.setSpacing(20);

        // set center of BorderPane
        this.setCenter(mainHBox);

        BorderPane.setMargin(mainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon

    }

    public String getBookName() {
        return tf1.getText();
    }

    public String getAuthorName() {
        return tf2.getText();
    }

    public int getSelectedYear() {
        try {
            return Integer.parseInt(yearComboBox.getValue());
        } catch (NumberFormatException e) {
            System.out.println("User has not selected a year");
            return -1;
        }
    }

    public String getSelectedCategory() {
        return categoryChoiceBox.getValue();
    }

    public String getSelectedCondition() {
        return conditionChoiceBox.getValue();
    }

    public double getOriginalPrice() {

        try {

            return Double.parseDouble(tf3.getText());

        }catch(NumberFormatException e) {
            System.out.println("User has not entered a price");
            return -1.0;
        }
    }

    public Button getListBookButton() {
        return b1;
    }

    public void setGeneratedPrice(double num) {
        tf4.setText(Double.toString(num));
    }
}
