import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
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

        // categories
        naturalscienceCheckBox = pane.getNaturalscienceCheckBox();
        computerscienceCheckBox = pane.getComputerscienceCheckBox();
        mathCheckBox = pane.getMathCheckBox();
        englishCheckBox = pane.getEnglishCheckBox();
        otherCheckBox = pane.getOtherCheckBox();

        // listeners
        naturalscienceCheckBox.selectedProperty().addListener(new NaturalScienceCheckBoxListener());
        computerscienceCheckBox.selectedProperty().addListener(new computerScienceCheckBoxListener());
        mathCheckBox.selectedProperty().addListener(new matchCheckBoxListener());
        englishCheckBox.selectedProperty().addListener(new englishCheckBoxListener());
        otherCheckBox.selectedProperty().addListener(new otherCheckBoxListener());


        // conditions
        likenewCheckBox = pane.getLikenewCheckBox();
        moderatelyusedCheckBox = pane.getModeratelyusedCheckBox();
        heavilyusedCheckBox = pane.getHeavilyusedCheckBox();

        // listeners
        likenewCheckBox.selectedProperty().addListener(new likeNewCheckBoxListener());
        moderatelyusedCheckBox.selectedProperty().addListener(new moderatelyUsedCheckBoxListener());
        heavilyusedCheckBox.selectedProperty().addListener(new heavilyUsedCheckBoxListener());

        // ascending or descending
        ascendingRadioButton = pane.getAscendingRadioButton();
        descendingRadioButton = pane.getDescendingRadioButton();

        // Initialize toggle group and assign it to the radio buttons
        toggleGroupPrices = new ToggleGroup();
        ascendingRadioButton.setToggleGroup(toggleGroupPrices);
        descendingRadioButton.setToggleGroup(toggleGroupPrices);

        // Set actions for radio buttons
        ascendingRadioButton.setOnAction(new ButtonHandler());
        descendingRadioButton.setOnAction(new ButtonHandler());

        resetfiltersButton = pane.getResetfiltersButton();
        resetfiltersButton.setOnAction(new ButtonHandler());
        // make a condition that queries the database for the
        // seller's books
        // if the seller has no books to display or not

        // if (!(DataBase.userHasBooks(user,...))) {
        //      for book in UserBooks:
        //          addBook(book);
        // }

        if(pane.hasBooks()) {
            pane.displayAllBooks();
        } else {
            pane.displayNoBooksFound();
        }

        this.getChildren().addAll(pane);
    }


    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {


            // radio buttons are tied to a toggle button
            if (toggleGroupPrices.getSelectedToggle() != null) {
                if(toggleGroupPrices.getSelectedToggle().equals(ascendingRadioButton)) {
                    pane.clearBooks();
                    pane.displayAllBooks();
                }
                if(toggleGroupPrices.getSelectedToggle().equals(descendingRadioButton)){
                    pane.clearBooks();
                    pane.displayAllBooks();
                }
            }
            if (a.getSource().equals(searchButton)) {
                pane.clearBooks();
                pane.displayAllBooks();
            }
            if (a.getSource().equals(resetfiltersButton)) {
                // Reset the toggle group (radio buttons)
                toggleGroupPrices.selectToggle(null);  // Deselect any selected radio button
                System.out.println("reset.");
                // Reset checkboxes (you may need to adjust the logic based on your specific checkboxes)
                naturalscienceCheckBox.setSelected(false);
                computerscienceCheckBox.setSelected(false);
                mathCheckBox.setSelected(false);
                englishCheckBox.setSelected(false);
                otherCheckBox.setSelected(false);
                likenewCheckBox.setSelected(false);
                moderatelyusedCheckBox.setSelected(false);
                heavilyusedCheckBox.setSelected(false);

                // Clear any text fields, if applicable
                searchField.clear();  // If you have a search text field

                // Optionally: reset other UI elements, such as sort selections, filters, etc.

                // Refresh the books display after resetting the filters
                pane.clearBooks();
                pane.displayAllBooks();
            }
        }
    }

    private class NaturalScienceCheckBoxListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {

            if(observableValue.getValue()) {
                // true value
            } else {
                // false value
            }
            pane.clearBooks();
            pane.displayAllBooks();
        }
    }

    private class computerScienceCheckBoxListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {

            if(observableValue.getValue()) {
                // true value
            } else {
                // false value
            }
            pane.clearBooks();
            pane.displayAllBooks();
        }
    }

    private class matchCheckBoxListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {

            if(observableValue.getValue()) {
                // true value
            } else {
                // false value
            }
            pane.clearBooks();
            pane.displayAllBooks();
        }
    }

    private class englishCheckBoxListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {

            if(observableValue.getValue()) {
                // true value
            } else {
                // false value
            }
            pane.clearBooks();
            pane.displayAllBooks();
        }
    }

    private class otherCheckBoxListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {

            if(observableValue.getValue()) {
                // true value
            } else {
                // false value
            }
            pane.clearBooks();
            pane.displayAllBooks();
        }
    }

    private class likeNewCheckBoxListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {

            if(observableValue.getValue()) {
                // true value
            } else {
                // false value
            }
            pane.clearBooks();
            pane.displayAllBooks();
        }
    }

    private class moderatelyUsedCheckBoxListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {

            if(observableValue.getValue()) {
                // true value
            } else {
                // false value
            }
            pane.clearBooks();
            pane.displayAllBooks();
        }
    }

    private class heavilyUsedCheckBoxListener implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {

            if(observableValue.getValue()) {
                // true value
            } else {
                // false value
            }
            pane.clearBooks();
            pane.displayAllBooks();
        }
    }

}
