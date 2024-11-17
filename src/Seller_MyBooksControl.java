import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Seller_MyBooksControl extends Pane {

    private User user;
    private Seller_MyBooksPane pane;
    private GridPane booksPane;
    private CheckBox naturalscienceCheckBox,computerscienceCheckBox,mathCheckBox,englishCheckBox,otherCheckBox,likenewCheckBox,moderatelyusedCheckBox,heavilyusedCheckBox;
    private RadioButton ascendingRadioButton,descendingRadioButton;
    private ToggleGroup toggleGroupPrices;
    private Button resetfiltersButton;
    private TextField searchField;
    private Button searchButton;

    public Seller_MyBooksControl(User user, double width, double height) {

        this.user = user;

        pane = new Seller_MyBooksPane(user, width,height);

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

        // books pane is the grid inside the scrollpane
        booksPane = pane.getBooksPane();

        // make a condition that queries the database for the
        // seller's books
        // if the seller has no books to display or not
        if(hasBooks()) {
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
        addBook(new Book());
        addBook(new Book());
        addBook(new Book());
        addBook(new Book());
        addBook(new Book());
        addBook(new Book());
        addBook(new Book());
        addBook(new Book());
        addBook(new Book());

    }

    // query the database to find books belonging to the user
    public boolean hasBooks() {

        return false;
    }

    // query the database for the user's books
    // if the book exists,
    // remove it and return true
    public boolean bookRemoved(Book book) {

        return false;
    }

    public void addBook(Book book) {

        Text titleText, authorText, categoryText, conditionText, priceText;
        int textWrapWidth;
        VBox bookVBox;
        BorderPane bp;
        Button removeButton;

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

        removeButton = new Button("Remove");
        removeButton.setOnAction(e-> {
            bookRemoved(book);
            // check again
            if(hasBooks()) {
                populateBooks();
            } else {
                pane.noBooksFound();
            }
        });

        bp = new BorderPane();
        bp.setTop(bookVBox);
        bp.setBottom(removeButton);
        bp.setStyle("-fx-background-color: white; " +"-fx-background-radius: 4px;" + "-fx-padding: 10px;");

        BorderPane.setAlignment(bookVBox, Pos.CENTER);
        BorderPane.setAlignment(removeButton, Pos.CENTER);
        BorderPane.setMargin(removeButton, new Insets(5));

        System.out.println(booksPane.getColumnCount() + ", " + booksPane.getRowCount());

        // Add VBox to the grid (placed in column, row) dynamically
        booksPane.add(bp, booksPane.getChildren().size() % 3, booksPane.getChildren().size() / 3);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

        }
    }

}
