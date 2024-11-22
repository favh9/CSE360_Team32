import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;

public class Buyer_CartPane extends BorderPane {

    private double width;
    private double height;
    private VBox cartVBox;
    private Text cartAmountText;
    private Button backButton;
    private User user;
    private ScrollPane sp;
    private List<Book> bookList;
    private int cartAmount;
    private double profit;
    private double subtotal;
    private double tax;
    private double total;
    private Label subtotalText;
    private Label taxText;
    private Label totalText;
    private Button checkOutButton;
    private BorderPane receiptBP;
    private HBox hbox1;
    private HBox hbox2;
    private HBox hbox3;

    public Buyer_CartPane(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        subtotal = 0;
        tax = .08; // hard code tax?
        total = 0;

        bookList = DataBase.getCart(user.getUserID());
        cartAmount = bookList.size();

        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);
        Font normalFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20);
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

        // add an hbox where the left displays...
        // a VBox with:
        // HBox subtotal
        // HBox tax
        // HBox total
        subtotalText = new Label("$" + subtotal);
        taxText = new Label("$" + tax);
        totalText = new Label("$" + total);

        subtotalText.setFont(normalFont);
        taxText.setFont(normalFont);
        totalText.setFont(normalFont);

        subtotalText.setPrefWidth(100);
        taxText.setPrefWidth(100);
        totalText.setPrefWidth(100);

        subtotalText.setAlignment(Pos.CENTER_RIGHT);
        taxText.setAlignment(Pos.CENTER_RIGHT);
        totalText.setAlignment(Pos.CENTER_RIGHT);

        Label subtotalLabel = new Label("Subtotal:");
        Label taxLabel = new Label("Tax:");
        Label totalLabel = new Label("Total:");

        subtotalLabel.setPrefWidth(100);
        taxLabel.setPrefWidth(100);
        totalLabel.setPrefWidth(100);

        subtotalLabel.setFont(normalFont);
        taxLabel.setFont(normalFont);
        totalLabel.setFont(normalFont);

        subtotalLabel.setAlignment(Pos.CENTER_RIGHT);
        taxLabel.setAlignment(Pos.CENTER_RIGHT);
        totalLabel.setAlignment(Pos.CENTER_RIGHT);

        hbox1 = new HBox(subtotalLabel,subtotalText);
        hbox2 = new HBox(taxLabel,taxText);
        hbox3 = new HBox(totalLabel,totalText);

        hbox1.setSpacing(100);
        hbox2.setSpacing(100);
        hbox3.setSpacing(100);

        checkOutButton = new Button("Checkout");
        checkOutButton.setFont(normalFont);
        checkOutButton.setPrefHeight(50);

        VBox leftVBox = new VBox(hbox1,hbox2,hbox3);
        VBox rightVBox = new VBox(checkOutButton);

        leftVBox.setAlignment(Pos.CENTER_LEFT);
        rightVBox.setAlignment(Pos.CENTER_RIGHT);

        receiptBP = new BorderPane();
        receiptBP.setLeft(leftVBox);
        receiptBP.setRight(rightVBox);

        receiptBP.setPadding(new Insets(5,20,5,20));
        receiptBP.setStyle(
                "-fx-border-radius: 2em;" + "-fx-background-color: #ffffff;" +
                        "-fx-border-width: 1;" + "-fx-border-color: black;"
        );

        BorderPane bp = new BorderPane();
        bp.setTop(new VBox(headerPane,headerAndScrollPane));
        bp.setBottom(receiptBP);
        bp.setPadding(new Insets(40,40,40,40));
        bp.setPrefWidth(width);
        bp.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );


        this.setCenter(bp);
        BorderPane.setMargin(bp, new Insets(20,20,20,20));

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

    public Button getCheckOutButton() {
        return checkOutButton;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void displayNoBooksFound() {

        Label noBooksFoundLabel = new Label("No Books Found");
        noBooksFoundLabel.setPadding(new Insets(100,100,100,100));
        noBooksFoundLabel.setFont(Font.font(48));

        cartVBox.getChildren().clear();

        cartVBox.getChildren().add(noBooksFoundLabel);

        receiptBP.setVisible(false);



    }

    // the buyer arrives at the cart with books
    public void atCart() {
        // display subtotal
        // display checkout
        displaySubtotal();
        hbox1.setVisible(true); // subtotal
        hbox2.setVisible(false); // tax
        hbox3.setVisible(false); // total
        checkOutButton.setVisible(true);

    }

    // the buyer hits checkout and confirms the purchase
    public void pushConfirm() {
        // display tax
        // display total
        displayTax();
        displayTotal();
        hbox2.setVisible(true); // tax
        hbox3.setVisible(true); // total
        checkOutButton.setVisible(false);
    }

    public void cancelConfirm() {
        // display tax
        // display total
        displayTax();
        displayTotal();
        hbox2.setVisible(false); // tax
        hbox3.setVisible(false); // total
        checkOutButton.setVisible(true);
    }

    public void displayCart() {

        cartVBox.getChildren().clear();
        cartAmountText.setText("0");
        cartAmount = 0;

        if(bookList.isEmpty()) {
            displayNoBooksFound();
        } else {
            subtotal = 0;
            for (Book book : bookList) {
                subtotal += book.getPrice();
                cartVBox.getChildren().add(bookVBox(book));
                increaseCount();
            }
            atCart(); // display the subtotal and checkout button
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
        price.setAlignment(Pos.CENTER_RIGHT);
        price.setPadding(new Insets(5,10,5,5));

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

    // get the integer values and modify the gui
    public void displaySubtotal() {
        subtotalText.setText(formatCurrency(subtotal));
    }

    public String formatCurrency(double num) {
        // Get a currency instance of NumberFormat
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        // Format the number as a currency string
        return currencyFormat.format(num);
    }

    public void displayTax() {
        taxText.setText(formatCurrency(computeTax()));
    }

    public double computeTax() {
        return tax * subtotal;
    }

    public double computeTotal() {
        return subtotal + computeTax();
    }

    public void displayTotal() {
        totalText.setText(formatCurrency(computeTotal()));
    }

    public boolean displayConfirmOrder() {
        String title = "Confirmation";
        String msg = "Please confirm your order.";

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        // Wait for user response
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;

    }

    public void displayTransactionSuccess() {

        String title = "Congratulations";
        String msg = "Your transaction was successful.\n" +
                "Click 'ok' to view your order.";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        // Wait for user response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // show a congratulations
            // empty the cart pane
            Buyer_OrdersControl orders = new Buyer_OrdersControl(user,width,height);
            Main.mainWindow.setScene(new Scene(orders));
        } else {
            Buyer_ShopControl shop = new Buyer_ShopControl(user,width,height);
            Main.mainWindow.setScene(new Scene(shop));
        }


    }

    public void displayNoPaymentMethodFound() {

        String title = "Transaction failed";
        String msg = "No payment method found.\n" +
                "Please click 'ok' to fill in your payment information.\n" +
                "Return to your cart to complete your purchase.";

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);

        // Wait for user response
        alert.showAndWait();
        Buyer_PaymentInfoControl payment = new Buyer_PaymentInfoControl(user,width,height);
        Main.mainWindow.setScene(new Scene(payment));

    }

}