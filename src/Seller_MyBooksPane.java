import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Seller_MyBooksPane extends BorderPane {

    private CheckBox naturalscienceCheckBox,computerscienceCheckBox,mathCheckBox,englishCheckBox,otherCheckBox,likenewCheckBox,moderatelyusedCheckBox,heavilyusedCheckBox;
    private RadioButton ascendingRadioButton,descendingRadioButton;
    private ToggleGroup toggleGroupPrices;
    private Button resetfiltersButton;
    private TextField searchField;
    private Button searchButton;
    private ScrollPane sp;
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
        Label categoryLabel = new Label("By Category:");

        // set attributes for category check boxes
        naturalscienceCheckBox = new CheckBox("Natural Science");
        naturalscienceCheckBox.setWrapText(true);
        computerscienceCheckBox = new CheckBox("Computer Science");
        computerscienceCheckBox.setWrapText(true);
        mathCheckBox = new CheckBox("Math");
        mathCheckBox.setWrapText(true);
        englishCheckBox = new CheckBox("English");
        englishCheckBox.setWrapText(true);
        otherCheckBox = new CheckBox("Other");
        otherCheckBox.setWrapText(true);

        // set attributes for the VBox that holds these items
        VBox vbox1 = new VBox(categoryLabel,naturalscienceCheckBox,computerscienceCheckBox,mathCheckBox,englishCheckBox,otherCheckBox);
        vbox1.setSpacing(2);

        // set attributes for the condition label
        Label conditionLabel = new Label("By Condition:");

        // set attributes for condition check boxes
        likenewCheckBox = new CheckBox("Used Like New");
        likenewCheckBox.setWrapText(true);
        moderatelyusedCheckBox = new CheckBox("Moderately Used");
        moderatelyusedCheckBox.setWrapText(true);
        heavilyusedCheckBox = new CheckBox("Heavily Used");
        heavilyusedCheckBox.setWrapText(true);

        // set attributes for the VBox that holds these items
        VBox vbox2 = new VBox(conditionLabel,likenewCheckBox,moderatelyusedCheckBox,heavilyusedCheckBox);
        vbox2.setSpacing(2);

        // set attribute for label
        Label l1 = new Label("Prices");

        // set attributes for toggle buttons
        ascendingRadioButton = new RadioButton("Ascending");
        descendingRadioButton = new RadioButton("Descending");

        toggleGroupPrices = new ToggleGroup();
        toggleGroupPrices.getToggles().addAll(ascendingRadioButton,descendingRadioButton);

        // set attributes for VBox
        VBox vbox3 = new VBox(l1,ascendingRadioButton,descendingRadioButton);
        vbox3.setSpacing(2);

        // set attributes for label
        Label resetLabel = new Label("Reset Filters");

        // set attributes for button
        resetfiltersButton = new Button("clear");

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
        booksPane = new GridPane();
        booksPane.setHgap(10);
        booksPane.setVgap(10);
        booksPane.setPadding(new Insets(10,10,10,10));
        booksPane.setStyle(
                "-fx-background-color: #FFC627;" + "-fx-border-color: #FFC627;"//ffffff white //FFC627 asu yellow
        );

        // set attributes for the scrollpane
        sp = new ScrollPane(booksPane);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.minViewportHeightProperty().set(280);
        sp.minViewportWidthProperty().set(525);
        sp.setStyle("-fx-background: #FFC627;" + "-fx-border-color: #FFC627;");

        // set attributes for the VBox that contains these items on the right
        VBox rightVBox = new VBox(spHeaderHBox,sp);

        BorderPane hbox = new BorderPane();
        hbox.setLeft(leftVBox);
        hbox.setRight(rightVBox);

        // set attributes for the submain VBox
        VBox submainVBox = new VBox(instructionLabel1,hbox);
        submainVBox.setSpacing(20);
        submainVBox.setPadding(new Insets(10,10,10,10));
        submainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #FFC627;" //ffffff white //FFC627 asu yellow
        );

        // set attributes for the main VBox
        VBox mainVBox = new VBox(headerHBox,submainVBox);
        mainVBox.setSpacing(5);
        mainVBox.setPadding(new Insets(40,40,40,40));
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

    public void noBooksFound() {

        BorderPane bp = new BorderPane();
        Label noBooksFoundLabel = new Label("No Books Found");
        noBooksFoundLabel.setFont(Font.font(48));
        bp.setCenter(noBooksFoundLabel);
        bp.setPadding(new Insets(100));
        sp.setContent(bp);

    }

    public CheckBox getNaturalscienceCheckBox() {
        return naturalscienceCheckBox;
    }

    public void setNaturalscienceCheckBox(CheckBox naturalscienceCheckBox) {
        this.naturalscienceCheckBox = naturalscienceCheckBox;
    }

    public CheckBox getComputerscienceCheckBox() {
        return computerscienceCheckBox;
    }

    public void setComputerscienceCheckBox(CheckBox computerscienceCheckBox) {
        this.computerscienceCheckBox = computerscienceCheckBox;
    }

    public CheckBox getMathCheckBox() {
        return mathCheckBox;
    }

    public void setMathCheckBox(CheckBox mathCheckBox) {
        this.mathCheckBox = mathCheckBox;
    }

    public CheckBox getEnglishCheckBox() {
        return englishCheckBox;
    }

    public void setEnglishCheckBox(CheckBox englishCheckBox) {
        this.englishCheckBox = englishCheckBox;
    }

    public CheckBox getOtherCheckBox() {
        return otherCheckBox;
    }

    public void setOtherCheckBox(CheckBox otherCheckBox) {
        this.otherCheckBox = otherCheckBox;
    }

    public CheckBox getLikenewCheckBox() {
        return likenewCheckBox;
    }

    public void setLikenewCheckBox(CheckBox likenewCheckBox) {
        this.likenewCheckBox = likenewCheckBox;
    }

    public CheckBox getModeratelyusedCheckBox() {
        return moderatelyusedCheckBox;
    }

    public void setModeratelyusedCheckBox(CheckBox moderatelyusedCheckBox) {
        this.moderatelyusedCheckBox = moderatelyusedCheckBox;
    }

    public CheckBox getHeavilyusedCheckBox() {
        return heavilyusedCheckBox;
    }

    public void setHeavilyusedCheckBox(CheckBox heavilyusedCheckBox) {
        this.heavilyusedCheckBox = heavilyusedCheckBox;
    }

    public RadioButton getAscendingRadioButton() {
        return ascendingRadioButton;
    }

    public void setAscendingRadioButton(RadioButton ascendingRadioButton) {
        this.ascendingRadioButton = ascendingRadioButton;
    }

    public RadioButton getDescendingRadioButton() {
        return descendingRadioButton;
    }

    public void setDescendingRadioButton(RadioButton descendingRadioButton) {
        this.descendingRadioButton = descendingRadioButton;
    }

    public ToggleGroup getToggleGroupPrices() {
        return toggleGroupPrices;
    }

    public void setToggleGroupPrices(ToggleGroup toggleGroupPrices) {
        this.toggleGroupPrices = toggleGroupPrices;
    }

    public Button getResetfiltersButton() {
        return resetfiltersButton;
    }

    public void setResetfiltersButton(Button resetfiltersButton) {
        this.resetfiltersButton = resetfiltersButton;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public void setSearchField(TextField searchField) {
        this.searchField = searchField;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public ScrollPane getSp() {
        return sp;
    }

    public void setSp(ScrollPane sp) {
        this.sp = sp;
    }
}
