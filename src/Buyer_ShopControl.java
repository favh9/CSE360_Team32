import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Buyer_ShopControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Buyer_ShopPane pane;
    private GridPane booksPane;
    private CheckBox naturalscienceCheckBox,computerscienceCheckBox,mathCheckBox,englishCheckBox,otherCheckBox,likenewCheckBox,moderatelyusedCheckBox,heavilyusedCheckBox;
    private RadioButton ascendingRadioButton,descendingRadioButton;
    private ToggleGroup toggleGroupPrices;
    private Button resetfiltersButton;
    private TextField searchField;
    private Button searchButton;
    private Button cartButton;

    public Buyer_ShopControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_ShopPane(user, width,height);

        // checkboxes for categories
        naturalscienceCheckBox = pane.getNaturalscienceCheckBox();
        computerscienceCheckBox = pane.getComputerscienceCheckBox();
        mathCheckBox = pane.getMathCheckBox();
        englishCheckBox = pane.getEnglishCheckBox();
        otherCheckBox = pane.getOtherCheckBox();

        // checkboxes for conditions
        likenewCheckBox = pane.getLikenewCheckBox();
        moderatelyusedCheckBox = pane.getModeratelyusedCheckBox();
        heavilyusedCheckBox = pane.getHeavilyusedCheckBox();

        // radio buttons for ascending/descending prices
        ascendingRadioButton = pane.getAscendingRadioButton();
        descendingRadioButton = pane.getDescendingRadioButton();

        // toggle group for the radio buttons
        toggleGroupPrices = pane.getToggleGroupPrices();

        // reset filter button to clear all filter buttons
        resetfiltersButton = pane.getResetfiltersButton();
        // register reset filters button with button handler for events
        resetfiltersButton.setOnAction(new ButtonHandler());

        // search field text field from user input
        searchField = pane.getSearchField();

        // search button when search field is filled
        searchButton = pane.getSearchButton();
        // register search button with button handler for events
        searchButton.setOnAction(new ButtonHandler());

        // cart button
        cartButton = pane.getCartButton();
        cartButton.setOnAction(new ButtonHandler());

        // books pane is the grid inside the scrollpane
        booksPane = pane.getBooksPane();

        // make a condition that queries the database for the
        // seller's books
        // if the seller has no books to display or not
        if(true) {
            populateBooks();
        } else {
            pane.noBooksFound();
        }

        this.getChildren().addAll(pane);
    }

    // query the database,
    // and display all of the books
    // displays the books found from the seller
    public void populateBooks() {

        // i.e. for books in User.books, add a book
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic", "New", 12.99);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", "Used", 8.49);
        Book book3 = new Book("1984", "George Orwell", "Dystopian", "Good", 6.99);
        Book book4 = new Book("The Catcher in the Rye", "J.D. Salinger", "Classic", "Like New", 10.50);
        Book book5 = new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", "New", 14.99);
        Book book6 = new Book("Moby-Dick", "Herman Melville", "Adventure", "Acceptable", 5.50);
        Book book7 = new Book("Pride and Prejudice", "Jane Austen", "Romance", "Used", 7.99);
        Book book8 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", "New", 20.00);
        Book book9 = new Book("Brave New World", "Aldous Huxley", "Science Fiction", "Good", 9.99);
        Book book10 = new Book("War and Peace", "Leo Tolstoy", "Historical Fiction", "Like New", 15.00);

        addBook(book1);
        addBook(book2);
        addBook(book3);
        addBook(book4);
        addBook(book5);
        addBook(book6);
        addBook(book7);
        addBook(book8);
        addBook(book9);
        addBook(book10);
    }

    // query the database to find books belonging to the user
    public boolean hasBooks() {

        return false;
    }

    // using the database,
    // insert a book into the cart's user table
    public boolean addedToCart(Book book) {

        return false;
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

        priceText = new Text("$" + book.getPrice());
        priceText.setWrappingWidth(textWrapWidth);

        // Add book details to the VBox
        bookVBox = new VBox();
        bookVBox.setSpacing(5);
        bookVBox.getChildren().add(titleText);
        bookVBox.getChildren().add(authorText);
        bookVBox.getChildren().add(categoryText);
        bookVBox.getChildren().add(conditionText);
        bookVBox.getChildren().add(priceText);

        addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(e-> {
            addedToCart(book);
            pane.updateBadgeCount();
            pane.setCartVisible(true);
            // check again
            if(true) {
                pane.clearBooks();
                populateBooks();
            } else {
                pane.setCartVisible(false);
                pane.noBooksFound();
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

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // take the user to the cart page
            if(e.getSource().equals(cartButton)) {

                Buyer_CartControl cart = new Buyer_CartControl(user,width,height);
                Main.mainWindow.setScene(new Scene(cart));

            }

        }
    }

}
