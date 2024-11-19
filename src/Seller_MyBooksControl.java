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

        // search field text field from user input
        searchField = pane.getSearchField();

        // search button when search field is filled
        searchButton = pane.getSearchButton();
        // register search button with button handler for events
        searchButton.setOnAction(new ButtonHandler());

        // make a condition that queries the database for the
        // seller's books
        // if the seller has no books to display or not

        // if (!(DataBase.userHasBooks(user,...))) {
        //      for book in UserBooks:
        //          addBook(book);
        // }

        pane.displayAllBooks();
//        pane.displayNoBooksFound();

        this.getChildren().addAll(pane);
    }


    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

        }
    }

}
