import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javax.swing.event.ChangeEvent;

import java.sql.Date;
import java.sql.SQLOutput;
import java.text.NumberFormat;

public class Seller_PostBookControl extends Pane {

    public double width;
    public double height;
    private final Seller_PostBookPane pane;
    private User user;

    private String title;
    private String author;
    private int year;
    private String category;
    private String condition;
    private double originalPrice;
    private double generatedPrice;
    private double adminCharge;
    private double profit;

    private TextField booknameField;
    private TextField authorField;
    private ComboBox<String> yearComboBox;
    private ComboBox<String> categoryComboBox;
    private ComboBox<String> conditionComboBox;
    private TextField priceField;
    private TextField generatedpriceField;
    private Button listmybookButton;

    private boolean confirm = false;

    public Seller_PostBookControl(User user, double width, double height) {

        this.width = width;
        this.height = height;
        this.user = user;

        pane = new Seller_PostBookPane(user,width,height);

        booknameField = pane.getBooknameField();
        authorField = pane.getAuthorField();
        yearComboBox = pane.getYearComboBox();
        categoryComboBox = pane.getCategoryComboBox();
        conditionComboBox = pane.getConditionComboBox();
        priceField = pane.getPriceField();
        generatedpriceField = pane.getGeneratedpriceField();
        listmybookButton = pane.getListmybookButton();

        booknameField.textProperty().addListener(new TitleFieldListener());
        authorField.textProperty().addListener(new AuthorFieldListener());
        yearComboBox.valueProperty().addListener(new YearFieldListener());
        categoryComboBox.valueProperty().addListener(new CategoryFieldListener());
        conditionComboBox.valueProperty().addListener(new ConditionFieldListener());
        priceField.textProperty().addListener(new PriceFieldListener());

        listmybookButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            if(pane.emptyFields()) {

                pane.displayEmptyFields();

            } else {

                if(pane.displayConfirmPost(adminCharge, profit)) {

                    insertBook();
                    pane.displayBookPosted();

                }
            }
        }

        // once the user confirms to post their book
        // use this method to insert the book into the database
        public void insertBook() {

            int userID = user.getUserID();

            DataBase.listBook(userID, title, year, author, category, condition, profit, generatedPrice);

        }
    }

    private class TitleFieldListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            int maxLength = 32; // Maximum character limit
            String allowedCharactersPattern = "^[a-zA-Z0-9 _.-]*$"; // Alphanumeric, space, and some special characters

            // Check length and character restrictions
            if (newValue.length() > maxLength || !newValue.matches(allowedCharactersPattern)) {
                // Revert to the previous valid value
                ((StringProperty) observableValue).setValue(oldValue);
                return;
            }

            // Additional validation or processing here
            // Example: Sanitize the input if needed

            // Optional: Process or store the sanitized input
            title = newValue.replaceAll("[^a-zA-Z0-9 _.-]", "");
        }
    }

    private class AuthorFieldListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            int maxLength = 32; // Maximum character limit
            String allowedCharactersPattern = "^[a-zA-Z0-9 _.-]*$"; // Alphanumeric, space, and some special characters

            // Check length and character restrictions
            if (newValue.length() > maxLength || !newValue.matches(allowedCharactersPattern)) {
                // Revert to the previous valid value
                ((StringProperty) observableValue).setValue(oldValue);
                return;
            }

            // Additional validation or processing here
            // Example: Sanitize the input if needed

            // Optional: Process or store the sanitized input
            author = newValue.replaceAll("[^a-zA-Z0-9 _.-]", "");
        }

    }

    private class YearFieldListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            year = Integer.parseInt(t1.trim());
        }
    }

    private class CategoryFieldListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            category = t1;
        }
    }

    private class ConditionFieldListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            condition = t1;
        }
    }

    private class PriceFieldListener implements ChangeListener<String> {

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            // Define maximum allowed value
            double maxPrice = 99999.99;

            // Regular expression for a valid price format
            String pricePattern = "^(\\d{0,5})(\\.\\d{0,2})?$"; // Allow empty input or partial input

            // Check if the input matches the pattern or is empty
            if (newValue.isEmpty() || newValue.matches(pricePattern)) {
                try {
                    if (!newValue.isEmpty()) {
                        // Parse the input to a double if not empty
                        double price = Double.parseDouble(newValue);

                        // Check for maximum value
                        if (price > maxPrice) {
                            // Reset to old value if price exceeds maximum
                            ((StringProperty) observableValue).setValue(oldValue);
                            return;
                        }

                        // Update the price if valid
                        originalPrice = price;
                        computeGeneratedPrice();
                        computeAdminCharge();
                        computeProfit();
                        pane.displayGeneratedPrice(generatedPrice);
                    } else {
                        // Handle empty input gracefully (e.g., reset original price)
                        originalPrice = 0.0;
                        pane.displayGeneratedPrice(0.0);
                    }
                } catch (NumberFormatException e) {
                    // Reset to old value in case of an exception
                    System.out.println("Invalid Price: " + e.getMessage());
                    ((StringProperty) observableValue).setValue(oldValue);
                }
            } else {
                // Reset to old value if invalid
                ((StringProperty) observableValue).setValue(oldValue);
            }
        }

        public void computeAdminCharge() {
            adminCharge = generatedPrice * Main.fee;
        }

        public void computeProfit() {
            profit = generatedPrice - adminCharge;
        }

        public void computeGeneratedPrice() {
            generatedPrice = originalPrice - (originalPrice * Main.fee);
        }
    }

//    private class GeneratedPriceFieldListener implements ChangeListener<String> {
//
//        @Override
//        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//            generatedPrice = Double.parseDouble(t1);
//        }
//    }

}
