import javafx.geometry.Insets;
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

    public Seller_PostBookPane(User user, double width, double height) {

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

    public TextField getBooknameField() {
        return booknameField;
    }

    public void setBooknameField(TextField booknameField) {
        this.booknameField = booknameField;
    }

    public TextField getAuthorField() {
        return authorField;
    }

    public void setAuthorField(TextField authorField) {
        this.authorField = authorField;
    }

    public ComboBox<String> getYearComboBox() {
        return yearComboBox;
    }

    public void setYearComboBox(ComboBox<String> yearComboBox) {
        this.yearComboBox = yearComboBox;
    }

    public ChoiceBox<String> getCategoryChoiceBox() {
        return categoryChoiceBox;
    }

    public void setCategoryChoiceBox(ChoiceBox<String> categoryChoiceBox) {
        this.categoryChoiceBox = categoryChoiceBox;
    }

    public ChoiceBox<String> getConditionChoiceBox() {
        return conditionChoiceBox;
    }

    public void setConditionChoiceBox(ChoiceBox<String> conditionChoiceBox) {
        this.conditionChoiceBox = conditionChoiceBox;
    }

    public TextField getPriceField() {
        return priceField;
    }

    public void setPriceField(TextField priceField) {
        this.priceField = priceField;
    }

    public TextField getGeneratedpriceField() {
        return generatedpriceField;
    }

    public void setGeneratedpriceField(TextField generatedpriceField) {
        this.generatedpriceField = generatedpriceField;
    }

    public Button getListmybookButton() {
        return listmybookButton;
    }

    public void setListmybookButton(Button listmybookButton) {
        this.listmybookButton = listmybookButton;
    }

}
