import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Buyer_CartPane extends BorderPane {

    private Button refreshButton;
    private VBox cartVBox;
    private Text cartAmountText;
    private Button backButton;
    private User user;
    private ScrollPane sp;
    private List<Book> bookList;

    public Buyer_CartPane(User user, double width, double height) {

        this.user = user;

        bookList = DataBase.getCart(user.getUserID());

        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);
        Font quantityFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 24);

        ImageView backImage = new ImageView(Main.backIcon);
        backImage.setFitHeight(40);
        backImage.setFitWidth(40);
        backButton = new Button();
        backButton.setBackground(null);
        backButton.setGraphic(backImage);

        // set attributes for the Title Label
        Label titleLabel = new Label("Cart");
        titleLabel.setFont(titleFont);

        // set attributes for the quantity label
        Label quantityLabel = new Label("amount");
        quantityLabel.setFont(quantityFont);

        // set attribute for the quantity label
        cartAmountText = new Text("0");
        cartAmountText.setFont(quantityFont);

        // set attributes for the HBox that holds the quantity and quantity label
        HBox quantityHBox = new HBox(cartAmountText, quantityLabel);
        quantityHBox.setSpacing(20);
        quantityHBox.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for container that holds Title and Quantity
        HBox leftHeaderPaneHBox = new HBox(backButton,titleLabel,quantityHBox);
        leftHeaderPaneHBox.setSpacing(40);
        leftHeaderPaneHBox.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header of the page
        BorderPane headerPane = new BorderPane();
        headerPane.setLeft(leftHeaderPaneHBox);

        // set attributes for the header label
        Label headerLabel1 = new Label("Book Title");
        headerLabel1.setPrefWidth(240);
        headerLabel1.setWrapText(true);
        headerLabel1.setFont(Font.font(20));
        headerLabel1.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel2 = new Label("Category");
        headerLabel2.setPrefWidth(240);
        headerLabel2.setWrapText(false);
        headerLabel2.setFont(Font.font(20));
        headerLabel2.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel3 = new Label("Condition");
        headerLabel3.setPrefWidth(240);
        headerLabel3.setWrapText(false);
        headerLabel3.setFont(Font.font(20));
        headerLabel3.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel4 = new Label("Price");
        headerLabel4.setPrefWidth(150);
        headerLabel4.setWrapText(true);
        headerLabel4.setFont(Font.font(20));
        headerLabel4.setAlignment(Pos.BASELINE_CENTER);

        // FOR DEBUGGING maybe?
        headerLabel1.setBorder(Border.stroke(Color.BLACK));
        headerLabel2.setBorder(Border.stroke(Color.BLACK));
        headerLabel3.setBorder(Border.stroke(Color.BLACK));
        headerLabel4.setBorder(Border.stroke(Color.BLACK));

        // set attributes for the header of the body
        HBox bodyheaderPane = new HBox(headerLabel1,headerLabel2,headerLabel3,headerLabel4);

        // set attributes for the line that separates the header and the scrollpane
        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(850);
        lineSeparator.setHeight(2);
        lineSeparator.setFill(Color.BLACK);

        // set attributes for the VBox that holds the lists of users
        cartVBox = new VBox();

        // set attributes for the scrollpane
        sp = new ScrollPane(cartVBox);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.minViewportHeightProperty().set(400);
        sp.setPrefHeight(400);
        sp.setStyle("-fx-background: white;");

        // set attributes for the scrollpane
        sp = new ScrollPane(cartVBox);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.minViewportHeightProperty().set(400);
        sp.setPrefHeight(400);
        sp.setStyle("-fx-background: white;");

        // set attributes for the users box that includes their header and scrollpane
        VBox headerAndScrollPane = new VBox(bodyheaderPane,lineSeparator,sp);

        // set attributes for the main VBox which excludes the navigation bar
        VBox mainVBox = new VBox(headerPane,headerAndScrollPane);
        mainVBox.setPadding(new Insets(40,40,40,40));
        mainVBox.setSpacing(10);
        mainVBox.setPrefWidth(width);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and page
        HBox navBarAndMainHBox = new HBox(mainVBox);
        navBarAndMainHBox.setSpacing(20);

        this.setCenter(mainVBox);
        this.setPadding(new Insets(20,20,20,20));
//        BorderPane.setMargin(navBarAndMainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon color

    }

    public Button getBackButton() {
        return backButton;
    }

    public void clearCart() {
        cartVBox.getChildren().clear();
        cartAmountText.setText("0");
    }

    public void displayCart() {

        for (Book book : bookList) {
            addBook(book);
        }

    }

    public void clearTransactions() {
        cartVBox.getChildren().clear();
        cartAmountText.setText("0");
    }

    // modifies and updates the display amount text by one
    public void updateCount() {
        String str = cartAmountText.getText();
        int incr = Integer.parseInt(str) + 1;
        cartAmountText.setText(Integer.toString(incr));
    }

    public void addBook(Book book) {

        // set attributes for the header label
        Label headerLabel1 = new Label("Book Title");
        headerLabel1.setPrefWidth(240);
        headerLabel1.setWrapText(true);
        headerLabel1.setFont(Font.font(20));
        headerLabel1.setAlignment(Pos.BASELINE_CENTER);

        VBox vbox1 = new VBox(headerLabel1);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setPadding(new Insets(5));

        // set attributes for the header label
        Label headerLabel2 = new Label("Category");
        headerLabel2.setPrefWidth(240);
        headerLabel2.setWrapText(false);
        headerLabel2.setFont(Font.font(20));
        headerLabel2.setAlignment(Pos.BASELINE_CENTER);

        VBox vbox2 = new VBox(headerLabel2);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(5));

        // set attributes for the header label
        Label headerLabel3 = new Label("Condition");
        headerLabel3.setPrefWidth(240);
        headerLabel3.setWrapText(false);
        headerLabel3.setFont(Font.font(20));
        headerLabel3.setAlignment(Pos.BASELINE_CENTER);

        VBox vbox3 = new VBox(headerLabel3);
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setPadding(new Insets(5));

        // set attributes for the header label
        Label headerLabel4 = new Label("Price");
        headerLabel4.setPrefWidth(150);
        headerLabel4.setWrapText(true);
        headerLabel4.setFont(Font.font(20));
        headerLabel4.setAlignment(Pos.BASELINE_CENTER);

        VBox vbox4 = new VBox(headerLabel4);
        vbox4.setAlignment(Pos.CENTER);
        vbox4.setPadding(new Insets(5));

        // set attributes for the line that separates the header and the scrollpane
        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(860);
        lineSeparator.setHeight(1);
        lineSeparator.setFill(Color.BLACK);

        /////////////////////////////////////////////////

        // set attributes for the header of the body
        HBox bodyheaderPane = new HBox(vbox1,vbox2,vbox3,vbox4);

        cartVBox.getChildren().addAll(bodyheaderPane,lineSeparator);

        sp.setContent(cartVBox);

    }

    public void displayNoBooksFound() {

        BorderPane bp = new BorderPane();
        Label noBooksFoundLabel = new Label("No Books Found");
        noBooksFoundLabel.setFont(Font.font(48));
        bp.setCenter(noBooksFoundLabel);
        bp.setPadding(new Insets(100));
        sp.setContent(bp);

    }

    public List<Book> getBookList() {
        return  bookList;
    }

}
