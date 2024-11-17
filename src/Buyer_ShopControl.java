
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Buyer_ShopControl extends Pane {

    private User user;
    private double width;
    private double height;
    private GridPane booksPane;
    private Buyer_ShopPane pane;

    public Buyer_ShopControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_ShopPane(user,width,height);
        booksPane = pane.getBooksPane();

        populateBooks();

        this.getChildren().addAll(pane);

    }

    // use to populate books into the scrollpane's gridpane
    public void populateBooks() {

        // Array of book titles
        String[] titles = {
                "Introduction to Algorithms",
                "The Art of Computer Programming",
                "Clean Code",
                "Artificial Intelligence: A Modern Approach",
                "The Elements of Style",
                "Pride and Prejudice",
                "The Catcher in the Rye",
                "Mathematics: Its Content, Methods, and Meaning",
                "Linear Algebra Done Right",
                "Physics for Scientists and Engineers"
        };

        // Array of authors
        String[] authors = {
                "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest",
                "Donald E. Knuth",
                "Robert C. Martin",
                "Stuart Russell, Peter Norvig",
                "William Strunk Jr., E.B. White",
                "Jane Austen",
                "J.D. Salinger",
                "Aleksandr D. Alexandrov, A. N. Kolmogorov",
                "Sheldon Axler",
                "Raymond A. Serway, John W. Jewett"
        };

        // Array of book categories
        String[] categories = {
                "Computer Science",      // Introduction to Algorithms
                "Computer Science",      // The Art of Computer Programming
                "Computer Science",      // Clean Code
                "Computer Science",      // Artificial Intelligence: A Modern Approach
                "English",               // The Elements of Style
                "English",               // Pride and Prejudice
                "English",               // The Catcher in the Rye
                "Math",                  // Mathematics: Its Content, Methods, and Meaning
                "Math",                  // Linear Algebra Done Right
                "Science"                // Physics for Scientists and Engineers
        };

        // Array of book conditions
        String[] conditions = {
                "Like-new",
                "Heavily used",
                "Moderately used",
                "Like-new",
                "Heavily used",
                "Moderately used",
                "Like-new",
                "Heavily used",
                "Moderately used",
                "Like-new"
        };

        // Array of book prices
        double[] prices = {
                59.99,
                89.99,
                34.95,
                99.95,
                12.99,
                14.95,
                8.99,
                35.00,
                29.99,
                120.00
        };

        Book book;
        Label titleLabel, authorLabel, categoryLabel, conditionLabel, priceLabel;
        Text titleText, authorText, categoryText, conditionText, priceText;
        int textWrapWidth;
        Button addtocartButton;
        VBox bookVBox;
        BorderPane bp;

        // Create VBox for each book and add it to the grid
        for (int i = 0; i < titles.length; i++) {

            textWrapWidth = 142;

            book = new Book();
            book.setTitle(titles[i]);
            book.setAuthor(authors[i]);
            book.setCategory(categories[i]);
            book.setCondition(conditions[i]);
            book.setPrice(prices[i]);

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

            Book finalBook = book;

            addtocartButton = new Button("add to cart");
            addtocartButton.textFillProperty().set(Color.web("#ffffff"));
            addtocartButton.setStyle(
                    "-fx-background-radius: 5em;" +
                            "-fx-background-color: #ffc627;"
            );

            addtocartButton.setOnAction(e-> new ButtonHandler());

            // Add book details to the VBox
            bookVBox = new VBox();
            bookVBox.setSpacing(5);
            bookVBox.getChildren().add(titleText);
            bookVBox.getChildren().add(authorText);
            bookVBox.getChildren().add(categoryText);
            bookVBox.getChildren().add(conditionText);
            bookVBox.getChildren().add(priceText);
            bookVBox.getChildren().add(addtocartButton);

            bp = new BorderPane();
            bp.setTop(bookVBox);
            bp.setBottom(addtocartButton);
            bp.setStyle("-fx-background-color: white; " +"-fx-background-radius: 4px;" + "-fx-padding: 10px;");

            BorderPane.setAlignment(bookVBox, Pos.CENTER);
            BorderPane.setAlignment(addtocartButton, Pos.CENTER);
            BorderPane.setMargin(addtocartButton, new Insets(5));


            // Add VBox to the grid (placed in row i, column 0)
            booksPane.add(bp, i % 3, i / 3);  // Place items in x columns (i % x), rows increase by y (i / y)
        }
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

        }
    }
}
