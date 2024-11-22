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

import javax.xml.crypto.Data;

public class Buyer_CartPane extends BorderPane {

    private VBox cartVBox;
    private Text cartAmountText;
    private Button backButton;
    private User user;
    private ScrollPane sp;
    private List<Book> bookList;
    private int cartAmount;
    private Button checkOutButton;

    public Buyer_CartPane(User user, double width, double height) {

        this.user = user;

        bookList = DataBase.getCart(user.getUserID());
        cartAmount = bookList.size();

        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);
        Font quantityFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 24);

        // back button image
        ImageView buttonView = new ImageView(Main.backIcon);
        buttonView.setFitHeight(40);
        buttonView.setFitWidth(40);
        backButton = new Button();
        backButton.setGraphic(buttonView);
        backButton.setBackground(null);

        // set attributes for the Title Label
        Label titleLabel = new Label("Cart");
        titleLabel.setFont(titleFont);

        // set attributes for the quantity label
        Label quantityLabel = new Label("amount");
        quantityLabel.setFont(quantityFont);

        // set attribute for the quantity label
        cartAmountText = new Text(Integer.toString(cartAmount));
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
        headerLabel2.setPrefWidth(200);
        headerLabel2.setWrapText(false);
        headerLabel2.setFont(Font.font(20));
        headerLabel2.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel3 = new Label("Condition");
        headerLabel3.setPrefWidth(200);
        headerLabel3.setWrapText(false);
        headerLabel3.setFont(Font.font(20));
        headerLabel3.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel4 = new Label("Price");
        headerLabel4.setPrefWidth(100);
        headerLabel4.setWrapText(false);
        headerLabel4.setFont(Font.font(20));
        headerLabel4.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel5 = new Label("Edit Cart");
        headerLabel5.setPrefWidth(100);
        headerLabel5.setWrapText(true);
        headerLabel5.setFont(Font.font(20));
        headerLabel5.setAlignment(Pos.BASELINE_CENTER);

        // FOR DEBUGGING maybe?
        headerLabel1.setBorder(Border.stroke(Color.BLACK));
        headerLabel2.setBorder(Border.stroke(Color.BLACK));
        headerLabel3.setBorder(Border.stroke(Color.BLACK));
        headerLabel4.setBorder(Border.stroke(Color.BLACK));
        headerLabel5.setBorder(Border.stroke(Color.BLACK));

        // set attributes for the header of the body
        HBox bodyheaderPane = new HBox(headerLabel1,headerLabel2,headerLabel3,headerLabel4,headerLabel5);

        // set attributes for the line that separates the header and the scrollpane
        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(840);
        lineSeparator.setHeight(2);
        lineSeparator.setFill(Color.BLACK);

        // set attributes for the VBox that holds the lists of users
        cartVBox = new VBox();

        // set attributes for the scrollpane
        sp = new ScrollPane(cartVBox);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setPrefViewportHeight(300);
        sp.setStyle("-fx-background: white;");

        // set attributes for the users box that includes their header and scrollpane
        VBox headerAndScrollPane = new VBox(bodyheaderPane,lineSeparator,sp);

        // set attributes for the main VBox which excludes the navigation bar
        VBox mainVBox = new VBox(headerPane,headerAndScrollPane);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setPrefWidth(width);
        mainVBox.setSpacing(10);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and page
        HBox MainHBox = new HBox(mainVBox);

        this.setCenter(MainHBox);
        BorderPane.setMargin(MainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon color

    }

    public void clearCart() {
        cartVBox.getChildren().clear();
        cartAmountText.setText("0");
        cartAmount = 0;
        for(Book book : bookList) {
            DataBase.removeFromCart(user.getUserID(),book.getID());
        }
    }

    // modifies and updates the display amount text by one
    public void increaseCount() {
        cartAmountText.setText(Integer.toString(++cartAmount));
    }

    public Button getBackButton() {
        return backButton;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void displayNoBooksFound() {

        BorderPane bp = new BorderPane();
        Label noBooksFoundLabel = new Label("No Books Found");
        noBooksFoundLabel.setFont(Font.font(48));
        bp.setCenter(noBooksFoundLabel);
        bp.setPadding(new Insets(100));
        sp.setContent(bp);

    }

    public void displayCart() {

        cartVBox.getChildren().clear();
        cartAmountText.setText("0");
        cartAmount = 0;

        for(Book book : bookList) {
            cartVBox.getChildren().add(bookVBox(book));
            increaseCount();
        }

    }


    public VBox bookVBox(Book book) {

        Button removeButton;

        // set attributes for the header label
        Label title = new Label(book.getTitle());
        title.setPrefWidth(240);
        title.setWrapText(true);
        title.setFont(Font.font(20));
        title.setAlignment(Pos.BASELINE_LEFT);
        title.setPadding(new Insets(5));

        VBox vbox1 = new VBox(title);
        vbox1.setAlignment(Pos.CENTER);

        // set attributes for the header label
        Label category = new Label(book.getCategory());
        category.setPrefWidth(200);
        category.setWrapText(false);
        category.setFont(Font.font(20));
        category.setAlignment(Pos.BASELINE_CENTER);
        category.setPadding(new Insets(5));

        VBox vbox2 = new VBox(category);
        vbox2.setAlignment(Pos.CENTER);

        // set attributes for the header label
        Label condition = new Label(book.getCondition());
        condition.setPrefWidth(200);
        condition.setWrapText(false);
        condition.setFont(Font.font(20));
        condition.setAlignment(Pos.BASELINE_CENTER);
        condition.setPadding(new Insets(5));

        VBox vbox3 = new VBox(condition);
        vbox3.setAlignment(Pos.CENTER);

        // set attributes for the header label
        Label price = new Label("$" + book.getPrice());
        price.setPrefWidth(100);
        price.setWrapText(false);
        price.setFont(Font.font(20));
        price.setAlignment(Pos.BASELINE_CENTER);
        price.setPadding(new Insets(5));

        VBox vbox4 = new VBox(price);
        vbox4.setAlignment(Pos.CENTER);

        // add remove button
        removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            DataBase.removeFromCart(user.getUserID(), book.getID());
            bookList.remove(book);
            displayCart();
        });

        VBox vbox5 = new VBox(removeButton);
        vbox5.setPadding(new Insets(5));
        vbox5.setPrefWidth(100);
        vbox5.setAlignment(Pos.CENTER);

        HBox transactionHBox = new HBox(vbox1,vbox2,vbox3,vbox4,vbox5);

        // set attributes for the line that separates the header and the scrollpane
        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(840);
        lineSeparator.setHeight(1);
        lineSeparator.setFill(Color.BLACK);

        return new VBox(transactionHBox,lineSeparator);

    }
}
