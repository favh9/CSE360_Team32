import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Buyer_ShopControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Buyer_ShopPane pane;
    private ToggleButton ascButton, descButton;
    private Button resetfiltersButton;
    private TextField searchField;
    private Button searchButton;
    private Button cartButton;

    public Buyer_ShopControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_ShopPane(user, width,height);

        // toggle button for ascending prices
        ascButton = pane.getAscendingRadioButton();
        ascButton.selectedProperty().addListener(new ToggleListener());

        // toggle button for descending prices
        descButton = pane.getDescendingRadioButton();
        descButton.selectedProperty().addListener(new ToggleListener());

        // reset filter button to clear all filter buttons
        resetfiltersButton = pane.getResetfiltersButton();
        // register reset filters button with button handler for events
        resetfiltersButton.setOnAction(new ButtonHandler());

        // search field text field from user input
        searchField = pane.getSearchField();
        searchField.textProperty().addListener(new SearchFieldListener());

        // search button when search field is filled
        searchButton = pane.getSearchButton();
        // register search button with button handler for events
        searchButton.setOnAction(new ButtonHandler());

        // cart button
        cartButton = pane.getCartButton();
        cartButton.setOnAction(new ButtonHandler());

        // make a condition that queries the database for the
        // seller's books
        // if the seller has no books to display or not
        if(pane.hasBooks()) {
            pane.displayAllBooks();
        } else {
            pane.noBooksFound();
        }

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // take the user to the cart page
            if(e.getSource().equals(cartButton)) {

                Buyer_CartControl cart = new Buyer_CartControl(user,width,height);
                Main.mainWindow.setScene(new Scene(cart));

            } else if (e.getSource().equals(searchButton)) {

                String searchString = pane.getSearchField().getText();
                pane.clearBooksPane();
                pane.displayAllBooks();

            } else if (e.getSource().equals(resetfiltersButton)) {

                pane.clearCategoryButtons();
                pane.clearConditionButtons();
                searchField.clear();
                pane.clearRadioButtons();
                pane.clearBooksPane();
                pane.displayAllBooks(); // might not fixing

            }

        }
    }

    private class ToggleListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {

            if(ascButton.isSelected()) {
                // clear the books pane
                pane.clearBooksPane();
                // populate the books in ascending order
                // ......
            } else if(descButton.isSelected()) {
                // clear the books pane
                pane.clearBooksPane();
                // populate the books in ascending order
                // ......
            }
            pane.displayAllBooks();

        }
    }

    private class SearchFieldListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

        }
    }

}
