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
    private User user;

    public Seller_MyBooksPane(User user, double width, double height) {

        this.user = user;

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
        vbox1.setSpacing(5);

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
        vbox2.setSpacing(5);

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
        vbox4.setSpacing(5);

        // set atributes for the VBox that holds all Left side items
        VBox leftVBox = new VBox(vbox1,vbox2,vbox3,vbox4);
        leftVBox.setSpacing(10);

        // set attributes for the title of the scrollpane
        Label spTitle = new Label("Available Books");
        spTitle.setFont(Font.font(20));
        spTitle.setPrefHeight(20);

        // set attributes for text field
        searchField = new TextField();
        searchField.setPrefWidth(475);
        searchField.setPrefHeight(20);
        searchField.setPromptText("Enter a title or author to search...");

        // Create a Button to trigger the search action
        searchButton = new Button("Search");
        searchButton.setPrefHeight(20);

        // Layout the TextField and Button in an HBox
        HBox searchbarHBox = new HBox(searchField, searchButton);
        searchbarHBox.setSpacing(10);

        // set attributes for the HBox
        HBox spHeaderHBox = new HBox(searchbarHBox);
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

    public Button getResetfiltersButton() {
        return resetfiltersButton;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    // query the database,
    // and display all of the books
    // displays the books found from the seller
    public void displayAllBooks() {

        // i.e. for books in User.books, add a book
        for (Book book : DataBase.myListedBooks(user.getUserID())) {
            addBook(book);
            System.out.println(book.getAuthor());
        }

    }

    // query the database to find books belonging to the user
    public boolean hasBooks() {

        return !(DataBase.myListedBooks(user.getUserID()).isEmpty());
    }

    // query the database for the user's books
    // if the book exists,
    // remove it and return true
    public void removeBook(Book book) {

        DataBase.deleteListing(book.getID());

    }

    // add a book to the book pane
    public void addBook(Book book) {

        Text titleText, authorText, categoryText, conditionText,/* quantityText,*/ priceText;
        //Label quantityLabel;
        int textWrapWidth;
        VBox bookVBox;
        BorderPane bp;
        Button removeButton;

        // Create VBox for each book and add it to the grid
        textWrapWidth = 142;

        // set the GUI book information
        titleText = new Text(book.getTitle());
        titleText.setWrappingWidth(textWrapWidth);

        authorText = new Text("by " + book.getAuthor());
        authorText.setWrappingWidth(textWrapWidth);

        categoryText = new Text(book.getCategory());
        categoryText.setWrappingWidth(textWrapWidth);

        conditionText = new Text(book.getCondition());
        conditionText.setWrappingWidth(textWrapWidth);

//        quantityLabel = new Label("Quantity: ");
//        quantityText = new Text(Integer.toString(book.getQuantity()));
//        quantityText.setWrappingWidth(textWrapWidth - quantityLabel.getWidth());
//
//        HBox quantityHBox = new HBox(quantityLabel,quantityText);

        priceText = new Text("$" + book.getPrice());
        priceText.setWrappingWidth(textWrapWidth);

        // Add book GUI to the VBox
        bookVBox = new VBox();
        bookVBox.setSpacing(5);
        bookVBox.getChildren().add(titleText);
        bookVBox.getChildren().add(authorText);
        bookVBox.getChildren().add(categoryText);
        bookVBox.getChildren().add(conditionText);
//        bookVBox.getChildren().add(quantityText);
        bookVBox.getChildren().add(priceText);

        // this remove button, has a book attached to it
        // if the user clicks it, the book quantity should be checked
        // and adjusted accordingly
        removeButton = new Button("Remove");
        removeButton.setOnAction(e-> {
            removeBook(book);
            // check again
            if(hasBooks()) {
                clearBooks();
                displayAllBooks();
            } else {
                displayNoBooksFound();
            }
        });

        // create the pane for the book GUI
        bp = new BorderPane();
        bp.setTop(bookVBox);
        bp.setBottom(removeButton);
        bp.setStyle("-fx-background-color: white; " +"-fx-background-radius: 4px;" + "-fx-padding: 10px;");

        // adjust the nodes' alignment
        BorderPane.setAlignment(bookVBox, Pos.CENTER);
        BorderPane.setAlignment(removeButton, Pos.CENTER);
        BorderPane.setMargin(removeButton, new Insets(5));

        // Add VBox to the grid (placed in column, row) dynamically
        booksPane.add(bp, booksPane.getChildren().size() % 3, booksPane.getChildren().size() / 3);
        sp.setContent(booksPane);
    }

    public void displayNoBooksFound() {

        BorderPane bp = new BorderPane();
        Label noBooksFoundLabel = new Label("No Books Found");
        noBooksFoundLabel.setFont(Font.font(48));
        bp.setCenter(noBooksFoundLabel);
        bp.setPadding(new Insets(100));
        sp.setContent(bp);

    }

    public void clearBooks() {
        booksPane.getChildren().clear();
    }
}
