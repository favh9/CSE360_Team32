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

    private Text badgeCountText;
    private CheckBox naturalscienceCheckBox,computerscienceCheckBox,mathCheckBox,englishCheckBox,otherCheckBox,likenewCheckBox,moderatelyusedCheckBox,heavilyusedCheckBox;
    private RadioButton ascendingRadioButton,descendingRadioButton;
    private ToggleGroup toggleGroupPrices;
    private Button resetfiltersButton;
    private TextField searchField;
    private Button searchButton;
    private ScrollPane sp;
    private GridPane booksPane;
    private Button cartButton;
    private StackPane cartStackPane;
    private User user;

    public Buyer_ShopPane(User user, double width, double height) {

        this.user = user;

        int cartTotal = DataBase.getCart(user.getUserID()).size();

        Buyer_NavigationControl navBarVBox = new Buyer_NavigationControl(user,width,height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Shop");
        titleLabel.setFont(Font.font(42));

        // Create a StackPane to hold both the cart image and the badge
        cartStackPane = new StackPane();

        if(cartTotal == 0) {
            cartStackPane.setVisible(false);
        }

        // add the cart image
        ImageView cartImage = new ImageView(Main.cartIcon);
        cartImage.setFitHeight(45);
        cartImage.setFitWidth(45);

        // add the cart button
        cartButton = new Button();
        cartButton.setGraphic(cartImage);
        cartButton.setBackground(Background.fill(Color.TRANSPARENT));

        // Create the badge circle to display the number of items
        Circle badge = new Circle(12);
        badge.setFill(Color.RED);

        // Create the text to show the item count inside the badge
        badgeCountText = new Text(Integer.toString(cartTotal));
        badgeCountText.setFill(Color.WHITE);
        badgeCountText.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        // Set the elements in stack pane position
        StackPane.setAlignment(badge, Pos.TOP_RIGHT); // This positions the badge at the top-right of the image
        StackPane.setAlignment(badgeCountText, Pos.TOP_RIGHT);
        badgeCountText.setTranslateX(-8);
        badgeCountText.setTranslateY(4);

        // Add the badge and the text to the StackPane
        cartStackPane.getChildren().addAll(cartButton,badge, badgeCountText);


        // set attributes for the header of the main page
        HBox headerHBox = new HBox(titleLabel, cartStackPane);
        headerHBox.setAlignment(Pos.CENTER_LEFT);
        headerHBox.setSpacing(530);
        HBox.setMargin(cartStackPane, new Insets(5,0,0,0));

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
        vbox3.setSpacing(5);

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

    public TextField getSearchField() {
        return searchField;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public Button getCartButton() {
        return cartButton;
    }

    public RadioButton getAscendingRadioButton() {
        return ascendingRadioButton;
    }

    public RadioButton getDescendingRadioButton() {
        return descendingRadioButton;
    }

    public Button getResetfiltersButton() {
        return resetfiltersButton;
    }

    public void setCartVisible(boolean b) {
        cartStackPane.setVisible(b);
    }

    public void clearCategoryButtons() {

        naturalscienceCheckBox.setSelected(false);
        computerscienceCheckBox.setSelected(false);
        mathCheckBox.setSelected(false);
        englishCheckBox.setSelected(false);
        otherCheckBox.setSelected(false);

    }

    public void clearConditionButtons() {

        likenewCheckBox.setSelected(false);
        moderatelyusedCheckBox.setSelected(false);
        heavilyusedCheckBox.setSelected(false);

    }

    public void clearRadioButtons() {

        ascendingRadioButton.setSelected(false);
        descendingRadioButton.setSelected(false);

    }

    public void updateBadgeCount() {

        int incr = Integer.parseInt(badgeCountText.getText());
        badgeCountText.setText(Integer.toString(++incr));

    }

    public void clearBooksPane() {
        booksPane.getChildren().clear();
    }

    public void addBook(Book book) {

        Text titleText, authorText, categoryText, conditionText, priceText;
        int textWrapWidth;
        VBox bookVBox;
        BorderPane bp;
        Button addToCartButton;

        // Create VBox for each book and add it to the grid
        textWrapWidth = 142;

        book.setTitle(book.getTitle());
        book.setAuthor(book.getAuthor());
        book.setCategory(book.getCategory());
        book.setCondition(book.getCondition());
        book.setPrice(book.getPrice());

        titleText = new Text(book.getTitle());
        titleText.setWrappingWidth(textWrapWidth);

        authorText = new Text("by " + book.getAuthor());
        authorText.setWrappingWidth(textWrapWidth);

        categoryText = new Text(book.getCategory());
        categoryText.setWrappingWidth(textWrapWidth);

        conditionText = new Text(book.getCondition());
        conditionText.setWrappingWidth(textWrapWidth);

        Hyperlink seller = new Hyperlink();
        seller.setWrapText(true);
        seller.setPadding(new Insets(0,0,0,-2));

        // find whose book this is
        // look for it in the database
        // set the text of the hyperlink to the seller's username
        seller.setText("username");

        seller.setOnAction(e-> {
            Alert popUpReview = new Alert(Alert.AlertType.INFORMATION);
            String title = "Seller's Review";
            String headerTitle = "This displays the seller's review";
            String msg = "This displays the top three reviews";
            popUpReview.setTitle(title);
            popUpReview.setHeaderText(headerTitle);
            popUpReview.setContentText(msg);
            popUpReview.show();
        });

        priceText = new Text("$" + book.getPrice());
        priceText.setWrappingWidth(textWrapWidth);

        // Add book details to the VBox
        bookVBox = new VBox();
        bookVBox.setSpacing(5);
        bookVBox.getChildren().add(titleText);
        bookVBox.getChildren().add(authorText);
        bookVBox.getChildren().add(categoryText);
        bookVBox.getChildren().add(conditionText);
        bookVBox.getChildren().add(seller);
        bookVBox.getChildren().add(priceText);

        addToCartButton = new Button("Add to Cart");

        boolean isInCartBool = DataBase.isInCart(user.getUserID(),book.getID());
        if(isInCartBool) {
            addToCartButton.setDisable(true);
        }

        addToCartButton.setOnAction(e-> {

            if(!isInCartBool) {
                // add to cart
                DataBase.addToCart(user.getUserID(), book.getID());

                // the number on the cart
                updateBadgeCount();
//                badgeCountText.setText(Integer.toString(DataBase.getCart(user.getUserID()).size()));

                // show the cart
                setCartVisible(true);

                // check again
                if (hasBooks()) {
                    clearBooksPane();
                    displayAllBooks();
                } else {
                    setCartVisible(false);
                    noBooksFound();
                }
            } else {
                setCartVisible(true);
                displayErrorAddingBook();
            }
        });

        bp = new BorderPane();
        bp.setTop(bookVBox);
        bp.setBottom(addToCartButton);
        bp.setStyle("-fx-background-color: white; " +"-fx-background-radius: 4px;" + "-fx-padding: 10px;");

        BorderPane.setAlignment(bookVBox, Pos.CENTER);
        BorderPane.setAlignment(addToCartButton, Pos.CENTER);
        BorderPane.setMargin(addToCartButton, new Insets(5));

        // Add VBox to the grid (placed in column, row) dynamically
        booksPane.add(bp, booksPane.getChildren().size() % 3, booksPane.getChildren().size() / 3);
    }

    // query the database,
    // and display all of the books
    // displays the books found from the seller

    public void displayAllBooks() {


        // i.e. for books in User.books, add a book
        String[] tempConditions = new String[3];
        String[] tempCategories = new String[5];
        int i = 0;
        if (naturalscienceCheckBox.isSelected()) {tempCategories[i++] = "Natural Science";}
        System.out.println("Hi");
        if (computerscienceCheckBox.isSelected()) {tempCategories[i++] = "Computer Science";}
        if (mathCheckBox.isSelected()) {tempCategories[i++] = "Math";}
        if (englishCheckBox.isSelected()) {tempCategories[i++] = "English";}
        if (otherCheckBox.isSelected()) {tempCategories[i++] = "Other";}
        String[] categories = new String[i];
        System.out.println(i);
        int ii = 0;
        if (i > 0) {
            while (ii < i) {
                categories[ii] =  tempCategories[ii];
                ii++;
            }
        }

        int j = 0;
        if (likenewCheckBox.isSelected()) {tempConditions[j++] = "Like New";}
        if (moderatelyusedCheckBox.isSelected()) {tempConditions[j++] = "Moderately Used";}
        if (heavilyusedCheckBox.isSelected()) {tempConditions[j++] = "Heavily Used";}
        String[] conditions = new String[j];
        int jj = 0;

        if (j > 0) {
            while (jj < j) {
                conditions[jj] = tempConditions[jj];
                jj++;
            }
        }
        int sortOrder = 1;
        if (descendingRadioButton.isSelected()) { sortOrder = 2;}
        for (Book book : DataBase.searchBooksByFilter( searchField.getText(), conditions, categories, sortOrder)) {
            addBook(book);
        }

    }

    // query the database to find books belonging to the user
    public boolean hasBooks() {
        return !(DataBase.getAllBooks().isEmpty());
    }

    public void noBooksFound() {

        BorderPane bp = new BorderPane();
        Label noBooksFoundLabel = new Label("No Books Found");
        noBooksFoundLabel.setFont(Font.font(48));
        bp.setCenter(noBooksFoundLabel);
        bp.setPadding(new Insets(100));
        sp.setContent(bp);

    }

    public void displayErrorAddingBook() {

        String title = "Warning";
        String msg = "You already have this in your cart";

        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }

}
