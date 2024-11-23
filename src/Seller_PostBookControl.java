import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

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
    private Button listmybookButton;

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
            double maxPrice = 10000.00;

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

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getGeneratedPrice() {
        return generatedPrice;
    }

    public double getAdminCharge() {
        return adminCharge;
    }

    public double getProfit() {
        return profit;
    }

    public TextField getBooknameField() {
        return booknameField;
    }

    public TextField getAuthorField() {
        return authorField;
    }

    public ComboBox<String> getYearComboBox() {
        return yearComboBox;
    }

    public ComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }

    public ComboBox<String> getConditionComboBox() {
        return conditionComboBox;
    }

    public TextField getPriceField() {
        return priceField;
    }

    public Button getListmybookButton() {
        return listmybookButton;
    }

    public Seller_PostBookPane getPane() {
        return pane;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public String getCondition() {
        return condition;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setGeneratedPrice(double generatedPrice) {
        this.generatedPrice = generatedPrice;
    }

    public void setAdminCharge(double adminCharge) {
        this.adminCharge = adminCharge;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public void setBooknameField(TextField booknameField) {
        this.booknameField = booknameField;
    }

    public void setAuthorField(TextField authorField) {
        this.authorField = authorField;
    }

    public void setYearComboBox(ComboBox<String> yearComboBox) {
        this.yearComboBox = yearComboBox;
    }

    public void setCategoryComboBox(ComboBox<String> categoryComboBox) {
        this.categoryComboBox = categoryComboBox;
    }

    public void setConditionComboBox(ComboBox<String> conditionComboBox) {
        this.conditionComboBox = conditionComboBox;
    }

    public void setPriceField(TextField priceField) {
        this.priceField = priceField;
    }

    public void setListmybookButton(Button listmybookButton) {
        this.listmybookButton = listmybookButton;
    }


}
