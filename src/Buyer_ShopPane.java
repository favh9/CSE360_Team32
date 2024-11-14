import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Buyer_ShopPane extends BorderPane {


    private Button resetfiltersButton;

    public Buyer_ShopPane(User user, double width, double height) {

        Buyer_NavigationControl navBarVBox = new Buyer_NavigationControl(user,width,height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Shop");
        titleLabel.setFont(Font.font(42));

        // Create a StackPane to hold both the cart image and the badge
        StackPane stackPane = new StackPane();

        // add the cart image
        ImageView cartImage = new ImageView(Main.cartIcon);
        cartImage.setFitHeight(45);
        cartImage.setFitWidth(45);

        // add the cart button
        Button cartButton = new Button();
        cartButton.setGraphic(cartImage);
        cartButton.setBackground(Background.fill(Color.TRANSPARENT));

        // add the button to the stack pane
        stackPane.getChildren().add(cartButton);

        // Create the badge circle to display the number of items
        Circle badge = new Circle(12);
        badge.setFill(Color.RED);

        // Create the text to show the item count inside the badge
        Text badgeText = new Text("0");
        badgeText.setFill(Color.WHITE);
        badgeText.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        // Set the elements in stack pane position
        StackPane.setAlignment(badge, Pos.TOP_RIGHT); // This positions the badge at the top-right of the image
        StackPane.setAlignment(badgeText, Pos.TOP_RIGHT);
        badgeText.setTranslateX(-8);
        badgeText.setTranslateY(4);

        // Add the badge and the text to the StackPane
        stackPane.getChildren().addAll(badge, badgeText);


        // set attributes for the header of the main page
        HBox headerHBox = new HBox(titleLabel, stackPane);
        headerHBox.setAlignment(Pos.CENTER_LEFT);
        headerHBox.setSpacing(530);
        HBox.setMargin(stackPane, new Insets(5,0,0,0));

        // set attributes for instruction label
        Label instructionLabel1 = new Label("Please use the filters on the left and/or search bar to find your book.");

        // set attributes for category label
        Label categoryLabel = new Label("Category");

        // set attributes for category check boxes
        CheckBox ch1 = new CheckBox("Natural Science");
        CheckBox ch2 = new CheckBox("Computer Science");
        CheckBox ch3 = new CheckBox("Math");
        CheckBox ch4 = new CheckBox("English");
        CheckBox ch5 = new CheckBox("Other");

        // set attributes for the VBox that holds these items
        VBox vbox1 = new VBox(categoryLabel,ch1,ch2,ch3,ch4,ch5);
        vbox1.setSpacing(2);

        // set attributes for the condition label
        Label conditionLabel = new Label("Condition");

        // set attributes for condition check boxes
        CheckBox ch6 = new CheckBox("Used Like New");
        CheckBox ch7 = new CheckBox("Moderately Used");
        CheckBox ch8 = new CheckBox("Heavily Used");

        // set attributes for the VBox that holds these items
        VBox vbox2 = new VBox(conditionLabel,ch6,ch7,ch8);
        vbox2.setSpacing(2);

        // set attribute for label
        Label l1 = new Label("Prices");

        // set attributes for toggle buttons
        RadioButton rb1 = new RadioButton("Ascending");
        RadioButton rb2 = new RadioButton("Descending");

        ToggleGroup toggleGroupPrices = new ToggleGroup();
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

        // set attributes for the title of the scrollpane
        Label spTitle = new Label("Available Books");
        spTitle.setFont(Font.font(20));
        spTitle.setPrefHeight(20);

        // set attributes for text field
        TextField searchField = new TextField();
        searchField.setPrefWidth(310);
        searchField.setPrefHeight(20);
        searchField.setPromptText("Enter a title or author to search...");

        // Create a Button to trigger the search action
        Button searchButton = new Button("Search");
        searchButton.setPrefHeight(20);

        // Layout the TextField and Button in an HBox
        HBox searchbarHBox = new HBox(searchField, searchButton);
        searchbarHBox.setSpacing(10);

        // set attributes for the HBox
        HBox spHeaderHBox = new HBox(spTitle,searchbarHBox);
        spHeaderHBox.setSpacing(20);

        // set attributes for the container inside the scrollpane
        GridPane gp = new GridPane();

        // set attributes for the scrollpane
        ScrollPane sp = new ScrollPane(gp);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.minViewportHeightProperty().set(350);
        sp.minViewportWidthProperty().set(525);
        sp.setStyle("-fx-background: white;");

        // set attributes for the VBox that contains these items on the right
        VBox rightVBox = new VBox(spHeaderHBox,sp);
        rightVBox.setSpacing(10);

        // set attributes for the HBox where the right side displays the list of books
        HBox hbox = new HBox(leftVBox,rightVBox);
        hbox.setSpacing(20);

        // set attributes for the submain VBox
        VBox submainVBox = new VBox(instructionLabel1,hbox);
        submainVBox.setSpacing(20);
        submainVBox.setPadding(new Insets(10,10,10,10));
        submainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;" //ffffff white //FFC627 asu yellow
        );

        // set attributes for the main VBox
        VBox mainVBox = new VBox(headerHBox,submainVBox);
        mainVBox.setSpacing(5);
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
}
