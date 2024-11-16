import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Seller_MyBooksPane extends BorderPane {

    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8;
    private RadioButton rb1,rb2;
    private ToggleGroup toggleGroupPrices;
    private Button resetfiltersButton;
    private TextField searchField;
    private Button searchButton;

    private GridPane booksPane;

    public Seller_MyBooksPane(User user, double width, double height) {

        Seller_NavigationControl navBarVBox = new Seller_NavigationControl(user,width,height);

        // set attributes for the Title Label
        Label titleLabel = new Label("My Books");
        titleLabel.setFont(Font.font(42));

        // set attributes for the header of the main page
        HBox headerHBox = new HBox(titleLabel);
        headerHBox.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for instruction label
        Label instructionLabel1 = new Label("Please use the filters on the left and/or search bar to find your book.");

        // set attributes for category label
        Label categoryLabel = new Label("Category");

        // set attributes for category check boxes
        ch1 = new CheckBox("Natural Science");
        ch2 = new CheckBox("Computer Science");
        ch3 = new CheckBox("Math");
        ch4 = new CheckBox("English");
        ch5 = new CheckBox("Other");

        // set attributes for the VBox that holds these items
        VBox vbox1 = new VBox(categoryLabel,ch1,ch2,ch3,ch4,ch5);
        vbox1.setSpacing(2);

        // set attributes for the condition label
        Label conditionLabel = new Label("Condition");

        // set attributes for condition check boxes
        ch6 = new CheckBox("Used Like New");
        ch7 = new CheckBox("Moderately Used");
        ch8 = new CheckBox("Heavily Used");

        // set attributes for the VBox that holds these items
        VBox vbox2 = new VBox(conditionLabel,ch6,ch7,ch8);
        vbox2.setSpacing(2);

        // set attribute for label
        Label l1 = new Label("Prices");

        // set attributes for toggle buttons
        rb1 = new RadioButton("Ascending");
        rb2 = new RadioButton("Descending");

        toggleGroupPrices = new ToggleGroup();
        toggleGroupPrices.getToggles().addAll(rb1,rb2);

        // set attributes for VBox
        VBox vbox3 = new VBox(l1,rb1,rb2);
        vbox3.setSpacing(2);

        // set attributes for label
        Label resetLabel = new Label("Reset Filters");

        // set attributes for button
        resetfiltersButton = new Button("reset");

        // set attributes for VBox
        VBox vbox4 = new VBox(resetLabel,resetfiltersButton);
        vbox4.setSpacing(2);

        // set atributes for the VBox that holds all Left side items
        VBox leftVBox = new VBox(vbox1,vbox2,vbox3,vbox4);
        leftVBox.setSpacing(10);

        // set attributes for text field
        searchField = new TextField();
        searchField.setPrefWidth(400);
        searchField.setPrefHeight(20);
        searchField.setPromptText("Enter a title or author to search...");

        // Create a Button to trigger the search action
        searchButton = new Button("Search");
        searchButton.setPrefHeight(20);

        // Layout the TextField and Button in an HBox
        HBox searchbarHBox = new HBox(searchField, searchButton);
        searchbarHBox.setSpacing(10);

        // set attributes for the VBox that holds the lists of users
        booksPane = new GridPane();
        booksPane.setAlignment(Pos.TOP_CENTER);
        booksPane.setHgap(10);
        booksPane.setVgap(10);
        booksPane.setPadding(new Insets(10,10,10,10));

        // set attributes for the scrollpane
        ScrollPane sp = new ScrollPane(booksPane);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.minViewportHeightProperty().set(400);
        sp.minViewportWidthProperty().set(460);
        sp.setPrefWidth(460);
        sp.setPrefHeight(400);
        sp.setStyle("-fx-background: white;");

        VBox rightVBox = new VBox(searchbarHBox,sp);
        rightVBox.setSpacing(10);

        BorderPane mainBorderPane = new BorderPane();
        mainBorderPane.setLeft(leftVBox);
        mainBorderPane.setRight(rightVBox);
//        HBox mainHBox = new HBox(leftVBox,rightVBox);
//        mainHBox.setSpacing(15);

        // set attributes for the main VBox
        VBox mainVBox = new VBox(headerHBox,mainBorderPane);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setPrefWidth(width - navBarVBox.getWidth() - 40);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and the main VBox
        HBox navBarAndMainHBox = new HBox(navBarVBox,mainVBox);
        navBarAndMainHBox.setSpacing(20);

        this.setCenter(navBarAndMainHBox);
        BorderPane.setMargin(navBarAndMainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon color

    }

    public GridPane getBooksPane() {
        return booksPane;
    }

    public void setBooksPane(GridPane booksPane) {
        this.booksPane = booksPane;
    }
}
