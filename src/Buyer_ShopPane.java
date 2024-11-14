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

    private TextField searchField;
    private Label cartCountLabel;
    private int cartCount;
    private CheckBox ch1,ch2,ch3,ch4,ch5;
    private CheckBox ch6,ch7,ch8;
    private RadioButton rb1,rb2;
    private ToggleGroup toggleGroupPrices;
    private Button cartButton, resetfiltersButton, searchButton;
    private GridPane gp;

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
        cartButton = new Button();
        cartButton.setGraphic(cartImage);
        cartButton.setBackground(Background.fill(Color.TRANSPARENT));

        // add the button to the stack pane
        stackPane.getChildren().add(cartButton);

        // Create the badge circle to display the number of items
        Circle badge = new Circle(12);
        badge.setFill(Color.RED);

        // Create the text to show the item count inside the badge
        cartCount = 0;
        cartCountLabel = new Label(Integer.toString(cartCount));
        Text badgeText = new Text(cartCountLabel.getText());
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

        // set attributes for the title of the scrollpane
        Label spTitle = new Label("Available Books");
        spTitle.setFont(Font.font(20));
        spTitle.setPrefHeight(20);

        // set attributes for text field
        searchField = new TextField();
        searchField.setPrefWidth(310);
        searchField.setPrefHeight(20);
        searchField.setPromptText("Enter a title or author to search...");

        // Create a Button to trigger the search action
        searchButton = new Button("Search");
        searchButton.setPrefHeight(20);

        // Layout the TextField and Button in an HBox
        HBox searchbarHBox = new HBox(searchField, searchButton);
        searchbarHBox.setSpacing(10);

        // set attributes for the HBox
        HBox spHeaderHBox = new HBox(spTitle,searchbarHBox);
        spHeaderHBox.setSpacing(20);

        // set attributes for the container inside the scrollpane
        gp = new GridPane();

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

    public TextField getSearchField() {
        return searchField;
    }

    public Label getCartCountLabel() {
        return cartCountLabel;
    }

    public int getCartCount() {
        return cartCount;
    }

    public CheckBox getCh1() {
        return ch1;
    }

    public CheckBox getCh2() {
        return ch2;
    }

    public CheckBox getCh3() {
        return ch3;
    }

    public CheckBox getCh4() {
        return ch4;
    }

    public CheckBox getCh5() {
        return ch5;
    }

    public CheckBox getCh6() {
        return ch6;
    }

    public CheckBox getCh7() {
        return ch7;
    }

    public CheckBox getCh8() {
        return ch8;
    }

    public ToggleGroup getToggleGroupPrices() {
        return toggleGroupPrices;
    }

    public Button getCartButton() {
        return cartButton;
    }

    public Button getResetfiltersButton() {
        return resetfiltersButton;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public GridPane getGp() {
        return gp;
    }

    public void setSearchField(TextField searchField) {
        this.searchField = searchField;
    }

    public void setCartCountLabel(Label cartCountLabel) {
        this.cartCountLabel = cartCountLabel;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public void setCh1(CheckBox ch1) {
        this.ch1 = ch1;
    }

    public void setCh2(CheckBox ch2) {
        this.ch2 = ch2;
    }

    public void setCh3(CheckBox ch3) {
        this.ch3 = ch3;
    }

    public void setCh4(CheckBox ch4) {
        this.ch4 = ch4;
    }

    public void setCh5(CheckBox ch5) {
        this.ch5 = ch5;
    }

    public void setCh6(CheckBox ch6) {
        this.ch6 = ch6;
    }

    public void setCh7(CheckBox ch7) {
        this.ch7 = ch7;
    }

    public void setCh8(CheckBox ch8) {
        this.ch8 = ch8;
    }

    public void setToggleGroupPrices(ToggleGroup toggleGroupPrices) {
        this.toggleGroupPrices = toggleGroupPrices;
    }

    public void setGp(GridPane gp) {
        this.gp = gp;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public void setResetfiltersButton(Button resetfiltersButton) {
        this.resetfiltersButton = resetfiltersButton;
    }

    public void setCartButton(Button cartButton) {
        this.cartButton = cartButton;
    }

    public void setRb2(RadioButton rb2) {
        this.rb2 = rb2;
    }

    public void setRb1(RadioButton rb1) {
        this.rb1 = rb1;
    }
}
