import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

    public void populateBooks() {

        // Example book data
        String[] bookTitles = {"The Great Gatsby", "1984", "Moby Dick", "To Kill a Mockingbird,","The Great Gatsby", "1984", "Moby Dick", "To Kill a Mockingbird,"};
        String[] bookAuthors = {"F. Scott Fitzgerald", "George Orwell", "Herman Melville", "Harper Lee","F. Scott Fitzgerald", "George Orwell", "Herman Melville", "Harper Lee"};
        String[] bookYears = {"1925", "1949", "1851", "1960","1925", "1949", "1851", "1960"};


        // Create VBox for each book and add it to the grid
        for (int i = 0; i < bookTitles.length; i++) {
            VBox bookVBox = new VBox(5); // '5' is for the vertical spacing inside the vbox
            bookVBox.setPrefWidth(250);
            bookVBox.setPrefHeight(150);
            bookVBox.setStyle("-fx-background-color: lightgrey; " +"-fx-background-radius: 4px;" + "-fx-padding: 10px;");

            // Add book details to the VBox
            bookVBox.getChildren().add(new Label("Title: " + bookTitles[i]));
            bookVBox.getChildren().add(new Label("Author: " + bookAuthors[i]));
            bookVBox.getChildren().add(new Label("Year: " + bookYears[i]));

            // Add VBox to the grid (placed in row i, column 0)
            booksPane.add(bookVBox, i % 2, i / 2);  // Place items in x columns (i % x), rows increase by y (i / y)
        }
    }
}
